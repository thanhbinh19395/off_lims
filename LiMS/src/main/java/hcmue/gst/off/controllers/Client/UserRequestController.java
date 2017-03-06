package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ho Phuong on 25/02/2017.
 */
@Controller
public class UserRequestController extends UserBaseController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/User/RequestPurchase", method = RequestMethod.POST)
    public String RequestPurchase(@ModelAttribute("request") Request rq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/User/Request/RequestPurchase?error";
        }
        requestService.save(rq);
        return "redirect:/User/Request/RequestPurchase?success";
    }

    @RequestMapping(value = "/User/Request/RequestPurchase", method = RequestMethod.GET)
    public String RequestPurchase(Model model) {
        model.addAttribute("request", new Request());
        return View();
    }

    @RequestMapping(value = "/Request/HowToRequest", method = RequestMethod.GET)
    public String HowToRequest(Model model) {
        return View("HowToRequest");
    }
}
