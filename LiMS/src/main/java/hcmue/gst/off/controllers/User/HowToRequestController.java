package hcmue.gst.off.controllers.User;

import hcmue.gst.off.extensions.UserBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hung Thinh on 25/02/2017.
 */
@Controller
@RequestMapping("/User")
public class HowToRequestController extends UserBaseController {
    @RequestMapping(value = "/HowToRequest", method = RequestMethod.GET)
    public String HowToRequest(Model model) {
        return View("HowToRequest");
    }
}
