package hcmue.gst.off.extensions;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.repositories.BookPageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ho Phuong on 14/02/2017.
 */
@Controller
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private BookPageableRepository bookPageableRepository;

    //@RequestMapping(value = "/showBookImage", method = RequestMethod.GET)
    //public void showImage(@RequestParam("bookId") long id, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
       // Book book = bookPageableRepository.findOne(id);
       /// response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
       // response.getOutputStream().write(book.getImage());
       // response.getOutputStream().close();
   // }
}
