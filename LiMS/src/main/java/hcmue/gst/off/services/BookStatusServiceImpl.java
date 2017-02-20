package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookStatusRepository;
import hcmue.gst.off.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by Lâm Anh Kiệt 15/02/2017
 */
@Service
public class BookStatusServiceImpl extends BaseCommand implements BookStatusService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookStatusRepository bookStatusRepository;

    @Override
    public Result<BookStatus> save(BookStatus bookStatus) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (bookStatus.getId() == null) {
            bookStatus.setCreated_by(user);
            bookStatus.setCreated_date(new Date());
        }
        else {
            bookStatus.setUpdate_date(new Date());
            bookStatus.setUpdate_by(user);
        }
        return Success(bookStatusRepository.save(bookStatus),"Lưu thành công");
    }

    @Override
    public Result<Iterable<BookStatus>> findAll() {
        return Success(bookStatusRepository.findAll());
    }

    @Override
    public Result<BookStatus> findOne(Long id) {
        return Success(bookStatusRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookStatusRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public Result<Iterable<BookStatus>> findByDescriptionContaining(String description) {
        return Success(bookStatusRepository.findByDescriptionContaining(description));
    }

    @Override
    public PageableResult<BookStatus> search(BookStatus model, Pageable p) {
        return Success(bookStatusRepository.search(model,new PageRequest(p.getPageNumber(),PAGESIZE,p.getSort())));
    }

    @Override
    public Result<Iterable<BookStatus>> search(BookStatus model) {
        return Success(bookStatusRepository.search(model));
    }
}
