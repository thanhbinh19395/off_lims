package hcmue.gst.off.services;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dylan on 2/27/2017.
 */
@Service
public class BookReservationServiceImpl extends BaseCommand implements BookReservationService {
    @Autowired
    BookReservationRepository bookReservationRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private BookService bookService;

    @Override
    public Result<BookReservation> save(BookReservation bookReservation) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());

        if (bookReservation.getId() == null) {
            bookReservation.setCreated_by(user);
            bookReservation.setCreated_date(new Date());
            bookReservation.setStatus(CommonStatus.PENDING);
            Book book = bookService.findOne(bookReservation.getBookId()).getData();
            book.setState(2);
            bookService.save(book);

        }
        else {
            bookReservation.setUpdate_date(new Date());
            bookReservation.setUpdate_by(user);
        }
        return Success(bookReservationRepository.save(bookReservation),"Successfully Saved ");
    }

    @Override
    public Result<Iterable<BookReservation>> findAll() {
        return Success(bookReservationRepository.findAll());
    }

    @Override
    public Result<BookReservation> findOne(Long id) {
        return Success(bookReservationRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookReservationRepository.delete(id);
        return Success(id,"Successfully Deleted ");
    }

    @Override
    public PageableResult<BookReservation> search(BookReservation model, Pageable p) {
        if(model.getCreated_by() == null){
            model.setCreated_by(new User());
        }
        if(model.getUpdate_by() == null){
            model.setUpdate_by(new User());
        }
        return Success(bookReservationRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<BookReservation>> search(BookReservation model) {
        return Success(bookReservationRepository.search(model));
    }
}
