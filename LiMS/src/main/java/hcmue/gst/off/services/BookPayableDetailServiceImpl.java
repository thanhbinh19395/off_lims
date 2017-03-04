package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookPayableDetail;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookPayableDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by dylan on 3/3/2017.
 */
@Service
public class BookPayableDetailServiceImpl extends BaseCommand implements BookPayableDetailService {
    @Autowired
    BookPayableDetailRepository bookPayableDetailRepository;

    @Override
    public Result<BookPayableDetail> save(BookPayableDetail bookPayableDetail) {
        SaveHandler(bookPayableDetail);
        return Success(bookPayableDetailRepository.save(bookPayableDetail), "Lưu thành công");
    }

    @Override
    public Result<Iterable<BookPayableDetail>> findAll() {
        return Success(bookPayableDetailRepository.findAll());
    }

    @Override
    public Result<BookPayableDetail> findOne(Long id) {
        return Success(bookPayableDetailRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookPayableDetailRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public PageableResult<BookPayableDetail> search(BookPayableDetail model, Pageable p) {
        return Success(bookPayableDetailRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));

    }

    @Override
    public Result<Iterable<BookPayableDetail>> search(BookPayableDetail model) {
      return Success(bookPayableDetailRepository.search(model));

    }
}
