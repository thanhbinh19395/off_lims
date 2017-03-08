package hcmue.gst.off.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;

/**
 * Created by dylan on 3/3/2017.
 */
@Entity
@Table(name = "bookpayabledetail")
public class BookPayableDetail extends BaseEntity{
    private Long bookPayableHeaderId;
    private Long bookId;
    @JsonIgnore
    private BookPayableHeader bookPayableHeader;
    private Book book;
    private Boolean isPaid;

    @Column(name = "bookpayable_id")
    public Long getBookPayableHeaderId() {
        return bookPayableHeaderId;
    }

    public void setBookPayableHeaderId(Long bookPayableHeaderId) {
        this.bookPayableHeaderId = bookPayableHeaderId;
    }
    @Column(name = "book_id")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookpayable_id",insertable = false,updatable = false)
    public BookPayableHeader getBookPayableHeader() {
        return bookPayableHeader;
    }

    public void setBookPayableHeader(BookPayableHeader bookPayableHeader) {
        this.bookPayableHeader = bookPayableHeader;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id",insertable = false,updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean pay) {
        isPaid = pay;
    }
}
