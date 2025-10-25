package com.example.pokemon_api.controller;

import com.example.pokemon_api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping(value = "/pokemon/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemon(@PathVariable String nameOrId) {
        return pokemonService.getPokemon(nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/pokemon", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemonList(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return pokemonService.getPokemonList(limit, offset)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/pokemon-species/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemonSpecies(@PathVariable String nameOrId) {
        return pokemonService.getPokemonSpecies(nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/ability/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemonAbility(@PathVariable String nameOrId) {
        return pokemonService.getPokemonAbility(nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/type/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemonType(@PathVariable String nameOrId) {
        return pokemonService.getPokemonType(nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/move/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getPokemonMove(@PathVariable String nameOrId) {
        return pokemonService.getPokemonMove(nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{endpoint}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getGenericEndpoint(@PathVariable String endpoint) {
        return pokemonService.getGenericEndpoint(endpoint)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{endpoint}/{nameOrId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> getGenericEndpointWithId(
            @PathVariable String endpoint,
            @PathVariable String nameOrId) {
        return pokemonService.getGenericEndpoint(endpoint + "/" + nameOrId)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}
