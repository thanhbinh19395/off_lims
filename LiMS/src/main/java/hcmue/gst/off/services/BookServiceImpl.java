package hcmue.gst.off.services;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dylan on 2/17/2017.
 */
@Service
public class BookServiceImpl extends BaseCommand implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookStatusRepository bookStatusRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Override
    public Result<Book> save(Book book) {

        User user = userService.findByUsername(securityService.findLoggedInUsername());
        Book tmp = bookRepository.findByBookCode(book.getBookCode());
        Long id = null;
        if (book.getId() == null) {
            if( tmp!=null)
            {
               return Fail("Duplicated Book Code");
            }
            else
            {
                book.setCreated_by(user);
                book.setCreated_date(new Date());
                book.setBookBorrowDetail(null);
                book.setState(BookTransactionStep.AVAILABLE);
                return Success(bookRepository.save(book), "Successfully Saved ");
            }
        }
        else{
            if((tmp!=null && tmp.getId().equals(book.getId())) || tmp == null)
            {
                book.setUpdate_date(new Date());
                book.setUpdate_by(user);
                return Success(bookRepository.save(book), "Successfully Saved ");
            }
        }
        return Fail("Trùng Book Code");
    }

    @Override
    public Result<Iterable<Book>> findAll() {
        return Success(bookRepository.findAll());
    }

    @Override
    public Result<Book> findOne(Long id) {
        return Success(bookRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookRepository.delete(id);
        return Success(id, "Successfully Deleted ");
    }

    @Override
    public PageableResult<Book> search(Book model, Pageable p) {
        return Success(bookRepository.search(model, new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<Book>> search(Book model) {
        return Success(bookRepository.search(model));
    }

    @Override
    public Result findByDate(Date beginDate, Date endDate) {
        return Success(bookRepository.findByDate(beginDate,endDate));
    }
}
