package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookPayableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/24/2017.
 */
@Controller
@RequestMapping("/Admin/BookPayableHeader")
public class BookPayableHeaderController extends AdminBaseController{
    @Autowired
    private BookPayableHeaderService bookPayableHeaderService;

    @RequestMapping("/ListBookPayableHeader")
    public String ListBookPayableHeader(Model model, BookPayableHeader BookPayableHeader, Pageable p) {
        getViewBag(model).put("listBookPayableHeader", bookPayableHeaderService.search(BookPayableHeader,p) );
        return View();
    }

    @RequestMapping("/InsertBookPayableHeader")
    public String InsertBookPayableHeader(Model model) {
        return View();
    }


}
