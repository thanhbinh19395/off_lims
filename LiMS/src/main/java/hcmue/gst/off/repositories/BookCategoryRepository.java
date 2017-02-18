package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.BookCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface BookCategoryRepository extends CrudRepository<BookCategory,Long> {
    @Query("select b from BookCategory b where b.category_name like %?1%")
    Iterable<BookCategory> findByCategory__nameContaining(String category_name);
}
