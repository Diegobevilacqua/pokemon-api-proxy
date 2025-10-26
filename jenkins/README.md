# Jenkins PR Pipeline for Pokemon API Proxy

This repository contains a single Jenkinsfile (`Jenkinsfile.pr`) specifically designed for Pull Request testing.

## Overview

The `Jenkinsfile.pr` is a Groovy-based Jenkins pipeline that automatically runs comprehensive tests whenever a Pull Request is created or updated.

## Pipeline Features

### 🔄 **Automated Stages:**

1. **Checkout** - Source code checkout from PR
2. **Build** - Gradle clean build (excluding tests)
3. **Unit Tests** - Run all unit tests with reporting
4. **Integration Tests** - Run integration tests
5. **Code Coverage** - Generate JaCoCo coverage reports
6. **Code Quality** - Checkstyle analysis
7. **Security Scan** - OWASP dependency vulnerability check
8. **Package** - Create executable JAR artifact

### 📊 **Reports Generated:**

- **JUnit Test Results** - `build/test-results/test/*.xml`
- **Code Coverage** - `build/reports/jacoco/test/html/`
- **Checkstyle Reports** - `build/reports/checkstyle/`
- **Security Reports** - `build/reports/dependency-check-report.html`

### 🔔 **GitHub Integration:**

- **PR Status Updates** - Automatic status updates on GitHub PRs
- **Success Status** - ✅ Green checkmark when all tests pass
- **Failure Status** - ❌ Red X when tests fail
- **Unstable Status** - ⚠️ Yellow warning when tests are unstable

## Setup Instructions

### 1. Jenkins Server Configuration

1. **Install Required Plugins:**
   ```
   - Pipeline
   - GitHub Integration
   - Gradle
   - JUnit
   - HTML Publisher
   - GitHub Commit Status
   ```

2. **Configure Tools:**
   - JDK 17
   - Gradle (or use wrapper)

3. **Configure Credentials:**
   - GitHub token for repository access
   - Email SMTP settings (optional)

### 2. Create Multibranch Pipeline

1. **New Item** → **Multibranch Pipeline**
2. **Branch Sources** → **GitHub**
3. **Repository:** `Diegobevilacqua/pokemon-api-proxy`
4. **Script Path:** `JenkinsfilePR`
5. **Scan Triggers:** GitHub webhook

### 3. GitHub Webhook Setup

1. Go to repository **Settings** → **Webhooks**
2. Add webhook URL: `https://your-jenkins-server/github-webhook/`
3. Select events:
   - Push
   - Pull request

### 4. Environment Variables

Set these in Jenkins job configuration:

```bash
GRADLE_OPTS=-Dorg.gradle.daemon=false
JAVA_OPTS=-Xmx1024m
SPRING_PROFILES_ACTIVE=test
```

## Pipeline Triggers

### Automatic Triggers
- **Pull Request Created** → Full PR testing pipeline
- **Pull Request Updated** → Re-run PR testing pipeline

### Manual Triggers
- **Rebuild** → Re-run failed pipeline
- **Workflow Dispatch** → Manual pipeline execution

## Test Coverage

The pipeline runs:
- **Unit Tests** - All `*Test.java` files
- **Integration Tests** - All `*IntegrationTest.java` files
- **Code Coverage** - JaCoCo reports with HTML output
- **Code Quality** - Checkstyle compliance
- **Security** - OWASP dependency vulnerability scan

## Success Criteria

For a PR to be considered successful:
- ✅ All unit tests must pass
- ✅ All integration tests must pass
- ✅ Code coverage meets minimum threshold
- ✅ Checkstyle compliance
- ✅ No critical security vulnerabilities
- ✅ Successful build and packaging

## Troubleshooting

### Common Issues

1. **Tests Fail**
   - Check test logs in Jenkins console
   - Verify Pokemon API connectivity
   - Review test environment variables

2. **Build Fails**
   - Check Java version compatibility
   - Verify Gradle wrapper permissions
   - Review dependency conflicts

3. **GitHub Status Not Updating**
   - Verify GitHub token permissions
   - Check webhook configuration
   - Review Jenkins GitHub plugin settings

### Logs and Debugging

- **Build Logs:** Available in Jenkins console output
- **Test Logs:** Published in test results
- **GitHub Logs:** Available in webhook delivery logs

## Usage

Once configured, the pipeline will automatically:

1. **Detect** new or updated Pull Requests
2. **Run** comprehensive tests
3. **Generate** detailed reports
4. **Update** PR status on GitHub
5. **Notify** developers of results

## Support

For issues with the Jenkins pipeline:
1. Check Jenkins system logs
2. Review pipeline console output
3. Verify plugin compatibility
4. Check GitHub webhook delivery logs
