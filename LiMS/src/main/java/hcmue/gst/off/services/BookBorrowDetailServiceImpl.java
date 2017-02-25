package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookBorrowDetailRepository;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by dylan on 2/23/2017.
 */
@Service
public class BookBorrowDetailServiceImpl extends BaseCommand implements BookBorrowDetailService {

    @Autowired
    BookBorrowDetailRepository bookBorrowDetailRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;



    @Override
    public Result<BookBorrowDetail> save(BookBorrowDetail bookBorrowDetail) {
        SaveHandler(bookBorrowDetail);
        return Success(bookBorrowDetailRepository.save(bookBorrowDetail),"Lưu thành công");
    }

    @Override
    public Result<Iterable<BookBorrowDetail>> findAll() {
        return Success(bookBorrowDetailRepository.findAll());
    }

    @Override
    public Result<BookBorrowDetail> findOne(Long id) {
        return Success(bookBorrowDetailRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookBorrowDetailRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public PageableResult<BookBorrowDetail> search(BookBorrowDetail model, Pageable p) {
        return Success(bookBorrowDetailRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<BookBorrowDetail>> search(BookBorrowDetail model) {
        return Success(bookBorrowDetailRepository.search(model));
    }
}