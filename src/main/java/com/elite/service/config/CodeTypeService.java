package com.elite.service.config;

import com.elite.model.config.CodeTypeModel;
import com.elite.model.config.CodeTypeReqModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CodeTypeService {

    List<CodeTypeModel> getCodeTypeDetails();

    CodeTypeModel getCodeTypeById(Long id);

    CodeTypeModel getCodeTypeByCode(String code);

    CodeTypeModel createCodeType(CodeTypeReqModel codeTypeReqModel);

    CodeTypeModel updateCodeType(Long id, CodeTypeReqModel codeTypeReqModel);

    Boolean deleteCodeType(Long id);

    Page<CodeTypeModel> searchCodeTypeDetails(String searchTerm, int pageIndex, int pageSize);
}
