package com.gst.controller;

import com.gst.repository.RoleRepository;
import com.gst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/ListUser")
    public String ListCountry(Model model) {
        model.addAttribute("listUser", userRepository.findAll());
        return View("ListUser");
    }

    @RequestMapping("/InsertUser")
    public String InsertCountry(Model model) {
        return View("InsertUser");
    }
    @RequestMapping("/UpdateUser/{UserId}")
    public String UpdateCountry(@PathVariable("UserId") long UserId, Model model) {
        model.addAttribute("user", userRepository.findOne(UserId));
        return View("UpdateUser");
    }
}
