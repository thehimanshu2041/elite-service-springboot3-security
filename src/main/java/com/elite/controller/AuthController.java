package com.elite.controller;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.LoginReqModel;
import com.elite.model.user.UserModel;
import com.elite.model.user.UserReqModel;
import com.elite.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = WebResource.CXT_PATH + "/auth")
@Tag(name = "Auth Api", description = "Auth Api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "User login", description = "User login")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                    schema = @Schema(implementation = String.class)))
            })
    @LogExecution
    @PostMapping(value = "login")
    public String login(@Valid @RequestBody LoginReqModel loginReqModel) {
        return userService.login(loginReqModel);
    }

    @Operation(summary = "User registration", description = "User registration")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.ALL_VALUE,
                                    schema = @Schema(implementation = Boolean.class)))
            })
    @LogExecution
    @PostMapping(value = "registration")
    public UserModel registration(@Valid @RequestBody UserReqModel userReqModel) {
        return userService.registration(userReqModel);
    }
}
