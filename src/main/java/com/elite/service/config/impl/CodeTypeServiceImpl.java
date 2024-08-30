package com.elite.service.config.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.entity.config.Code;
import com.elite.entity.config.CodeType;
import com.elite.mapper.config.CodeTypeMapper;
import com.elite.model.config.CodeTypeModel;
import com.elite.model.config.CodeTypeReqModel;
import com.elite.repository.config.CodeRepository;
import com.elite.repository.config.CodeTypeRepository;
import com.elite.service.config.CodeTypeService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class CodeTypeServiceImpl implements CodeTypeService {

    private final CodeTypeRepository codeTypeRepository;

    private final CodeTypeMapper codeTypeMapper;

    private final CodeRepository codeRepository;

    public CodeTypeServiceImpl(CodeTypeRepository codeTypeRepository, CodeTypeMapper codeTypeMapper, CodeRepository codeRepository) {
        this.codeTypeRepository = codeTypeRepository;
        this.codeTypeMapper = codeTypeMapper;
        this.codeRepository = codeRepository;
    }

    @Override
    public List<CodeTypeModel> getCodeTypeDetails() {
        log.info(MessageResource.getMessage(ESLog.ES_020));
        return codeTypeRepository
                .findAll()
                .stream()
                .map(codeTypeMapper::convertCodeTypeToCodeTypeModel)
                .collect(Collectors.toList());
    }

    @Override
    public CodeTypeModel getCodeTypeById(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_021), id);
        return codeTypeMapper
                .convertCodeTypeToCodeTypeModel(
                        codeTypeRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009))));
    }

    @Override
    public CodeTypeModel getCodeTypeByCode(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_022), code);
        return codeTypeMapper
                .convertCodeTypeToCodeTypeModel(
                        codeTypeRepository
                                .findByCode(code)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009))));
    }

    @Override
    public CodeTypeModel createCodeType(CodeTypeReqModel codeTypeReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_023), codeTypeReqModel.getCode());
        return codeTypeMapper
                .convertCodeTypeToCodeTypeModel(
                        codeTypeRepository
                                .save(
                                        codeTypeMapper
                                                .convertCodeTypeReqModelToCodeType(codeTypeReqModel)));
    }

    @Override
    public CodeTypeModel updateCodeType(Long id, CodeTypeReqModel codeTypeReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_024), id, codeTypeReqModel.getCode());
        CodeType codeType = codeTypeRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        codeType.setCode(codeTypeReqModel.getCode());
        codeType.setName(codeTypeReqModel.getName());
        codeType.setDescription(codeTypeReqModel.getDescription());
        return codeTypeMapper
                .convertCodeTypeToCodeTypeModel(
                        codeTypeRepository
                                .save(codeType));
    }

    @Override
    public Boolean deleteCodeType(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_025), id);
        CodeType codeType = codeTypeRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        List<Code> deletedCodes = codeRepository.findAllByCodeTypeIdOrderByUpdatedDateDesc(codeType.getId());
        codeRepository.deleteAll(deletedCodes);
        codeTypeRepository.delete(codeType);
        return true;
    }

    @Override
    public Page<CodeTypeModel> searchCodeTypeDetails(String searchTerm, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<CodeType> codeTypes = codeTypeRepository.findByNameContainingIgnoreCaseOrderByUpdatedDateDesc(searchTerm, pageRequest);
        List<CodeTypeModel> codeTypesDetails = codeTypes.getContent().stream().map(codeTypeMapper::convertCodeTypeToCodeTypeModel).toList();
        return new PageImpl<>(codeTypesDetails, pageRequest, codeTypes.getTotalElements());
    }
}
