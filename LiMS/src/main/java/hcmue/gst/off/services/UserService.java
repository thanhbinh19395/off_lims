package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.Result;

/**
 * Created by WIN8.1 on 09/02/2017.
 */
public interface UserService {
    Result<Iterable<User>> findAll();
    Result<User> findOne(Long id);
    Result<User> save(User model);
    Result delete(Long id);
    User findByUsername(String username);
    Result<Iterable<User>> search(User model);
}
