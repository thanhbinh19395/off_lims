package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface BookStatusRepository extends CrudRepository<BookStatus,Long> {

    String searchQuery = "SELECT m FROM BookStatus m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.description LIKE %:#{#model.description}% OR ISNULL(:#{#model.description}) = true) "
            ;

    @Query(searchQuery)
    Page<BookStatus> search(@Param("model") BookStatus model, Pageable page);

    @Query(searchQuery)
    Iterable<BookStatus> search(@Param("model") BookStatus model);
    Iterable<BookStatus> findByDescriptionContaining(String description);

}
