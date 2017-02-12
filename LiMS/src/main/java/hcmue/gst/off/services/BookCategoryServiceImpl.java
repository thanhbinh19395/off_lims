package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.repositories.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;


    @Override
    public BookCategory save(BookCategory bookCategory) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (bookCategory.getId() == 0) {
            bookCategory.setCreated_by(user);
            bookCategory.setCreated_date(new Date());
        }
        else {
            bookCategory.setUpdate_date(new Date());
            bookCategory.setUpdate_by(user);
        }
        return bookCategoryRepository.save(bookCategory);
    }
}
