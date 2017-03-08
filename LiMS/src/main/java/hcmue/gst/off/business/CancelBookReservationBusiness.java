package hcmue.gst.off.business;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.entities.BookTransactionStep;
import hcmue.gst.off.entities.CommonStatus;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookReservationService;
import hcmue.gst.off.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ho Phuong on 07/03/2017.
 */
@Component
public class CancelBookReservationBusiness extends BaseCommand {
    private Long id;

    @Autowired
    private BookReservationService bookReservationService;

    @Autowired
    private BookService bookService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result Execute() {
        //Modify status of reservation
        BookReservation bookReservation = bookReservationService.findOne(id).getData();
        bookReservation.setStatus(CommonStatus.CANCELLED);
        bookReservationService.save(bookReservation);

        //Modify status of book to BORROWED instead RESERVED
        Book book = bookService.findOne(bookReservation.getBookId()).getData();
        book.setState(BookTransactionStep.BORROWED);
        bookService.save(book);

        return Success();
    }
}
