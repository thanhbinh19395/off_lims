package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
public class InsertBookBorrow extends BaseCommand {
    private BookBorrowHeader header;
    private List<BookBorrowDetail> details;

    @Autowired
    BookBorrowHeaderService bookBorrowHeaderService;
    @Autowired
    BookBorrowDetailService bookBorrowDetailService;

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

      return Success();
    }
}
