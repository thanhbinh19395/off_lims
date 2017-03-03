package hcmue.gst.off.business;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
public class InsertBookBorrowBusiness extends BaseCommand {
    private BookBorrowHeader header;
    private List<BookBorrowDetail> details;

    @Autowired
    BookBorrowHeaderService bookBorrowHeaderService;
    @Autowired
    BookBorrowDetailService bookBorrowDetailService;

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    public BookBorrowHeader getHeader() {
        return header;
    }

    public void setHeader(BookBorrowHeader header) {
        this.header = header;
    }

    public List<BookBorrowDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BookBorrowDetail> details) {
        this.details = details;
    }

    public Result Execute(){
        header.setStatus(CommonStatus.PENDING);

        bookBorrowHeaderService.save(header);
        Result<BookBorrowHeader> bookBorrowHeader = bookBorrowHeaderService.save(header);
        if(!bookBorrowHeader.isSuccess())
        {
            return Fail("Lưu thất bại");
        }


        for (BookBorrowDetail detail: details)
        {
            Book tmp = detail.getBook();
            tmp.setState(BookTransactionStep.BORROWED);
            bookService.save(tmp);
            detail.setBookBorrowHeader(bookBorrowHeader.getData());
            detail.setBookBorrowHeaderId(bookBorrowHeader.getData().getId());
            bookBorrowDetailService.save(detail);
            if(!bookBorrowDetailService.save(detail).isSuccess())
            {
                return Fail("Lưu thất bại");
            }
        }
        // khóa phiếu mượn lại
        User curUser = header.getUser();
        curUser.setBorrowable(Boolean.FALSE);
        userService.save(curUser);
        return Success(header.getId(),"Lưu thành công");
    }
}
