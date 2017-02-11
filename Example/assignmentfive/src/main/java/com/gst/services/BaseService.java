package com.gst.services;

import com.gst.extension.Result;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
public class BaseService {
    public <T> Result<T> Success(T data, String message)
    {
        return new Result<T>(data,message,true);
    }
    public <T> Result<T> Success(T data)
    {
        return new Result<T>(data,"success",true);
    }
    public <T> Result<T> Success()
    {
        return new Result<T>(null,"success",true);
    }
    public <T> Result<T> Fail(String message, T data)
    {
        return new Result<T>(data,message,false);
    }
    public <T> Result<T> Fail(String message)
    {
        return new Result<T>(null,message,false);
    }
    public <T> Result<T> Fail()
    {
        return new Result<T>(null,"fail",false);
    }
}
