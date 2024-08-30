package com.elite.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "UserSettingModel", description = "User Setting model")
public class UserSettingModel {

    @Schema(format = "integer", description = "Id")
    @JsonProperty("id")
    private Long id;

    @Schema(format = "string", description = "Uid")
    @JsonProperty("uid")
    private String uid;

    @Schema(format = "string", description = "Secret")
    @JsonProperty("secret")
    private String secret;

    @Schema(format = "string", description = "Token")
    @JsonProperty("token")
    private String token;

    @Schema(format = "boolean", description = "Notification")
    @JsonProperty("notification")
    private Boolean notification;

    @Schema(format = "boolean", description = "Email")
    @JsonProperty("email")
    private Boolean email;

    @Schema(format = "boolean", description = "NightMode")
    @JsonProperty("night_mode")
    private Boolean nightMode;

}
