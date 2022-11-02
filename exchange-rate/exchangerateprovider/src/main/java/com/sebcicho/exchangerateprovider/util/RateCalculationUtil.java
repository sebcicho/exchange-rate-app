package com.sebcicho.exchangerateprovider.util;

import lombok.NonNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class RateCalculationUtil {

    private static final Map<String, Double> SPREAD_MAP;
    private static final Double DEFAULT_SPREAD = 2.75;

    private static final int SCALE = 10;

    static {
        SPREAD_MAP = new HashMap<>();
        SPREAD_MAP.put("JPY", 3.25);
        SPREAD_MAP.put("HKD", 3.25);
        SPREAD_MAP.put("KRW", 3.25);
        SPREAD_MAP.put("MYR", 4.5);
        SPREAD_MAP.put("INR", 4.5);
        SPREAD_MAP.put("MXN", 4.5);
        SPREAD_MAP.put("RUB", 6D);
        SPREAD_MAP.put("CNY", 6D);
        SPREAD_MAP.put("ZAR", 6D);
    }

    public static BigDecimal calculateRate(@NonNull String from,
                                           @NonNull Double fromRate,
                                           @NonNull String to,
                                           @NonNull Double toRate,
                                           @NonNull String baseCurrency) {
        if(fromRate == 0) {
            throw new IllegalArgumentException("Can not divide by zero!");
        }

        BigDecimal fromRateBigDec = BigDecimal.valueOf(fromRate);
        BigDecimal toRateBigDec = BigDecimal.valueOf(toRate);
        BigDecimal formSpread = BigDecimal.valueOf(getSpread(baseCurrency, from));
        BigDecimal toSpread = BigDecimal.valueOf(getSpread(baseCurrency, to));
        BigDecimal maxSpread = formSpread.max(toSpread);

        return (toRateBigDec.divide(fromRateBigDec, SCALE, RoundingMode.HALF_DOWN))
                .multiply((BigDecimal.valueOf(100D).subtract(maxSpread))
                        .divide(BigDecimal.valueOf(100D), SCALE, RoundingMode.HALF_DOWN));
    }

    private static double getSpread(String baseCurrency, String currency) {
        return baseCurrency.equals(currency) ? 0 : SPREAD_MAP.getOrDefault(currency, DEFAULT_SPREAD);
    }
}
