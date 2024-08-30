package com.elite.model.config;

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
@Schema(name = "CodeTypeReqModel", description = "Code type req model")
public class CodeTypeReqModel {

    @NotNull(message = "Code can't be null.")
    @Schema(format = "string", description = "Code")
    @JsonProperty("code")
    private String code;

    @NotNull(message = "Name can't be null.")
    @Schema(format = "string", description = "Name")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Description can't be null.")
    @Schema(format = "string", description = "Code")
    @JsonProperty("description")
    private String description;
}
