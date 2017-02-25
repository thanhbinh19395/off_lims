package hcmue.gst.off.controllers.User;

import hcmue.gst.off.extensions.UserBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hung Thinh on 24/02/2017.
 */
@Controller
@RequestMapping("/User")
public class HowToBorrowController extends UserBaseController {

    @RequestMapping(value = "/HowToBorrow", method = RequestMethod.GET)
    public String HowToBorrow(Model model) {
        return View("HowToBorrow");
    }
}
