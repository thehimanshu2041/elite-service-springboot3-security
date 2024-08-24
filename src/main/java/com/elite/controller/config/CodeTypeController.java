package com.elite.controller.config;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeTypeDetail;
import com.elite.service.config.CodeTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/config/code-type")
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
                                    array = @ArraySchema(schema = @Schema(implementation = CodeTypeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public List<CodeTypeDetail> getCodeTypeDetails() {
        return codeTypeService.getCodeTypeDetails();
    }

    @Operation(summary = "Search code type detail", description = "Search code type detail")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeTypeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("search")
    public Page<CodeTypeDetail> searchCodeTypeDetails(@Nullable @RequestParam String searchTerm,
                                                      @RequestParam(defaultValue = "0") int pageIndex,
                                                      @RequestParam(defaultValue = "10") int pageSize) {
        return codeTypeService.searchCodeTypeDetails(searchTerm, pageIndex, pageSize);
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
                                    schema = @Schema(implementation = CodeTypeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}")
    public CodeTypeDetail getCodeType(@NotNull @PathVariable Long id) {
        return codeTypeService.getCodeType(id);
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
                                    schema = @Schema(implementation = CodeTypeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code/{code}")
    public CodeTypeDetail getCodeType(@NotNull @PathVariable String code) {
        return codeTypeService.getCodeType(code);
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
                                    schema = @Schema(implementation = CodeTypeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CodeTypeDetail createCodeType(@Valid @RequestBody CodeTypeDetail codeTypeDetail) {
        return codeTypeService.createCodeType(codeTypeDetail);
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
                                    schema = @Schema(implementation = CodeTypeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public CodeTypeDetail updateCodeType(@NotNull @PathVariable Long id,
                                         @Valid @RequestBody CodeTypeDetail codeTypeDetail) {
        return codeTypeService.updateCodeType(id, codeTypeDetail);
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
    public void deleteCodeType(@NotNull @PathVariable Long id) {
        codeTypeService.deleteCodeType(id);
    }
}
