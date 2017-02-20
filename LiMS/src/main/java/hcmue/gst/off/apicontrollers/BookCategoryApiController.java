package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.BookCategoryRepository;
import hcmue.gst.off.services.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    Result Deletes(Long id) {
        return bookCategoryService.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult<BookCategory> GetList(BookCategory model, Pageable p) {
        return bookCategoryService.search(model,p);
    }

    @RequestMapping("/FindByNameContaining")
    Result GetListByName(String category_name)
    {
        return bookCategoryService.findByCategory_nameContaining(category_name);
    }
    @RequestMapping("/Search")
    PageableResult<BookCategory> Search(BookCategory model, Pageable p){
        return bookCategoryService.search(model,p);
    }
}
