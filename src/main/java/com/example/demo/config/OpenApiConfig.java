package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "API",
    description = "API", version = "v1"))
@SecurityScheme(
    name = "bearerAuth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT")
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi userOpenApi() {
    String[] packagesToScan = {"com.example.demo.controller"};
    return GroupedOpenApi.builder().group("API").pathsToMatch("/api/**")
        .packagesToScan(packagesToScan).build();
  }

  @Bean
  public OpenAPI demoApi() {
    return new OpenAPI()
        .info(new Info().title(" API")
            .description("API Documentation")
            .version("v0.0.1")
            .license(new License().name("Apache 2.0").url("https://springdoc.org")))
        .externalDocs(new ExternalDocumentation()
            .description("API Documentation")
            .url("https://springshop.wiki.github.org/docs"));
  }
}
