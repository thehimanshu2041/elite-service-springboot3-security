package com.elite.service.config.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.entity.config.Code;
import com.elite.entity.config.CodeType;
import com.elite.mapper.config.CodeMapper;
import com.elite.model.config.CodeDetail;
import com.elite.repository.config.CodeRepository;
import com.elite.repository.config.CodeTypeRepository;
import com.elite.service.config.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    private final CodeTypeRepository codeTypeRepository;

    private final CodeMapper codeMapper;

    public CodeServiceImpl(CodeRepository codeRepository, CodeTypeRepository codeTypeRepository, CodeMapper codeMapper) {
        this.codeRepository = codeRepository;
        this.codeTypeRepository = codeTypeRepository;
        this.codeMapper = codeMapper;
    }


    @Override
    public List<CodeDetail> getCodeDetails() {
        log.info(MessageResource.getMessage(ESLog.ES_026));
        return codeRepository
                .findAll()
                .stream()
                .map(codeMapper::toCodeDetail)
                .collect(Collectors.toList());
    }

    @Override
    public CodeDetail getCode(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_027), id);
        return codeMapper
                .toCodeDetail(
                        codeRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010))));
    }

    @Override
    public CodeDetail getCode(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_028), code);
        return codeMapper
                .toCodeDetail(
                        codeRepository
                                .findByCode(code)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010))));
    }

    @Override
    public List<CodeDetail> getCodeDetailsByType(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_029), id);
        codeTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return codeRepository
                .findAllByCodeTypeId(id)
                .stream()
                .map(codeMapper::toCodeDetail)
                .collect(Collectors.toList());
    }

    @Override
    public List<CodeDetail> getCodeDetailsByType(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_029), code);
        CodeType codeType = codeTypeRepository.findByCode(code)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return getCodeDetailsByType(codeType.getId());
    }

    @Override
    public CodeDetail createCode(CodeDetail codeDetail) {
        log.info(MessageResource.getMessage(ESLog.ES_030), codeDetail.getCode(), codeDetail.getCodeTypeId());
        codeTypeRepository.findById(codeDetail.getCodeTypeId())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return codeMapper
                .toCodeDetail(
                        codeRepository
                                .save(
                                        codeMapper
                                                .toCode(codeDetail)));
    }

    @Override
    public CodeDetail updateCode(Long id, CodeDetail codeDetail) {
        log.info(MessageResource.getMessage(ESLog.ES_031), id, codeDetail.getCode());
        Code code = codeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010)));
        code.setCode(codeDetail.getCode());
        code.setName(codeDetail.getName());
        code.setDescription(codeDetail.getDescription());
        return codeMapper
                .toCodeDetail(
                        codeRepository
                                .save(code));
    }

    @Override
    public Page<CodeDetail> searchCodeDetails(String searchTerm, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Code> codes = codeRepository.findByNameContainingIgnoreCase(searchTerm, pageRequest);
        List<CodeDetail> codeDetails = codes.getContent().stream().map(codeMapper::toCodeDetail).toList();
        return new PageImpl<>(codeDetails, pageRequest, codes.getTotalElements());
    }

    @Override
    public Page<CodeDetail> searchCodeDetailsByType(Long id, String searchTerm, int pageIndex, int pageSize) {
        codeTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Code> codes = codeRepository.findByCodeTypeIdAndNameContainingIgnoreCase(id, searchTerm, pageRequest);
        List<CodeDetail> codeDetails = codes.getContent().stream().map(codeMapper::toCodeDetail).toList();
        return new PageImpl<>(codeDetails, pageRequest, codes.getTotalElements());
    }

    @Override
    public Page<CodeDetail> searchCodeDetailsByType(String code, String searchTerm, int pageIndex, int pageSize) {
        CodeType codeType = codeTypeRepository.findByCode(code)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return searchCodeDetailsByType(codeType.getId(), searchTerm, pageIndex, pageSize);
    }

    @Override
    public void deleteCode(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_032), id);
        codeRepository.deleteById(id);
    }
}
