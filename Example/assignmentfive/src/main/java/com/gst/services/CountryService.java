package com.gst.services;

import com.gst.domain.Country;
import com.gst.extension.Result;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
public interface CountryService {
    Result<Iterable<Country>> findAll();

    Result<Country> findOne(int id);

    Result<Country> save(Country contact);

    Result delete(int id);
}
