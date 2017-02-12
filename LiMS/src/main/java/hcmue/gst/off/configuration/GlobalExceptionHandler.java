package hcmue.gst.off.configuration;

import hcmue.gst.off.extensions.CustomException;
import hcmue.gst.off.extensions.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public Result handleCustomRuntimeException(HttpServletRequest request, CustomException ex){
        return new Result<Object>(ex.getData(),ex.getMessage(),false);
    }

    //all
    @ExceptionHandler(Exception.class)
    public Result<Object> handleAllException(HttpServletRequest request, Exception ex){
        return new Result<Object>(null,ex.getClass().getSimpleName(),false);
    }

    //rest bind
    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(HttpServletRequest request, BindException ex){
        return new Result<Object>(ex.getBindingResult().getAllErrors(),ex.getBindingResult().getFieldError().getField() + " " + ex.getBindingResult().getFieldError().getDefaultMessage(),false);
    }

    //service
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(HttpServletRequest request, BindException ex){
        return new Result<Object>(ex.getBindingResult().getAllErrors(),ex.getBindingResult().getFieldError().getField() + " " + ex.getBindingResult().getFieldError().getDefaultMessage(),false);
    }

}
