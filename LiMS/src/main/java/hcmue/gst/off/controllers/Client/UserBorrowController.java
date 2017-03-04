package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BookBorrowCart;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookRepository;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ho Phuong on 25/02/2017.
 */
@Controller
public class UserBorrowController extends UserBaseController{

    private final int MAX_BORROWING_DAYS = 7;
    private final int MAX_BORROWING_BOOK = 3;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookBorrowHeaderService bookBorrowHeaderService;

    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date borrowDate = new Date();

        model.addAttribute("borrowDate", sdf.format(borrowDate));
        Calendar c = Calendar.getInstance();
        c.setTime(borrowDate);
        c.add(Calendar.DATE, MAX_BORROWING_DAYS);
        model.addAttribute("returnDate", sdf.format(c.getTime()));
        List<Book> bookList = new ArrayList<>();
        session = request.getSession(false);
        for (int i = 1; i <= MAX_BORROWING_BOOK; i++) {
            Long id = (Long)session.getAttribute("item"+i);
            if (id != null) {
                bookList.add(bookRepository.findOne(id));
            }
        }
        model.addAttribute("bookList", bookList);
        if (bookList.size() < MAX_BORROWING_BOOK) {
            model.addAttribute("notEnough", true);
        }
        return View("RegistryBorrowingForm");
    }

    @RequestMapping(value = "/User/Borrow/RegistryBorrowForm/Delete", method = RequestMethod.POST)
    public String delete(@RequestParam("ID") Long id, HttpServletRequest request, HttpSession session, Model model) {
        session = request.getSession(false);
        for (int i = 1; i <= MAX_BORROWING_BOOK; i++) {
            Long currId = (Long)session.getAttribute("item"+i);
            if (currId != null && id.compareTo((Long)session.getAttribute("item"+i))==0) {
                session.removeAttribute("item"+i);
            }
        }
        return "redirect:/User/Borrow/RegistryBorrowForm";
    }

    @RequestMapping(value = "/User/Borrow/RegistryBorrowForm/handleSubmit", method = RequestMethod.POST)
    public String handleBorrow(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest request,
                               @RequestParam("returnDate") String returnDate) {
        User currUser = securityService.getUser();
        currUser.setName(user.getName());
        currUser.setBirthday(user.getBirthday());
        currUser.setEmail(user.getEmail());
        currUser.setAddress(user.getAddress());
        currUser.setPhone(user.getPhone());
        currUser.setIdcard(user.getIdcard());
        userService.save(currUser);
        //Lay thong tin cua cac sach co trong gio
        List<Book> bookList = new ArrayList<>();
        session = request.getSession(false);
        for (int i = 1; i < MAX_BORROWING_BOOK; i++) {
            Long id = (Long)session.getAttribute("item"+i);
            if (id != null) {
                bookList.add(bookRepository.findOne(id));
            }
        }
        //Luu thong tin sach vao entity
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Set<BookBorrowDetail> bookBorrowDetails = new HashSet<>();
        try {
            Date dateConverted = sdf.parse(returnDate);
            BookBorrowHeader bookBorrowHeader = new BookBorrowHeader(dateConverted, user.getId());
            bookBorrowHeaderService.save(bookBorrowHeader);

            for (int i = 0; i < bookList.size(); i++) {
                BookBorrowDetail bookBorrowDetail = new BookBorrowDetail(bookBorrowHeader.getId(), bookList.get(i).getId());
                bookBorrowDetailService.save(bookBorrowDetail);
                bookBorrowDetails.add(bookBorrowDetail);
            }
            bookBorrowHeader.setBookBorrowDetails(bookBorrowDetails);
            bookBorrowHeaderService.save(bookBorrowHeader);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Xoa session sau khi gui
        for (int i = 1; i <= MAX_BORROWING_BOOK; i++) {
            Long id = (Long)session.getAttribute("item"+i);
            if (id!=null) session.removeAttribute("item"+i);
        }
        //Thay doi trang thai cua sach
        return "redirect:/User/Borrow/RegistryBorrowForm";
    }
}
