package com.example.pokemon_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PokemonApiApplicationTests {

	@Test
	void contextLoads() {
		// This test verifies that the Spring application context loads successfully
		// It ensures all beans are properly configured and dependencies are resolved
	}

	@Test
	void applicationStartsSuccessfully() {
		// This test verifies that the application can start without errors
		// It's a basic smoke test to ensure the application is properly configured
	}

}
