package com.gst.apicontroller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.*;

/**
 * Created by Thanh Binh on 2/8/2017.
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
