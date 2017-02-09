package com.gst.apicontroller;

import com.gst.domain.Country;
import com.gst.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Thanh Binh on 2/8/2017.
 */

@RestController()
@RequestMapping("/api/Country")
public class CountryApiController {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryApiController(CountryRepository countryRepository)
    {
        this.countryRepository = countryRepository;
    }

    @RequestMapping("/Save")
    Country Save(Country model){
        return countryRepository.save(model);
    }
    @RequestMapping("/Deletes")
    void Deletes(int id){
        countryRepository.delete(id);
    }
    @RequestMapping("/GetList")
    Iterable<Country> GetList(){
        return countryRepository.findAll();
    }
}
