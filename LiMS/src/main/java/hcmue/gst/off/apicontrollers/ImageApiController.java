package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
@RestController
@RequestMapping("/api/Image")
public class ImageApiController {
    @Autowired
    StorageService storageService;

    @RequestMapping("/Store")
    public Result Save(MultipartFile image){
        MultipartFile f = new MultipartFile() {
            @Override
            public String getName() {
                return image.getName();
            }

            @Override
            public String getOriginalFilename() {
                String name = UUID.randomUUID().toString();
                String ext = image.getOriginalFilename().split("\\.")[1];
                return name + "." + ext;
            }

            @Override
            public String getContentType() {
                return image.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return image.isEmpty();
            }

            @Override
            public long getSize() {
                return image.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return image.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return image.getInputStream();
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {
                image.transferTo(file);
            }
        };
        return storageService.store(f);
    }
    @RequestMapping("/Get/{filename:.+}")
    public ResponseEntity<Resource> Get(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\""+file.getFilename()+"\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(file);
    }

}