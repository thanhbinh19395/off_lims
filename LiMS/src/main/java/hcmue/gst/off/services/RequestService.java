package hcmue.gst.off.services;


import hcmue.gst.off.entities.Request;
import hcmue.gst.off.extensions.Result;

/**
 * Created by WIN8.1 on 10/02/2017.
 * Edited by dylan on 18/02/2017.
 */
public interface RequestService {
    Result<Iterable<Request>> findAll();

    Result<Request> findOne(Long id);

    Result<Request> save(Request request);

    Result delete(Long id);

    Result findByStatus(String status);
}
