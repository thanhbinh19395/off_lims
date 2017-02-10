package hcmue.gst.off.controllers;

import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home")
    public String Index(Model model) {
        String name = userService.findByUsername(securityService.findLoggedInUsername()).getUsername();
        model.addAttribute("admin", name);
        return View("Index");
    }
}
