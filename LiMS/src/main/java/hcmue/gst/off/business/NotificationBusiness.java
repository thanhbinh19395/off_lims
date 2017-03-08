package hcmue.gst.off.business;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.MailService;
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

/**
 * Created by dylan on 3/6/2017.
 */
@Component
public class NotificationBusiness {

    private final int oneDay = 864000000;

    @Autowired
    BookBorrowHeaderService bookBorrowHeaderService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MailService mailService;

    @Scheduled(initialDelay=3000, fixedDelay=oneDay )
    public void Notify()
    {
        logger.info("Cron job begins");

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
