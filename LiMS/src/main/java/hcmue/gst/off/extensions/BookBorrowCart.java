package hcmue.gst.off.extensions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hcmue.gst.off.entities.Book;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Ho Phuong on 28/02/2017.
 */

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookBorrowCart implements Serializable {
    private static final long serialVersionUID = -1947132804983588610L;

    private List<Book> bookList;

    public void addBook(Book book) {
        if(!bookList.contains(book)) {
            bookList.add(book);
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void clear() {
        bookList.clear();
    }
}
