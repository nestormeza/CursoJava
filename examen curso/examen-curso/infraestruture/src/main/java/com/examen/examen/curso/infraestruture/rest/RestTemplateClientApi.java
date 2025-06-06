package com.examen.examen.curso.infraestruture.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateClientApi {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
