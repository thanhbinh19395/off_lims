package hcmue.gst.off.controllers.User;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.BookPageableService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private BookPageableService bookPageableService;

    @RequestMapping(value = "/")
    public String Index(Model model, Pageable pageable) {

        boolean isAnonymous = true;
        Page<Book> bookPage = bookPageableService.findAll(pageable);
        PageWrapper<Book> page = new PageWrapper<Book>(bookPageableService.findAll(pageable), "/");
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        if (securityService.findLoggedInUsername() == null) {
            model.addAttribute("isAnonymous", isAnonymous);
            return View("Index");
        }

        model.addAttribute("isUser", true);
        return View("Index");
    }
}
