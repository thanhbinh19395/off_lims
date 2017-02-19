package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by WIN8.1 on 09/02/2017.
 */
public interface UserService {
    Result<Iterable<User>> findAll();
    Result<User> findOne(Long id);
    Result<User> save(User model);
    Result delete(Long id);
    User findByUsername(String username);
    PageableResult<User> search(User model, Pageable p);
    Result<Iterable<User>> search(User model);

}
