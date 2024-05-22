package com.elite;

import com.elite.core.swagger.ApiSecuritySchemes;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Elite Service", description = "Elite Service", version = "0.1"))
@ApiSecuritySchemes
@SpringBootApplication
public class EliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EliteApplication.class, args);
	}

}
