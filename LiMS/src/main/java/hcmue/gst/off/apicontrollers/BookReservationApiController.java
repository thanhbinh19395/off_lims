package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.entities.CommonStatus;
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
    private BookReservationService bookReservationService;

    @RequestMapping("/Save")
    Result Save(BookReservation model) {
        return bookReservationService.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return bookReservationService.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult GetList(BookReservation model, Pageable p) {
        return bookReservationService.search(model,p);
    }
    @RequestMapping("/Handle")
    Result Handle(Long id) {
        BookReservation model = bookReservationService.findOne(id).getData();
        model.setStatus(CommonStatus.FINISHED);
        if (true)
        {
            return new Result(model,"Book Reservation already solved", false);
        }
        return bookReservationService.save(model);
    }
}
