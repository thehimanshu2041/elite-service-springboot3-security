package com.elite.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Authentication detail", description = "Authentication detail")
public class AuthenticationDetail {

    @Schema(format = "string", description = "Provided access token")
    @JsonProperty("access_token")
    private String access_token;

    @Schema(description = "User roles")
    @JsonProperty("roles")
    private List<String> roles;

}
