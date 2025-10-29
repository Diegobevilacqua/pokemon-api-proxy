# Multi-stage build for Pokemon API Proxy
FROM gradle:8.11-jdk23 AS builder

# Set working directory
WORKDIR /app

# Copy gradle files
COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./

# Copy source code
COPY src/ src/

# Build the application
RUN ./gradlew clean build -x test -x checkstyleMain -x checkstyleTest

# Runtime stage
FROM eclipse-temurin:23-jre

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create app user
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory
WORKDIR /app

# Copy the built jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership to app user
RUN chown -R appuser:appuser /app
USER appuser

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
