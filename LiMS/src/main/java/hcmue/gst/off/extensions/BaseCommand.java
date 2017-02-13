package hcmue.gst.off.extensions;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class BaseCommand {

    public final <T> Result<T> Success(T data, String message) {
        return new Result<T>(data,message,true);
    }
    public final <T> Result<T> Success(T data) {
        return this.Success(data,"success");
    }
    public final <T> Result<T> Success(String message) {
        return this.Success(null,message);
    }
    public final <T> Result<T> Success() {
        return this.Success(null,"success");
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

}
