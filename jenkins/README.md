# Jenkins CI/CD Setup for Pokemon API Proxy

This directory contains Jenkins configuration files for automated CI/CD pipeline.

## Files Overview

- `Jenkinsfile` - Main pipeline for full CI/CD (build, test, deploy)
- `Jenkinsfile.PR` - Simplified pipeline specifically for PR testing
- `jenkins-config.xml` - Jenkins job configuration for GitHub integration

## Jenkins Pipeline Features

### Main Pipeline (`Jenkinsfile`)
- ✅ **Checkout** - Source code checkout
- ✅ **Build** - Gradle build without tests
- ✅ **Unit Tests** - Run unit tests with reporting
- ✅ **Integration Tests** - Run integration tests
- ✅ **Code Coverage** - Generate and publish JaCoCo reports
- ✅ **Code Quality** - Checkstyle analysis
- ✅ **Security Scan** - Dependency vulnerability check
- ✅ **Package** - Create executable JAR
- ✅ **Docker Build** - Build and push Docker image (main branch only)
- ✅ **Notifications** - Email notifications on success/failure

### PR Pipeline (`Jenkinsfile.PR`)
- ✅ **Checkout** - PR source code checkout
- ✅ **Build** - Gradle build
- ✅ **Unit Tests** - Run unit tests
- ✅ **Integration Tests** - Run integration tests
- ✅ **Code Coverage** - Generate coverage reports
- ✅ **Package** - Create JAR artifact
- ✅ **PR Status** - Update PR status

## Setup Instructions

### 1. Jenkins Server Setup

1. **Install Required Plugins:**
   - Pipeline
   - GitHub Integration
   - Gradle
   - JUnit
   - HTML Publisher
   - Email Extension
   - Docker Pipeline

2. **Configure Tools:**
   - JDK 17
   - Gradle (or use wrapper)
   - Docker (if building images)

3. **Configure Credentials:**
   - GitHub token for repository access
   - Docker Hub credentials (if pushing images)
   - Email SMTP settings

### 2. Create Multi-Branch Pipeline

1. **New Item** → **Multibranch Pipeline**
2. **Branch Sources** → **GitHub**
3. **Repository:** `Diegobevilacqua/pokemon-api-proxy`
4. **Script Path:** `jenkins/Jenkinsfile`
5. **Scan Triggers:** GitHub webhook

### 3. GitHub Webhook Configuration

1. Go to repository **Settings** → **Webhooks**
2. Add webhook URL: `https://your-jenkins-server/github-webhook/`
3. Select events:
   - Push
   - Pull request
   - Release

### 4. Environment Variables

Set these in Jenkins job configuration:

```bash
# Java/Gradle
GRADLE_OPTS=-Dorg.gradle.daemon=false
JAVA_OPTS=-Xmx1024m

# Application
SPRING_PROFILES_ACTIVE=test
POKEMON_API_BASE_URL=https://pokeapi.co/api/v2
POKEMON_API_TIMEOUT=5000

# Docker (if enabled)
DOCKER_REGISTRY=your-registry.com
DOCKER_IMAGE_NAME=pokemon-api-proxy
```

## Pipeline Triggers

### Automatic Triggers
- **Push to main/develop** → Full CI/CD pipeline
- **Pull Request** → PR testing pipeline
- **Tag creation** → Release pipeline

### Manual Triggers
- **Workflow Dispatch** → Manual pipeline execution
- **Rebuild** → Re-run failed pipeline

## Test Reports

The pipeline generates and publishes:

- **JUnit Test Results** - `build/test-results/test/*.xml`
- **Code Coverage** - `build/reports/jacoco/test/html/`
- **Checkstyle Reports** - `build/reports/checkstyle/`
- **Security Reports** - `build/reports/dependency-check-report.html`

## Notifications

### Success Notifications
- ✅ Email to PR author
- ✅ GitHub status update
- ✅ Build artifacts archived

### Failure Notifications
- ❌ Email to PR author with error details
- ❌ GitHub status update
- ❌ Build logs available

## Troubleshooting

### Common Issues

1. **Gradle Build Fails**
   - Check Java version compatibility
   - Verify Gradle wrapper permissions
   - Check for dependency conflicts

2. **Tests Fail**
   - Verify test environment variables
   - Check Pokemon API connectivity
   - Review test logs for specific failures

3. **Docker Build Fails**
   - Verify Docker daemon is running
   - Check Docker registry credentials
   - Review Dockerfile syntax

4. **GitHub Integration Issues**
   - Verify webhook URL is accessible
   - Check GitHub token permissions
   - Review Jenkins GitHub plugin configuration

### Logs and Debugging

- **Build Logs:** Available in Jenkins console output
- **Test Logs:** Published in test results
- **Docker Logs:** Available in Docker build output
- **GitHub Logs:** Available in webhook delivery logs

## Alternative: GitHub Actions

If you prefer GitHub Actions over Jenkins, see `.github/workflows/ci-cd.yml` for a complete GitHub Actions workflow that provides similar functionality.

## Support

For issues with the Jenkins setup:
1. Check Jenkins system logs
2. Review pipeline console output
3. Verify plugin compatibility
4. Check GitHub webhook delivery logs
