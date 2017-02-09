package hcmue.gst.off.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by WIN8.1 on 07/02/2017.
 */
@Entity
@Table(name = "bookcategory")
public class BookCategory extends Base implements Serializable {
    private String category_name;
    private Set<Book> books;

    public BookCategory() {
    }

    public BookCategory(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }


    @OneToMany(mappedBy = "bookCategory",cascade = CascadeType.ALL)
    public Set<Book> getBook() {
        return books;
    }

    public void setBook(Set<Book> books) {
        this.books = books;
    }
}
