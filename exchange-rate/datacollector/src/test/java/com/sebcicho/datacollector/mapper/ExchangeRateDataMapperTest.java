package com.sebcicho.datacollector.mapper;

import com.google.common.collect.ImmutableMap;
import com.sebcicho.databaseaccessor.entites.Rate;
import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExchangeRateDataMapperTest {

    @Test
    void testFromDtoNullRatesMap() {
        ExchangeRateDataDto exchangeRateDataDto = new ExchangeRateDataDto(1, "base", new Date(1666305591L), null);

        Set<Rate> resultSet = ExchangeRateDataMapper.fromDto(exchangeRateDataDto);

        assertTrue(resultSet.isEmpty());
    }

    @Test
    void testFromDtoNonNullRatesMap() {
        ExchangeRateDataDto exchangeRateDataDto = new ExchangeRateDataDto(1, "base", new Date(1666305591L), ImmutableMap.of("EUR", 0.9, "PLN", 4.4));

        Set<Rate> resultSet = ExchangeRateDataMapper.fromDto(exchangeRateDataDto);

        assertEquals(2, resultSet.size());
        assertTrue(resultSet.stream().anyMatch(r -> r.getCurrency().equals("EUR")));
        assertTrue(resultSet.stream().anyMatch(r -> r.getCurrency().equals("PLN")));
    }


}
