# 💱 Fintech Exchange Rate API & QA Automation Framework

A robust demonstration project showcasing a **Spring Boot REST API** for currency conversion paired with a professional **Java-based Test Automation Framework**. This project is designed to reflect real-world FinTech testing scenarios, including API validation, error handling, and CI/CD integration.

##  Features

### Backend API
- **Technology**: Spring Boot 3.x, Java 17
- **Endpoint**: `GET /api/rates` & `POST /api/rates`
- **Functionality**: Real-time currency conversion simulation with mock exchange rates.
- **Validation**: Strict input validation for currency codes and amounts.
- **Error Handling**: Proper HTTP status codes (200, 400, 500) and JSON error responses.

### QA Automation Framework
- **Core**: JUnit 5, RestAssured
- **Pattern**: Layered Architecture (Client → Steps → Tests)
- **Reporting**: Allure 2 integration with detailed steps, attachments, and history.
- **Assertions**: AssertJ for fluent and readable assertions.
- **Logging**: SLF4J with structured logging for debugging.

### DevOps & CI/CD
- **Build Tool**: Maven
- **Containerization**: Docker & Dockerfile included
- **CI/CD**: Fully configured GitLab CI/CD pipeline (Free Tier compatible).
- **Artifacts**: Automatic generation of JUnit reports and Allure HTML reports.

---

## 🛠 Tech Stack

| Category | Technologies |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot, JUnit 5 |
| **Testing** | RestAssured, AssertJ, Allure |
| **Build** | Maven |
| **Container** | Docker |
| **CI/CD** | GitLab CI |
