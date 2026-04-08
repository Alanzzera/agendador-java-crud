package com.alanzzera.agendador.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Agendador")
                        .description(
                                "API REST para sistema de agendamento.<br>© Alan Griebler - Todos os direitos reservados.<br>")
                        .version("1.0.0")
                        .contact(
                                new Contact()
                                        .name("Alanzzera")
                                        .url("https://github.com/Alanzzera")));
    }
}
