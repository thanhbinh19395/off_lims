package hcmue.gst.off.controllers.Public;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.PublicBaseController;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookCategoryRepository;
import hcmue.gst.off.services.BookPageableService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
public class HomeController extends PublicBaseController {

    private Book bookResult = new Book();

    @Autowired
    private BookPageableService bookPageableService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index(Model model, Pageable pageable) {
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.findAll(pageable),"/");
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return View("Index");
    }

    @RequestMapping(value = "/book/searchQuery", method = RequestMethod.GET)
    public String search(@RequestParam("searchResult") String searchResult, Pageable pageable, Model model) {
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.search(bookResult, pageable), "/book/searchQuery?searchResult="+searchResult);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return View("Index");
    }

    @RequestMapping(value = "/book/searchHandle", method = RequestMethod.POST)
    public String searchHandle(@RequestParam("search") String searchResult,
                               @RequestParam(value = "searchType") int searchType) {
        bookResult = new Book();
        switch (searchType) {
            case 1: bookResult.setBookCode(searchResult); break;
            case 2: bookResult.setName(searchResult); break;
            case 3: bookResult.setAuthor(searchResult); break;
            case 4: bookResult.setPublish_year(Integer.parseInt(searchResult)); break;
        }
        return "redirect:/book/searchQuery?searchResult="+searchResult;
    }

    @RequestMapping(value = "/bookCategory/{id}", method = RequestMethod.GET)
    public String bookByCategory(@PathVariable("id") Long id, Pageable pageable, Model model) {
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.findBybookCategoryId(id, pageable), "/bookCategory/"+id);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return View("Index");
    }

    @RequestMapping(value = "/book/new")
    public String bookByNew(Pageable pageable, Model model) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE,-7);
        Date beginDate = c.getTime();
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.findByDate(beginDate,today,pageable), "/book/new");
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return View("Index");
    }
}
