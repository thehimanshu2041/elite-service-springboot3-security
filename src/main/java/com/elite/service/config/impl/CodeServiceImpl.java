package com.elite.service.config.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.entity.config.Code;
import com.elite.entity.config.CodeType;
import com.elite.mapper.config.CodeMapper;
import com.elite.model.config.CodeModel;
import com.elite.model.config.CodeReqModel;
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
    public List<CodeModel> getCodeDetails() {
        log.info(MessageResource.getMessage(ESLog.ES_026));
        return codeRepository
                .findAll()
                .stream()
                .map(codeMapper::convertCodeToCodeModel)
                .collect(Collectors.toList());
    }

    @Override
    public CodeModel getCodeById(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_027), id);
        return codeMapper
                .convertCodeToCodeModel(
                        codeRepository
                                .findById(id)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010))));
    }

    @Override
    public CodeModel getCodeByCode(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_028), code);
        return codeMapper
                .convertCodeToCodeModel(
                        codeRepository
                                .findByCode(code)
                                .orElseThrow(() ->
                                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010))));
    }


    @Override
    public CodeModel createCode(CodeReqModel codeReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_030), codeReqModel.getCode(), codeReqModel.getCodeTypeId());
        codeTypeRepository.findById(codeReqModel.getCodeTypeId())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return codeMapper
                .convertCodeToCodeModel(
                        codeRepository
                                .save(
                                        codeMapper
                                                .convertCodeReqModelToCode(codeReqModel)));
    }

    @Override
    public CodeModel updateCode(Long id, CodeReqModel codeReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_031), id, codeReqModel.getCode());
        Code code = codeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010)));
        code.setCode(codeReqModel.getCode());
        code.setName(codeReqModel.getName());
        code.setDescription(codeReqModel.getDescription());
        return codeMapper
                .convertCodeToCodeModel(
                        codeRepository
                                .save(code));
    }

    @Override
    public Boolean deleteCode(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_032), id);
        Code code = codeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_010)));
        codeRepository.delete(code);
        return true;
    }

    @Override
    public List<CodeModel> getCodeDetailsByTypeId(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_029), id);
        codeTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return codeRepository
                .findAllByCodeTypeIdOrderByUpdatedDateDesc(id)
                .stream()
                .map(codeMapper::convertCodeToCodeModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CodeModel> getCodeDetailsByTypeCode(String code) {
        log.info(MessageResource.getMessage(ESLog.ES_029), code);
        CodeType codeType = codeTypeRepository.findByCode(code)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return getCodeDetailsByTypeId(codeType.getId());
    }


    @Override
    public Page<CodeModel> searchCodeDetails(String searchTerm, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Code> codes = codeRepository.findByNameContainingIgnoreCaseOrderByUpdatedDateDesc(searchTerm, pageRequest);
        List<CodeModel> codeModels = codes.getContent().stream().map(codeMapper::convertCodeToCodeModel).toList();
        return new PageImpl<>(codeModels, pageRequest, codes.getTotalElements());
    }

    @Override
    public Page<CodeModel> searchCodeDetailsByTypeId(Long id, String searchTerm, int pageIndex, int pageSize) {
        codeTypeRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Code> codes = codeRepository.findByCodeTypeIdAndNameContainingIgnoreCaseOrderByUpdatedDateDesc(id, searchTerm, pageRequest);
        List<CodeModel> codeModels = codes.getContent().stream().map(codeMapper::convertCodeToCodeModel).toList();
        return new PageImpl<>(codeModels, pageRequest, codes.getTotalElements());
    }

    @Override
    public Page<CodeModel> searchCodeDetailsByTypeCode(String code, String searchTerm, int pageIndex, int pageSize) {
        CodeType codeType = codeTypeRepository.findByCode(code)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_009)));
        return searchCodeDetailsByTypeId(codeType.getId(), searchTerm, pageIndex, pageSize);
    }

}
