package hcmue.gst.off.viewmodel;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ho Phuong on 04/03/2017.
 */
public class BookBorrowItem extends BaseCommand {
    private BookBorrowHeader header;
    private List<BookBorrowDetail> details;

    public BookBorrowItem(BookBorrowHeader header, List<BookBorrowDetail> details) {
        this.header = header;
        this.details = details;
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


}
