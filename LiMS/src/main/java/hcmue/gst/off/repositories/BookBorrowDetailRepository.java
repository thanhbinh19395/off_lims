package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookBorrowDetail;
import hcmue.gst.off.entities.BookBorrowHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/23/2017.
 */
@Repository
public interface BookBorrowDetailRepository extends CrudRepository<BookBorrowDetail,Long> {
    String searchQuery = "SELECT m FROM BookBorrowDetail m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.note LIKE %:#{#model.note}% OR ISNULL(:#{#model.note}) = true) "
            + "AND ( m.bookBorrowHeaderId = :#{#model.bookBorrowHeaderId} OR ISNULL(:#{#model.bookBorrowHeaderId}) = true) "
            + "AND ( m.bookId = :#{#model.bookId} OR ISNULL(:#{#model.bookId}) = true) "
            ;
    @Query(searchQuery)
    Page<BookBorrowDetail> search(@Param("model") BookBorrowDetail model, Pageable page);
    @Query(searchQuery)
    Iterable<BookBorrowDetail> search(@Param("model") BookBorrowDetail model);
}
