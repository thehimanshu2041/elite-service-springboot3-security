package com.elite.model.config;

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
@Schema(name = "Country Detail", description = "Country detail")
public class CountryDetail {

    @Schema(format = "integer", description = "Provide username")
    @JsonProperty("id")
    private Long id;

    @Schema(format = "string", description = "Provide username")
    @JsonProperty("isp")
    private String isp;

    @Schema(format = "string", description = "Provide username")
    @JsonProperty("name")
    private String name;

    @Schema(format = "string", description = "Provide username")
    @JsonProperty("niceName")
    private String niceName;

    @Schema(format = "string", description = "Provide username")
    @JsonProperty("iso3")
    private String iso3;

    @Schema(format = "string", description = "Provide username")
    @JsonProperty("numCode")
    private String numCode;

    @Schema(format = "integer", description = "Provide username")
    @JsonProperty("phoneCode")
    private Long phoneCode;
}
