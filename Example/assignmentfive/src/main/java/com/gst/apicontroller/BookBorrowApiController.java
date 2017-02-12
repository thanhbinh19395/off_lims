package com.gst.apicontroller;

import com.gst.domain.BookBorrow;
import com.gst.domain.BookCategory;
import com.gst.repository.BookBorrowRepository;
import com.gst.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/BookBorrow")
public class BookBorrowApiController {
    private final BookBorrowRepository bookBorrowRepository;

    @Autowired
    public BookBorrowApiController(BookBorrowRepository bookBorrowRepository)
    {
        this.bookBorrowRepository = bookBorrowRepository;
    }

    @RequestMapping("/Save")
    BookBorrow Save(BookBorrow model){
        return bookBorrowRepository.save(model);
    }
    @RequestMapping("/Deletes")
    void Deletes(long id){
        bookBorrowRepository.delete(id);
    }
    @RequestMapping("/GetList")
    Iterable<BookBorrow> GetList(){
        return bookBorrowRepository.findAll();
    }
}
