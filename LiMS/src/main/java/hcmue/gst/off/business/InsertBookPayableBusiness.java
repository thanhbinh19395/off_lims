package hcmue.gst.off.business;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by dylan on 3/3/2017.
 */
@Component
public class InsertBookPayableBusiness extends BaseCommand {
    private BookPayableHeader header;
    private List<BookPayableDetail> details;

    @Autowired
    private BookPayableHeaderService bookPayableHeaderService;
    @Autowired
    BookPayableDetailService bookPayableDetailService;

    @Autowired
    BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    public BookPayableHeader getHeader() {
        return header;
    }

    public void setHeader(BookPayableHeader header) {
        this.header = header;
    }

    public List<BookPayableDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BookPayableDetail> details) {
        this.details = details;
    }

    public Result Execute() {
        BookBorrowHeader curBB = header.getBookBorrowHeader();
        // calc duedate
        Date dateBB = curBB.getReturnDate();
        header.setOverDue(header.getActualReturnDate().getTime() - dateBB.getTime());



        Result<BookPayableHeader> bpd = bookPayableHeaderService.save(header);
        // book state borrowed -> available
        if (!bpd.isSuccess()) {
            Fail(bpd.getMessage());
        }
        for (BookPayableDetail detail : details) {
            Book tmp = detail.getBook();
            tmp.setState(BookTransactionStep.AVAILABLE);
            bookService.save(tmp);
            detail.setBookPayableHeader(bpd.getData());
            detail.setBookPayableHeaderId(bpd.getData().getId());
            Result bp = bookPayableDetailService.save(detail);
            if (!bp.isSuccess()) {
                Fail(bp.getMessage());
            }

        }
        // bbheader.status = solved;
        curBB.setStatus(CommonStatus.SOLVED);
        bookBorrowHeaderService.save(curBB);

        // user.borrowable = true
        User curUser = curBB.getUser();
        curUser.setBorrowable(Boolean.TRUE);
        userService.save(curUser);
        return Success(header.getId(), "Lưu thành công");

    }
}
