package com.elite.mapper.config;

import com.elite.entity.config.Code;
import com.elite.model.config.CodeDetail;
import com.elite.model.user.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    UserDetail.Gender toUserDetailGender(Code code);

    @Mapping(target = "id", ignore = true)
    Code toCode(CodeDetail codeDetail);

    CodeDetail toCodeDetail(Code code);

}
