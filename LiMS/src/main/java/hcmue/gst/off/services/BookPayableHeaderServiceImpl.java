package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.entities.CommonStatus;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookPayableHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by dylan on 2/24/2017.
 */
@Service
public class BookPayableHeaderServiceImpl extends BaseCommand implements BookPayableHeaderService {

    @Autowired
    BookPayableHeaderRepository bookPayableHeaderRepository;

    @Override
    public Result<BookPayableHeader> save(BookPayableHeader bookPayableHeader) {
        SaveHandler(bookPayableHeader);
        if(bookPayableHeader.getId()== null)
        {
            bookPayableHeader.setStatus(CommonStatus.PENDING.getValue());
        }
        return Success(bookPayableHeaderRepository.save(bookPayableHeader),"Lưu thành công");
    }

    @Override
    public Result<Iterable<BookPayableHeader>> findAll() {
        return Success(bookPayableHeaderRepository.findAll());
    }

    @Override
    public Result<BookPayableHeader> findOne(Long id) {
        return Success(bookPayableHeaderRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookPayableHeaderRepository.delete(id);
        return Success(id, "Xóa thành công");
    }

    @Override
    public PageableResult<BookPayableHeader> search(BookPayableHeader model, Pageable p) {
        return Success(bookPayableHeaderRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<BookPayableHeader>> search(BookPayableHeader model) {
        return Success(bookPayableHeaderRepository.search(model));
    }
}
