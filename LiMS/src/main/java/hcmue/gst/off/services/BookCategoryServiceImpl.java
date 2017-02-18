package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by Lâm Anh Kiệt 15/02/2017
 */
@Service
public class BookCategoryServiceImpl extends BaseCommand implements BookCategoryService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;


    @Override
    public Result<BookCategory> save(BookCategory bookCategory) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (bookCategory.getId() == 0) {
            bookCategory.setCreated_by(user);
            bookCategory.setCreated_date(new Date());
        }
        else {
            bookCategory.setUpdate_date(new Date());
            bookCategory.setUpdate_by(user);
        }
        return Success(bookCategoryRepository.save(bookCategory),"Lưu thành công");
    }

    @Override
    public Result<Iterable<BookCategory>> findAll() {
        return Success(bookCategoryRepository.findAll());
    }

    @Override
    public Result<BookCategory> findOne(Long id) {
        return Success(bookCategoryRepository.findOne(id));
    }

    @Override
    public Result delete(Long id) {
        bookCategoryRepository.delete(id);
        return Success(id,"Xóa thành công");
    }

    @Override
    public Result<Iterable<BookCategory>> findByCategory_nameContaining(String category_name) {
        return Success(bookCategoryRepository.findByCategory__nameContaining(category_name));
    }
}
