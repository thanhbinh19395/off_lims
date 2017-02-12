package hcmue.gst.off.extensions;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class BaseCommand {

    public <T> Result<T> Success(T data, String message) {
        return new Result<T>(data,message,true);
    }
    public <T> Result<T> Success(T data) {
        return this.Success(data,"success");
    }
    public <T> Result<T> Success(String message) {
        return this.Success(null,message);
    }
    public <T> Result<T> Success() {
        return this.Success(null,"success");
    }
    public <T> Result<T> Fail(String message, T data) {
        return new Result<T>(data,message,false);
    }
    public <T> Result<T> Fail(T data) {
        return this.Fail("fail",data);
    }
    public <T> Result<T> Fail(String message) {
        return this.Fail(message,null);
    }
    public <T> Result<T> Fail() {
        return this.Fail("fail",null);
    }
}