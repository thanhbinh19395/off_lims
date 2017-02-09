package hcmue.gst.off.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "reservation")
public class BookReservation extends Base implements Serializable {
    private BookBorrow bookBorrow;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookborrow_id")
    public BookBorrow getBookBorrow() {
        return bookBorrow;
    }

    public void setBookBorrow(BookBorrow bookBorrow) {
        this.bookBorrow = bookBorrow;
    }

}
