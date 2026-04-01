package com.fintech.tests.client;

import com.fintech.api.dto.ExchangeRateRequest;
import com.fintech.api.dto.ExchangeRateResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final Logger log = LoggerFactory.getLogger(ApiClient.class);

    @Step("Get exchange rate for {from} to {to} with amount {amount}")
    public ExchangeRateResponse getExchangeRate(String from, String to, Double amount) {
        log.info("Requesting exchange rate: {} -> {} (amount: {})", from, to, amount);

        Response response = given()
                .param("from", from)
                .param("to", to)
                .param("amount", amount)
                .when()
                .get("/api/rates")
                .then()
                .log().all()
                .extract()
                .response();

        if (response.statusCode() == 200) {
            return response.as(ExchangeRateResponse.class);
        } else {
            throw new RuntimeException("Failed to get exchange rate. Status: " + response.statusCode());
        }
    }

    @Step("Convert currency using POST method")
    public ExchangeRateResponse convertCurrency(ExchangeRateRequest request) {
        log.info("Converting currency: {} -> {} (amount: {})",
                request.getFrom(), request.getTo(), request.getAmount());

        Response response = given()
                .body(request)
                .when()
                .post("/api/rates")
                .then()
                .log().all()
                .extract()
                .response();

        if (response.statusCode() == 200) {
            return response.as(ExchangeRateResponse.class);
        } else {
            throw new RuntimeException("Failed to convert currency. Status: " + response.statusCode());
        }
    }
}