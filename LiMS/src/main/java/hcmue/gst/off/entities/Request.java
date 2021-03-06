package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "request")
public class Request extends BaseEntity implements Serializable{
    private String book_name;
    private String author;
    private String publisher;
    private Integer published_year;
    private String note;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublished_year() {
        return published_year;
    }

    public void setPublished_year(Integer published_year) {
        this.published_year = published_year;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
