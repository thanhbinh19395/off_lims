package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;


/**
 * Created by Ho Phuong on 15/02/2017.
 */
public interface BookPageableService {
    Page<Book> findAll(Pageable pageable);
    Page<Book> search(Book model, Pageable pageable);
    Page<Book> findBybookCategoryId(Long id, Pageable pageable);
    Page<Book> findByDate(Date beginDate, Date endDate, Pageable pageable);
}
