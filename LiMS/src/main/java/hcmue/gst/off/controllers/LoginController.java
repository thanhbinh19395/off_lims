package hcmue.gst.off.controllers;

import hcmue.gst.off.extensions.ControllerBase;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
@RequestMapping("/")
public class LoginController extends ControllerBase {

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid");
        if (logout != null)
            model.addAttribute("logout", "You have been logged out");
        return View("login");
    }
    /*
    @RequestMapping(value = "/processLogin", method = RequestMethod.POST)
    public String login(@ModelAttribute("modelLogin") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login?error";
        }
        securityService.autoLogin(user.getUsername(), user.getPassword());
        return "redirect:/home";
    }
    */
}
