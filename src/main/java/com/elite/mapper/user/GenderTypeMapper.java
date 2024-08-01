package com.elite.mapper.user;

import com.elite.entity.config.CodeType;
import com.elite.model.user.UserDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderTypeMapper {

    UserDetail.Gender.GenderType toGenderType(CodeType codeType);
}
