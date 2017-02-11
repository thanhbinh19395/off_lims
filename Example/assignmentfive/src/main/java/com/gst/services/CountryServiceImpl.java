package com.gst.services;

import com.gst.domain.Country;
import com.gst.extension.Result;
import com.gst.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thanh Binh on 2/11/2017.
 */
@Service
public class CountryServiceImpl extends BaseService implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public Result<Iterable<Country>> findAll() {
        return Success(countryRepository.findAll());
    }

    @Override
    public Result<Country> findOne(int id) {
        return Success(countryRepository.findOne(id));
    }

    @Override
    public Result<Country> save(Country contact) {
        return Success(countryRepository.save(contact), "Lưu thành công");
    }

    @Override
    public Result delete(int id) {
        countryRepository.delete(id);
        return Success(null, "Xóa thành công");
    }
}
