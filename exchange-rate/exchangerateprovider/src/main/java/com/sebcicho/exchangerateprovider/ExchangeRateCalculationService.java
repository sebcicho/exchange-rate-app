package com.sebcicho.exchangerateprovider;

import com.sebcicho.databaseaccessor.DataRepo;
import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.sql.Date;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Service
@EntityScan({"com.sebcicho.databaseaccessor"})
@ComponentScan({"com.sebcicho.databaseaccessor"})
public class ExchangeRateCalculationService {
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

        return toRate.getRate();
    }

}
