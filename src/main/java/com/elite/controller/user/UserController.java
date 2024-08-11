package com.elite.controller.user;

import com.elite.constants.WebResource;
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
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/user")
@Tag(name = "User api", description = "User api")
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
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public UserDetail getUserDetail() {
        return userService.getUserDetail();
    }
}
