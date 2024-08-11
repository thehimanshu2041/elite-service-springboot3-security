package com.elite.service.config.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.entity.config.Code;
import com.elite.entity.config.CodeType;
import com.elite.mapper.config.CodeTypeMapper;
import com.elite.model.config.CodeTypeDetail;
import com.elite.repository.config.CodeRepository;
import com.elite.repository.config.CodeTypeRepository;
import com.elite.service.config.CodeTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CodeTypeDetail> getCodeTypeDetails() {
        log.info(MessageResource.getMessage(ESLog.ES_020));
        return codeTypeRepository
                .findAll()
                .stream()
                .map(codeTypeMapper::toCodeTypeDetail)
                .collect(Collectors.toList());
    }

    @Override
    public CodeTypeDetail getCodeType(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_021), id);
        return codeTypeMapper
                .toCodeTypeDetail(
                        codeTypeRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009))));
    }

    @Override
    public CodeTypeDetail getCodeType(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_022), code);
        return codeTypeMapper
                .toCodeTypeDetail(
                        codeTypeRepository
                                .findByCode(code)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009))));
    }

    @Override
    public CodeTypeDetail createCodeType(CodeTypeDetail codeTypeDetail) {
        log.info(MessageResource.getMessage(ESLog.ES_023), codeTypeDetail.getCode());
        return codeTypeMapper
                .toCodeTypeDetail(
                        codeTypeRepository
                                .save(
                                        codeTypeMapper
                                                .toCodeType(codeTypeDetail)));
    }

    @Override
    public CodeTypeDetail updateCodeType(Long id, CodeTypeDetail codeTypeDetail) {
        log.info(MessageResource.getMessage(ESLog.ES_024), id, codeTypeDetail.getCode());
        codeTypeRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        CodeType codeType = codeTypeMapper.toCodeType(codeTypeDetail);
        codeType.setId(id);
        return codeTypeMapper
                .toCodeTypeDetail(
                        codeTypeRepository
                                .save(codeType));
    }

    @Override
    public void deleteCodeType(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_025), id);
        List<Code> deletedCodes = codeRepository.findAllByCodeTypeId(id);
        for (Code deletedCode : deletedCodes) {
            codeRepository.deleteById(deletedCode.getId());
        }
        codeTypeRepository.deleteById(id);
    }
}
