package com.elite.controller.user.api;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.core.swagger.ApiSecuritySchemes;
import com.elite.model.user.*;
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
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get current loggedIn user detail", description = "Get current loggedIn user detail", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public UserModel getUserDetails() {
        return userService.getUserDetails();
    }

    @Operation(summary = "Get user detail by id", description = "Get user detail by id", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("{id}")
    public UserModel getUserDetailById(@NotNull @PathVariable Long id) {
        return userService.getUserDetailById(id);
    }

    @Operation(summary = "Update user detail by id", description = "Update user detail by id", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PutMapping("{id}")
    public UserModel updateUserDetail(@NotNull @PathVariable Long id, @Valid @RequestBody UserReqModel userReqModel) {
        return userService.updateUserDetail(id, userReqModel);
    }

    @Operation(summary = "Patch user detail by id", description = "Patch user detail by id", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PatchMapping("{id}")
    public UserModel patchUserDetail(@NotNull @PathVariable Long id, @Valid @RequestBody UserPatchReqModel userPatchReqModel) {
        return userService.patchUserDetail(id, userPatchReqModel);
    }

    @Operation(summary = "Patch user password", description = "Patch user password", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PatchMapping("/password/{id}")
    public UserModel patchUserPassword(@NotNull @PathVariable Long id, @Valid @RequestBody UserPasswordPatchReqModel userPasswordPatchReqModel) {
        return userService.patchUserPassword(id, userPasswordPatchReqModel);
    }

    @Operation(summary = "Delete user detail by id", description = "Delete user detail by id", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Boolean.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public Boolean deleteUserDetail(@NotNull @PathVariable Long id) {
        return userService.deleteUserDetail(id);
    }

    @Operation(summary = "Get current loggedIn user settings", description = "Get current loggedIn user settings", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_PDF_VALUE,
                                    schema = @Schema(implementation = UserSettingModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/settings")
    public UserSettingModel getUserSettings() {
        return userService.getUserSettings();
    }

    @Operation(summary = "Patch user settings", description = "Patch user settings", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_PDF_VALUE,
                                    schema = @Schema(implementation = UserSettingModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PatchMapping("/settings/{id}")
    public UserSettingModel patchUserSettings(@NotNull @PathVariable Long id, @Valid @RequestBody UserPatchSettingReqModel userPatchSettingReqModel) {
        return userService.patchUserSettings(id, userPatchSettingReqModel);
    }

    @Operation(summary = "Get current logged in User detail using pdf", description = "Get current logged in User detail using pdf", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_PDF_VALUE,
                                    schema = @Schema(implementation = ResponseEntity.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/pdf")
    public ResponseEntity<?> getUserDetailsPdf() {
        return userService.getUserDetailsPdf();
    }

    @Operation(summary = "Get current logged in User using qr code", description = "Get current logged in User using qr code", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_PDF_VALUE,
                                    schema = @Schema(implementation = ResponseEntity.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/qr")
    public ResponseEntity<?> getUserDetailsQR() {
        return userService.getUserDetailsQR();
    }

    @Operation(summary = "Search user detail by criteria", description = "Search User detail by criteria", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/search")
    public Page<UserModel> searchUserDetails(@Nullable @RequestParam String searchTerm,
                                             @RequestParam(defaultValue = "0") int pageIndex,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return userService.searchUserDetails(searchTerm, pageIndex, pageSize);
    }
}
