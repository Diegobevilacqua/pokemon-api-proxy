package com.example.pokemon_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private WebClient.Builder webClientBuilder;

    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        pokemonService = new PokemonService("https://pokeapi.co/api/v2", 5000, webClientBuilder);
    }

    @Test
    void getPokemon_ShouldReturnPokemonData_WhenValidNameProvided() {
        // Given
        String pokemonName = "pikachu";
        String expectedResponse = "{\"name\":\"pikachu\",\"id\":25}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemon(pokemonName))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getPokemon_ShouldReturnError_WhenPokemonNotFound() {
        // Given
        String pokemonName = "nonexistent";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(WebClientResponseException.create(404, "Not Found", null, null, null)));

        // When & Then
        StepVerifier.create(pokemonService.getPokemon(pokemonName))
                .expectError(WebClientResponseException.class)
                .verify();
    }

    @Test
    void getPokemonList_ShouldReturnPokemonList_WhenValidParametersProvided() {
        // Given
        int limit = 10;
        int offset = 0;
        String expectedResponse = "{\"count\":1328,\"results\":[]}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(java.util.function.Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemonList(limit, offset))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getPokemonSpecies_ShouldReturnSpeciesData_WhenValidNameProvided() {
        // Given
        String pokemonName = "pikachu";
        String expectedResponse = "{\"name\":\"pikachu\",\"evolution_chain\":{}}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemonSpecies(pokemonName))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getPokemonAbility_ShouldReturnAbilityData_WhenValidNameProvided() {
        // Given
        String abilityName = "static";
        String expectedResponse = "{\"name\":\"static\",\"effect\":\"May paralyze attacker\"}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemonAbility(abilityName))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getPokemonType_ShouldReturnTypeData_WhenValidNameProvided() {
        // Given
        String typeName = "electric";
        String expectedResponse = "{\"name\":\"electric\",\"damage_relations\":{}}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemonType(typeName))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getPokemonMove_ShouldReturnMoveData_WhenValidNameProvided() {
        // Given
        String moveName = "thunderbolt";
        String expectedResponse = "{\"name\":\"thunderbolt\",\"power\":90}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getPokemonMove(moveName))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    void getGenericEndpoint_ShouldReturnData_WhenValidEndpointProvided() {
        // Given
        String endpoint = "generation";
        String expectedResponse = "{\"name\":\"generation-i\",\"pokemon_species\":[]}";
        
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonService.getGenericEndpoint(endpoint))
                .expectNext(expectedResponse)
                .verifyComplete();
    }
}
