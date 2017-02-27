package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dylan on 2/27/2017.
 */
@RestController
@RequestMapping("/api/BookReservation")
public class BookReservationApiController {
    @Autowired
    private BookReservationService BookReservationService;

    @RequestMapping("/Save")
    Result Save(BookReservation model) {
        return BookReservationService.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return BookReservationService.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult GetList(BookReservation model, Pageable p) {
        return BookReservationService.search(model,p);
    }
}
