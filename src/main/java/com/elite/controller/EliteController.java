package com.elite.controller;

import com.elite.constants.WebResource;
import com.elite.core.cache.CacheStore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = WebResource.CXT_PATH)
@Tag(name = "Elite Service", description = "Elite Service.")
public class EliteController {

    private final CacheStore  cacheStore;

    public EliteController(CacheStore cacheStore) {
        this.cacheStore = cacheStore;
    }

    @Operation(summary = "Elite detail", description = "Elite detail")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operation Successful",
                            content =
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class)))
            })
    @GetMapping
    public String getElite() {
        if(cacheStore.check("B")){
            return "Cache Working... " + cacheStore.get("B");
        }else{
            cacheStore.add("B", "test");
        }
        return "Cache Not Working...";
    }
}
