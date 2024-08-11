package com.elite.mapper.config;

import com.elite.entity.config.CodeType;
import com.elite.model.config.CodeTypeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CodeTypeMapper {

    @Mapping(target = "id", ignore = true)
    CodeType toCodeType(CodeTypeDetail codeTypeDetail);

    CodeTypeDetail toCodeTypeDetail(CodeType codeType);
}
