package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.business.FindBookBorrowBusiness;
import hcmue.gst.off.business.SendEmailBusiness;
import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.model.BookBorrowItem;
import hcmue.gst.off.services.BookReservationService;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.MailService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ho Phuong on 03/03/2017.
 */
@Controller
public class UserReserveController extends UserBaseController {

    @Autowired
    private BookReservationService bookReservationService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SendEmailBusiness sendEmailBusiness;

    @RequestMapping(value = "/User/Book/ViewDetail/reserveBook", method = RequestMethod.POST)
    @ResponseBody
    public Result reserve(BookReservation bookReservation, Model model) {
        if (securityService.getUser().getBorrowable() == true) {
            bookReservationService.save(bookReservation);
        }
        if (securityService.getUser().getEmail() == "" || securityService.getUser().getEmail() == null) {
            return new Result(null,false);
        }
        return new Result(null, true);
    }
    @RequestMapping(value = "/Reservation/HowToReserve", method = RequestMethod.GET)
    public String HowToReserve() {
        return View("HowToReserve");
    }
}
