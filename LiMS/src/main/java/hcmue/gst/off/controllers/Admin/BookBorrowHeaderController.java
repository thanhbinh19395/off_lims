package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/21/2017.
 */
@Controller
@RequestMapping("/Admin/BookBorrowHeader")
public class BookBorrowHeaderController extends AdminBaseController{

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @RequestMapping("/ListBookBorrowHeader")
    public String ListBookBorrowHeader(Model model, BookBorrowHeader data, Pageable p) {
        getViewBag(model).put("listBookBorrowHeader", bookBorrowHeaderService.search(data,p));
        return View();
    }
    @RequestMapping("/CreateBookBorrowHeader")
    public String CreateBookBorrowHeader(Model model, BookBorrowHeader data, Pageable p) {
        getViewBag(model).put("listBookBorrowHeader", bookBorrowHeaderService.search(data,p));
        return View();
    }
}
