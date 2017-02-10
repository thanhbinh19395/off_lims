package com.gst.controller;

import com.gst.repository.BookCategoryRepository;
import com.gst.repository.RoleRepository;
import com.gst.services.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Controller
@RequestMapping("/Role")
public class RoleController extends BaseController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping("/ListRole")
    public String ListCountry(Model model) {
        model.addAttribute("listRole", roleRepository.findAll());
        return View("ListRole");
    }

    @RequestMapping("/InsertRole")
    public String InsertCountry(Model model) {
        return View("InsertRole");
    }
    @RequestMapping("/UpdateRole/{RoleId}")
    public String UpdateCountry(@PathVariable("RoleId") long RoleId, Model model) {
        model.addAttribute("role", roleRepository.findOne(RoleId));
        return View("UpdateRole");
    }
}
