package hcmue.gst.off.apicontrollers;

import com.sun.net.httpserver.Authenticator;
import hcmue.gst.off.entities.Role;
import hcmue.gst.off.extensions.Result;
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
    private RoleService roleService;

    @RequestMapping("/Save")
    Result<Role> Save(Role model){
        return roleService.save(model);
    }
    @RequestMapping("/Deletes")
    Result Deletes(long id){
        return roleService.delete(id);
    }
    @RequestMapping("/GetList")
    Result<Iterable<Role>> GetList(){
        return roleService.findAll();
    }
    @RequestMapping("/FindByNameContaining")
    Result<Iterable<Role>> FindByNameContaining(String name){
        return roleService.findByNameContaining(name);
    }

    @RequestMapping("/Search")
    Result<Iterable<Role>> Search(Role model){
        return roleService.search(model);
    }
}
