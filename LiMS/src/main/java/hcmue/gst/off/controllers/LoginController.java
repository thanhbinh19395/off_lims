package hcmue.gst.off.controllers;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

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
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
