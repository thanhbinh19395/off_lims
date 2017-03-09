package hcmue.gst.off.services;

import hcmue.gst.off.entities.Country;
import hcmue.gst.off.extensions.*;
import hcmue.gst.off.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
@Service
public class CountryServiceImpl extends BaseCommand implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public Result<Iterable<Country>> findAll() {
        return Success(countryRepository.findAll());
    }

    @Override
    public Result<Country> findOne(Long id) {
        return Success(countryRepository.findOne(id));
    }

    @Override
    public Result<Country> save(Country contact) {
        return Success(countryRepository.save(contact), "Successfully Saved ");
    }

    @Override
    public Result<Long> delete(Long id) {
        countryRepository.delete(id);
        return Success(id,"Successfully Deleted ");
    }
}