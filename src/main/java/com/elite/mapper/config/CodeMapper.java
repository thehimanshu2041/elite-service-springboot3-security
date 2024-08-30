package com.elite.mapper.config;

import com.elite.entity.config.Code;
import com.elite.model.config.CodeModel;
import com.elite.model.config.CodeReqModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    Code convertCodeReqModelToCode(CodeReqModel codeReqModel);

    CodeModel convertCodeToCodeModel(Code code);

}
