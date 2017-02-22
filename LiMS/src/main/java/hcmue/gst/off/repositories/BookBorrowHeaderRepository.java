package hcmue.gst.off.repositories;


import hcmue.gst.off.entities.BookBorrowHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/21/2017.
 */
@Repository
public interface BookBorrowHeaderRepository extends CrudRepository<BookBorrowHeader,Long>{
    String searchQuery = "SELECT m FROM  BookBorrowHeader m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.bookTransaction = :#{#model.bookTransaction} OR ISNULL(:#{#model.bookTransaction}) = true) "
            ;
    @Query(searchQuery)
    Page<BookBorrowHeader> search(@Param("model") BookBorrowHeader model, Pageable page);
    @Query(searchQuery)
    Iterable<BookBorrowHeader> search(@Param("model") BookBorrowHeader model);
}
