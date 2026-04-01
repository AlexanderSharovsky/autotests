package com.fintech.tests;

import com.fintech.tests.config.BaseTest;
import com.fintech.tests.steps.ExchangeRateSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Exchange Rate API")
public class ExchangeRateApiTest extends BaseTest {

    private final ExchangeRateSteps steps = new ExchangeRateSteps();

    @Test
    @DisplayName("TC001: Successful USD to EUR conversion")
    @Story("Currency Conversion")
    @Description("Verify that USD to EUR conversion works correctly with proper calculation")
    @Severity(SeverityLevel.CRITICAL)
    public void testUsdToEurConversion() {
        steps.verifyExchangeRateCalculation("USD", "EUR", 100.0);
    }

    @Test
    @DisplayName("TC002: Successful EUR to GBP conversion")
    @Story("Currency Conversion")
    @Description("Verify that EUR to GBP conversion works correctly")
    @Severity(SeverityLevel.NORMAL)
    public void testEurToGbpConversion() {
        steps.verifyExchangeRateCalculation("EUR", "GBP", 50.0);
    }

    @Test
    @DisplayName("TC003: Error handling for unsupported currency")
    @Story("Error Handling")
    @Description("Verify that API returns proper error for unsupported currency codes")
    @Severity(SeverityLevel.NORMAL)
    public void testUnsupportedCurrencyError() {
        steps.verifyInvalidCurrencyError("XYZ");
    }

    @Test
    @DisplayName("TC004: Error handling for negative amount")
    @Story("Error Handling")
    @Description("Verify that API rejects negative amounts with proper error message")
    @Severity(SeverityLevel.NORMAL)
    public void testNegativeAmountError() {
        steps.verifyNegativeAmountError();
    }

    @Test
    @DisplayName("TC005: Same currency conversion (rate should be 1)")
    @Story("Currency Conversion")
    @Description("Verify that converting same currency returns rate of 1.0")
    @Severity(SeverityLevel.MINOR)
    public void testSameCurrencyConversion() {
        steps.verifyExchangeRateCalculation("USD", "USD", 100.0);
    }
}