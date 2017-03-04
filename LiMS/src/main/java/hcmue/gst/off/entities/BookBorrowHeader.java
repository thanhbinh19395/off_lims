package hcmue.gst.off.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import hcmue.gst.off.extensions.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private Integer status;

    private Long userId;
    private User user;

    public BookBorrowHeader() {

    }

    public BookBorrowHeader(Date returnDate, Long userId) {
        this.returnDate = returnDate;
        this.userId = userId;
        status = CommonStatus.PENDING;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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





}
