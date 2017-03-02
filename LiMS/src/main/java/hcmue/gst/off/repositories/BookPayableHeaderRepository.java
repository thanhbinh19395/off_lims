package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookPayableHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/24/2017.
 */
@Repository
public interface BookPayableHeaderRepository extends CrudRepository<BookPayableHeader,Long> {
    String searchQuery = "SELECT m FROM  BookPayableHeader m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.overDue = :#{#model.overDue} OR ISNULL(:#{#model.overDue}) = true) "
            + "AND ( m.bookBorrowId = :#{#model.bookBorrowId} OR ISNULL(:#{#model.bookBorrowId}) = true) "
            + "AND ( m.actualReturnDate = :#{#model.actualReturnDate} OR ISNULL(:#{#model.actualReturnDate}) = true) "
            + "AND ( m.status = :#{#model.status} OR ISNULL(:#{#model.status}) = true) "
            ;
    @Query(searchQuery)
    Page<BookPayableHeader> search(@Param("model") BookPayableHeader model, Pageable page);
    @Query(searchQuery)
    Iterable<BookPayableHeader> search(@Param("model") BookPayableHeader model);
}
