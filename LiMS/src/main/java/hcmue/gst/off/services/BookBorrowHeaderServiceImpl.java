package hcmue.gst.off.services;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.CommonStatus;
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

import java.util.*;

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
        SaveHandler(bookBorrowHeader);
        if (bookBorrowHeader.getId() == null) {
            if(bookBorrowHeader.getStatus()==null)
            {
                bookBorrowHeader.setStatus(CommonStatus.PENDING);
            }
            user.setBorrowable(false); //Prevent user from continue borrowing
        }
        return Success(bookBorrowHeaderRepository.save(bookBorrowHeader),"Successfully Saved ");
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
        return Success(id,"Successfully Deleted ");
    }

    @Override
    public PageableResult<BookBorrowHeader> search(BookBorrowHeader model, Pageable p) {
        return Success(bookBorrowHeaderRepository.search(model,new PageRequest(p.getPageNumber(), PAGESIZE, p.getSort())));
    }

    @Override
    public Result<Iterable<BookBorrowHeader>> search(BookBorrowHeader model) {
        return Success(bookBorrowHeaderRepository.search(model));
    }

    @Override
    public Result<List<BookBorrowHeader>> findDeadlineBBHeader() {
        BookBorrowHeader model = new BookBorrowHeader();
        model.setStatus(CommonStatus.INPROGRESS);

        Iterable<BookBorrowHeader> list  = bookBorrowHeaderRepository.search(model);
        List<BookBorrowHeader> rs =new ArrayList<>();
        // get due date
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, +4);

        Date dueDate = cal.getTime();

        for(BookBorrowHeader bb : list)
        {
            // compare return date =  due date
            if(getZeroTimeDate(bb.getReturnDate()).compareTo(getZeroTimeDate(dueDate)) == 0)
            {
                rs.add(bb);
            }
        }
        return Success(rs);
    }

    @Override
    public Result<List<BookBorrowHeader>> findCancledBBHeader() {
        BookBorrowHeader model = new BookBorrowHeader();
        model.setStatus(CommonStatus.PENDING);
        Iterable<BookBorrowHeader> list  = bookBorrowHeaderRepository.search(model);
        List<BookBorrowHeader> rs =new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date dueDate = cal.getTime();
        for(BookBorrowHeader bb : list)
        {
            // compare return date =  due date
            if(getZeroTimeDate(bb.getReturnDate()).compareTo(getZeroTimeDate(dueDate)) == 0)
            {
                rs.add(bb);
            }
        }
        return Success(rs);
    }


    public static Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( fecha );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }
}
