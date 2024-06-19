package com.anthony.biblioteca_virtual.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(name = "Anthony",
                email = "antho7294@gmail.com",
                url = "https://github.com/An7h0ny1/Biblioteca-virtual.git"),
        title = "Biblioteca Virtual",
        version = "1.0.0",
        description = "API para la red de lectores y recomendación de libros.",
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
        termsOfService = "http://swagger.io/terms/"
        ),
        servers = {
                @Server(
                        description = "Local Env",
                        url = "http://localhost:8080/api/v1"
                ),
                @Server(
                        description = "Production Env",
                        url = "http://localhost:8080/api/v1/2"
                )
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authorization header using the Bearer scheme.",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
