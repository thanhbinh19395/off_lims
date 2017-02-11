package com.gst.apicontroller;

import com.gst.extension.Result;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
public class BaseApiController {
    public Result Success(Object data, String message){
        return new Result<Object>(data,message,true);
    }
    public Result Success(Object data){
        return new Result<Object>(data,"success",true);
    }
    public Result Fail(Object data, String message){
        return new Result<Object>(data,message,false);
    }
    public Result Fail(String message){
        return new Result<Object>(null,message,false);
    }

}
