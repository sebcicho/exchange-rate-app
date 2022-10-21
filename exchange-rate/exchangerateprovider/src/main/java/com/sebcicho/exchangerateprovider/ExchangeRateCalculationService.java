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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Service
@EntityScan({"com.sebcicho.databaseaccessor"})
@ComponentScan({"com.sebcicho.databaseaccessor"})
@EnableConfigurationProperties(BaseCurrencyProperty.class)
public class ExchangeRateCalculationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateCalculationService.class);

    ExchangeRateCalculationService(BaseCurrencyProperty configProperties, DataRepo dataRepo) {
        this.configProperties = configProperties;
        this.dataRepo = dataRepo;
    }

    @Autowired
    private BaseCurrencyProperty configProperties;
    @Autowired
    private DataRepo dataRepo;

    public Iterable<Rate> getAll() {
        return dataRepo.getRates();
    }

    @Nullable
    public BigDecimal getRate(@Nullable Date requestedDate, @Nonnull String from, @Nonnull String to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        Rate fromRate = dataRepo.getRate(requestedDate, from);
        Rate toRate = dataRepo.getRate(requestedDate, to);

        if (fromRate == null || toRate == null) {
            return null;
        }

        Date date = requestedDate != null ? requestedDate : fromRate.getDate();

        dataRepo.incrementCounters(date, from, to);

        try {
            return RateCalculationUtil.calculateRate(from, fromRate.getRate(), to, toRate.getRate(), configProperties.getCurrency());
        } catch (IllegalArgumentException e) {
            LOGGER.warn(String.format("Can not calculate exchange rate %s", e.getMessage()));
            return null;
        }
    }

}
