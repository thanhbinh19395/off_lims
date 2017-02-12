package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "request")
public class Request extends EntityBase implements Serializable{
    private String book_name;
    private String author;


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
