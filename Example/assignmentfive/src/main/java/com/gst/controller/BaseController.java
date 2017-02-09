package com.gst.controller;

/**
 * Created by Thanh Binh on 2/7/2017.
 */
public class BaseController
{
    protected String View(){
     return  View(Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    protected String View(String viewName){
        return this.getClass().getSimpleName() + "/" + viewName;
    }
}
