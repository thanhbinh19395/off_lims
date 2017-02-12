package hcmue.gst.off.services;

import hcmue.gst.off.entities.Country;
import hcmue.gst.off.extensions.Result;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
public interface CountryService {
    Result<Iterable<Country>> findAll();

    Result<Country> findOne(Long id);

    Result<Country> save(Country contact);

    Result delete(Long id);
}