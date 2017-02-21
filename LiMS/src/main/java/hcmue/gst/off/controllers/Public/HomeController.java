package hcmue.gst.off.controllers.Public;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.extensions.PageWrapper;
import hcmue.gst.off.extensions.PublicBaseController;
import hcmue.gst.off.extensions.UserBaseController;
import hcmue.gst.off.services.BookPageableService;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class HomeController extends PublicBaseController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookPageableService bookPageableService;

    @RequestMapping(value = "/")
    public String Index(Model model, Pageable pageable) {

        Page<Book> bookPage = bookPageableService.findAll(pageable);
        PageWrapper<Book> page = new PageWrapper<Book>(bookPageableService.findAll(pageable), "/");
        model.addAttribute("books", page.getContent());
        model.addAttribute("page",page);
        return View("Index");
    }
}
