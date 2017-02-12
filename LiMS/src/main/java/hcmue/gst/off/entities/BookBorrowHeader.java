package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "bookborrowheader")
public class BookBorrowHeader extends BaseEntity implements Serializable{
    private Date returnDate;
    private int bookTransaction;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private BookReservation bookReservation;


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getBookTransaction() {
        return bookTransaction;
    }

    public void setBookTransaction(int bookTransaction) {
        this.bookTransaction = bookTransaction;
    }

    @OneToMany(mappedBy = "bookBorrowHeader",cascade = CascadeType.ALL)
    public Set<BookBorrowDetail> getBookBorrowDetails() {
        return bookBorrowDetails;
    }
    public void setBookBorrowDetails(Set<BookBorrowDetail> bookBorrowDetails) {
        this.bookBorrowDetails = bookBorrowDetails;
    }

    @OneToOne(mappedBy = "bookBorrowHeader")
    public BookReservation getBookReservation() {
        return bookReservation;
    }
    public void setBookReservation(BookReservation bookReservation) {
        this.bookReservation = bookReservation;
    }
}
