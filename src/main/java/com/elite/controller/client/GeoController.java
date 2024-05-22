package com.elite.controller.client;


import com.elite.constants.WebResource;
import com.elite.core.swagger.ApiSecuritySchemes;
import com.elite.model.client.GeoInfo;
import com.elite.service.client.GeoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/client/geo")
@Tag(name = "Geo Service", description = "Geo Service.")
public class GeoController {

    private final GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @Operation(summary = "Geo info", description = "Geo info", security = @SecurityRequirement(name = ApiSecuritySchemes.JWT_SECURITY_SCHEME))
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = GeoInfo.class)))
            })
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public GeoInfo getGeoInfo() {
        return geoService.getGeoInfo();
    }
}
