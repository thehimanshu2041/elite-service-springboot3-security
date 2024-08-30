package com.elite.model.config;

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
@Schema(name = "CountryReqModel", description = "Country req model")
public class CountryReqModel {

    @NotNull(message = "Isp can't be null.")
    @Schema(format = "string", description = "Isp")
    @JsonProperty("isp")
    private String isp;

    @NotNull(message = "Name can't be null.")
    @Schema(format = "string", description = "Name")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Nice name can't be null.")
    @Schema(format = "string", description = "Nice name")
    @JsonProperty("niceName")
    private String niceName;

    @Schema(format = "string", description = "Iso3")
    @JsonProperty("iso3")
    private String iso3;

    @Schema(format = "string", description = "Num code")
    @JsonProperty("numCode")
    private Long numCode;

    @NotNull(message = "Phone code can't be null.")
    @Schema(format = "integer", description = "Phone code")
    @JsonProperty("phoneCode")
    private Long phoneCode;
}
