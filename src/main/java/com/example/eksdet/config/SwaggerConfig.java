package com.example.eksdet.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("API Gateway")
                        .version("1.0")
                        .description("Read‑only proxy to Node.js apps"));
    }
}
