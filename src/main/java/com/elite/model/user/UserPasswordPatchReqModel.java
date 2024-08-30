package com.elite.model.user;

import com.elite.core.bean.validation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserPasswordPatchReqModel", description = "User password patch req model")
public class UserPasswordPatchReqModel {

    @PasswordValidator
    @NotNull(message = "Old password can't be null.")
    @Schema(format = "string", description = "Old password")
    @JsonProperty("old_password")
    private String oldPassword;

    @PasswordValidator
    @NotNull(message = "Password can't be null.")
    @Schema(format = "string", description = "Password")
    @JsonProperty("password")
    private String password;
}
