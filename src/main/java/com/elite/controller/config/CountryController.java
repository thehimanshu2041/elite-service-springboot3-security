package com.elite.controller.config;

import com.elite.constants.WebResource;
import com.elite.core.log.LogExecution;
import com.elite.model.config.CountryModel;
import com.elite.service.config.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = WebResource.CXT_PATH + "/config/country")
@Tag(name = "Country Api", description = "Country Api")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get country list", description = "Get country list")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CountryModel.class))))
            })
    @LogExecution
    @GetMapping
    public List<CountryModel> getCountries() {
        return countryService.getCountries();
    }

    @Operation(summary = "Get country by id", description = "Get country by id")
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
    @GetMapping("{id}")
    public CountryModel getCountryById(@NotNull @PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @Operation(summary = "Get country by code", description = "Get country by code")
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
    @GetMapping("code/{code}")
    public CountryModel getCountryByCode(@NotNull @PathVariable String code) {
        return countryService.getCountryByCode(code);
    }

    @Operation(summary = "Search countries by criteria", description = "Search countries by criteria")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = CountryModel.class))))
            })
    @LogExecution
    @GetMapping("/search")
    public Page<CountryModel> searchCountries(@Nullable @RequestParam String searchTerm,
                                              @RequestParam(defaultValue = "0") int pageIndex,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return countryService.searchCountries(searchTerm, pageIndex, pageSize);
    }
}
