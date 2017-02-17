package hcmue.gst.off.services;

import hcmue.gst.off.entities.BookCategory;
import hcmue.gst.off.entities.BookStatus;
import hcmue.gst.off.extensions.Result;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by Lâm Anh Kiệt 15/02/2017
 */
public interface BookStatusService {
    Result<BookStatus> save(BookStatus bookCategory);
    Result<Iterable<BookStatus>> findAll();
    Result<BookStatus> findOne(Long id);
    Result delete(Long id);
}
