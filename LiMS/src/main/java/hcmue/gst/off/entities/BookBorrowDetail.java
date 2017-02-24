package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "bookborrowdetail")
public class BookBorrowDetail extends BaseEntity {

    private String note;
    private Book book;


    private BookBorrowHeader bookBorrowHeader;

    private Long bookId;
    private Long bookBorrowHeaderId;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id",insertable = false,updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(name = "bookborrowheader_id")
    public Long getBookBorrowHeaderId() {
        return bookBorrowHeaderId;
    }

    public void setBookBorrowHeaderId(Long bookBorrowHeaderId) {
        this.bookBorrowHeaderId = bookBorrowHeaderId;
    }

    @Column(name = "book_id")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @ManyToOne
    @JoinColumn(name =  "bookborrowheader_id",insertable = false,updatable = false)
    public BookBorrowHeader getBookBorrowHeader() {
        return bookBorrowHeader;
    }

    public void setBookBorrowHeader(BookBorrowHeader bookBorrowHeader) {
        this.bookBorrowHeader = bookBorrowHeader;
    }
}
