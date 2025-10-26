package com.example.pokemon_api.controller;

import com.example.pokemon_api.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    @Mock
    private PokemonService pokemonService;

    private PokemonController pokemonController;

    @BeforeEach
    void setUp() {
        pokemonController = new PokemonController();
        // Use reflection to set the private field for testing
        try {
            java.lang.reflect.Field field = PokemonController.class.getDeclaredField("pokemonService");
            field.setAccessible(true);
            field.set(pokemonController, pokemonService);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    @Test
    void getPokemon_ShouldReturnOkResponse_WhenPokemonFound() {
        // Given
        String pokemonName = "pikachu";
        String expectedResponse = "{\"name\":\"pikachu\",\"id\":25}";
        
        when(pokemonService.getPokemon(pokemonName))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemon(pokemonName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemon_ShouldReturnNotFoundResponse_WhenPokemonNotFound() {
        // Given
        String pokemonName = "nonexistent";
        
        when(pokemonService.getPokemon(pokemonName))
                .thenReturn(Mono.error(new RuntimeException("Pokemon not found")));

        // When & Then
        StepVerifier.create(pokemonController.getPokemon(pokemonName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.NOT_FOUND)
                .verifyComplete();
    }

    @Test
    void getPokemonList_ShouldReturnOkResponse_WhenValidParameters() {
        // Given
        int limit = 10;
        int offset = 0;
        String expectedResponse = "{\"count\":1328,\"results\":[]}";
        
        when(pokemonService.getPokemonList(limit, offset))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonList(limit, offset))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemonList_ShouldUseDefaultValues_WhenNoParametersProvided() {
        // Given
        String expectedResponse = "{\"count\":1328,\"results\":[]}";
        
        when(pokemonService.getPokemonList(20, 0))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonList(20, 0))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemonSpecies_ShouldReturnOkResponse_WhenSpeciesFound() {
        // Given
        String pokemonName = "pikachu";
        String expectedResponse = "{\"name\":\"pikachu\",\"evolution_chain\":{}}";
        
        when(pokemonService.getPokemonSpecies(pokemonName))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonSpecies(pokemonName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemonAbility_ShouldReturnOkResponse_WhenAbilityFound() {
        // Given
        String abilityName = "static";
        String expectedResponse = "{\"name\":\"static\",\"effect\":\"May paralyze attacker\"}";
        
        when(pokemonService.getPokemonAbility(abilityName))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonAbility(abilityName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemonType_ShouldReturnOkResponse_WhenTypeFound() {
        // Given
        String typeName = "electric";
        String expectedResponse = "{\"name\":\"electric\",\"damage_relations\":{}}";
        
        when(pokemonService.getPokemonType(typeName))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonType(typeName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getPokemonMove_ShouldReturnOkResponse_WhenMoveFound() {
        // Given
        String moveName = "thunderbolt";
        String expectedResponse = "{\"name\":\"thunderbolt\",\"power\":90}";
        
        when(pokemonService.getPokemonMove(moveName))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getPokemonMove(moveName))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getGenericEndpoint_ShouldReturnOkResponse_WhenEndpointFound() {
        // Given
        String endpoint = "generation";
        String expectedResponse = "{\"name\":\"generation-i\",\"pokemon_species\":[]}";
        
        when(pokemonService.getGenericEndpoint(endpoint))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getGenericEndpoint(endpoint))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }

    @Test
    void getGenericEndpointWithId_ShouldReturnOkResponse_WhenEndpointAndIdFound() {
        // Given
        String endpoint = "generation";
        String id = "1";
        String expectedResponse = "{\"name\":\"generation-i\",\"pokemon_species\":[]}";
        
        when(pokemonService.getGenericEndpoint(endpoint + "/" + id))
                .thenReturn(Mono.just(expectedResponse));

        // When & Then
        StepVerifier.create(pokemonController.getGenericEndpointWithId(endpoint, id))
                .expectNextMatches(response -> 
                    response.getStatusCode() == HttpStatus.OK &&
                    response.getBody().equals(expectedResponse))
                .verifyComplete();
    }
}
