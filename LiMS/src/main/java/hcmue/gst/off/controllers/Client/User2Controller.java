package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.business.ListBookBorrowBusiness;
import hcmue.gst.off.business.ListBookRequestBusiness;
import hcmue.gst.off.business.ListBookReservationBusiness;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.UserRepository;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tranv on 19/02/2017.
 */
@Controller
@RequestMapping("/User")
public class User2Controller extends UserBaseController{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ListBookBorrowBusiness listBookBorrowBusiness;
    @Autowired
    private ListBookReservationBusiness listBookReservationBusiness;
    @Autowired
    private ListBookRequestBusiness listBookRequestBusiness;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public String UpdatePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword, @RequestParam("username") String username, HttpServletRequest request) {
        User userToUpdate = userService.findByUsername(username);
        if (!bCryptPasswordEncoder.matches(oldPassword,userToUpdate.getPassword())) {
            return "redirect:/Client/UpdatePassword?error";
        }
        userToUpdate.setPassword(newPassword);
        userService.save(userToUpdate);
        return "redirect:/logout";
    }

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.GET)
    public String UpdatePassword(Model model) {
        model.addAttribute("username", securityService.findLoggedInUsername());
        return View();
    }
    @RequestMapping(value = "/UpdateInfoUser", method = RequestMethod.GET)
    public String UpdateInfoUser(Model model){
        User userExist = userService.findByUsername(securityService.findLoggedInUsername());
        model.addAttribute("User", userExist);
        return View("UpdateInfoUser");
    }
    @RequestMapping(value = "/SaveInfoUser", method = RequestMethod.POST)
    public String ProcessUpdateInfoUser (@ModelAttribute("User") User user,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/User/UpdateInfoUser?error";
        }
        User temp = userService.findByUsername(securityService.findLoggedInUsername());
        temp.setName(user.getName());
        temp.setPhone(user.getPhone());
        temp.setAddress(user.getAddress());
        temp.setIdcard(user.getIdcard());
        temp.setEmail(user.getEmail());
        temp.setBirthday(user.getBirthday());
        userRepository.save(temp);
        return "redirect:/User/UpdateInfoUser?success";
    }

    @RequestMapping(value = "/ViewLog", method = RequestMethod.GET)
    public String viewLog(Model model) {
        listBookBorrowBusiness.Execute();
        listBookReservationBusiness.Execute();
        listBookRequestBusiness.Execute();
        model.addAttribute("bookBorrowList",  listBookBorrowBusiness.getBorrowItemList());
        model.addAttribute("bookReservationList", listBookReservationBusiness.getBookReservationList());
        model.addAttribute("bookRequestList", listBookRequestBusiness.getRequestList());
        return View("ViewLog");
    }


}
