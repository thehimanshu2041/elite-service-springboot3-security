package com.elite.model.user;

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
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "UserReqModel", description = "User req model")
public class UserReqModel {

    @UsernameValidator
    @NotNull(message = "Username can't be null.")
    @Schema(format = "string", description = "Username")
    @JsonProperty("username")
    private String username;

    @Email
    @NotNull(message = "Email can't be null.")
    @Schema(format = "string", description = "Email")
    @JsonProperty("email")
    private String email;

    @PasswordValidator
    @NotNull(message = "Password can't be null.")
    @Schema(format = "string", description = "Password")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "First name can't be null.")
    @Schema(format = "string", description = "First name")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last name can't be null.")
    @Schema(format = "string", description = "Last name")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Gender can't be null.")
    @Schema(format = "string", description = "Gender")
    @JsonProperty("gender")
    private Long genderId;

    @NotNull(message = "Address can't be null.")
    @Schema(format = "string", description = "Address")
    @JsonProperty("address")
    private String address;

    @NotNull(message = "Phone can't be null.")
    @Schema(format = "integer", description = "Phone")
    @JsonProperty("phone")
    private Long phone;

    @NotNull(message = "Country can't be null.")
    @Schema(format = "string", description = "Country")
    @JsonProperty("country")
    private Long countryId;

}
