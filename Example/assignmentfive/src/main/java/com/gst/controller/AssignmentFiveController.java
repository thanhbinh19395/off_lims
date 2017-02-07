package com.gst.controller;

import com.google.gson.Gson;
import com.gst.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thanh Binh on 1/27/2017.
 */

@Controller
public class AssignmentFiveController extends BaseController{
    private final TeamRepository teamRepository;

    @Autowired
    public AssignmentFiveController(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }
    @RequestMapping("/teammgt")
    public String TeamMgt(Model model) {
        model.addAttribute("hello", teamRepository.findAll());
        model.addAttribute("phuonggay", teamRepository.findAll());
        model.addAttribute("thinhgay", teamRepository.findAll());
        return View("hello");
    }
}
