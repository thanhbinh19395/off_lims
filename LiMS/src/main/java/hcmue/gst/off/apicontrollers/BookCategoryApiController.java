package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.repositories.BookCategoryRepository;
import hcmue.gst.off.services.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/BookCategory")
public class BookCategoryApiController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @RequestMapping("/Save")
    BookCategory Save(BookCategory model){
        return bookCategoryService.save(model);
    }
    @RequestMapping("/Deletes")
    void Deletes(long id){
        bookCategoryRepository.delete(id);
    }
    @RequestMapping("/GetList")
    Iterable<BookCategory> GetList(){
        return bookCategoryRepository.findAll();
    }
}
