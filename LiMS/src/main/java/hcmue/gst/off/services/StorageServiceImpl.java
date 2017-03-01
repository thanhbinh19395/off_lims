package hcmue.gst.off.services;

/**
 * Created by Thanh Binh on 2/25/2017.
 */
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl extends BaseCommand implements StorageService {

    @Autowired
    private HttpServletRequest request;
    private final Path rootLocation = Paths.get("src/main/resources/static/uploadedImages");

    @Override
    public Result store(MultipartFile file) {
        try {
            //Files.copy(file.getInputStream(), Paths.get(request.getServletContext().getRealPath("")).resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            return Success(file.getOriginalFilename());
        } catch (IOException e) {
            return Fail(e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {

    }
}
