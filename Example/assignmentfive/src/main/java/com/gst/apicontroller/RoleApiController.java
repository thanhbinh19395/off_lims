package com.gst.apicontroller;

import com.gst.domain.BookCategory;
import com.gst.domain.Role;
import com.gst.repository.BookCategoryRepository;
import com.gst.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/Role")
public class RoleApiController {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleApiController(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @RequestMapping("/Save")
    Role Save(Role model){
        return roleRepository.save(model);
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
