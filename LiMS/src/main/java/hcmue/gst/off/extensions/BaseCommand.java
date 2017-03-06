package hcmue.gst.off.extensions;

import hcmue.gst.off.entities.User;
import hcmue.gst.off.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public abstract class BaseCommand {
    protected final int PAGESIZE = 20;
    @Autowired
    protected SecurityService securityService;

    protected final <T> Result<T> Success(T data, String message) {
        return new Result<T>(data,message,true);
    }
    protected final <T> Result<T> Success(T data) {
        return this.Success(data,"success");
    }
    protected final <T> Result<T> Success(String message) {
        return new Result<T>(null,message,true);
    }
    protected final <T> Result<T> Success() {
        return new Result<T>(null,"success",true);
    }


    protected final <T> Result<T> Fail(String message, T data) {
        return new Result<T>(data,message,false);
    }
    protected final <T> Result<T> Fail(T data) {
        return this.Fail("fail",data);
    }
    protected final <T> Result<T> Fail(String message) {
        return this.Fail(message,null);
    }
    protected final <T> Result<T> Fail() {
        return this.Fail("fail",null);
    }
    protected final <T> PageableResult<T> Success(Page<T> data, String message) {
        return new PageableResult<T>(data,message,true);
    }
    protected final <T> PageableResult<T> Success(Page<T> data) {
        return this.Success(data,"success");
    }

    protected void SaveHandler(BaseEntity entity){
        User user = securityService.getUser();
        if (entity.getId() == null) {
            entity.setCreated_by(user);
            entity.setUpdate_by(user);
            entity.setCreated_date(new Date());
            entity.setUpdate_date(new Date());
        }
        else {
            entity.setUpdate_date(new Date());
            entity.setUpdate_by(user);
        }
    }
}
