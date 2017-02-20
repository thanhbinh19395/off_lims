package hcmue.gst.off.extensions;

import hcmue.gst.off.entities.Request;

import java.io.Serializable;

/**
 * Created by dylan on 2/19/2017.
 */
public class Mail implements Serializable {
    public Long id;
    public String message;

    public Mail() {
    }

    public Mail(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
