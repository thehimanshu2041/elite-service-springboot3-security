package com.elite.controller.config.api;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CountryModel;
import com.elite.model.config.CountryReqModel;
import com.elite.service.config.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = WebResource.AUTH_PATH + "/config/country")
@Tag(name = "Country Api", description = "Country Api")
public class CountryApiController {

    private final CountryService countryService;

    public CountryApiController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Operation(summary = "Create country", description = "Create country")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CountryModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public CountryModel createCountry(@Valid @RequestBody CountryReqModel countryReqModel) {
        return countryService.createCountry(countryReqModel);
    }

    @Operation(summary = "Update country", description = "Update country")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CountryModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("{id}")
    public CountryModel updateCountry(@NotNull @PathVariable Long id, @Valid @RequestBody CountryReqModel countryReqModel) {
        return countryService.updateCountry(id, countryReqModel);
    }

    @Operation(summary = "Delete country", description = "Delete country")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CountryModel.class)))
            })
    @LogExecution
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public Boolean deleteCountry(@NotNull @PathVariable Long id) {
        return countryService.deleteCountry(id);
    }
}
