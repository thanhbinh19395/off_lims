package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.Country;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.CustomException;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RequestRepository;
import hcmue.gst.off.services.CountryService;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/Request")
public class RequestApiController {
    @Autowired
    private RequestService requestService;

    @RequestMapping("/Save")
    Result Save(@Validated Request model){
        return requestService.save(model);
    }
    @RequestMapping("/Deletes")
    Result Deletes(Long id){
        return requestService.delete(id);
    }
    @RequestMapping("/GetList")
    Result GetList(){
        return requestService.findAll();
    }

}
