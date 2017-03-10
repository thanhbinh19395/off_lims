package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.CommonStatus;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Thanh Binh on 3/9/2017.
 */
@Component
public class HandlePendingBookBorrowBusiness extends BaseCommand {
    @Autowired
    BookBorrowHeaderService bookBorrowHeaderService;

    Long bookBorrowHeaderId;

    public Result<BookBorrowHeader> Execute(){
        BookBorrowHeader bbHeader = bookBorrowHeaderService.findOne(bookBorrowHeaderId).getData();
        if(bbHeader.getStatus() == CommonStatus.PENDING){
            bbHeader.setStatus(CommonStatus.INPROGRESS);
            return bookBorrowHeaderService.save(bbHeader);
        }
        else{
            return Fail("Status is not corrected");
        }
    }

    public Long getBookBorrowHeaderId() {
        return bookBorrowHeaderId;
    }

    public void setBookBorrowHeaderId(Long bookBorrowHeaderId) {
        this.bookBorrowHeaderId = bookBorrowHeaderId;
    }
}
