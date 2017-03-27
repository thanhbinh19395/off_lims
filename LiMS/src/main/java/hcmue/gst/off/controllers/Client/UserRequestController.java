package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.business.CancelBookRequestBusiness;
import hcmue.gst.off.business.ListBookRequestBusiness;
import hcmue.gst.off.business.ListBookReservationBusiness;
import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ho Phuong on 25/02/2017.
 */
@Controller
public class UserRequestController extends UserBaseController {

    @Autowired
    private ListBookRequestBusiness listBookRequestBusiness;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CancelBookRequestBusiness cancelBookRequestBusiness;

    @RequestMapping(value = "/User/RequestPurchase", method = RequestMethod.POST)
    @ResponseBody
    Result<Request> RequestPurchase(@ModelAttribute("request") Request request) {
        if (request.getBook_name().equals("")) {
            return new Result(null, "false", false);
        }
        return requestService.save(request);
    }

    @RequestMapping(value = "/User/Request/RequestPurchase", method = RequestMethod.GET)
    public String RequestPurchase(Model model, Pageable pageable) {
        listBookRequestBusiness.ExecutePageable(pageable);
        PageWrapper<Request> page = new PageWrapper<>(listBookRequestBusiness.getRequestListPageable(),"/User/Request/RequestPurchase");
        model.addAttribute("bookRequestList", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("request", new Request());
        return View();
    }

    @RequestMapping(value = "/ViewLog/BookRequest/cancel", method = RequestMethod.POST)
    @ResponseBody
    Result cancelBookRequest(Long id) {
        cancelBookRequestBusiness.setId(id);
        return cancelBookRequestBusiness.Execute();
    }

    @RequestMapping(value = "/Request/HowToRequest", method = RequestMethod.GET)
    public String HowToRequest(Model model) {
        return View("HowToRequest");
    }
}
