package com.elite.model.user;

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
@Schema(name = "UserPatchSettingReqModel", description = "User patch setting req model")
public class UserPatchSettingReqModel {

    @NotNull(message = "Refresh credentials can't be null.")
    @Schema(format = "boolean", description = "Refresh credentials")
    @JsonProperty("refresh_credentials")
    private Boolean refreshCredentials;

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
