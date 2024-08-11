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
@Schema(name = "Code Detail", description = "Code Detail")
public class CodeDetail {

    @Schema(format = "integer", description = "Provide id")
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Code can't be null.")
    @Schema(format = "string", description = "Provide code")
    @JsonProperty("code")
    private String code;

    @NotNull(message = "Name can't be null.")
    @Schema(format = "string", description = "Provide name")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Description can't be null.")
    @Schema(format = "string", description = "Provide code")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Reference id can't be null.")
    @Schema(format = "integer", description = "Provide reference id")
    @JsonProperty("codeTypeId")
    private Long codeTypeId;
}
