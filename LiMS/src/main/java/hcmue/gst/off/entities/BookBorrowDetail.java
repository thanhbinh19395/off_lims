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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }



    @ManyToOne
    @JoinColumn(name = "bookBorrow_id")
    public BookBorrowHeader getBookBorrowHeader() {
        return bookBorrowHeader;
    }

    public void setBookBorrowHeader(BookBorrowHeader bookBorrowHeader) {
        this.bookBorrowHeader = bookBorrowHeader;
    }
}
