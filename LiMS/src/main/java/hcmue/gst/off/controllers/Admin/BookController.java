package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/15/2017.
 */
@Controller
@RequestMapping("/Admin/Book")
public class BookController extends AdminBaseController{
    @Autowired
    private BookService bookService;

    @RequestMapping("/ListBook")
    public String ListBook(Model model) {
        getViewBag(model).put("listBook", bookService.findAll());
        return View();
    }

    @RequestMapping("/InsertBook")
    public String InsertBook(Model model) {
        return View();
    }

    @RequestMapping("/UpdateBook/{BookId}")
    public String UpdateBook(@PathVariable("BookId") long BookId, Model model) {
        getViewBag(model).put("Book",bookService.findOne(BookId));
        return View();
    }

}
