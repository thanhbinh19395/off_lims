package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dylan on 2/21/2017.
 */
@RestController
@RequestMapping("/api/BookBorrowHeader")
public class BookBorrowHeaderApiController {
    @Autowired
    private BookBorrowHeaderService BookBorrowHeaderService;

    @RequestMapping("/Save")
    Result Save(BookBorrowHeader model) {
        return BookBorrowHeaderService.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return BookBorrowHeaderService.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult<BookBorrowHeader> GetList(BookBorrowHeader model, Pageable p) {
        return BookBorrowHeaderService.search(model,p);
    }

    @RequestMapping("/Search")
    PageableResult<BookBorrowHeader> Search(BookBorrowHeader model, Pageable p){
        return BookBorrowHeaderService.search(model,p);
    }
}
