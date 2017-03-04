package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.business.InsertBookPayableBusiness;
import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.BookPayableDetail;
import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.BookPayableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by dylan on 2/24/2017.
 */
@RestController
@RequestMapping("/api/BookPayable")
public class BookPayableApiController {
    @Autowired
    private BookPayableHeaderService bookPayableHeaderService;

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private InsertBookPayableBusiness insertBookPayableBusiness;

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
    @RequestMapping("/Insert")
    Result InsertBookPayable()
    {

        // Dữ liệu giả
        /*
        BookPayableHeader header = new BookPayableHeader();
        List<BookPayableDetail> details = new ArrayList<BookPayableDetail>();
        BookBorrowHeader bookBorrowHeader = bookBorrowHeaderService.findOne(Long.valueOf(6)).getData();
        header.setActualReturnDate(new Date());
        header.setBookBorrowHeader(bookBorrowHeader);
        header.setBookBorrowId(bookBorrowHeader.getId());
        Set<BookBorrowDetail> borrowDetailList = bookBorrowHeader.getBookBorrowDetails();
        for(BookBorrowDetail borrowDetail : borrowDetailList)
        {
            BookPayableDetail tmp = new BookPayableDetail();
            tmp.setBook(borrowDetail.getBook());
            tmp.setBookId(borrowDetail.getBookId());
            tmp.setIsPaid(Boolean.TRUE);
            details.add(tmp);
        }
        insertBookPayableBusiness.setDetails(details);
        insertBookPayableBusiness.setHeader(header);
        */
        return insertBookPayableBusiness.Execute();
    }
}
