package com.example.starterkit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Starter Kit API")
                        .version("1.0.0")
                        .description("🚀 API documentation for the Spring Boot Starter Kit project.")
                        .contact(new Contact()
                                .name("Vishwanath Patil")
                                .email("vishwanathmalipatil@gmail.com")
                                .url("https://github.com/patilvishi/spring-boot-starter-kit"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
