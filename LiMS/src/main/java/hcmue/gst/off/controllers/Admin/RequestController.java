package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.extensions.Mail;
import hcmue.gst.off.services.CountryService;
import hcmue.gst.off.services.RequestService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public String ListRequest(Model model,Request data, Pageable p) {
        getViewBag(model).put("listRequest", requestService.search(data,p));
        return View();
    }

    @RequestMapping("/Approve/{RequestId}")
    public String Approve(@PathVariable("RequestId") long requestId, Model model)
    {
        Mail mail = new Mail(requestId,"");
        getViewBag(model).put("email",mail);
        return View();
    }

    @RequestMapping("/Reject/{RequestId}")
    public String Reject(@PathVariable("RequestId") long requestId, Model model)
    {
        Mail mail = new Mail(requestId,"");
        getViewBag(model).put("email",mail);
        return View();
    }


}
