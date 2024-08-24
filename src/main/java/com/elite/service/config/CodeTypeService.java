package com.elite.service.config;

import com.elite.model.config.CodeTypeDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeTypeService {

    List<CodeTypeDetail> getCodeTypeDetails();

    Page<CodeTypeDetail> searchCodeTypeDetails(String searchTerm, int pageIndex, int pageSize);

    CodeTypeDetail getCodeType(Long id);

    CodeTypeDetail getCodeType(String code);

    CodeTypeDetail createCodeType(CodeTypeDetail codeTypeDetail);

    CodeTypeDetail updateCodeType(Long id, CodeTypeDetail codeTypeDetail);

    void deleteCodeType(Long id);
}
