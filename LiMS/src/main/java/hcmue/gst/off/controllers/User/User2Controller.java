package hcmue.gst.off.controllers.User;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.UserRepository;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

}
