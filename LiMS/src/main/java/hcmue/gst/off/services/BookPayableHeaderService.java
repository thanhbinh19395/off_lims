package hcmue.gst.off.services;


import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Pageable;

/**
 * Created by dylan on 2/24/2017.
 */
public interface BookPayableHeaderService {
    Result<BookPayableHeader> save(BookPayableHeader bookPayableHeader);
    Result<Iterable<BookPayableHeader>> findAll();
    Result<BookPayableHeader> findOne(Long id);
    Result delete(Long id);
    PageableResult<BookPayableHeader> search(BookPayableHeader model, Pageable p) ;
    Result<Iterable<BookPayableHeader>> search(BookPayableHeader model);
}
