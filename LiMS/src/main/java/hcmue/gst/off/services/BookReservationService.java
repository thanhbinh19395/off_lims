package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

/**
 * Created by dylan on 2/27/2017.
 */
public interface BookReservationService {
    Result<BookReservation> save(BookReservation bookReservation);
    Result<Iterable<BookReservation>> findAll();
    Result<BookReservation> findOne(Long id);
    Result delete(Long id);
    PageableResult<BookReservation> search(BookReservation model, Pageable p) ;
    Result<Iterable<BookReservation>> search(BookReservation model);
}
