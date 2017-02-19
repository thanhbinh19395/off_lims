package hcmue.gst.off.entities;

import com.fasterxml.jackson.annotation.*;

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
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String idcard;
    private String email;
    private Date birthday;
    private String username;
    private String password;
    private String confirmPassword;
    private boolean status;



    private Long roleId;
    private Role role;
    private Set<Request> requests;
    private Set<Book> books;
    private Set<BookBorrowDetail> bookBorrowDetails;
    private Set<BookStatus> bookStatuses;
    private Set<BookBorrowHeader> bookBorrowHeaders;
    private Set<BookReservation> bookReservations;
    private Set<BookCategory> bookCategories;


    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role_id")
    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", insertable = false, updatable = false)
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "created_by",cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @OneToMany(mappedBy = "created_by",cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<BookBorrowHeader> getBookBorrowHeaders() {
        return bookBorrowHeaders;
    }

    public void setBookBorrowHeaders(Set<BookBorrowHeader> bookBorrowHeaders) {
        this.bookBorrowHeaders = bookBorrowHeaders;
    }

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    public Set<BookReservation> getBookReservations() {
        return bookReservations;
    }

    public void setBookReservations(Set<BookReservation> bookReservations) {
        this.bookReservations = bookReservations;
    }

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    public Set<BookBorrowDetail> getBookBorrowDetails() {
        return bookBorrowDetails;
    }

    public void setBookBorrowDetails(Set<BookBorrowDetail> bookBorrowDetails) {
        this.bookBorrowDetails = bookBorrowDetails;
    }

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    public Set<BookStatus> getBookStatuses() {
        return bookStatuses;
    }

    public void setBookStatuses(Set<BookStatus> bookStatuses) {
        this.bookStatuses = bookStatuses;
    }

    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }
    @JsonIgnore
    public void setBookCategories(Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }


}
