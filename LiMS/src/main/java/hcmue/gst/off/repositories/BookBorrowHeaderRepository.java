package hcmue.gst.off.repositories;


import hcmue.gst.off.entities.BookBorrowHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by dylan on 2/21/2017.
 */
@Repository
public interface BookBorrowHeaderRepository extends CrudRepository<BookBorrowHeader, Long> {
    String searchQuery = "SELECT m FROM  BookBorrowHeader m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.status = :#{#model.status} OR ISNULL(:#{#model.status}) = true) "
            + "AND ( m.userId = :#{#model.userId} OR ISNULL(:#{#model.userId}) = true) "
            ;
    String searchOverdueQuery = searchQuery
            + "AND m.returnDate < CURRENT_DATE "
            + "AND m.status = 1 "
            ;

    @Query(searchQuery)
    Page<BookBorrowHeader> search(@Param("model") BookBorrowHeader model, Pageable page);

    @Query(searchQuery)
    Iterable<BookBorrowHeader> search(@Param("model") BookBorrowHeader model);

    @Query(searchOverdueQuery)
    Page<BookBorrowHeader> searchOverdue(@Param("model") BookBorrowHeader model, Pageable page);

    @Query(searchOverdueQuery)
    Iterable<BookBorrowHeader> searchOverdue(@Param("model") BookBorrowHeader model);

}
