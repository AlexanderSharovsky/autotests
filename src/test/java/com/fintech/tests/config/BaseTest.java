package com.fintech.tests.config;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Epic("Exchange Rate API Tests")
public abstract class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080");

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        log.info("Test environment initialized with base URL: {}", BASE_URL);
    }

    @BeforeEach
    public void setupTest(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();
        log.info("Starting test: {}", testName);
    }
}