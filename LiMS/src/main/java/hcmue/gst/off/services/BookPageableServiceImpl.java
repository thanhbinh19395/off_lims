package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.repositories.BookPageableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



/**
 * Created by Ho Phuong on 15/02/2017.
 */
@Service
public class BookPageableServiceImpl implements BookPageableService {

    @Autowired
    private BookPageableRepository bookPageableRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        Page<Book> bookList = bookPageableRepository.findAll(pageable);
        return bookList;
    }
}
