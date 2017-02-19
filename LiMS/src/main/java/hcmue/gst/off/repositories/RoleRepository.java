package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    String searchQuery = "SELECT m FROM Role m WHERE ( m.id = :#{#model.id} OR ISNULL(:#{#model.id}) = true) "
            + "AND ( m.name LIKE %:#{#model.name}% OR ISNULL(:#{#model.name}) = true) "
            ;
    Iterable<Role> findByNameContaining(String name);

    @Query(searchQuery)
    Iterable<Role> search(@Param("model") Role model);
}
