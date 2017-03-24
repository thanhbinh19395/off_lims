package hcmue.gst.off.business;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
@Component
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

    public InsertBookBorrowBusiness(BookBorrowHeader header, List<BookBorrowDetail> details) {
        this.header = header;
        this.details = details;
    }

    public InsertBookBorrowBusiness() {
        //this.header= new BookBorrowHeader(new Date(),(long)1);
    }

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
        User curUser = userService.findOne(header.getUserId()).getData();
        if(!curUser.getBorrowable()) {
            return Fail("User " + curUser.getUsername() + " is not Borrowable !");
        }
        header.setStatus(CommonStatus.INPROGRESS);
        Result<BookBorrowHeader> bookBorrowHeader = bookBorrowHeaderService.save(header);
        if(!bookBorrowHeader.isSuccess())
        {
            return Fail(bookBorrowHeader.getMessage());
        }


        for (BookBorrowDetail detail: details)
        {
            Book tmp = bookService.findOne(detail.getBookId()).getData();
            if(tmp.getState() == BookTransactionStep.BORROWED)
                return Fail("Book [" + tmp.getBookCode() +"]" + tmp.getName() + " had been borrowed");
            tmp.setState(BookTransactionStep.BORROWED);
            bookService.save(tmp);
            //detail.setBookBorrowHeader(bookBorrowHeader.getData());
            detail.setBookBorrowHeaderId(bookBorrowHeader.getData().getId());
            Result bbdResult = bookBorrowDetailService.save(detail);
            if(!bbdResult.isSuccess())
            {
                return Fail(bbdResult.getMessage());
            }
        }
        // khóa phiếu mượn lại

        curUser.setBorrowable(Boolean.FALSE);
        userService.save(curUser);
        return Success(header.getId(),"Successfully Saved ");
    }
}
