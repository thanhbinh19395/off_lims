package hcmue.gst.off.entities;


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
    private int publish_year;
    private byte[] image;
    private BookStatus bookStatus;
    private BookCategory bookCategory;
    private BookBorrowDetail bookBorrowDetail;

    public Book() {
    }

    public Book(String name, String author ,int publish_year, byte[] image, BookCategory bookCategory) {
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

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @ManyToOne
    @JoinColumn(name = "book_category_id")
    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }


    @OneToOne(mappedBy = "book")
    public BookBorrowDetail getBookBorrowDetail() {
        return bookBorrowDetail;
    }

    public void setBookBorrowDetail(BookBorrowDetail bookBorrowDetail) {
        this.bookBorrowDetail = bookBorrowDetail;
    }


    @ManyToOne
    @JoinColumn(name = "bookstatus_id")
    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
