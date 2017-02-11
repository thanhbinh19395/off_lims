package com.gst.apicontroller;

import com.gst.domain.Country;
import com.gst.extension.CustomException;
import com.gst.extension.Result;
import com.gst.repository.CountryRepository;
import com.gst.services.CountryService;
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
public class CountryApiController extends BaseApiController {
    @Autowired
    private CountryService countryService;


    @RequestMapping("/Save")
    Result Save(Country model){
        return countryService.save(model);
    }
    @RequestMapping("/Deletes")
    Result Deletes(int id){
        return countryService.delete(id);
    }
    @RequestMapping("/GetList")
    Result GetList(){
        return countryService.findAll();
    }

    @RequestMapping("/test")
    Result test(int id){

        if(id == 1)
            return Fail("fail rồi nha");

        return Success(countryService.findAll(),"hello");
    }
}
