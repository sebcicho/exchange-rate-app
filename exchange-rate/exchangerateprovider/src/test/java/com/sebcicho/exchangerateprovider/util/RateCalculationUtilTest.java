package com.sebcicho.exchangerateprovider.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateCalculationUtilTest {

    public static Stream<Arguments> provideTestParameters() {
        return Stream.of(
                Arguments.of("EUR", 1D, "PLN", 4.8D, "EUR", 4.668D),
                Arguments.of("JPY", 147D, "PLN", 4.8D, "EUR", 0.03159183673469387D),
                Arguments.of("PLN", 4.8D, "RUB", 60D, "EUR", 11.75D),
                Arguments.of("PLN", 4.8D, "CHF", 1.1, "EUR", 0.22286458333333337D),
                Arguments.of("MYR", 4.63D, "JPY", 147D, "EUR", 30.3207343412527D)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestParameters")
    void testCalculations(String from,
                          Double fromRate,
                          String to,
                          Double toRate,
                          String baseCurrency,
                          Double expected) {
        Double result = RateCalculationUtil.calculateRate(from, fromRate, to, toRate, baseCurrency);

        assertEquals(expected, result);
    }
}
