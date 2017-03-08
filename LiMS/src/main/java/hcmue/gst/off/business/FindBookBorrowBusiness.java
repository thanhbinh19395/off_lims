package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.model.BookBorrowItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ho Phuong on 07/03/2017.
 */
@Component
public class FindBookBorrowBusiness extends BaseCommand {
    private BookBorrowItem bookBorrowItem;

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;


    public BookBorrowItem getBookBorrowItem() {
        return bookBorrowItem;
    }

    public void setBookBorrowItem(BookBorrowItem bookBorrowItem) {
        this.bookBorrowItem = bookBorrowItem;
    }

    public Result ExecuteWithBookId(Long bookId) {
        bookBorrowItem = new BookBorrowItem();
        BookBorrowDetail bookBorrowDetailSearchModel = new BookBorrowDetail();
        bookBorrowDetailSearchModel.setBookId(bookId);
        final Iterator<BookBorrowDetail> itr = bookBorrowDetailService.search(bookBorrowDetailSearchModel).getData().iterator();
        BookBorrowDetail bookBorrowDetail = itr.next();
        while (itr.hasNext()) {
            bookBorrowDetail = itr.next();
        }
        BookBorrowHeader bookBorrowHeaderSearchModel = new BookBorrowHeader();
        bookBorrowHeaderSearchModel.setId(bookBorrowDetail.getBookBorrowHeaderId());
        BookBorrowHeader bookBorrowHeader = bookBorrowHeaderService.search(bookBorrowHeaderSearchModel).getData().iterator().next();
        bookBorrowDetailSearchModel.setBookBorrowHeaderId(bookBorrowHeader.getId());
        bookBorrowDetailSearchModel.setBookId(null);
        List<BookBorrowDetail> bookBorrowDetailList = new ArrayList<>();
        for (BookBorrowDetail item : bookBorrowDetailService.search(bookBorrowDetailSearchModel).getData()) {
            bookBorrowDetailList.add(item);
        }
        bookBorrowItem.setHeader(bookBorrowHeader);
        bookBorrowItem.setDetails(bookBorrowDetailList);
        return Success();
    }

    public Result ExecuteWithBBHeaderId(Long BBHeaderId) {
        bookBorrowItem = new BookBorrowItem();
        BookBorrowHeader bookBorrowHeader = bookBorrowHeaderService.findOne(BBHeaderId).getData();
        BookBorrowDetail bookBorrowDetailSearchModel = new BookBorrowDetail();
        bookBorrowDetailSearchModel.setBookBorrowHeaderId(bookBorrowHeader.getId());
        List<BookBorrowDetail> bookBorrowDetailList = new ArrayList<>();
        for (BookBorrowDetail item: bookBorrowDetailService.search(bookBorrowDetailSearchModel).getData()) {
            bookBorrowDetailList.add(item);
        }
        bookBorrowItem.setHeader(bookBorrowHeader);
        bookBorrowItem.setDetails(bookBorrowDetailList);
        return Success();
    }
}
