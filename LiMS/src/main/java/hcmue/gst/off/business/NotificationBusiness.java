package hcmue.gst.off.business;

import hcmue.gst.off.entities.*;
import hcmue.gst.off.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by dylan on 3/6/2017.
 */
@Component
public class NotificationBusiness {

    private final int oneDay = 864000000;

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;
    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
    @Scheduled(cron = "0 50 14 * * ?" )
    public void Notify()
    {
        logger.info("Cron job begins");
        // delete bb header pending overdue
        List<BookBorrowHeader> listCancledBBHeader = bookBorrowHeaderService.findCancledBBHeader().getData();


        for(BookBorrowHeader bb: listCancledBBHeader)
        {
            bb.setStatus(CommonStatus.CANCELLED);
            Set<BookBorrowDetail> details = bb.getBookBorrowDetails();
            for(BookBorrowDetail detail : details)
            {
                Book tmp = detail.getBook();
                tmp.setState(BookTransactionStep.AVAILABLE);
                bookService.save(tmp);
            }
            User user= bb.getUser();
            user.setBorrowable(Boolean.TRUE);
            userService.save(user);
        }

        // mail to due day bb header
        List<BookBorrowHeader> listDueDateBBHeader = bookBorrowHeaderService.findDeadlineBBHeader().getData();
        for(BookBorrowHeader bb : listDueDateBBHeader)
        {
            String to = bb.getUser().getEmail();
            String subject = "[NOTIFY] YOUR BORROWED BOOK IS COMING DUE";
            String body = "Acccount" + bb.getCreated_by().getUsername()+"có phiếu mượn sách còn 3 ngày hết hạn";
            mailService.sendMail(to,subject,body);
        }
        logger.info("Cron job finish");

    }

}
