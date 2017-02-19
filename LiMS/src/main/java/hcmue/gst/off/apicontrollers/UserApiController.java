package hcmue.gst.off.apicontrollers;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.PageableResult;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @RequestMapping("/Search")
    Result<Iterable<User>> GetList(User model){
        return userService.search(model);
    }

    @RequestMapping("/PageableSearch")
    PageableResult<User> Search(User model, Pageable p){
        return userService.search(model,p);
    }

}
