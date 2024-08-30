package com.elite.controller.config;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeModel;
import com.elite.service.config.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = WebResource.CXT_PATH + "/config/code")
@Tag(name = "Code Api", description = "Code Api")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @Operation(summary = "Get code list", description = "Get code list")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping
    public List<CodeModel> getCodeDetails() {
        return codeService.getCodeDetails();
    }

    @Operation(summary = "Get code by id", description = "Get code by id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CodeModel.class)))
            })
    @LogExecution
    @GetMapping("{id}")
    public CodeModel getCodeById(@NotNull @PathVariable Long id) {
        return codeService.getCodeById(id);
    }

    @Operation(summary = "Get code by type", description = "Get code by type")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CodeModel.class)))
            })
    @LogExecution
    @GetMapping("code/{code}")
    public CodeModel getCodeByType(@NotNull @PathVariable String code) {
        return codeService.getCodeByCode(code);
    }

    @Operation(summary = "Get code list by type id", description = "Get code list by type id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping("code-type/{id}")
    public List<CodeModel> getCodeDetailsByTypeId(@NotNull @PathVariable Long id) {
        return codeService.getCodeDetailsByTypeId(id);
    }

    @Operation(summary = "Get code list by code", description = "Get code list by code")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping("code-type/code/{code}")
    public List<CodeModel> getCodeDetailsByTypeCode(@NotNull @PathVariable String code) {
        return codeService.getCodeDetailsByTypeCode(code);
    }

    @Operation(summary = "Search code list by criteria", description = "Search code list by criteria")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping("search")
    public Page<CodeModel> searchCodeDetails(@Nullable @RequestParam String searchTerm,
                                             @RequestParam(defaultValue = "0") int pageIndex,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetails(searchTerm, pageIndex, pageSize);
    }

    @Operation(summary = "Search code list with type id and criteria", description = "Search code list with type id and criteria")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping("code-type/search/{id}")
    public Page<CodeModel> searchCodeDetailsByTypeId(@NotNull @PathVariable Long id,
                                                     @Nullable @RequestParam String searchTerm,
                                                     @RequestParam(defaultValue = "0") int pageIndex,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetailsByTypeId(id, searchTerm, pageIndex, pageSize);
    }

    @Operation(summary = "Search code list with type code and criteria", description = "Search code list with type code and criteria")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeModel.class))))
            })
    @LogExecution
    @GetMapping("code-type/code/search/{code}")
    public Page<CodeModel> searchCodeDetailsByTypeCode(@NotNull @PathVariable String code,
                                                       @Nullable @RequestParam String searchTerm,
                                                       @RequestParam(defaultValue = "0") int pageIndex,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetailsByTypeCode(code, searchTerm, pageIndex, pageSize);
    }
}
