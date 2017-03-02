package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    String searchQuery = "SELECT m FROM Request m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.book_name LIKE %:#{#model.book_name}% OR ISNULL(:#{#model.book_name}) = true) "
            + "AND ( m.author LIKE %:#{#model.author}% OR ISNULL(:#{#model.author}) = true) "
            + "AND ( m.status = :#{#model.status} OR ISNULL(:#{#model.status}) = true) "
            ;
    Iterable<Request> findByStatusContaining(String status);
    @Query(searchQuery)
    Page<Request> search(@Param("model") Request model, Pageable page);
    @Query(searchQuery)
    Iterable<Request> search(@Param("model") Request model);

}
