package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by dylan on 2/24/2017.
 */
@Entity
@Table(name = "bookpayable")
public class BookPayableHeader extends BaseEntity {

    private Long bookBorrowId;
    private Date actualReturnDate;
    private long overDue;
    private int status;
    private Set<BookPayableDetail> bookPayableDetails;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private BookBorrowHeader bookBorrowHeader;

    @Column(name = "bookborrowheader_id")
    public Long getBookBorrowId() {
        return bookBorrowId;
    }

    @OneToMany(mappedBy = "bookPayableHeader",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public Set<BookPayableDetail> getBookPayableDetails() {
        return bookPayableDetails;
    }
    public void setBookPayableDetails(Set<BookPayableDetail> bookPayableDetails) {
        this.bookPayableDetails = bookPayableDetails;
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

    public long getOverDue() {
        return overDue;
    }

    public void setOverDue(long overDue) {
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
