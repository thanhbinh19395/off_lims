package hcmue.gst.off.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    private long id;
    private String name;
    private String phone;
    private String address;
    private String idcard;
    private Date birthday;
    private String username;
    private String password;
    private String confirmPassword;
    private boolean status;
    private Role role;
    private Set<Request> requests;
    private Set<Book> books;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private Set<BookStatus> bookStatuses;
    private Set<BookBorrow> bookBorrows;
    private Set<BookReservation> bookReservations;
    private Set<BookCategory> bookCategories;


    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @ManyToOne
    @JoinColumn(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @OneToMany(mappedBy = "created_by",cascade = CascadeType.ALL)
    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @OneToMany(mappedBy = "created_by",cascade = CascadeType.ALL)
    public Set<BookBorrow> getBookBorrows() {
        return bookBorrows;
    }

    public void setBookBorrows(Set<BookBorrow> bookBorrows) {
        this.bookBorrows = bookBorrows;
    }

    @OneToMany(mappedBy = "created_by")
    public Set<BookReservation> getBookReservations() {
        return bookReservations;
    }

    public void setBookReservations(Set<BookReservation> bookReservations) {
        this.bookReservations = bookReservations;
    }

    @OneToMany(mappedBy = "created_by")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "created_by")
    public Set<BookBorrowDetail> getBookBorrowDetails() {
        return bookBorrowDetails;
    }

    public void setBookBorrowDetails(Set<BookBorrowDetail> bookBorrowDetails) {
        this.bookBorrowDetails = bookBorrowDetails;
    }

    @OneToMany(mappedBy = "created_by")
    public Set<BookStatus> getBookStatuses() {
        return bookStatuses;
    }

    public void setBookStatuses(Set<BookStatus> bookStatuses) {
        this.bookStatuses = bookStatuses;
    }

    @OneToMany(mappedBy = "created_by")
    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }
}
