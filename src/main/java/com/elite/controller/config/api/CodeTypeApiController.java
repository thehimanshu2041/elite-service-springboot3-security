package com.elite.controller.config.api;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeTypeModel;
import com.elite.model.config.CodeTypeReqModel;
import com.elite.service.config.CodeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/config/code-type")
@Tag(name = "Code type Api", description = "Code type Api")
public class CodeTypeApiController {

    private final CodeTypeService codeTypeService;

    public CodeTypeApiController(CodeTypeService codeTypeService) {
        this.codeTypeService = codeTypeService;
    }

    @Operation(summary = "Save code type", description = "Save code type")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CodeTypeModel createCodeType(@Valid @RequestBody CodeTypeReqModel codeTypeReqModel) {
        return codeTypeService.createCodeType(codeTypeReqModel);
    }

    @Operation(summary = "Update code type", description = "Update code type")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public CodeTypeModel updateCodeType(@NotNull @PathVariable Long id,
                                        @Valid @RequestBody CodeTypeReqModel codeTypeReqModel) {
        return codeTypeService.updateCodeType(id, codeTypeReqModel);
    }

    @Operation(summary = "Delete code type", description = "Delete code type")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = void.class)))
            })
    @LogExecution
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public Boolean deleteCodeType(@NotNull @PathVariable Long id) {
        return codeTypeService.deleteCodeType(id);
    }

}
