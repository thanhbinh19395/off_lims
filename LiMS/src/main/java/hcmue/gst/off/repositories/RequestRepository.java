package hcmue.gst.off.repositories;

import hcmue.gst.off.entities.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    Iterable<Request> findByStatusContaining(String status);
}
