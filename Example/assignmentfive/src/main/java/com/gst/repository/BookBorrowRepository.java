package com.gst.repository;

import com.gst.domain.BookBorrow;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by WIN8.1 on 10/02/2017.
 */
public interface BookBorrowRepository extends CrudRepository<BookBorrow,Long> {
}
