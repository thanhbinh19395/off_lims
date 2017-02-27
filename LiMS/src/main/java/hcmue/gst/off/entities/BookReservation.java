package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "reservation")
public class BookReservation extends BaseEntity implements Serializable {
    private Long bookId;
    private Book book;
    private Date pickUpDate;
    private String status;

    @Column(name = "book_id")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id",insertable = false,updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


