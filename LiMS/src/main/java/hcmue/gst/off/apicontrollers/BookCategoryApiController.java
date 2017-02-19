package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookCategoryRepository;
import hcmue.gst.off.services.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/BookCategory")
public class BookCategoryApiController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @RequestMapping("/Save")
    Result Save(BookCategory model) {
        return bookCategoryService.save(model);
    }

    @RequestMapping("/Deletes")
    void Deletes(long id) {
        bookCategoryService.delete(id);
    }

    @RequestMapping("/GetList")
    Result GetList() {
        return bookCategoryService.findAll();
    }

    @RequestMapping("/FindByNameContaining")
    Result GetListByName(String category_name)
    {
        return bookCategoryService.findByCategory_nameContaining(category_name);
    }
}
