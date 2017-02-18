package hcmue.gst.off.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class AdminHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String parentId = httpServletRequest.getParameter("ParentId");
        Map<String, Object> viewBagData =  new HashMap<String, Object>();
        if(modelAndView.getModel().get("ViewBagData") == null){
            modelAndView.getModelMap().addAttribute("ViewBagData",viewBagData);
        }
        else{
            viewBagData = (Map<String, Object>) modelAndView.getModel().get("ViewBagData");
        }
        if( parentId != null && !StringUtils.isEmptyOrWhitespace(parentId)) {
            modelAndView.getModelMap().addAttribute("Layout","/Shared/Admin/layoutPopup");
            viewBagData.put("ParentId",parentId);
        }
        else {
            modelAndView.getModelMap().addAttribute("Layout","/Shared/Admin/layout");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //date format
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(df);
        String viewbagStr = "<script> var ViewBag = %s;</script>";
        modelAndView.getModelMap().addAttribute("ViewBag", String.format(viewbagStr,mapper.writeValueAsString(modelAndView.getModelMap().get("ViewBagData"))));
        String uuid = UUID.randomUUID().toString();
        httpServletRequest.getSession().setAttribute(uuid,"/templates" + modelAndView.getViewName()+".js");
        modelAndView.getModelMap().addAttribute("PageModule",uuid);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
