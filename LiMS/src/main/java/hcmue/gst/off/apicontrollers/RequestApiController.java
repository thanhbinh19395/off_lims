package hcmue.gst.off.apicontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmue.gst.off.entities.Country;
import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.CustomException;
import hcmue.gst.off.extensions.Mail;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.repositories.RequestRepository;
import hcmue.gst.off.services.CountryService;
import hcmue.gst.off.services.MailService;
import hcmue.gst.off.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@RestController
@RequestMapping("/api/Request")
public class RequestApiController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private MailService mailService;

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
    @RequestMapping("/Approve")
    Result Approve(Mail mail){
        Request request = requestService.findOne(mail.getId()).getData();
        request.setStatus("đã xử lý");
        mailService.sendMail(request.getCreated_by().getEmail(),"Approved Your Request", mail.getMessage());
        return requestService.save(request);
    }
    @RequestMapping("/Reject")
    Result Reject(Mail mail){
        Request request = requestService.findOne(mail.getId()).getData();
        request.setStatus("đã xử lý");
        mailService.sendMail(request.getCreated_by().getEmail(),"Rejected Your Request", mail.getMessage());
        return requestService.save(request);
    }

}
