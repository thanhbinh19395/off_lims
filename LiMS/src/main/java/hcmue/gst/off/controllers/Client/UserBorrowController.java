package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BookBorrowCart;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ho Phuong on 25/02/2017.
 */
@Controller
public class UserBorrowController extends UserBaseController{

    private final int MAX_BORROWING_DAYS = 7;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/Borrow/HowToBorrow", method = RequestMethod.GET)
    public String HowToBorrow() {
        return View("HowToBorrow");
    }

    @RequestMapping(value = "/Borrow/OverdueBook", method = RequestMethod.GET)
    public String OverdueBook() {
        return View("OverdueBook");
    }

    @RequestMapping(value ="/User/Borrow/RegistryBorrowForm", method = RequestMethod.GET)
    public String Borrow(Model model, HttpSession session, HttpServletRequest request) {
        User user = securityService.getUser();
        model.addAttribute("user", user);
        Date borrowDate = new Date();
        model.addAttribute("borrowDate", borrowDate);
        Calendar c = Calendar.getInstance();
        c.setTime(borrowDate);
        c.add(Calendar.DATE, MAX_BORROWING_DAYS);
        model.addAttribute("returnDate", borrowDate);
        List<Book> bookList = new ArrayList<>();
        session = request.getSession(false);
        for (int i = 1; i < 4; i++) {
            Long id = (Long)session.getAttribute("item"+i);
            if (id != null) {
                bookList.add(bookRepository.findOne(id));
            }
        }
        model.addAttribute("bookList", bookList);
        if (bookList.size() < 3) {
            model.addAttribute("notEnough", true);
        }
        return View("RegistryBorrowingForm");
    }

    @RequestMapping(value = "/User/Borrow/RegistryBorrowForm/Delete", method = RequestMethod.POST)
    public String delete(@RequestParam("ID") Long id, HttpServletRequest request, HttpSession session, Model model) {
        String sessionItem = "";
        session = request.getSession(false);
        for (int i = 1; i < 4; i++) {
            sessionItem = "item"+i;
            if (id.equals((Long)session.getAttribute(sessionItem))) {
                session.removeAttribute(sessionItem);
            }
        }
        return "redirect:/User/Borrow/RegistryBorrowForm";
    }
}
