package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.extensions.UserBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hung Thinh on 27/02/2017.
 */
@Controller
public class ContactController extends UserBaseController{
    @RequestMapping(value = "/Contact", method = RequestMethod.GET)
    public String Contact() {
        return View();
    }
}
