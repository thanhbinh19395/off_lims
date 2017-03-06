package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookPayableHeader;
import hcmue.gst.off.entities.BookReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/27/2017.
 */
@Repository
public interface BookReservationRepository extends CrudRepository<BookReservation,Long>
{ String searchQuery = "SELECT m FROM  BookReservation m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
        + "AND ( m.bookId = :#{#model.bookId} OR ISNULL(:#{#model.bookId}) = true) "
        + "AND ( m.pickUpDate = :#{#model.pickUpDate} OR ISNULL(:#{#model.pickUpDate}) = true) "
        + "AND ( m.created_by.id = :#{#model.created_by.id} OR ISNULL(:#{#model.created_by.id}) = true)"
        + "AND ( m.status = :#{#model.status} OR ISNULL(:#{#model.status}) = true) "
        ;
    @Query(searchQuery)
    Page<BookReservation> search(@Param("model") BookReservation model, Pageable page);
    @Query(searchQuery)
    Iterable<BookReservation> search(@Param("model") BookReservation model);
}
