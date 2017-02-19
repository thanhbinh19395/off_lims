package hcmue.gst.off.controllers.User;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.repositories.UserRepository;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hung Thinh on 17/02/2017.
 */
@Controller
public class UpdatePasswordController extends BaseController {

    @Inject
    private UserRepository users;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
    public String UpdatePassword(@ModelAttribute User user, HttpServletRequest request) {
        User userToUpdate = users.findByUsername(user.getUsername());
        String oldpassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("new_password");
       if (!userToUpdate.getPassword().matches(oldpassword)) {
           return "redirect:/UpdatePassword?error";
       }
       userToUpdate.setPassword(newPassword);
       users.save(userToUpdate);
       return "redirect:/logout";
    }
    @RequestMapping(value = "/UpdatePassword", method = RequestMethod.GET)
    public String UpdatePassword(Model model) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        model.addAttribute("userNeedChange", user);
        return View();
    }
    @ModelAttribute(value ="username")
    public String getUsername(){
        return(securityService.findLoggedInUsername());
    }
}
