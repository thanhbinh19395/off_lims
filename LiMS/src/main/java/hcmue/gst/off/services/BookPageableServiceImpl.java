package hcmue.gst.off.services;

import hcmue.gst.off.entities.Book;
import hcmue.gst.off.repositories.BookPageableRepository;
import hcmue.gst.off.repositories.BookRepository;
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

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookPageableRepository.findAll(pageable);

    }
    @Override
    public Page<Book> search(Book model, Pageable pageable) {
        return bookRepository.search(model, pageable);
    }
}
