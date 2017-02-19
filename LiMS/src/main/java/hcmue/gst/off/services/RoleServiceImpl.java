package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.BaseCommand;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.*;
import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Service
public class RoleServiceImpl extends BaseCommand implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Override
    public Result<Iterable<Role>> findAll() {
        return Success(roleRepository.findAll());
    }

    @Override
    public Result<Iterable<Role>> findByNameContaining(String name) {
        return Success(roleRepository.findByNameContaining(name));
    }

    @Override
    public Result<Role> findOne(Long id) {
        return Success(roleRepository.findOne(id));
    }

    @Override
    public Result<Role> save(Role role) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (role.getId() == null) {
            role.setCreated_by(user);
            role.setCreated_date(new Date());
        }
        else {
            role.setUpdate_date(new Date());
            role.setUpdate_by(user);
        }
        return Success(roleRepository.save(role));
    }

    @Override
    public Result<Iterable<Role>> search(Role role) {
        return Success(roleRepository.search(role));
    }

    @Override
    public Result delete(Long id) {
        roleRepository.delete(id);
        return Success();
    }
}
