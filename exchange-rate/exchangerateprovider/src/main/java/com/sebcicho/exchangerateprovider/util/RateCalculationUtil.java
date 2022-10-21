package com.sebcicho.exchangerateprovider.util;

import java.util.HashMap;
import java.util.Map;

public class RateCalculationUtil {

    private static final Map<String, Double> SPREAD_MAP;
    private static final Double DEFAULT_SPREAD = 2.75;

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

    public static double calculateRate(String from,
                                Double fromRate,
                                String to,
                                Double toRate) {
        double maxSpread = Double.max(SPREAD_MAP.getOrDefault(from, DEFAULT_SPREAD),
                SPREAD_MAP.getOrDefault(to, DEFAULT_SPREAD));

        if(fromRate == 0) {
            throw new IllegalArgumentException("Can not divide by zero!");
        }

        return (toRate/fromRate) * ((100 - maxSpread)/100);
    }

}
