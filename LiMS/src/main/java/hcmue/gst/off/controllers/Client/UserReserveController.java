package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.BookReservationService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ho Phuong on 03/03/2017.
 */
@Controller
public class UserReserveController extends UserBaseController {

    @Autowired
    private BookReservationService bookReservationService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/User/Book/ViewDetail/reserveBook", method = RequestMethod.POST)
    public String reserve(@ModelAttribute("reservationBook") BookReservation bookReservation,Model model) {
        if (securityService.getUser().getBorrowable() == true) {
            bookReservationService.save(bookReservation);
        }
        return "redirect:/Book/ViewDetail/"+bookReservation.getBookId();
    }
}
