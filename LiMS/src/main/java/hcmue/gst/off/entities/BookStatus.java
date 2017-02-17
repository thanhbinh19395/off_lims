package hcmue.gst.off.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmue.gst.off.extensions.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
@Entity
@Table(name = "bookstatus")
public class BookStatus extends BaseEntity implements Serializable {
    private String description;
    private Set<Book> books;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "bookStatus", cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
