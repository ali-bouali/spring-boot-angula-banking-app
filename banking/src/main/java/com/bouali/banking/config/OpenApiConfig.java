package com.bouali.banking.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ali Bouali
 */

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customnOpenApiConfig() {
    final String securitySchemeName = "bearerAuth";
    return new OpenAPI()
        .addSecurityItem(
            new SecurityRequirement()
                .addList(securitySchemeName)
        )
        .components(
            new Components()
                .addSecuritySchemes(
                    securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        );

  }
}
