package com.elite.controller.config.api;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeModel;
import com.elite.model.config.CodeReqModel;
import com.elite.service.config.CodeService;
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
@RequestMapping(value = WebResource.AUTH_PATH + "/config/code")
@Tag(name = "Code Api", description = "Code Api")
public class CodeApiController {

    private final CodeService codeService;

    public CodeApiController(CodeService codeService) {
        this.codeService = codeService;
    }


    @Operation(summary = "Save code", description = "Save code")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CodeModel createCode(@Valid @RequestBody CodeReqModel codeReqModel) {
        return codeService.createCode(codeReqModel);
    }

    @Operation(summary = "Update code", description = "Update code")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public CodeModel updateCode(@NotNull @PathVariable Long id,
                                @Valid @RequestBody CodeReqModel codeReqModel) {
        return codeService.updateCode(id, codeReqModel);
    }

    @Operation(summary = "Delete code", description = "Delete code")
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
    public Boolean deleteCode(@NotNull @PathVariable Long id) {
        return codeService.deleteCode(id);
    }
}
