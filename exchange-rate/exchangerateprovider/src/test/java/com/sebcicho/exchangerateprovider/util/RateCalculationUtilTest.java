package com.sebcicho.exchangerateprovider.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateCalculationUtilTest {

    public static Stream<Arguments> provideTestParameters() {
        return Stream.of(
                Arguments.of("EUR", 1D, "PLN", 4.8D, "EUR", new BigDecimal("4.66800000000000000000")),
                Arguments.of("JPY", 147D, "PLN", 4.8D, "EUR",  new BigDecimal("0.03159183671100000000")),
                Arguments.of("PLN", 4.8D, "RUB", 60D, "EUR",  new BigDecimal("11.75000000000000000000")),
                Arguments.of("PLN", 4.8D, "CHF", 1.1, "EUR",  new BigDecimal("0.22286458336575000000")),
                Arguments.of("MYR", 4.63D, "JPY", 147D, "EUR",  new BigDecimal("30.32073434125600000000"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestParameters")
    void testCalculations(String from,
                          Double fromRate,
                          String to,
                          Double toRate,
                          String baseCurrency,
                          BigDecimal expected) {
        BigDecimal result = RateCalculationUtil.calculateRate(from, fromRate, to, toRate, baseCurrency);

        assertEquals(expected, result);
    }
}
