package com.sebcicho.datacollector.mapper;

import com.sebcicho.databaseaccessor.entites.Rate;
import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import com.google.common.collect.ImmutableSet;

import java.util.Set;
import java.util.stream.Collectors;

public class ExchangeRateDataMapper {

    public static Set<Rate> fromDto(ExchangeRateDataDto rateDataDto) {
        if (rateDataDto.getRates() == null) {
            return ImmutableSet.of();
        }
        return rateDataDto.getRates().entrySet().stream().map(entry -> {
            Rate rate = new Rate();
            rate.setCounter(0);
            rate.setCurrency(entry.getKey());
            rate.setRate(entry.getValue());
            rate.setDate(rateDataDto.getDate());
            return rate;
        }).collect(Collectors.toSet());
    }

}
