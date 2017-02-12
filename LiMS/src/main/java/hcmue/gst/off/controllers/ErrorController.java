package hcmue.gst.off.controllers;

import hcmue.gst.off.extensions.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ho Phuong on 13/02/2017.
 */
@Controller
public class ErrorController extends BaseController implements org.springframework.boot.autoconfigure.web.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String errorPage() {
        return View("error","Shared");
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(Model model) {
        return View("access_denied","Shared");
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }
}
