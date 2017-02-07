package com.gst.config;
import com.google.gson.Gson;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Object parentId = httpServletRequest.getParameterValues("ParentId");
        if(parentId != null) {
            modelAndView.getModelMap().addAttribute("Layout","layoutPopup");
            modelAndView.getModelMap().addAttribute("ParentId",parentId.toString());
        }
        else {
            modelAndView.getModelMap().addAttribute("Layout","layout");
        }
        Gson gson = new Gson();
        String viewbagStr = "<script> var ViewBag = %s;</script>";
        modelAndView.getModelMap().addAttribute("ViewBag", String.format(viewbagStr,gson.toJson(modelAndView.getModelMap())));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
