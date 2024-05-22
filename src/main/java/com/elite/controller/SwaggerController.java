package com.elite.controller;

import com.elite.constants.WebResource;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Controller
@RequestMapping(value = WebResource.CXT_PATH + "/swagger")
public class SwaggerController {

    @GetMapping()
    public String swagger() {
        return "swagger";
    }
}
