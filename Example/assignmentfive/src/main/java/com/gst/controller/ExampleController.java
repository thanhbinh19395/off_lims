package com.gst.controller;

import com.gst.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thanh Binh on 2/8/2017.
 */
@Controller
@RequestMapping("/Example")
public class ExampleController extends BaseController {
    private final CountryRepository countryRepository;

    @Autowired
    public ExampleController(CountryRepository countryRepository)
    {
        this.countryRepository = countryRepository;
    }

    @RequestMapping("/ListCountry")
    public String ListCountry(Model model) {
        model.addAttribute("listCountry", countryRepository.findAll());
        return View();
    }

    @RequestMapping("/InsertCountry")
    public String InsertCountry(Model model) {
        return View();
    }

    @RequestMapping("/UpdateCountry/{CountryId}")
    public String UpdateCountry(@PathVariable("CountryId") int CountryId, Model model) {
        model.addAttribute("country", countryRepository.findOne(CountryId));
        return View();
    }
}
