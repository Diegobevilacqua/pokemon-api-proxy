# Pokemon API Proxy

A Spring Boot-based REST API proxy for the [PokéAPI](https://pokeapi.co/), providing a reactive and efficient interface to access Pokémon data.

## 📋 Overview

This application serves as a proxy service for the PokéAPI, offering:
- **Reactive Programming** - Built with Spring WebFlux for non-blocking, high-performance operations
- **Comprehensive API Coverage** - Access to Pokémon, species, abilities, types, and moves
- **RESTful Interface** - Clean, intuitive REST endpoints
- **Production Ready** - Docker support, health checks, and comprehensive testing

## ✨ Features

- 🚀 **Reactive Architecture** - Spring WebFlux with Project Reactor
- 🐳 **Docker Support** - Containerized with multi-stage builds
- ✅ **Comprehensive Testing** - Unit and integration tests with Testcontainers
- 📊 **Code Quality** - Checkstyle, JaCoCo coverage, and OWASP dependency scanning
- 🏥 **Health Monitoring** - Built-in health check endpoint
- 🔒 **Security** - OWASP dependency vulnerability scanning
- 🔄 **CI/CD Ready** - Jenkins pipeline for automated testing and deployment

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Gradle 8.x** (wrapper included)
- **Docker** (optional, for containerized deployment)

### Running Locally

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd pokemon-api
   ```

2. **Build the project:**
   ```bash
   ./gradlew clean build
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

4. **Verify the application is running:**
   ```bash
   curl http://localhost:8080/health
   ```

### Running with Docker

1. **Build and run with Docker Compose:**
   ```bash
   docker-compose up --build
   ```

2. **Or build and run the Docker image directly:**
   ```bash
   docker build -t pokemon-api-proxy .
   docker run -p 8080:8080 pokemon-api-proxy
   ```

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/v2
```

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v2/pokemon/{nameOrId}` | Get Pokémon by name or ID |
| GET | `/api/v2/pokemon?limit={limit}&offset={offset}` | Get paginated list of Pokémon |
| GET | `/api/v2/pokemon-species/{nameOrId}` | Get Pokémon species information |
| GET | `/api/v2/ability/{nameOrId}` | Get ability details |
| GET | `/api/v2/type/{nameOrId}` | Get type information |
| GET | `/api/v2/move/{nameOrId}` | Get move details |
| GET | `/api/v2/{endpoint}` | Generic endpoint for other resources |
| GET | `/health` | Health check endpoint |
| GET | `/` | API information and available endpoints |

### Example Requests

```bash
# Get Pokémon by ID
curl http://localhost:8080/api/v2/pokemon/1

# Get Pokémon by name
curl http://localhost:8080/api/v2/pokemon/pikachu

# Get paginated Pokémon list
curl http://localhost:8080/api/v2/pokemon?limit=10&offset=0

# Get Pokémon species
curl http://localhost:8080/api/v2/pokemon-species/1

# Get ability information
curl http://localhost:8080/api/v2/ability/65

# Get type information
curl http://localhost:8080/api/v2/type/electric

# Get move information
curl http://localhost:8080/api/v2/move/tackle
```

## 🧪 Testing

### Run All Tests
```bash
./gradlew test integrationTest
```

### Run Unit Tests Only
```bash
./gradlew test
```

### Run Integration Tests Only
```bash
./gradlew integrationTest
```

### Generate Coverage Reports
```bash
./gradlew jacocoTestReport
# Reports available at: build/reports/jacoco/test/html/index.html
```

### Code Quality Checks
```bash
# Run Checkstyle
./gradlew checkstyleMain checkstyleTest

# Run OWASP Dependency Check
./gradlew dependencyCheckAnalyze
```

## 🏗️ Project Structure

```
pokemon-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/pokemon_api/
│   │   │       ├── config/          # Configuration classes
│   │   │       ├── controller/      # REST controllers
│   │   │       ├── service/         # Business logic
│   │   │       └── PokemonApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/                    # Test classes
│       └── resources/
│           └── application-test.properties
├── config/
│   ├── checkstyle/                  # Checkstyle configuration
│   └── dependency-check/            # OWASP suppressions
├── jenkins/                         # Jenkins pipeline files
├── build.gradle                     # Gradle build configuration
├── Dockerfile                       # Docker image definition
├── docker-compose.yml              # Docker Compose configuration
└── README.md                        # This file
```

## 🔧 Configuration

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Server configuration
server.port=8080

# Pokemon API configuration
pokemon.api.base-url=https://pokeapi.co/api/v2
pokemon.api.timeout=5000
```

### Environment Variables

You can override configuration using environment variables:

```bash
export SPRING_PROFILES_ACTIVE=production
export POKEMON_API_BASE_URL=https://pokeapi.co/api/v2
export POKEMON_API_TIMEOUT=5000
```

## 📦 Building

### Build Executable JAR
```bash
./gradlew clean build
```

The JAR file will be located at:
```
build/libs/pokemon-api-0.0.1-SNAPSHOT.jar
```

### Run the JAR
```bash
java -jar build/libs/pokemon-api-0.0.1-SNAPSHOT.jar
```

## 🎯 Technologies Used

- **Java 21** - Programming language
- **Spring Boot 3.3.0** - Application framework
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive programming library
- **Gradle** - Build tool
- **JUnit 5** - Testing framework
- **Testcontainers** - Integration testing
- **MockWebServer** - HTTP server mocking
- **JaCoCo** - Code coverage
- **Checkstyle** - Code quality
- **OWASP Dependency Check** - Security scanning
- **Docker** - Containerization

## 🚀 CI/CD

This project includes a Jenkins pipeline for automated testing and deployment:

- **Location:** `jenkins/Jenkinsfile.pr`
- **Documentation:** See `jenkins/README.md` for details
- **Features:**
  - Automated test execution
  - Code coverage reporting
  - Code quality checks
  - Security scanning
  - GitHub integration

## 📊 Code Quality

This project maintains high code quality standards:

- **Test Coverage:** Minimum 80% (enforced by JaCoCo)
- **Code Style:** Checkstyle compliance
- **Security:** OWASP dependency vulnerability scanning
- **Static Analysis:** Automated code quality checks

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- [PokéAPI](https://pokeapi.co/) for providing the Pokémon data
- Spring Boot team for the excellent framework
- All contributors who have helped improve this project

## 📞 Support

For issues, questions, or contributions:
- Open an issue on GitHub
- Check the [Wiki](../../wiki) for detailed documentation
- Review the [Jenkins Pipeline documentation](jenkins/README.md)

---

**Built with ❤️ using Spring Boot**
