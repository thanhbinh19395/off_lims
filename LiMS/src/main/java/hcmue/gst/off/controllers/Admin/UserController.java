package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.services.RoleService;
import hcmue.gst.off.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thanh Binh on 2/13/2017.
 */
@Controller
@RequestMapping("/Admin/User")
public class UserController extends AdminBaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/ListUser")
    public String ListUser(Model model, User data, Pageable page) {
        getViewBag(model).put("listUser",userService.search(data,page));
        return View();
    }

    @RequestMapping("/InsertUser")
    public String InsertUser(Model model) {
        return View();
    }
    @RequestMapping("/UpdateUser/{UserId}")
    public String UpdateUser(@PathVariable("UserId") long UserId, Model model) {
        getViewBag(model).put("user",userService.findOne(UserId));
        return View();
    }
}
