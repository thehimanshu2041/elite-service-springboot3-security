package com.elite.service.config;

import com.elite.model.config.CountryDetail;

import java.util.List;

public interface CountryService {

    List<CountryDetail> getCountries();

    CountryDetail getCountry(Long id);

    CountryDetail getCountry(String code);
}
