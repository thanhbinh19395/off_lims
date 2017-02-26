package hcmue.gst.off.controllers.Client;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tranv on 20/02/2017.
 */
@Controller
@RequestMapping("/Book")
public class Book2Controller extends UserBaseController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/ViewDetail/{id}", method = RequestMethod.GET)
    public String ViewBookDetail(@PathVariable("id") String id, Model model){
        Book book = bookRepository.findOne(Long.parseLong(id));
        if(book != null) {
            model.addAttribute("Book", book);
            return View("ViewDetail");
        }
        else {
            return "redirect:/";
        }
    }


}
