package hcmue.gst.off.business;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.model.BookBorrowItem;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.MailService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ho Phuong on 08/03/2017.
 */
@Component
public class SendEmailBusiness extends BaseCommand{

    @Autowired
    private MailService mailService;

    @Autowired
    private FindBookBorrowBusiness findBookBorrowBusiness;

    @Autowired
    private BookService bookService;

    @Autowired
    private SecurityService securityService;


    public Result toBorrowUser(Long bookId) {
        findBookBorrowBusiness.ExecuteWithBookId(bookId);
        BookBorrowItem bookBorrowItem = findBookBorrowBusiness.getBookBorrowItem();
        String userEmail = bookBorrowItem.getHeader().getUser().getEmail();
        String emailContext = "Hi " + bookBorrowItem.getHeader().getUser().getName()+"\n" +
                "The book "+bookService.findOne(bookId).getData().getName()+
                " have been reserved by someone.\n"+
                "Please return book to library on time. \n"+
                "Thank you.\n"+
                "Best regards,\n"+
                "From OFF Library.";
        String subject = "Warning, the book is reserved";
        mailService.sendMail(userEmail,subject,emailContext);
        return Success();
    }

    public Result toReservationUser(Long bookId) {
        User user = securityService.getUser();
        findBookBorrowBusiness.ExecuteWithBookId(bookId);
        BookBorrowItem bookBorrowItem = findBookBorrowBusiness.getBookBorrowItem();
        Book book = bookService.findOne(bookId).getData();
        String emailContext = "Hi " + user.getName() + "\n"+
                "You have been made a reservaion for: \n" +
                "Book: "+book.getName()+"\n"+
                "Author: " + book.getAuthor()+"\n"+
                "This book will be back at library on " + bookBorrowItem.getHeader().getReturnDate()+"\n"+
                "Please take note and go to library for borrowing this book."+
                "Thank you.\n"+
                "Best regards,\n"+
                "From OFF Library.";
        String subject = "Book reservation confirm";
        mailService.sendMail(user.getEmail(),subject,emailContext);
        return Success();
    }

    public Result sendEmail(Long bookId) {
        toReservationUser(bookId);
        toBorrowUser(bookId);
        return Success();
    }

}
