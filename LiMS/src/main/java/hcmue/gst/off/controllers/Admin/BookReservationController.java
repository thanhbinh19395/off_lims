package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookReservationService;
import hcmue.gst.off.services.BookReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/27/2017.
 */
@Controller
@RequestMapping("/Admin/BookReservation")
public class BookReservationController extends AdminBaseController {
    @Autowired
    private BookReservationService BookReservationService;

    @RequestMapping("/ListBookReservation")
    public String ListBookReservation(Model model, BookReservation BookReservation, Pageable p) {
        getViewBag(model).put("listBookReservation", BookReservationService.search(BookReservation, p));
        return View();
    }


}
