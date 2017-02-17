package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thanh Binh on 2/13/2017.
 */
@RestController
@RequestMapping("/api/User")
public class UserApiController {
    @Autowired
    private UserService userService;

    @RequestMapping("/Save")
    Result<User> Save(User model){
        return userService.save(model);
    }
    @RequestMapping("/Deletes")
    Result Deletes(long id){
        return userService.delete(id);
    }
    @RequestMapping("/GetList")
    Result<Iterable<User>> GetList(){
        return userService.findAll();
    }
}
