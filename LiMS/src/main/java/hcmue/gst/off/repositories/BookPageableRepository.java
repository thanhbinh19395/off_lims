package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Ho Phuong on 14/02/2017.
 */
@Repository
public interface BookPageableRepository extends CrudRepository<Book,Long> {
    String stringQuery = "select m from Book m where m.created_date between :beginDate  and :endDate";

    @Query(stringQuery)
    Page<Book> findByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, Pageable pageable);



    Page<Book> findAll(Pageable pageable);
    Page<Book> findBybookCategoryId(Long id, Pageable pageable);
}
