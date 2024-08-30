package com.elite.model.config;

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
@Schema(name = "CodeModel", description = "Code model")
public class CodeModel {

    @Schema(format = "integer", description = "Id")
    @JsonProperty("id")
    private Long id;

    @Schema(format = "string", description = "Code")
    @JsonProperty("code")
    private String code;

    @Schema(format = "string", description = "Name")
    @JsonProperty("name")
    private String name;

    @Schema(format = "string", description = "Code")
    @JsonProperty("description")
    private String description;

    @Schema(format = "integer", description = "Reference id")
    @JsonProperty("codeTypeId")
    private Long codeTypeId;
}
