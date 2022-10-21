package com.sebcicho.exchangerateprovider;

import com.sebcicho.databaseaccessor.DataRepo;
import com.sebcicho.databaseaccessor.entites.Rate;
import com.sebcicho.exchangerateprovider.util.RateCalculationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@EntityScan({"com.sebcicho.databaseaccessor"})
@ComponentScan({"com.sebcicho.databaseaccessor"})
@EnableConfigurationProperties(AdditionalConfigProperties.class)
public class ExchangeRateCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateCalculationService.class);

    @Autowired
    private AdditionalConfigProperties configProperties;
    @Autowired
    private DataRepo dataRepo;

    public Iterable<Rate> getAll() {
        return dataRepo.getRates();
    }

    public Double getRate(Date date, String from, String to) {
        Rate fromRate = dataRepo.getRate(date, from);
        Rate toRate = dataRepo.getRate(date, to);

        if (fromRate == null || toRate == null) {
            return null;
        }
        //TODO bump counters

        try {
            return RateCalculationUtil.calculateRate(from, fromRate.getRate(), to, toRate.getRate(), configProperties.getBaseCurrency());
        } catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("Can not calculate exchange rate %s", e.getMessage()));
            return null;
        }
    }

}
