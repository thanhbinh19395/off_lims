package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Country;
import hcmue.gst.off.extensions.CustomException;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@RestController
@RequestMapping("/api/Country")
public class CountryApiController {
    @Autowired
    private CountryService countryService;

    @RequestMapping("/Save")
    Result Save(@Validated Country model){
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
        if(id == 1) {
            throw new CustomException("Lỗi : loi");
        }

        return countryService.findAll();
    }
}

