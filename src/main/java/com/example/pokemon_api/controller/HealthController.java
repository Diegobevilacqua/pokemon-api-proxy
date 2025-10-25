package com.example.pokemon_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "Pokemon API Proxy");
        health.put("timestamp", LocalDateTime.now());
        health.put("version", "1.0.0");
        return ResponseEntity.ok(health);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> info = new HashMap<>();
        info.put("message", "Pokemon API Proxy");
        info.put("version", "1.0.0");
        info.put("endpoints", new String[]{
            "/api/v2/pokemon/{nameOrId}",
            "/api/v2/pokemon?limit={limit}&offset={offset}",
            "/api/v2/pokemon-species/{nameOrId}",
            "/api/v2/ability/{nameOrId}",
            "/api/v2/type/{nameOrId}",
            "/api/v2/move/{nameOrId}",
            "/health"
        });
        return ResponseEntity.ok(info);
    }
}
