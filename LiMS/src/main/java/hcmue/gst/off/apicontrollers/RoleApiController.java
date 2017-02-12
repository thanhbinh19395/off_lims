package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.repositories.RoleRepository;
import hcmue.gst.off.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/Role")
public class RoleApiController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/Save")
    Role Save(Role model){
        return roleService.save(model);
    }
    @RequestMapping("/Deletes")
    void Deletes(long id){
        roleRepository.delete(id);
    }
    @RequestMapping("/GetList")
    Iterable<Role> GetList(){
        return roleRepository.findAll();
    }
}
