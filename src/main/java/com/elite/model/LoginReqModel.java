package com.elite.model;

import com.elite.core.bean.validation.PasswordValidator;
import com.elite.core.bean.validation.UsernameValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "LoginReqModel", description = "Login request model")
public class LoginReqModel {

    @UsernameValidator
    @NotNull(message = "Username can't be null.")
    @Schema(format = "string", description = "Username")
    @JsonProperty("username")
    private String username;

    @PasswordValidator
    @NotNull(message = "Password can't be null.")
    @Schema(format = "string", description = "Password")
    @JsonProperty("password")
    private String password;
}
