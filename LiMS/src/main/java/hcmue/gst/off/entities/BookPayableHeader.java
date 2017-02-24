package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by dylan on 2/24/2017.
 */
@Entity
@Table(name = "bookpayable")
public class BookPayableHeader extends BaseEntity {

    private Long bookBorrowId;
    private Date actualReturnDate;
    private Float overDue;


    private BookBorrowHeader bookBorrowHeader;

    @Column(name = "bookborrowheader_id")
    public Long getBookBorrowId() {
        return bookBorrowId;
    }

    public void setBookBorrowId(Long bookBorrowId) {
        this.bookBorrowId = bookBorrowId;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Float getOverDue() {
        return overDue;
    }

    public void setOverDue(Float overDue) {
        this.overDue = overDue;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookborrowheader_id", insertable = false, updatable = false)
    public BookBorrowHeader getBookBorrowHeader() {
        return bookBorrowHeader;
    }

    public void setBookBorrowHeader(BookBorrowHeader bookBorrowHeader) {
        this.bookBorrowHeader = bookBorrowHeader;
    }
}
