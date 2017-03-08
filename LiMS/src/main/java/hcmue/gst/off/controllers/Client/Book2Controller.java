package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.business.FindBookBorrowBusiness;
import hcmue.gst.off.entities.*;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.model.BookBorrowItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tranv on 20/02/2017.
 */
@Controller
public class Book2Controller extends UserBaseController {

    private final Integer MAX_BORROWING_BOOK = 3;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private FindBookBorrowBusiness findBookBorrowBusiness;

    @RequestMapping(value = "/Book/ViewDetail/{id}", method = RequestMethod.GET)
    public String ViewBookDetail(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletRequest request){
        Book book = bookRepository.findOne(id);
        model.addAttribute("Book", book);
        //Check item already in book cart

        session = request.getSession(false);
        if (session != null) {
            for (int i = 1; i < 4; i++) {
                Long compareId = (Long) session.getAttribute("item" + i);
                if (compareId != null && compareId.compareTo(id) == 0) {
                    model.addAttribute("existed", true);
                    break;
                }
            }
        }
        //Check user able to borrow book
        if (securityService.getUser() != null) {
            model.addAttribute("user", securityService.getUser());
            if (!securityService.getUser().getBorrowable()) {
                model.addAttribute("forbiddenUser", true);
            }
        }
        if (book.getState() == BookTransactionStep.BORROWED || book.getState() == BookTransactionStep.RESERVATED) {
            findBookBorrowBusiness.ExecuteWithBookId(id);
            BookBorrowItem item = findBookBorrowBusiness.getBookBorrowItem();
            Date returnDate = item.getHeader().getReturnDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("returnDate", sdf.format(returnDate));
            model.addAttribute("reservationBook", new BookReservation());
            model.addAttribute("bookBorrowHeader", item.getHeader());
            if (book.getState() == BookTransactionStep.RESERVATED) {
                Calendar c = Calendar.getInstance();
                c.setTime(returnDate);
                c.add(Calendar.DATE, 1);
                model.addAttribute("returnDate", sdf.format(returnDate));
            }
        }

        return View("ViewDetail");
    }

    @RequestMapping(value = "/User/Book/ViewDetail/addToBookCart", method = RequestMethod.POST)
    public String addToBookCart(@RequestParam("ID") Long id, Model model, HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        //Check book exist in book cart
        for (int i = 1; i <= MAX_BORROWING_BOOK; i++ ) {
            if (session.getAttribute("item"+i)==null) {
                session.setAttribute("item"+i,id);
                break;
            }
        }
        return "redirect:/User/Borrow/RegistryBorrowForm";
    }


}
