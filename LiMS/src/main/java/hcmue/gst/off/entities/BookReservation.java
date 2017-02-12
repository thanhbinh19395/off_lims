package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "reservation")
public class BookReservation extends EntityBase implements Serializable {
    private BookBorrowHeader bookBorrowHeader;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookborrow_id")
    public BookBorrowHeader getBookBorrowHeader() {
        return bookBorrowHeader;
    }

    public void setBookBorrowHeader(BookBorrowHeader bookBorrowHeader) {
        this.bookBorrowHeader = bookBorrowHeader;
    }

}
