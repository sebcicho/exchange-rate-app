package com.sebcicho.exchangerateprovider;

import com.sebcicho.databaseaccessor.DataRepo;
import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

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

}
