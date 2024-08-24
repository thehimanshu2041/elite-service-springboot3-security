package com.elite.controller.config;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CodeDetail;
import com.elite.service.config.CodeService;
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
@RequestMapping(value = WebResource.AUTH_PATH + "/config/code")
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
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public List<CodeDetail> getCodeDetails() {
        return codeService.getCodeDetails();
    }

    @Operation(summary = "Search code list", description = "Search code list")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("search")
    public Page<CodeDetail> searchCodeDetails(@Nullable @RequestParam String searchTerm,
                                              @RequestParam(defaultValue = "0") int pageIndex,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetails(searchTerm, pageIndex, pageSize);
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
                                    schema = @Schema(implementation = CodeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}")
    public CodeDetail getCode(@NotNull @PathVariable Long id) {
        return codeService.getCode(id);
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
                                    schema = @Schema(implementation = CodeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code/{code}")
    public CodeDetail getCode(@NotNull @PathVariable String code) {
        return codeService.getCode(code);
    }

    @Operation(summary = "Get code list by id", description = "Get code list by id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code-type/{id}")
    public List<CodeDetail> getCodeDetailsByType(@NotNull @PathVariable Long id) {
        return codeService.getCodeDetailsByType(id);
    }

    @Operation(summary = "Search code list with id", description = "Search code list with id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code-type/search/{id}")
    public Page<CodeDetail> searchCodeDetailsByType(@NotNull @PathVariable Long id,
                                                    @Nullable @RequestParam String searchTerm,
                                                    @RequestParam(defaultValue = "0") int pageIndex,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetailsByType(id, searchTerm, pageIndex, pageSize);
    }

    @Operation(summary = "Get code list by code", description = "Get code list by code")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code-type/code/{code}")
    public List<CodeDetail> getCodeDetailsByType(@NotNull @PathVariable String code) {
        return codeService.getCodeDetailsByType(code);
    }

    @Operation(summary = "Search code list with code", description = "Search code list with code")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CodeDetail.class))))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("code-type/code/search/{code}")
    public Page<CodeDetail> searchCodeDetailsByType(@NotNull @PathVariable String code,
                                                    @Nullable @RequestParam String searchTerm,
                                                    @RequestParam(defaultValue = "0") int pageIndex,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        return codeService.searchCodeDetailsByType(code, searchTerm, pageIndex, pageSize);
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
                                    schema = @Schema(implementation = CodeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CodeDetail createCode(@Valid @RequestBody CodeDetail codeTypeDetail) {
        return codeService.createCode(codeTypeDetail);
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
                                    schema = @Schema(implementation = CodeDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public CodeDetail updateCode(@NotNull @PathVariable Long id,
                                 @Valid @RequestBody CodeDetail codeTypeDetail) {
        return codeService.updateCode(id, codeTypeDetail);
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
    public void deleteCode(@NotNull @PathVariable Long id) {
        codeService.deleteCode(id);
    }
}
