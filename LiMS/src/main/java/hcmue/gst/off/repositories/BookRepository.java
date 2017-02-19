package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/15/2017.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
}
