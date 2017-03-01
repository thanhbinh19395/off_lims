package hcmue.gst.off.services;

import hcmue.gst.off.extensions.Result;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
public interface StorageService {


    Result store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
