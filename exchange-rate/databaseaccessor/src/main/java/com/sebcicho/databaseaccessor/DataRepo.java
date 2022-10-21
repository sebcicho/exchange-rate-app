package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    public Rate getRate(Date date, String currency) {
        return ratesRepository.findByDateAndCurrency(date, currency).stream().findAny().orElse(null);
    }
}
