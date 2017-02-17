package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by Ho Phuong on 15/02/2017.
 */
public interface BookService {
    Page<Book> findAll(Pageable pageable);

}
