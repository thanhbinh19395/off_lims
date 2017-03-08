package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by dylan on 2/21/2017.
 */
public interface BookBorrowHeaderService {
    Result<BookBorrowHeader> save(BookBorrowHeader bookBorrowHeader);
    Result<Iterable<BookBorrowHeader>> findAll();
    Result<BookBorrowHeader> findOne(Long id);
    Result delete(Long id);
    PageableResult<BookBorrowHeader> search(BookBorrowHeader model, Pageable p) ;
    Result<Iterable<BookBorrowHeader>> search(BookBorrowHeader model);
    Result<List<BookBorrowHeader>> findDeadlineBBHeader();

}
