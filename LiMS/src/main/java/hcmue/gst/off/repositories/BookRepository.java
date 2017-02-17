package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ho Phuong on 14/02/2017.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    Page<Book> findAll(Pageable pageable);
}
