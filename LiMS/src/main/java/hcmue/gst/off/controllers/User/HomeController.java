package hcmue.gst.off.controllers;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.BookService;
import hcmue.gst.off.services.SecurityService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Controller
@RequestMapping("/")
public class HomeController extends UserBaseController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/")
    public String Index(Model model, Pageable pageable) {

        boolean isAnonymous = true;
        if (securityService.findLoggedInUsername() == null) {
            model.addAttribute("isAnonymous", isAnonymous);
            return View("Index");
        }
        PageWrapper<Book> page = new PageWrapper<Book>(bookService.getAll(pageable), "/");
        model.addAttribute("page",page);
        model.addAttribute("isUser", true);
        return View("Index");
    }
}
