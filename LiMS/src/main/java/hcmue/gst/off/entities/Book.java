package hcmue.gst.off.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "book")
public class Book extends BaseEntity implements Serializable {
    private String name;
    private String author;
    private Integer publish_year;
    private byte[] image;
    private BookStatus bookStatus;
    private BookCategory bookCategory;
    private BookBorrowDetail bookBorrowDetail;
    private Long bookCategoryId;
    private Long bookStatusId;

    public Book(){


    }


    public Book(String name, String author ,Integer publish_year, byte[] image, BookCategory bookCategory) {
        this.name = name;
        this.author = author;
        this.publish_year = publish_year;
        this.image = image;
        this.bookCategory = bookCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(Integer publish_year) {
        this.publish_year = publish_year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "bookcategory_id")
    public Long getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Long bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    @Column(name = "bookstatus_id")
    public Long getBookStatusId() {
        return bookStatusId;
    }

    public void setBookStatusId(Long bookStatusId) {
        this.bookStatusId = bookStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "bookcategory_id", insertable = false,updatable = false)
    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }


    @OneToOne(mappedBy = "book",cascade = {CascadeType.ALL})
    public BookBorrowDetail getBookBorrowDetail() {
        return bookBorrowDetail;
    }

    public void setBookBorrowDetail(BookBorrowDetail bookBorrowDetail) {
        this.bookBorrowDetail = bookBorrowDetail;
    }


    @ManyToOne
    @JoinColumn(name = "bookstatus_id", insertable = false,updatable = false)
    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
