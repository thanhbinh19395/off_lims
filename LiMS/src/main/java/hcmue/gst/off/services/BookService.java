package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Created by dylan on 2/17/2017.
 */
public interface BookService {
    Result<Book> save(Book Book);
    Result<Iterable<Book>> findAll();
    Result<Book> findOne(Long id);
    Result delete(Long id);
    PageableResult<Book> search(Book model, Pageable p) ;
    Result<Iterable<Book>> search(Book model);
    Result<Iterable<Book>> findByDate(Date beginDate, Date endDate);
}
