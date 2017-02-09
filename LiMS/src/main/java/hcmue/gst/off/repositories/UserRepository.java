package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
