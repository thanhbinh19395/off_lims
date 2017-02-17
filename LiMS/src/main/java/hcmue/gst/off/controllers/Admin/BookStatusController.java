package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookStatusService;
import hcmue.gst.off.services.BookStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/17/2017.
 */
@Controller
@RequestMapping("/Admin/BookStatus")
public class BookStatusController extends AdminBaseController {
    @Autowired
    private BookStatusService bookStatusService;

    @RequestMapping("/ListBookStatus")
    public String ListBookStatus(Model model) {
        getViewBag(model).put("listBookStatus", bookStatusService.findAll());
        return View();
    }

    @RequestMapping("/InsertBookStatus")
    public String InsertBookStatus(Model model) {
        return View();
    }

    @RequestMapping("/UpdateBookStatus/{BookStatusId}")
    public String UpdateBookStatus(@PathVariable("BookStatusId") long BookStatusId, Model model) {
        getViewBag(model).put("BookStatus",bookStatusService.findOne(BookStatusId));
        return View();
    }
}
