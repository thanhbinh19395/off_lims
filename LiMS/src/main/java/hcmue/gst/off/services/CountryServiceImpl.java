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
public class CountryServiceImpl extends CommandBase implements CountryService {

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
    public Result<Integer> delete(int id) {
        countryRepository.delete(id);
        return Success(id,"Xóa thành công");
    }
}