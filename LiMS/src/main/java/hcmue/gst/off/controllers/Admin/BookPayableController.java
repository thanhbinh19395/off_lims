package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookPayableHeaderService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/24/2017.
 */
@Controller
@RequestMapping("/Admin/BookPayable")
public class BookPayableController extends AdminBaseController{
    @Autowired
    private BookPayableHeaderService bookPayableHeaderService;
    @Autowired
    private SecurityService securityService;
    @RequestMapping("/ListBookPayable")
    public String ListBookPayable(Model model, BookPayableHeader BookPayableHeader, Pageable p) {
        getViewBag(model).put("listBookPayable", bookPayableHeaderService.search(BookPayableHeader,p) );
        return View();
    }

    @RequestMapping("/InsertBookPayable")
    public String InsertBookPayable(Model model) {
        getViewBag(model).put("user", securityService.getUser());
        return View();
    }


}
