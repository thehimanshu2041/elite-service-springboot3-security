package com.elite.service.config;

import com.elite.model.config.CountryModel;
import com.elite.model.config.CountryReqModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

    List<CountryModel> getCountries();

    CountryModel createCountry(CountryReqModel countryReqModel);

    CountryModel updateCountry(Long id, CountryReqModel countryReqModel);

    Boolean deleteCountry(Long id);

    CountryModel getCountryById(Long id);

    CountryModel getCountryByCode(String code);

    Page<CountryModel> searchCountries(String searchTerm, int pageIndex, int pageSize);

}
