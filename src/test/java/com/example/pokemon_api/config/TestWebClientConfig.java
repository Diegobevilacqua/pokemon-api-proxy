package com.example.pokemon_api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
public class TestWebClientConfig {

    @Bean
    @Primary
    public WebClient.Builder testWebClientBuilder() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024));
    }
}
