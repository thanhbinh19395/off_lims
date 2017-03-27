package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.business.*;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tranv on 19/02/2017.
 */
@Controller
@RequestMapping("/User")
public class User2Controller extends UserBaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ListBookBorrowBusiness listBookBorrowBusiness;
    @Autowired
    private ListBookReservationBusiness listBookReservationBusiness;
    @Autowired
    private CancelBookBorrowBusiness cancelBookBorrowBusiness;
    @Autowired
    private CancelBookReservationBusiness cancelBookReservationBusiness;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    @ResponseBody Result<User> UpdatePassword(@RequestParam("oldPassword") String oldPassword,
                                              @RequestParam("newPassword") String newPassword) {
        User user = securityService.getUser();
        //if (!bCryptPasswordEncoder.matches(oldPassword,user.getPassword())) {
        //    return new Result(null,"Invailid old password. Try again!", false);
        //}
        return userService.save(user);

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
    @ResponseBody  Result<User> ProcessUpdateInfoUser (@ModelAttribute("User") User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/ViewLog", method = RequestMethod.GET)
    public String viewLog(Model model) {
        listBookBorrowBusiness.Execute();
        listBookReservationBusiness.Execute();
        model.addAttribute("bookBorrowList",  listBookBorrowBusiness.getBorrowItemList());
        model.addAttribute("bookReservationList", listBookReservationBusiness.getBookReservationList());
        return View("ViewLog");
    }

    @RequestMapping(value = "/ViewLog/BookBorrow/cancel")
    @ResponseBody
    Result cancelBookBorrow(Long id) {
        cancelBookBorrowBusiness.setId(id);
        return cancelBookBorrowBusiness.Execute();
    }

    @RequestMapping(value = "/ViewLog/BookReservation/cancel")
    @ResponseBody
    Result cancelBookReservation(Long id) {
        cancelBookReservationBusiness.setId(id);
        return cancelBookReservationBusiness.Execute();
    }

}
