package com.elite.model;

import com.elite.core.bean.validation.PasswordValidator;
import com.elite.core.bean.validation.UsernameValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
@Schema(name = "User detail", description = "User detail")
public class UserDetail {

    @UsernameValidator
    @NotNull(message = "Username can't be null.")
    @Schema(format = "string", description = "Provide username")
    @JsonProperty("username")
    private String username;

    @Email
    @NotNull(message = "Email can't be null.")
    @Schema(format = "string", description = "Provide email")
    @JsonProperty("email")
    private String email;

    @PasswordValidator
    @NotNull(message = "Password can't be null.")
    @Schema(format = "string", description = "Provide password")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "First name can't be null.")
    @Schema(format = "string", description = "Provide first name")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last name can't be null.")
    @Schema(format = "string", description = "Provide last name")
    @JsonProperty("last_name")
    private String lastName;

}
