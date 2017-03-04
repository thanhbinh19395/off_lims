package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookPayableDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 3/3/2017.
 */
@Repository
public interface BookPayableDetailRepository extends CrudRepository<BookPayableDetail, Long> {

    String searchQuery = "SELECT m FROM BookPayableDetail m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.bookPayableHeaderId = :#{#model.bookPayableHeaderId} OR ISNULL(:#{#model.bookPayableHeaderId}) = true) "
            + "AND ( m.bookId = :#{#model.bookId} OR ISNULL(:#{#model.bookId}) = true)"
            + "AND ( m.isPaid = :#{#model.isPaid} OR ISNULL(:#{#model.isPaid}) = true)";

    @Query(searchQuery)
    Page<BookPayableDetail> search(@Param("model") BookPayableDetail model, Pageable page);

    @Query(searchQuery)
    Iterable<BookPayableDetail> search(@Param("model") BookPayableDetail model);

}
