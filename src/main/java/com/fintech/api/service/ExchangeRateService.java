package com.fintech.api.service;

import com.fintech.api.dto.ExchangeRateRequest;
import com.fintech.api.dto.ExchangeRateResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {

    // Mock exchange rates (in real app, this would call external API)
    private static final Map<String, Map<String, BigDecimal>> EXCHANGE_RATES = new HashMap<>();

    static {
        // USD base rates
        Map<String, BigDecimal> usdRates = new HashMap<>();
        usdRates.put("EUR", new BigDecimal("0.85"));
        usdRates.put("GBP", new BigDecimal("0.73"));
        usdRates.put("JPY", new BigDecimal("110.0"));
        usdRates.put("CHF", new BigDecimal("0.92"));
        EXCHANGE_RATES.put("USD", usdRates);

        // EUR base rates
        Map<String, BigDecimal> eurRates = new HashMap<>();
        eurRates.put("USD", new BigDecimal("1.18"));
        eurRates.put("GBP", new BigDecimal("0.86"));
        eurRates.put("JPY", new BigDecimal("129.4"));
        eurRates.put("CHF", new BigDecimal("1.08"));
        EXCHANGE_RATES.put("EUR", eurRates);

        // GBP base rates
        Map<String, BigDecimal> gbpRates = new HashMap<>();
        gbpRates.put("USD", new BigDecimal("1.37"));
        gbpRates.put("EUR", new BigDecimal("1.16"));
        gbpRates.put("JPY", new BigDecimal("150.5"));
        gbpRates.put("CHF", new BigDecimal("1.26"));
        EXCHANGE_RATES.put("GBP", gbpRates);
    }

    public ExchangeRateResponse convertCurrency(ExchangeRateRequest request) {
        validateRequest(request);

        BigDecimal rate = getExchangeRate(request.getFrom(), request.getTo());
        // ИСПРАВЛЕНИЕ: явно конвертируем Double в BigDecimal
        BigDecimal convertedAmount = BigDecimal.valueOf(request.getAmount()).multiply(rate).setScale(2, RoundingMode.HALF_UP);

        return ExchangeRateResponse.builder()
                .fromCurrency(request.getFrom())
                .toCurrency(request.getTo())
                .amount(BigDecimal.valueOf(request.getAmount()))
                .convertedAmount(convertedAmount)
                .exchangeRate(rate)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    private void validateRequest(ExchangeRateRequest request) {
        if (request.getFrom() == null || request.getTo() == null) {
            throw new IllegalArgumentException("Currency codes cannot be null");
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Разрешаем конвертацию одной валюты в себя
        if (!request.getFrom().equals(request.getTo())) {
            if (!EXCHANGE_RATES.containsKey(request.getFrom())) {
                throw new IllegalArgumentException("Unsupported source currency: " + request.getFrom());
            }

            if (!EXCHANGE_RATES.get(request.getFrom()).containsKey(request.getTo())) {
                throw new IllegalArgumentException("Unsupported target currency: " + request.getTo());
            }
        }
    }

    private BigDecimal getExchangeRate(String from, String to) {
        if (from.equals(to)) {
            return BigDecimal.ONE; // Курс 1.0 при конвертации одной валюты в себя
        }
        return EXCHANGE_RATES.get(from).get(to);
    }
}