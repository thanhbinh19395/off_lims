package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.Result;

/**
 * Created by dylan on 2/17/2017.
 */
public interface BookService {
    Result<Book> save(Book Book);
    Result<Iterable<Book>> findAll();
    Result<Book> findOne(Long id);
    Result delete(Long id);
}
