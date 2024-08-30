package com.elite.model.user;

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
@Schema(name = "UserSettingReqModel", description = "User Setting req model")
public class UserSettingReqModel {

    @NotNull(message = "Uid can't be null.")
    @Schema(format = "string", description = "Uid")
    @JsonProperty("uid")
    private String uid;

    @NotNull(message = "Secret can't be null.")
    @Schema(format = "string", description = "Secret")
    @JsonProperty("secret")
    private String secret;

    @NotNull(message = "Token can't be null.")
    @Schema(format = "string", description = "Token")
    @JsonProperty("token")
    private String token;

    @NotNull(message = "Notification can't be null.")
    @Schema(format = "boolean", description = "Notification")
    @JsonProperty("notification")
    private Boolean notification;

    @NotNull(message = "Email can't be null.")
    @Schema(format = "boolean", description = "Email")
    @JsonProperty("email")
    private Boolean email;

    @NotNull(message = "Night mode id can't be null.")
    @Schema(format = "boolean", description = "NightMode")
    @JsonProperty("night_mode")
    private Boolean nightMode;

}
