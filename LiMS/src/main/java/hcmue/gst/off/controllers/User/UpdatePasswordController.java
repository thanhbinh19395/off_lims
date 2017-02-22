package hcmue.gst.off.controllers.User;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hung Thinh on 17/02/2017.
 */
@Controller
@RequestMapping("/User")
public class UpdatePasswordController extends UserBaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public String UpdatePassword(@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword, @RequestParam("username") String username, HttpServletRequest request) {
        User userToUpdate = userService.findByUsername(username);
        if (!bCryptPasswordEncoder.matches(oldPassword,userToUpdate.getPassword())) {
            return "redirect:/User/UpdatePassword?error";
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

}
