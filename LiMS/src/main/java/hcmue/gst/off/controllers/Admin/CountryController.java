package hcmue.gst.off.controllers.Admin;

import hcmue.gst.off.extensions.AdminBaseController;
import hcmue.gst.off.extensions.BaseController;
import hcmue.gst.off.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@Controller
@RequestMapping("/Admin/Country")
public class CountryController extends AdminBaseController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @RequestMapping("/ListCountry")
    public String ListCountry(Model model) {
        model.addAttribute("listCountry", countryService.findAll());
        return View();
    }

    @RequestMapping("/InsertCountry")
    public String InsertCountry(Model model) {
        return View();
    }

    @RequestMapping("/UpdateCountry/{CountryId}")
    public String UpdateCountry(@PathVariable("CountryId") Long CountryId, Model model) {
        model.addAttribute("country", countryService.findOne(CountryId));
        return View();
    }
}
