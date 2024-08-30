package com.elite.mapper.config;

import com.elite.entity.config.Country;
import com.elite.model.config.CountryModel;
import com.elite.model.config.CountryReqModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryModel convertCountrytoCountryModel(Country country);

    Country convertCountryReqModelToCountry(CountryReqModel countryReqModel);
}
