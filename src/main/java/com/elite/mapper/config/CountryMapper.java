package com.elite.mapper.config;

import com.elite.entity.config.Country;
import com.elite.model.config.CountryDetail;
import com.elite.model.user.UserDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    UserDetail.Country toUserDetailCountry(Country country);

    CountryDetail toCountryDetail(Country country);
}
