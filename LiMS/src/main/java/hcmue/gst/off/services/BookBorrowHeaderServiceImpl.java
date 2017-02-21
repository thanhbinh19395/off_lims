package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookBorrowHeaderRepository;
import hcmue.gst.off.repositories.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dylan on 2/21/2017.
 */
@Service
public class BookBorrowHeaderServiceImpl extends BaseCommand implements BookBorrowHeaderService {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookBorrowHeaderRepository bookBorrowHeaderRepository;

    @Override
    public Result<BookBorrowHeader> save(BookBorrowHeader bookBorrowHeader) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (bookBorrowHeader.getId() == null) {
            bookBorrowHeader.setCreated_by(user);
            bookBorrowHeader.setCreated_date(new Date());
        }
        else {
            bookBorrowHeader.setUpdate_date(new Date());
            bookBorrowHeader.setUpdate_by(user);
        }
        return Success(bookBorrowHeaderRepository.save(bookBorrowHeader),"Lưu thành công");
    }

    @Override
    public Result<Iterable<BookBorrowHeader>> findAll() {
        return Success(bookBorrowHeaderRepository.findAll());
    }

    @Override
    public Result<BookBorrowHeader> findOne(Long id) {
        return Success(bookBorrowHeaderRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookBorrowHeaderRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public PageableResult<BookBorrowHeader> search(BookBorrowHeader model, Pageable p) {
        return Success(bookBorrowHeaderRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<BookBorrowHeader>> search(BookBorrowHeader model) {
        return Success(bookBorrowHeaderRepository.search(model));
    }
}
