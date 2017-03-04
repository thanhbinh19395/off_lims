package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookBorrowDetailRepository;
import hcmue.gst.off.repositories.BookBorrowHeaderRepository;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.viewmodel.BookBorrowItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ho Phuong on 04/03/2017.
 */
@Component
public class ListBookBorrow extends BaseCommand {
    private List<BookBorrowItem> borrowItemList;

    @Autowired
    BookBorrowHeaderRepository bookBorrowHeaderRepository;
    @Autowired
    BookBorrowDetailRepository bookBorrowDetailRepository;

    public List<BookBorrowItem> getBorrowItemList() {
        return borrowItemList;
    }

    public void setBorrowItemList(List<BookBorrowItem> borrowItemList) {
        this.borrowItemList = borrowItemList;
    }

    public Result Execute(){
        User user = securityService.getUser();
        borrowItemList = new ArrayList<>();
        BookBorrowHeader bookBorrowHeaderSearchModel = new BookBorrowHeader();
        bookBorrowHeaderSearchModel.setUserId(user.getId());
        BookBorrowDetail bookBorrowDetailSearchModel = new BookBorrowDetail();
        for (BookBorrowHeader bookBorrowHeader: bookBorrowHeaderRepository.search(bookBorrowHeaderSearchModel)) {
            bookBorrowDetailSearchModel.setBookBorrowHeaderId(bookBorrowHeader.getId());
            List<BookBorrowDetail> bookBorrowDetailList = new ArrayList<>();
            for (BookBorrowDetail bookBorrowDetail : bookBorrowDetailRepository.search(bookBorrowDetailSearchModel)) {
                bookBorrowDetailList.add(bookBorrowDetail);
            }
            borrowItemList.add(new BookBorrowItem(bookBorrowHeader,bookBorrowDetailList));
        }
        return Success();
    }
}
