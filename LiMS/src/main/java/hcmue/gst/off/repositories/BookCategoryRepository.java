package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.entities.BookCategory;
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
public interface BookCategoryRepository extends CrudRepository<BookCategory,Long> {
    String searchQuery = "SELECT m FROM BookCategory m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.category_name LIKE %:#{#model.category_name}% OR ISNULL(:#{#model.category_name}) = true) "
            ;
    @Query(searchQuery)
    Page<BookCategory> search(@Param("model") BookCategory model, Pageable page);

    @Query(searchQuery)
    Iterable<BookCategory> search(@Param("model") BookCategory model);

    @Query("select b from BookCategory b where b.category_name like %?1%")
    Iterable<BookCategory> findByCategory__nameContaining(String category_name);
}
