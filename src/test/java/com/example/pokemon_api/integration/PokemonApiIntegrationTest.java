package com.example.pokemon_api.integration;

import com.example.pokemon_api.PokemonApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = PokemonApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PokemonApiIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void healthEndpoint_ShouldReturnOkWithCorrectData() {
        webTestClient.get()
                .uri("/health")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP")
                .jsonPath("$.service").isEqualTo("Pokemon API Proxy")
                .jsonPath("$.version").isEqualTo("1.0.0")
                .jsonPath("$.timestamp").exists();
    }

    @Test
    void rootEndpoint_ShouldReturnOkWithApiInformation() {
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.message").isEqualTo("Pokemon API Proxy")
                .jsonPath("$.version").isEqualTo("1.0.0")
                .jsonPath("$.endpoints").isArray()
                .jsonPath("$.endpoints[0]").exists();
    }

    @Test
    void pokemonEndpoint_ShouldReturnOk_WhenValidPokemonName() {
        webTestClient.get()
                .uri("/api/v2/pokemon/pikachu")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("pikachu")
                .jsonPath("$.id").isEqualTo(25);
    }

    @Test
    void pokemonEndpoint_ShouldReturnNotFound_WhenInvalidPokemonName() {
        webTestClient.get()
                .uri("/api/v2/pokemon/nonexistentpokemon12345")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void pokemonListEndpoint_ShouldReturnOkWithPagination() {
        webTestClient.get()
                .uri("/api/v2/pokemon?limit=5&offset=0")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.count").exists()
                .jsonPath("$.results").isArray()
                .jsonPath("$.results.length()").isEqualTo(5);
    }

    @Test
    void pokemonListEndpoint_ShouldUseDefaultValues_WhenNoParameters() {
        webTestClient.get()
                .uri("/api/v2/pokemon")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.count").exists()
                .jsonPath("$.results").isArray()
                .jsonPath("$.results.length()").isEqualTo(20); // Default limit
    }

    @Test
    void pokemonSpeciesEndpoint_ShouldReturnOk_WhenValidPokemonName() {
        webTestClient.get()
                .uri("/api/v2/pokemon-species/pikachu")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("pikachu")
                .jsonPath("$.evolution_chain").exists();
    }

    @Test
    void abilityEndpoint_ShouldReturnOk_WhenValidAbilityName() {
        webTestClient.get()
                .uri("/api/v2/ability/static")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("static")
                .jsonPath("$.effect_entries").exists();
    }

    @Test
    void typeEndpoint_ShouldReturnOk_WhenValidTypeName() {
        webTestClient.get()
                .uri("/api/v2/type/electric")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("electric")
                .jsonPath("$.damage_relations").exists();
    }

    @Test
    void moveEndpoint_ShouldReturnOk_WhenValidMoveName() {
        webTestClient.get()
                .uri("/api/v2/move/thunderbolt")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("thunderbolt")
                .jsonPath("$.power").exists();
    }

    @Test
    void genericEndpoint_ShouldReturnOk_WhenValidEndpoint() {
        webTestClient.get()
                .uri("/api/v2/generation")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.count").exists()
                .jsonPath("$.results").isArray();
    }

    @Test
    void genericEndpointWithId_ShouldReturnOk_WhenValidEndpointAndId() {
        webTestClient.get()
                .uri("/api/v2/generation/1")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .jsonPath("$.name").isEqualTo("generation-i")
                .jsonPath("$.pokemon_species").exists();
    }

    @Test
    void corsHeaders_ShouldBePresent() {
        webTestClient.get()
                .uri("/api/v2/pokemon/pikachu")
                .header("Origin", "http://localhost:3000")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().exists("Access-Control-Allow-Origin");
    }
}
