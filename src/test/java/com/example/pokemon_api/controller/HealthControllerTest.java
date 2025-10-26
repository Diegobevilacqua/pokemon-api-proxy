package com.example.pokemon_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private HealthController healthController;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        healthController = new HealthController();
    }

    @Test
    void health_ShouldReturnOkResponseWithCorrectData() {
        // When
        ResponseEntity<Map<String, Object>> response = healthController.health();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("UP", body.get("status"));
        assertEquals("Pokemon API Proxy", body.get("service"));
        assertEquals("1.0.0", body.get("version"));
        assertNotNull(body.get("timestamp"));
        
        // Verify timestamp is recent (within last minute)
        LocalDateTime timestamp = (LocalDateTime) body.get("timestamp");
        assertTrue(timestamp.isAfter(LocalDateTime.now().minusMinutes(1)));
        assertTrue(timestamp.isBefore(LocalDateTime.now().plusMinutes(1)));
    }

    @Test
    void root_ShouldReturnOkResponseWithCorrectData() {
        // When
        ResponseEntity<Map<String, Object>> response = healthController.root();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("Pokemon API Proxy", body.get("message"));
        assertEquals("1.0.0", body.get("version"));
        
        @SuppressWarnings("unchecked")
        String[] endpoints = (String[]) body.get("endpoints");
        assertNotNull(endpoints);
        assertTrue(endpoints.length > 0);
        
        // Verify specific endpoints are present
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/pokemon/{nameOrId}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/pokemon?limit={limit}&offset={offset}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/pokemon-species/{nameOrId}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/ability/{nameOrId}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/type/{nameOrId}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/api/v2/move/{nameOrId}"));
        assertTrue(java.util.Arrays.asList(endpoints).contains("/health"));
    }

    @Test
    void health_ShouldReturnConsistentResponseFormat() {
        // When
        ResponseEntity<Map<String, Object>> response1 = healthController.health();
        ResponseEntity<Map<String, Object>> response2 = healthController.health();

        // Then
        assertEquals(response1.getStatusCode(), response2.getStatusCode());
        assertEquals(response1.getBody().get("status"), response2.getBody().get("status"));
        assertEquals(response1.getBody().get("service"), response2.getBody().get("service"));
        assertEquals(response1.getBody().get("version"), response2.getBody().get("version"));
        
        // Timestamps should be different (allow for small time differences)
        LocalDateTime timestamp1 = (LocalDateTime) response1.getBody().get("timestamp");
        LocalDateTime timestamp2 = (LocalDateTime) response2.getBody().get("timestamp");
        assertNotNull(timestamp1);
        assertNotNull(timestamp2);
        // Just verify they exist and are recent, don't compare exact equality
        assertTrue(timestamp1.isAfter(LocalDateTime.now().minusMinutes(1)));
        assertTrue(timestamp2.isAfter(LocalDateTime.now().minusMinutes(1)));
    }

    @Test
    void root_ShouldReturnConsistentResponseFormat() {
        // When
        ResponseEntity<Map<String, Object>> response1 = healthController.root();
        ResponseEntity<Map<String, Object>> response2 = healthController.root();

        // Then
        assertEquals(response1.getStatusCode(), response2.getStatusCode());
        assertEquals(response1.getBody().get("message"), response2.getBody().get("message"));
        assertEquals(response1.getBody().get("version"), response2.getBody().get("version"));
        assertArrayEquals((String[]) response1.getBody().get("endpoints"), (String[]) response2.getBody().get("endpoints"));
    }
}
