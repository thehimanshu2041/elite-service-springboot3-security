package com.elite.model.user;

import com.elite.model.config.CodeModel;
import com.elite.model.config.CountryModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserModel", description = "User model")
public class UserModel {

    @Schema(format = "integer", description = "Id")
    @JsonProperty("id")
    private Long id;

    @Schema(format = "string", description = "Username")
    @JsonProperty("username")
    private String username;

    @Schema(format = "string", description = "Email")
    @JsonProperty("email")
    private String email;

    @Schema(format = "string", description = "Password")
    @JsonProperty("password")
    private String password;

    @Schema(format = "string", description = "First name")
    @JsonProperty("first_name")
    private String firstName;

    @Schema(format = "string", description = "Last name")
    @JsonProperty("last_name")
    private String lastName;

    @Schema(format = "string", description = "Gender")
    @JsonProperty("gender")
    private CodeModel gender;

    @Schema(format = "string", description = "Address")
    @JsonProperty("address")
    private String address;

    @Schema(format = "integer", description = "Phone")
    @JsonProperty("phone")
    private Long phone;

    @Schema(format = "string", description = "Country")
    @JsonProperty("country")
    private CountryModel country;

}
