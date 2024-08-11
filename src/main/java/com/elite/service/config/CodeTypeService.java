package com.elite.service.config;

import com.elite.model.config.CodeTypeDetail;

import java.util.List;

public interface CodeTypeService {

    List<CodeTypeDetail> getCodeTypeDetails();

    CodeTypeDetail getCodeType(Long id);

    CodeTypeDetail getCodeType(String code);

    CodeTypeDetail createCodeType(CodeTypeDetail codeTypeDetail);

    CodeTypeDetail updateCodeType(Long id, CodeTypeDetail codeTypeDetail);

    void deleteCodeType(Long id);
}
