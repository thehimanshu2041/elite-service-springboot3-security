package com.elite.controller.config;

import com.elite.constants.WebResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = WebResource.CXT_PATH + "/config/code")
@Tag(name = "Code Service", description = "Code Service.")
public class CodeController {
}
