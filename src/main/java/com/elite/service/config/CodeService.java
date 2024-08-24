package com.elite.service.config;

import com.elite.model.config.CodeDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeService {

    List<CodeDetail> getCodeDetails();

    CodeDetail getCode(Long id);

    CodeDetail getCode(String code);

    List<CodeDetail> getCodeDetailsByType(Long id);

    List<CodeDetail> getCodeDetailsByType(String code);

    CodeDetail createCode(CodeDetail codeTypeDetail);

    CodeDetail updateCode(Long id, CodeDetail codeTypeDetail);

    Page<CodeDetail> searchCodeDetails(String searchTerm, int pageIndex, int pageSize);

    Page<CodeDetail> searchCodeDetailsByType(Long id, String searchTerm, int pageIndex, int pageSize);

    Page<CodeDetail> searchCodeDetailsByType(String code, String searchTerm, int pageIndex, int pageSize);

    void deleteCode(Long id);
}
