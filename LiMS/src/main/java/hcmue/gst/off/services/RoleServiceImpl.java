package hcmue.gst.off.services;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.entities.User;
import hcmue.gst.off.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Override
    public Role save(Role role) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        if (role.getId() == 0) {
            role.setCreated_by(user);
            role.setCreated_date(new Date());
        }
        else {
            role.setUpdate_date(new Date());
            role.setUpdate_by(user);
        }
        return roleRepository.save(role);
    }
}
