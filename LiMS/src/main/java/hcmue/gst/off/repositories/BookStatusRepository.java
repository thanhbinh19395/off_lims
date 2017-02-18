package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface BookStatusRepository extends CrudRepository<BookStatus,Long> {
    List<BookStatus> findByDescription(String description);
    Iterable<BookStatus> findByDescriptionContaining(String description);

}
