package hcmue.gst.off.controllers.User;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hung Thinh on 19/02/2017.
 */
@Controller
public class RequestPurchaseController extends UserBaseController{

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/RequestPurchase", method = RequestMethod.POST)
    public String RequestPurchase(@ModelAttribute("request") Request rq) {
        requestService.save(rq);
        return "redirect:/RequestPurchase?success";
    }

    @RequestMapping(value = "/RequestPurchase", method = RequestMethod.GET)
    public String RequestPurchase(Model model) {
        model.addAttribute("request", new Request());
        return View();
    }
}
