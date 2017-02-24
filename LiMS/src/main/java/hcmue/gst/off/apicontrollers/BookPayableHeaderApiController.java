package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookPayableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dylan on 2/24/2017.
 */
@RestController
@RequestMapping("/api/BookPayableHeader")
public class BookPayableHeaderApiController {
    @Autowired
    private BookPayableHeaderService bookPayableHeaderService;

    @RequestMapping("/Save")
    Result Save(BookPayableHeader model) {
        return bookPayableHeaderService.save(model);
    }

    @RequestMapping("/Deletes")
    Result Deletes(Long id) {
        return bookPayableHeaderService.delete(id);
    }

    @RequestMapping("/GetList")
    PageableResult<BookPayableHeader> GetList(BookPayableHeader model, Pageable p) {
        return bookPayableHeaderService.search(model,p);
    }

    @RequestMapping("/Search")
    PageableResult<BookPayableHeader> Search(BookPayableHeader model, Pageable p){
        return bookPayableHeaderService.search(model,p);
    }
}
