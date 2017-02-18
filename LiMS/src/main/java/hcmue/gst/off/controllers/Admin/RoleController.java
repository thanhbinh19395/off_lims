package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.entities.Role;
import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.extensions.Result;
import hcmue.gst.off.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@Controller
@RequestMapping("/Admin/Role")
public class RoleController extends AdminBaseController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @RequestMapping("/ListRole")
    public String ListRole(Model model, Role role) {
        getViewBag(model).put("listRole",roleService.search(role));
        return View();
    }

    @RequestMapping("/InsertRole")
    public String InsertRole(Model model) {
        return View();
    }
    @RequestMapping("/UpdateRole/{RoleId}")
    public String UpdateRole(@PathVariable("RoleId") long RoleId, Model model) {
        getViewBag(model).put("role",roleService.findOne(RoleId));
        return View();
    }
}