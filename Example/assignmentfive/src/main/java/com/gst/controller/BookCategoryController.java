package com.gst.controller;

import com.gst.repository.BookCategoryRepository;
import com.gst.repository.CountryRepository;
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
@RequestMapping("/BookCategory")
public class BookCategoryController extends BaseController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @RequestMapping("/ListBookCategory")
    public String ListCountry(Model model) {
        model.addAttribute("listBookCategory", bookCategoryRepository.findAll());
        return View("ListBookCategory");
    }

    @RequestMapping("/InsertBookCategory")
    public String InsertCountry(Model model) {
        return View("InsertBookCategory");
    }
    @RequestMapping("/UpdateBookCategory/{BookCategoryId}")
    public String UpdateCountry(@PathVariable("BookCategoryId") long BookCategoryId, Model model) {
        model.addAttribute("bookCategory", bookCategoryRepository.findOne(BookCategoryId));
        return View("UpdateBookCategory");
    }

}
