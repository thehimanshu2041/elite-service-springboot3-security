package com.elite.service.config;

import com.elite.model.config.CountryDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

    List<CountryDetail> getCountries();

    Page<CountryDetail> searchCountries(String searchTerm, int pageIndex, int pageSize);

    CountryDetail getCountry(Long id);

    CountryDetail getCountry(String code);
}
