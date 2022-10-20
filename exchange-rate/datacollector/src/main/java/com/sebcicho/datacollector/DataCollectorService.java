package com.sebcicho.datacollector;

import com.sebcicho.databaseaccessor.DataRepo;
import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import com.sebcicho.datacollector.mapper.ExchangeRateDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({"com.sebcicho.databaseaccessor"})
public class DataCollectorService {

    @Autowired
    private DataRepo dataRepo;

    public void storeExchangeDataEntry(ExchangeRateDataDto exchangeRateDataDto) {
        dataRepo.addRates(ExchangeRateDataMapper.fromDto(exchangeRateDataDto));
    }

}
