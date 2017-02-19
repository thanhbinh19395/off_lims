package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.CountryService;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/18/2017.
 */
@Controller
@RequestMapping("Admin/Request")
public class RequestController extends AdminBaseController {
    @Autowired
    private RequestService requestService;

    @RequestMapping("/ListRequest")
    public String ListRequest(Model model) {
        getViewBag(model).put("listRequest", requestService.findAll());
        return View();
    }


}
