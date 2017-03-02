package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.BookBorrowCart;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranv on 20/02/2017.
 */
@Controller
public class Book2Controller extends UserBaseController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/Book/ViewDetail/{id}", method = RequestMethod.GET)
    public String ViewBookDetail(@PathVariable("id") String id, Model model, HttpSession session, HttpServletRequest request){
        Book book = bookRepository.findOne(Long.parseLong(id));
        model.addAttribute("Book", book);
        session = request.getSession();
        for (int i = 1; i < 4 ; i++) {
            if (id.equals((Long)session.getAttribute("item"+i))) {
                model.addAttribute("existed", true);
            }
        }
        return View("ViewDetail");
    }

    @RequestMapping(value = "/User/Book/ViewDetail/addToBookCart", method = RequestMethod.POST)
    public String addToBookCart(@RequestParam("ID") Long id, Model model, HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        //Check book exist in book cart
        String item = "item";

        if (session.getAttribute("item1")==null) {

            session.setAttribute("item1", id);
        }
        else if (session.getAttribute("item2")==null) {
            session.setAttribute("item2",id);
        }
        else if (session.getAttribute("item3")==null) {
            session.setAttribute("item3",id);
        }
        return "redirect:/User/Borrow/RegistryBorrowForm";
    }


}
