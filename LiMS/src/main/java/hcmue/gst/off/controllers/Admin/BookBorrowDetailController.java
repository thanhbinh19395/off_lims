package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.BookBorrowDetailService;
import hcmue.gst.off.services.BookBorrowHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dylan on 2/23/2017.
 */
@Controller
@RequestMapping("/Admin/BookBorrowDetail")
public class BookBorrowDetailController extends AdminBaseController {
    @Autowired
    private BookBorrowDetailService bookBorrowDetailService;



}
