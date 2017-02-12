package hcmue.gst.off.controllers;

import hcmue.gst.off.extensions.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
//Khi tao controller thi tao folder Hello trong thu muc templates de chua View cua controller do
//Extext BaseEntity Controller tra ve ham View(<viewName>)
@Controller
@RequestMapping("/Hello")
public class HelloController extends BaseController {

    @RequestMapping("/hello")
    public String pringtHello(Model model) {
        String message =  "Hello OFF";
        model.addAttribute("message", message );
        return View("hello");
    }
}
