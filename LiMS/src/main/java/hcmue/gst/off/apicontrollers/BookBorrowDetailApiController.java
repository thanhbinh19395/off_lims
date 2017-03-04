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

/**
 * Created by dylan on 2/23/2017.
 */
//bỏ không xài
@RestController
@RequestMapping("/api/BookBorrowDetail")
public class BookBorrowDetailApiController {
    @Autowired
    private BookBorrowDetailService bookBorrowDetail;

    @RequestMapping("/Save")
    Result Save(BookBorrowDetail model) {
        return bookBorrowDetail.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return bookBorrowDetail.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult<BookBorrowDetail> GetList(BookBorrowDetail model, Pageable p) {
        return bookBorrowDetail.search(model,p);
    }

    @RequestMapping("/Search")
    PageableResult<BookBorrowDetail> Search(BookBorrowDetail model, Pageable p){
        return bookBorrowDetail.search(model,p);
    }
}
