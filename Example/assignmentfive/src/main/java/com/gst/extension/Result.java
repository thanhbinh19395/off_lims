package com.gst.extension;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
public class Result<T> {
    private T Data;
    private String Message;
    private boolean IsSuccess;

    public Result(T data, String message, boolean isSuccess) {
        Data = data;
        Message = message;
        IsSuccess = isSuccess;
    }
    public Result(T data, boolean isSuccess) {
        Data = data;
        Message = isSuccess == true ?  "success" : "fail";
        IsSuccess = isSuccess;
    }
    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isSuccess() {
        return IsSuccess;
    }

    public void setSuccess(boolean success) {
        IsSuccess = success;
    }
}

