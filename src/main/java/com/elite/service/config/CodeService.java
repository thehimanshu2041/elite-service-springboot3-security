package com.elite.service.config;

import com.elite.model.config.CodeDetail;

import java.util.List;

public interface CodeService {

    List<CodeDetail> getCodeDetails();

    CodeDetail getCode(Long id);

    CodeDetail getCode(String code);

    List<CodeDetail> getCodeDetailsByType(Long id);

    CodeDetail createCode(CodeDetail codeTypeDetail);

    CodeDetail updateCode(Long id, CodeDetail codeTypeDetail);

    void deleteCode(Long id);
}
