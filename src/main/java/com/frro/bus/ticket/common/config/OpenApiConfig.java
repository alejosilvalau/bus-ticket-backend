package com.frro.bus.ticket.common.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Bus Ticket API")
                        .description("API for managing bus tickets, fleets, journeys and bookings")
                        .version("1.0.0"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public OpenApiCustomizer pageableOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null)
                return;
            for (PathItem pathItem : openApi.getPaths().values()) {
                Stream.of(pathItem.getGet(), pathItem.getPost(), pathItem.getPut(),
                        pathItem.getPatch(), pathItem.getDelete())
                        .filter(op -> op != null && op.getParameters() != null)
                        .forEach(operation -> {
                            List<Parameter> params = new ArrayList<>(operation.getParameters());
                            boolean hasPageable = params.removeIf(p -> "pageable".equals(p.getName()));
                            if (hasPageable) {
                                params.add(new Parameter()
                                        .name("page")
                                        .in("query")
                                        .description("Page number (0-based)")
                                        .schema(new IntegerSchema()
                                                .minimum(BigDecimal.ZERO)
                                                .example("0")));
                                params.add(new Parameter()
                                        .name("size")
                                        .in("query")
                                        .description("Page size (max 100)")
                                        .schema(new IntegerSchema()
                                                .minimum(BigDecimal.ZERO)
                                                .maximum(BigDecimal.valueOf(100))
                                                .example("20")));
                            }
                            operation.setParameters(params);
                        });
            }
        };
    }
}
