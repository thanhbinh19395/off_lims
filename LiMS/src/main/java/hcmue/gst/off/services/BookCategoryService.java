package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.Country;
import hcmue.gst.off.extensions.Result;
import org.springframework.stereotype.Service;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by Lâm Anh Kiệt 15/02/2017
 */

public interface BookCategoryService {
    Result<BookCategory> save(BookCategory bookCategory);
    Result<Iterable<BookCategory>> findAll();
    Result<BookCategory> findOne(Long id);
    Result delete(Long id);
}
