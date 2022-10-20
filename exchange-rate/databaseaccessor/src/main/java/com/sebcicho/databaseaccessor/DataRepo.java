package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DataRepo {

    @Autowired
    RatesRepository ratesRepository;

    public void addRates(Set<Rate> rateSet) {
        ratesRepository.saveAll(rateSet);
    }

    public Iterable<Rate> getRates() {
        return ratesRepository.findAll();
    }
}
