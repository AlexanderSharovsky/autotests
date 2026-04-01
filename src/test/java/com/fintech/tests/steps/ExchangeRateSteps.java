package com.fintech.tests.steps;

import com.fintech.api.dto.ExchangeRateRequest;
import com.fintech.api.dto.ExchangeRateResponse;
import com.fintech.tests.client.ApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

public class ExchangeRateSteps {

    private final ApiClient apiClient = new ApiClient();

    @Step("Verify exchange rate calculation for {from} to {to}")
    public void verifyExchangeRateCalculation(String from, String to, Double amount) {
        ExchangeRateResponse response = apiClient.getExchangeRate(from, to, amount);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getFromCurrency()).isEqualTo(from);
        Assertions.assertThat(response.getToCurrency()).isEqualTo(to);
        Assertions.assertThat(response.getAmount()).isEqualTo(BigDecimal.valueOf(amount));
        Assertions.assertThat(response.getConvertedAmount()).isGreaterThan(BigDecimal.ZERO);
        Assertions.assertThat(response.getExchangeRate()).isGreaterThan(BigDecimal.ZERO);
        Assertions.assertThat(response.getTimestamp()).isNotEmpty();

        // Verify calculation accuracy
        BigDecimal expectedConverted = response.getAmount().multiply(response.getExchangeRate());
        Assertions.assertThat(response.getConvertedAmount())
                .isEqualByComparingTo(expectedConverted.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Step("Verify error handling for invalid currency")
    public void verifyInvalidCurrencyError(String invalidCurrency) {
        Response response = given()
                .param("from", invalidCurrency)
                .param("to", "USD")
                .param("amount", 100.0)
                .when()
                .get("/api/rates")
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(400);
    }

    @Step("Verify error handling for negative amount")
    public void verifyNegativeAmountError() {
        Response response = given()
                .param("from", "USD")
                .param("to", "EUR")
                .param("amount", -100.0)
                .when()
                .get("/api/rates")
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(400);
    }
}