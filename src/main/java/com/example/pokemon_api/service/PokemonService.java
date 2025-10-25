package com.example.pokemon_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PokemonService {

    private final WebClient webClient;

    public PokemonService(@Value("${pokemon.api.base-url}") String baseUrl,
                         @Value("${pokemon.api.timeout}") int timeout,
                         WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<String> getPokemon(String nameOrId) {
        return webClient.get()
                .uri("/pokemon/{nameOrId}", nameOrId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getPokemonList(int limit, int offset) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon")
                        .queryParam("limit", limit)
                        .queryParam("offset", offset)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getPokemonSpecies(String nameOrId) {
        return webClient.get()
                .uri("/pokemon-species/{nameOrId}", nameOrId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getPokemonAbility(String nameOrId) {
        return webClient.get()
                .uri("/ability/{nameOrId}", nameOrId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getPokemonType(String nameOrId) {
        return webClient.get()
                .uri("/type/{nameOrId}", nameOrId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getPokemonMove(String nameOrId) {
        return webClient.get()
                .uri("/move/{nameOrId}", nameOrId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }

    public Mono<String> getGenericEndpoint(String endpoint) {
        return webClient.get()
                .uri("/{endpoint}", endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofMillis(5000));
    }
}
