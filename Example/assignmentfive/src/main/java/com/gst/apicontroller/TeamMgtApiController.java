package com.gst.apicontroller;

import com.gst.domain.Team;
import com.gst.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Thanh Binh on 1/27/2017.
 */

@RestController
public class TeamMgtApiController {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamMgtApiController(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }

    @RequestMapping("/api/TeamMgt/GetList")
    Collection<Team> getList(){
        return (Collection<Team>) teamRepository.findAll();
    }
    @RequestMapping("/api/TeamMgt/GetById")
    Team getById(int id){
        return teamRepository.findOne(id);
    }
    @RequestMapping("/api/TeamMgt/Save")
    Team save(Team model){
        return teamRepository.save(model);
    }
    @RequestMapping("/api/TeamMgt/DeleteById")
    void deleteById(int id){
         teamRepository.delete(id);
    }
    @RequestMapping("/api/TeamMgt/GetListByName")
    Collection<Team> getListByName(String name){
        return teamRepository.findByNameContaining(name);
    }
}
