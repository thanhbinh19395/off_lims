package com.gst.apicontroller;

import com.gst.domain.BookCategory;
import com.gst.domain.Country;
import com.gst.repository.BookCategoryRepository;
import com.gst.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/BookCategory")
public class BookCategoryApiController {
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryApiController(BookCategoryRepository bookCategoryRepository)
    {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @RequestMapping("/Save")
    BookCategory Save(BookCategory model){
        return bookCategoryRepository.save(model);
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
