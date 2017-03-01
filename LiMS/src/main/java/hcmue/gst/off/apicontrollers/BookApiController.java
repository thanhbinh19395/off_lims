package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookCategoryService;
import hcmue.gst.off.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    PageableResult GetList(Book model, Pageable p) {
        return bookService.search(model,p);
    }
    @RequestMapping("/Search")
    Result<Iterable<Book>> Search(Book model){
        return bookService.search(model);
    }

    @RequestMapping("/PageableSearch")
    PageableResult<Book> PageableSearch(Book model, Pageable p){
        return bookService.search(model,p);
    }
}
