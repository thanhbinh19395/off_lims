package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dylan on 2/15/2017.
 */
@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    String searchQuery = "SELECT m FROM Book m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
    //String searchQuery = "SELECT m FROM Book m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.name LIKE %:#{#model.name}% OR ISNULL(:#{#model.name}) = true) "
            + "AND ( m.publish_year = :#{#model.publish_year} OR ISNULL(:#{#model.publish_year}) = true) "
            + "AND ( m.author LIKE %:#{#model.author}% OR ISNULL(:#{#model.author}) = true) "
            + "AND ( m.bookCategoryId = :#{#model.bookCategoryId} OR ISNULL(:#{#model.bookCategoryId}) = true) "
            + "AND ( m.bookStatusId = :#{#model.bookStatusId} OR ISNULL(:#{#model.bookStatusId}) = true) "
            ;
    @Query(searchQuery)
    Page<Book> search(@Param("model") Book model, Pageable page);
    @Query(searchQuery)
    Iterable<Book> search(@Param("model") Book model);
    Book findByBookCode(String bookcode);

}
