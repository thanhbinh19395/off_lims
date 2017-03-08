package hcmue.gst.off.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "book")

public class Book extends BaseEntity implements Serializable {
    private String name;
    private String author;
    private Integer publish_year;
    private String publisher;
    private String imageUrl;
    private BookStatus bookStatus;
    private BookCategory bookCategory;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private Long bookCategoryId;
    private Long bookStatusId;
    private String bookCode;
    private Integer state;


    public Book(){


    }


    public Book(String name, String author ,Integer publish_year, String imageUrl, BookCategory bookCategory) {
        this.name = name;
        this.author = author;
        this.publish_year = publish_year;
        this.imageUrl = imageUrl;
        this.bookCategory = bookCategory;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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


    @OneToMany(mappedBy = "book",cascade = {CascadeType.ALL})
    @JsonIgnore
    public Set<BookBorrowDetail> getBookBorrowDetail() {
        return bookBorrowDetails;
    }

    public void setBookBorrowDetail(Set<BookBorrowDetail> bookBorrowDetail) {
        this.bookBorrowDetails = bookBorrowDetail;
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

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
