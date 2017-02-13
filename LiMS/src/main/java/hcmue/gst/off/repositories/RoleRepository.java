package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Iterable<Role> findByNameContaining(String name);
}
