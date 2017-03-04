package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date pickUpDate;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}


