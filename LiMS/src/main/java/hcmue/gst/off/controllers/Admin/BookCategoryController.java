package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookCategoryService;
import hcmue.gst.off.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/15/2017.
 */
@Controller
@RequestMapping("/Admin/BookCategory")
public class BookCategoryController extends AdminBaseController {
    @Autowired
    private BookCategoryService bookCategoryService;

    @RequestMapping("/ListBookCategory")
    public String ListBookCategory(Model model, BookCategory bookCategory, Pageable p) {
        getViewBag(model).put("listBookCategory", bookCategoryService.search(bookCategory,p));
        return View();
    }

    @RequestMapping("/InsertBookCategory")
    public String InsertBookCategory(Model model) {
        return View();
    }

    @RequestMapping("/UpdateBookCategory/{BookCategoryId}")
    public String UpdateBookCategory(@PathVariable("BookCategoryId") long BookCategoryId, Model model) {
        getViewBag(model).put("bookcategory",bookCategoryService.findOne(BookCategoryId));
        return View();
    }
}
