package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.extensions.Result;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
public interface RoleService {
    Result<Iterable<Role>> findAll();

    Result<Iterable<Role>> findByNameContaining(String name);

    Result<Role> findOne(Long id);

    Result<Role> save(Role contact);

    Result delete(Long id);
}
