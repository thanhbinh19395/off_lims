package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookCategoryService;
import hcmue.gst.off.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dylan on 2/17/2017.
 */
@RestController
@RequestMapping ("/api/Book")
public class BookApiController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/Save")
    Result Save(Book model) {
        return bookService.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return bookService.delete(id);
    }

    @RequestMapping("/GetList")
    Result GetList() {
        return bookService.findAll();
    }
}
