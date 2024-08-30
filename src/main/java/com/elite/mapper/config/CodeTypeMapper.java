package com.elite.mapper.config;

import com.elite.entity.config.CodeType;
import com.elite.model.config.CodeTypeModel;
import com.elite.model.config.CodeTypeReqModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeTypeMapper {

    CodeType convertCodeTypeReqModelToCodeType(CodeTypeReqModel codeTypeReqModel);

    CodeTypeModel convertCodeTypeToCodeTypeModel(CodeType codeType);
}
