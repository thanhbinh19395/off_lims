package hcmue.gst.off.extensions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class AdminBaseController extends BaseController {

    @Autowired
    private HttpServletRequest request;


    protected final Map<String,Object> getViewBag(Model model){
        if(model.asMap().get("ViewBagData") == null){
            Map<String, Object> viewBagData = new HashMap<String, Object>();
            model.addAttribute("ViewBagData",viewBagData);
        }
        return (Map<String, Object>) model.asMap().get("ViewBagData");
    }

    @Override
    protected String View(String viewName) {
        return "/Admin/" + super.View(viewName);
    }
}
