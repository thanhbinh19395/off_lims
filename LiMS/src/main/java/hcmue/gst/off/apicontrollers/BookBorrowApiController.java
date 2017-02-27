package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dylan on 2/21/2017.
 */
@RestController
@RequestMapping("/api/BookBorrow")
public class BookBorrowApiController {
    @Autowired
    private BookBorrowHeaderService BookBorrowHeaderService;
    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;

    @RequestMapping("/Save")
    Result Save(BookBorrowHeader header,List<BookBorrowDetail> detail) {
        BookBorrowDetail bookBorrowDetail = new BookBorrowDetail();
        return BookBorrowHeaderService.save(header);
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
