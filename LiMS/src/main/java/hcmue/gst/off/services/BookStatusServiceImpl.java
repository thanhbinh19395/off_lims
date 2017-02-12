package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.repositories.BookStatusRepository;
import hcmue.gst.off.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Service
public class BookStatusServiceImpl implements BookStatusService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookStatusRepository bookStatusRepository;

    @Override
    public BookStatus save(BookStatus bookStatus) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (bookStatus.getId() == 0) {
            bookStatus.setCreated_by(user);
            bookStatus.setCreated_date(new Date());
        }
        else {
            bookStatus.setUpdate_date(new Date());
            bookStatus.setUpdate_by(user);
        }
        return bookStatusRepository.save(bookStatus);
    }
}
