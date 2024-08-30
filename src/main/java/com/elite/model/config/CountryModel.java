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
@Schema(name = "CountryModel", description = "Country model")
public class CountryModel {

    @Schema(format = "integer", description = "Id")
    @JsonProperty("id")
    private Long id;

    @Schema(format = "string", description = "Isp")
    @JsonProperty("isp")
    private String isp;

    @Schema(format = "string", description = "Name")
    @JsonProperty("name")
    private String name;

    @Schema(format = "string", description = "Nice name")
    @JsonProperty("niceName")
    private String niceName;

    @Schema(format = "string", description = "Iso3")
    @JsonProperty("iso3")
    private String iso3;

    @Schema(format = "string", description = "Num code")
    @JsonProperty("numCode")
    private Long numCode;

    @Schema(format = "integer", description = "Phone code")
    @JsonProperty("phoneCode")
    private Long phoneCode;
}
