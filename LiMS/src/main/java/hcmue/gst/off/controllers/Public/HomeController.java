package hcmue.gst.off.controllers.Public;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.PublicBaseController;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookCategoryRepository;
import hcmue.gst.off.services.BookPageableService;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
public class HomeController extends PublicBaseController {

    private Book bookResult = new Book();
    private Book newBook = new Book();

    @Autowired
    private BookPageableService bookPageableService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index(Model model, Pageable pageable, HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        int count = 0;
        for (int i = 1; i < 4; i++) {
            if ((Long)session.getAttribute("item"+i) !=null) {
                count++;
            }
        }
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.findAll(pageable),"/");
        final Iterator<Book> itr = bookService.findAll().getData().iterator();
        newBook = itr.next();
        while(itr.hasNext()) {
            newBook = itr.next();
        }
        model.addAttribute("newBook", newBook);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        model.addAttribute("header","All book");
        model.addAttribute("total", count);
        return View("Index");
    }

    @RequestMapping(value = "/book/searchQuery", method = RequestMethod.GET)
    public String search(@RequestParam("searchType")int searchType, @RequestParam("searchResult") String searchResult, Pageable pageable, Model model) {
        bookResult = new Book();
        switch (searchType) {
            case 1: bookResult.setBookCode(searchResult); break;
            case 2: bookResult.setName(searchResult); break;
            case 3: bookResult.setAuthor(searchResult); break;
            case 4: bookResult.setPublish_year(Integer.parseInt(searchResult)); break;
        }
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.search(bookResult, pageable), "/book/searchQuery?searchResult="+searchResult);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("newBook", newBook);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        model.addAttribute("result",searchResult);
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
        return "redirect:/book/searchQuery?searchType="+searchType+"&"+"searchResult="+searchResult;
    }

    @RequestMapping(value = "/book/bookCategory/{Id}", method = RequestMethod.GET)
    public String bookByCategory(@PathVariable("Id") Long id, Pageable pageable, Model model) {
        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.findBybookCategoryId(id, pageable), "/book/bookCategory/"+id);
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("newBook", newBook);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        BookCategory bookCategory = bookCategoryRepository.findOne(id);
        model.addAttribute("bookCategory", bookCategory);
        return View("Index");
    }
    @RequestMapping(value = "/book/bookStatus", method = RequestMethod.GET)
    public String bookByBorrowed(@RequestParam("status")String status, Pageable pageable, Model model) {
        Book bookSearchModel = new Book();
        switch (status) {
            case "available":
                bookSearchModel.setState(0);
                break;
            case "borrowed":
                bookSearchModel.setState(1);
                break;
            case "reserved":
                bookSearchModel.setState(2);
                break;
            default: bookSearchModel.setState(-1);
        }

        PageWrapper<Book> page = new PageWrapper<>(bookPageableService.search(bookSearchModel,pageable),"/book/bookStatus/borrowed");
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        model.addAttribute("newBook", newBook);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return View("Index");
    }
}
