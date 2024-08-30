package com.elite.controller.config;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeTypeModel;
import com.elite.service.config.CodeTypeService;
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
@RequestMapping(value = WebResource.CXT_PATH + "/config/code-type")
@Tag(name = "Code type Api", description = "Code type Api")
public class CodeTypeController {

    private final CodeTypeService codeTypeService;

    public CodeTypeController(CodeTypeService codeTypeService) {
        this.codeTypeService = codeTypeService;
    }


    @Operation(summary = "Get code type list", description = "Get code type list")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeTypeModel.class))))
            })
    @LogExecution
    @GetMapping
    public List<CodeTypeModel> getCodeTypeDetails() {
        return codeTypeService.getCodeTypeDetails();
    }

    @Operation(summary = "Get code type by id", description = "Get code type by id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CodeTypeModel.class)))
            })
    @LogExecution
    @GetMapping("{id}")
    public CodeTypeModel getCodeTypeById(@NotNull @PathVariable Long id) {
        return codeTypeService.getCodeTypeById(id);
    }

    @Operation(summary = "Get code type by code", description = "Get code type by code")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CodeTypeModel.class)))
            })
    @LogExecution
    @GetMapping("code/{code}")
    public CodeTypeModel getCodeTypeByCode(@NotNull @PathVariable String code) {
        return codeTypeService.getCodeTypeByCode(code);
    }

    @Operation(summary = "Search code type detail by criteria", description = "Search code type detail by criteria")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeTypeModel.class))))
            })
    @LogExecution
    @GetMapping("search")
    public Page<CodeTypeModel> searchCodeTypeDetails(@Nullable @RequestParam String searchTerm,
                                                     @RequestParam(defaultValue = "0") int pageIndex,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return codeTypeService.searchCodeTypeDetails(searchTerm, pageIndex, pageSize);
    }
}
