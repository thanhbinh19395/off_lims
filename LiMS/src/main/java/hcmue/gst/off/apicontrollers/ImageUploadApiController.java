package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
@RestController
@RequestMapping("/api/ImageUpload")
public class ImageUploadApiController {
    @Autowired
    StorageService storageService;
    @RequestMapping("/Store")
    public Result Save(MultipartFile file){
        return storageService.store(file);
    }

    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        //0. notice, we have used MultipartHttpServletRequest

        //1. get the files from the request object
        Iterator<String> itr =  request.getFileNames();

        MultipartFile mpf = request.getFile(itr.next());
        System.out.println(mpf.getOriginalFilename() +" uploaded!");
        //2. send it back to the client as <img> that calls get method
        //we are using getTimeInMillis to avoid server cached image

        return "<img src='http://localhost:8080/spring-mvc-file-upload/rest/cont/get/"+ Calendar.getInstance().getTimeInMillis()+"' />";

    }

}
