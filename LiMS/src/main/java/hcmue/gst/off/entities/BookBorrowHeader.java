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
    private Integer bookTransaction;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private BookReservation bookReservation;

    private Long userId;
    private User user;


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getBookTransaction() {
        return bookTransaction;
    }

    public void setBookTransaction(Integer bookTransaction) {
        this.bookTransaction = bookTransaction;
    }

    @OneToMany(mappedBy = "bookBorrowHeader",cascade = CascadeType.ALL)
    public Set<BookBorrowDetail> getBookBorrowDetails() {
        return bookBorrowDetails;
    }
    public void setBookBorrowDetails(Set<BookBorrowDetail> bookBorrowDetails) {
        this.bookBorrowDetails = bookBorrowDetails;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




    @OneToOne(mappedBy = "bookBorrowHeader")
    public BookReservation getBookReservation() {
        return bookReservation;
    }
    public void setBookReservation(BookReservation bookReservation) {
        this.bookReservation = bookReservation;
    }
}
