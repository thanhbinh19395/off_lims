package com.gst.config;
import com.google.gson.Gson;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Thanh Binh on 2/7/2017.
 */
public class CustomHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String parentId = httpServletRequest.getParameter("ParentId");
        if( parentId != null && !StringUtils.isEmptyOrWhitespace(parentId)) {
            modelAndView.getModelMap().addAttribute("Layout","layoutPopup");
            modelAndView.getModelMap().addAttribute("ParentId",parentId);
        }
        else {
            modelAndView.getModelMap().addAttribute("Layout","layout");
        }

        Gson gson = new Gson();
        String viewbagStr = "<script> var ViewBag = %s;</script>";
        modelAndView.getModelMap().addAttribute("ViewBag", String.format(viewbagStr,gson.toJson(modelAndView.getModelMap())));
        String uuid = UUID.randomUUID().toString();
        httpServletRequest.getSession().setAttribute(uuid,"/templates/" + modelAndView.getViewName()+".js");
        modelAndView.getModelMap().addAttribute("PageModule",uuid);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
