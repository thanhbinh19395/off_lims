package com.gst.config;

import com.gst.extension.CustomException;
import com.gst.extension.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public Result handleRuntimeException(HttpServletRequest request, CustomException ex){
        return new Result<Object>(ex.getData(),ex.getMessage(),false);
    }
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> handleException(HttpServletRequest request, CustomException ex){
        return new Result<Object>(ex.getData(),ex.getMessage(),false);
    }
}
