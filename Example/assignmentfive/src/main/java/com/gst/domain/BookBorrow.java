package com.gst.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
@Entity
@Table(name = "bookborrow")
public class BookBorrow extends Base implements Serializable {
    private Date returnDate;
    private int bookTransaction;



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

}
