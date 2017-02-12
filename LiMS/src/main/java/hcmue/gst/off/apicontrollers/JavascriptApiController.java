package hcmue.gst.off.apicontrollers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@RestController
@RequestMapping("/api/Javascript")
public class JavascriptApiController {
    @RequestMapping("/GetJS")
    public void GetJS(String id , HttpSession session, HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource(session.getAttribute(id).toString());
        session.removeAttribute(id);
        response.setContentType("application/javascript");
        IOUtils.copy(resource.getInputStream(), response.getOutputStream());
        response.flushBuffer();
    }
}
