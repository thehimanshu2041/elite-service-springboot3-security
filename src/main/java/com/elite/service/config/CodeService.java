package com.elite.service.config;

import com.elite.model.config.CodeModel;
import com.elite.model.config.CodeReqModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeService {

    List<CodeModel> getCodeDetails();

    CodeModel getCodeById(Long id);

    CodeModel getCodeByCode(String code);

    CodeModel createCode(CodeReqModel codeReqModel);

    CodeModel updateCode(Long id, CodeReqModel codeReqModel);

    Boolean deleteCode(Long id);

    List<CodeModel> getCodeDetailsByTypeId(Long id);

    List<CodeModel> getCodeDetailsByTypeCode(String code);

    Page<CodeModel> searchCodeDetails(String searchTerm, int pageIndex, int pageSize);

    Page<CodeModel> searchCodeDetailsByTypeId(Long id, String searchTerm, int pageIndex, int pageSize);

    Page<CodeModel> searchCodeDetailsByTypeCode(String code, String searchTerm, int pageIndex, int pageSize);


}
