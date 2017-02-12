package hcmue.gst.off.entities;

import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "bookstatus")
public class BookStatus extends BaseEntity implements Serializable {
    private String description;
    private BookBorrowDetail bookBorrowDetail;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(mappedBy = "bookStatus")
    public BookBorrowDetail getBookBorrowDetail() {
        return bookBorrowDetail;
    }

    public void setBookBorrowDetail(BookBorrowDetail bookBorrowDetail) {
        this.bookBorrowDetail = bookBorrowDetail;
    }
}
