package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dylan on 2/17/2017.
 */
@Service
public class BookServiceImpl extends BaseCommand implements BookService  {
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
        if (book.getId() == null) {
            book.setCreated_by(user);
            book.setCreated_date(new Date());
            book.setBookStatusId(bookStatusRepository.findByDescription("Available").get(0).getId());
            book.setBookBorrowDetail(new BookBorrowDetail());
        }
        else {
            book.setUpdate_date(new Date());
            book.setUpdate_by(user);
        }

        return Success(bookRepository.save(book),"Lưu thành công");
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
        return Success(id,"Xóa thành công");
    }
}
