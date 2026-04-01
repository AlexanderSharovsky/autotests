# 💱 Fintech Exchange Rate API & QA Automation Framework

> ** Live Demo:** Check out the latest **Allure Test Report** generated automatically by CI/CD pipeline:  
> 👉 **[View Live Allure Report](https://alexandersharovsky.github.io/autotests/)**  

A robust demonstration project showcasing a **Spring Boot REST API** for currency conversion paired with **Java-based Test Automation Framework**. Designed to reflect FinTech testing scenarios, including API validation, error handling, and seamless CI/CD integration with live reporting.


---

## 🏁 Quick Start & CI/CD Demo

### 1️ View Live CI/CD Pipeline
This project is configured to run automatically on every push.
- Go to the **Actions** tab in this repository.
- Select the latest workflow run.
- Watch the stages: **Build** → **Test (Docker)** → **Report**.
- **Download Artifacts**: After a successful run, download the `allure-report` artifact to view detailed test results locally.

### 2️⃣ How to Re-run Tests (Rerun Workflow)
1. Navigate to **Actions** → Select a workflow run.
2. Click the **Re-run jobs** button (top right).
3. Select **Re-run all jobs** to trigger the full pipeline again.

### 3️⃣ Run Locally
Want to run tests on your machine?

**Prerequisites:** Java 17+, Maven, Docker.

# 1. Start the API
mvn spring-boot:run

# 2. In a new terminal, run tests
mvn clean test

# 3. Generate & View Allure Report
mvn allure:serve

Key Features
🛠 Backend API
Tech Stack: Spring Boot 3.x, Java 17.
Endpoints: GET /api/rates & POST /api/rates for real-time currency conversion simulation.
Logic: Mock exchange rates with strict validation (currency codes, positive amounts).
Resilience: Proper HTTP status codes (200, 400, 500) and structured JSON error responses.
🧪 QA Automation Framework
Core: JUnit 5 + RestAssured for powerful API testing.
Architecture: Clean layered pattern (Client → Steps → Tests) for maintainability.
Reporting: Integrated Allure 2 with steps, attachments, and history trends.
Assertions: Fluent AssertJ for readable and robust validations.
Logging: SLF4J with detailed request/response logging for easy debugging.
⚙️ DevOps & CI/CD
Containerization: Multi-stage Dockerfile using Eclipse Temurin images for optimal size and security.
Pipeline: GitHub Actions workflow automating Build, Test, and Report stages.
Artifacts: Automatic upload of JUnit XML reports and Allure HTML reports on every run.

🧪 Test Coverage Scenarios
The current suite covers critical FinTech scenarios:
✅ Happy Path: Successful currency conversions (USD→EUR, EUR→GBP).
✅ Edge Case: Same-currency conversion (verifying rate = 1.0).
✅ Negative Testing: Handling unsupported currency codes (400 Bad Request).
✅ Validation: Rejecting invalid inputs like negative amounts.

