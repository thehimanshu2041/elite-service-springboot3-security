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

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
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

    @NotNull(message = "Gender can't be null.")
    @Schema(format = "string", description = "Provide gender")
    @JsonProperty("gender")
    private Gender gender;

    @NotNull(message = "Address can't be null.")
    @Schema(format = "string", description = "Provide address")
    @JsonProperty("address")
    private String address;

    @NotNull(message = "Phone can't be null.")
    @Schema(format = "integer", description = "Provide phone")
    @JsonProperty("phone")
    private BigInteger phone;

    @NotNull(message = "Country can't be null.")
    @Schema(format = "string", description = "Provide Country")
    @JsonProperty("country")
    private Country country;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Validated
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(name = "Gender detail", description = "Gender detail")
    public static class Gender {

        @Schema(format = "integer", description = "Provide id")
        @JsonProperty("id")
        private Long id;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("code")
        private String code;

        @Schema(format = "string", description = "Provide name")
        @JsonProperty("name")
        private String name;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("description")
        private String description;

        @Schema(format = "string", description = "Provide gender type")
        @JsonProperty("genderType")
        private GenderType genderType;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Validated
        @JsonIgnoreProperties(ignoreUnknown = true)
        @Schema(name = "Gender type detail", description = "Gender type detail")
        public static class GenderType {
            @Schema(format = "integer", description = "Provide id")
            @JsonProperty("id")
            private Long id;

            @Schema(format = "string", description = "Provide code")
            @JsonProperty("code")
            private String code;

            @Schema(format = "string", description = "Provide name")
            @JsonProperty("name")
            private String name;

            @Schema(format = "string", description = "Provide code")
            @JsonProperty("description")
            private String description;
        }

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Validated
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(name = "Country detail", description = "Country detail")
    public static class Country {

        @Schema(format = "integer", description = "Provide id")
        @JsonProperty("id")
        private Long id;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("isp")
        private String isp;

        @Schema(format = "string", description = "Provide name")
        @JsonProperty("name")
        private String name;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("niceName")
        private String niceName;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("iso3")
        private String iso3;

        @Schema(format = "string", description = "Provide code")
        @JsonProperty("numCode")
        private String numCode;

        @Schema(format = "integer", description = "Provide code")
        @JsonProperty("phoneCode")
        private BigInteger phoneCode;

    }

}
