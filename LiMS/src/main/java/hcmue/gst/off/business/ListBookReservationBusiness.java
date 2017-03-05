package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookReservation;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookReservationRepository;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ho Phuong on 05/03/2017.
 */
@Component
public class ListBookReservationBusiness extends BaseCommand{
    List<BookReservation> bookReservationList;

    @Autowired
    BookReservationRepository bookReservationRepository;
    @Autowired
    SecurityService securityService;

    public List<BookReservation> getBookReservationList() {
        return bookReservationList;
    }

    public void setBookReservationList(List<BookReservation> bookReservationList) {
        this.bookReservationList = bookReservationList;
    }

        public Result Execute() {
        User user = securityService.getUser();
        bookReservationList = new ArrayList<>();
        BookReservation bookReservationSearchModel = new BookReservation();
        bookReservationSearchModel.setCreated_by(user);
        for (BookReservation bookReservation : bookReservationRepository.search(bookReservationSearchModel)) {
            bookReservationList.add(bookReservation);
        }
        return Success();
    }
}
