package com.elite.controller.user;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.core.swagger.ApiSecuritySchemes;
import com.elite.model.user.UserDetail;
import com.elite.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/user")
@Tag(name = "User Api", description = "User Api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get current logged in User detail", description = "Get current logged in User detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public UserDetail getUserDetail() {
        return userService.getUserDetail();
    }

    @Operation(summary = "Get current logged in User detail", description = "Get current logged in User detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}")
    public UserDetail getUserDetail(@NotNull @PathVariable Long id) {
        return userService.getUserDetail(id);
    }

    @Operation(summary = "Search User detail", description = "Search User detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/search")
    public Page<UserDetail> searchUserDetail(@Nullable @RequestParam String searchTerm,
                                             @RequestParam(defaultValue = "0") int pageIndex,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return userService.searchUserDetail(searchTerm, pageIndex, pageSize);
    }

    @Operation(summary = "Get current logged in User detail", description = "Get current logged in User detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDetail.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("{id}")
    public UserDetail updateUserDetail(@NotNull @PathVariable Long id, @Valid @RequestBody UserDetail userDetail) {
        return userService.updateUserDetail(id, userDetail);
    }

    @Operation(summary = "Download current logged in User detail", description = "Download current logged in User detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_PDF_VALUE,
                                    schema = @Schema(implementation = Byte.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/download")
    public ResponseEntity<?> downloadUserDetail() {
        return userService.downloadUserDetail();
    }
}
