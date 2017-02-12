package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface BookCategoryRepository extends CrudRepository<BookCategory, Long> {

}
