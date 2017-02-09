package hcmue.gst.off.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "bookborrowdetail")
public class BookBorrowDetail extends Base {

    private String note;
    private Book book;
    private BookStatus bookStatus;
    private BookBorrow bookBorrow;

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

    @OneToOne
    @JoinColumn(name = "bookstatus_id")
    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @ManyToOne
    @JoinColumn(name = "bookBorrow_id")
    public BookBorrow getBookBorrow() {
        return bookBorrow;
    }

    public void setBookBorrow(BookBorrow bookBorrow) {
        this.bookBorrow = bookBorrow;
    }
}
