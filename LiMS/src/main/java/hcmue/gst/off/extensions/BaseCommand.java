package hcmue.gst.off.extensions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class BaseCommand {
    protected final int PAGESIZE = 1;

    public final <T> Result<T> Success(T data, String message) {
        return new Result<T>(data,message,true);
    }
    public final <T> Result<T> Success(T data) {
        return this.Success(data,"success");
    }
    public final <T> Result<T> Success(String message) {
        return new Result<T>(null,message,true);
    }
    public final <T> Result<T> Success() {
        return new Result<T>(null,"success",true);
    }


    public final <T> Result<T> Fail(String message, T data) {
        return new Result<T>(data,message,false);
    }
    public final <T> Result<T> Fail(T data) {
        return this.Fail("fail",data);
    }
    public final <T> Result<T> Fail(String message) {
        return this.Fail(message,null);
    }
    public final <T> Result<T> Fail() {
        return this.Fail("fail",null);
    }
    public final <T> PageableResult<T> Success(Page<T> data, String message) {
        return new PageableResult<T>(data,message,true);
    }
    public final <T> PageableResult<T> Success(Page<T> data) {
        return this.Success(data,"success");
    }

}
