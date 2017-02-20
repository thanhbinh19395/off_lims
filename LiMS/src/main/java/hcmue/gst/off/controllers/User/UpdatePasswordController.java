package hcmue.gst.off.controllers.User;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hung Thinh on 17/02/2017.
 */
@Controller
public class UpdatePasswordController extends BaseController {

    @Autowired
    private UserService users;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public String UpdatePassword(@ModelAttribute User user, HttpServletRequest request) {
        User userToUpdate = users.findByUsername(user.getUsername());
        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("new_password");
       if (!userToUpdate.getPassword().matches(oldPassword)) {
           return "redirect:/UpdatePassword?error";
       }
       //Thay doi password trong database
       userToUpdate.setPassword(newPassword);
       users.save(userToUpdate);
       //Thay doi password trong Spring Security
       inMemoryUserDetailsManager.changePassword(oldPassword,newPassword);
       return "redirect:/logout";
    }
    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.GET)
    public String UpdatePassword(Model model) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        model.addAttribute("userNeedChange", user);
        model.addAttribute("username",securityService.findLoggedInUsername());
        return View();
    }
}
