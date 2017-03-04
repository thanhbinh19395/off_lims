package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookPayableDetail;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

/**
 * Created by dylan on 3/3/2017.
 */
public interface BookPayableDetailService {
    Result<BookPayableDetail> save(BookPayableDetail BookPayableDetail);
    Result<Iterable<BookPayableDetail>> findAll();
    Result<BookPayableDetail> findOne(Long id);
    Result delete(Long id);
    PageableResult<BookPayableDetail> search(BookPayableDetail model, Pageable p) ;
    Result<Iterable<BookPayableDetail>> search(BookPayableDetail model);
}
