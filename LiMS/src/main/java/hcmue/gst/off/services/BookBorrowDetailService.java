package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

/**
 * Created by dylan on 2/23/2017.
 */
public interface BookBorrowDetailService {
    Result<BookBorrowDetail> save(BookBorrowDetail bookBorrowDetail);
    Result<Iterable<BookBorrowDetail>> findAll();
    Result<BookBorrowDetail> findOne(Long id);
    Result delete(Long id);
    PageableResult<BookBorrowDetail> search(BookBorrowDetail model, Pageable p) ;
    Result<Iterable<BookBorrowDetail>> search(BookBorrowDetail model);
}
