package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dylan on 2/15/2017.
 */
public interface BookRepository extends CrudRepository<Book,Long> {
}
