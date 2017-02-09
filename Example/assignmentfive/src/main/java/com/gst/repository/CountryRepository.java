package com.gst.repository;

import com.gst.domain.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Thanh Binh on 2/8/2017.
 */
public interface CountryRepository extends CrudRepository<Country,Integer> {
}
