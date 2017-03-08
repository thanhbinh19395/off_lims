package hcmue.gst.off.business;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ho Phuong on 06/03/2017.
 */
@Component
public class CancelBookBorrowBusiness extends BaseCommand {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public Result Execute() {
        //Modify header status
        BookBorrowHeader bookBorrowHeader = bookBorrowHeaderService.findOne(id).getData();
        bookBorrowHeader.setStatus(CommonStatus.CANCELLED);
        bookBorrowHeaderService.save(bookBorrowHeader);

        //Modify status of book to AVAILABLE instead BORROWED
        BookBorrowDetail bookBorrowDetailSearchModel = new BookBorrowDetail();
        bookBorrowDetailSearchModel.setBookBorrowHeaderId(id);
        for (BookBorrowDetail bookBorrowDetail : bookBorrowDetailService.search(bookBorrowDetailSearchModel).getData()) {
            Book book = bookService.findOne(bookBorrowDetail.getBookId()).getData();
            book.setState(BookTransactionStep.AVAILABLE);
            bookService.save(book);
        }

        //Modify status of user
        User user = userService.findOne(bookBorrowHeader.getUserId()).getData();
        user.setBorrowable(true);
        userService.save(user);
        return Success();
    }

}
