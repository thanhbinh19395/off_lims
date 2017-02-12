package hcmue.gst.off.extensions;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public class CustomException extends RuntimeException {
    Object data;

    public CustomException(String message, Object data) {
        super(message);
        this.data = data;

    }

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Object getData() {
        return data;
    }
}
