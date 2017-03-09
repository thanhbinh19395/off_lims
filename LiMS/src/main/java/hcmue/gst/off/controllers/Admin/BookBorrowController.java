package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.RequestService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/21/2017.
 */
@Controller
@RequestMapping("/Admin/BookBorrow")
public class BookBorrowController extends AdminBaseController{

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/ListBookBorrow")
    public String ListBookBorrow(Model model, BookBorrowHeader data, Pageable p) {
        getViewBag(model).put("listBookBorrow", bookBorrowHeaderService.search(data,p));
        return View();
    }
    @RequestMapping("/InsertBookBorrow")
    public String InsertBookBorrow(Model model, BookBorrowHeader data, Pageable p) {
        getViewBag(model).put("user", securityService.getUser());
        return View();
    }
    @RequestMapping("/ViewBookBorrow/{BookBorrowHeaderId}")
    public String ViewBookBorrow(@PathVariable("BookBorrowHeaderId") long BookBorrowHeaderId, Model model) {
        getViewBag(model).put("bookBorrow",bookBorrowHeaderService.findOne(BookBorrowHeaderId));
        return View();
    }
    @RequestMapping("/HandlePendingBookBorrow")
    public String HandlePendingBookBorrow() {
        return View();
    }

}
