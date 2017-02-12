package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import org.springframework.stereotype.Service;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Service
public interface BookCategoryService {
    BookCategory save(BookCategory bookCategory);
}
