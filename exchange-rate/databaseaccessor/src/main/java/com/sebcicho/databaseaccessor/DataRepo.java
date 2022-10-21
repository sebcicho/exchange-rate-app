package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Service
public class DataRepo {

    @Autowired
    RatesRepository ratesRepository;

    DataRepo(RatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }

    public void addRates(Set<Rate> rateSet) {
        ratesRepository.saveAll(rateSet);
    }

    public Iterable<Rate> getRates() {
        return ratesRepository.findAll();
    }

    @Nullable
    public Rate getRate(@Nullable Date date, @Nonnull String currency) {
        Objects.requireNonNull(currency);
        if (date == null) {
            return ratesRepository.findTop1ByCurrencyOrderByDateDesc(currency).stream().findAny().orElse(null);
        }
        return ratesRepository.findByDateAndCurrency(date, currency).stream().findAny().orElse(null);
    }

    public void incrementCounters(@Nonnull Date date, @Nonnull String from, @Nonnull String to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        Objects.requireNonNull(date);

        ratesRepository.incrementCounters(date, from, to);
    }
}
