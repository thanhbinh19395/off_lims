package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.extensions.UserBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ho Phuong on 25/02/2017.
 */
@Controller
public class UserBorrowController extends UserBaseController{

    @RequestMapping(value = "/Borrow/HowToBorrow", method = RequestMethod.GET)
    public String HowToBorrow() {
        return View("HowToBorrow");
    }

    @RequestMapping(value = "/Borrow/OverdueBook", method = RequestMethod.GET)
    public String OverdueBook() {
        return View("OverdueBook");
    }

    @RequestMapping(value ="/User/Borrow/")
    public String Borrow(Model model) {

        return View("RegistryBorrowingForm");
    }
}