package com.sebcicho.datacollector;

import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class DataCollectionScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCollectionScheduler.class);

    @Autowired
    private DataCollectorStorageService dataCollectorStorageService;

    @Autowired
    private ExchangeRateApiClient exchangeRateApiClient;

    @Scheduled(cron = "0 5 0 * * *")
    public void scheduleFixedRateTask() {
        LOGGER.info(
                "Storing new data in the database");
        ExchangeRateDataDto exchangeRateDataDto = exchangeRateApiClient.queryApi();
        dataCollectorStorageService.storeExchangeDataEntry(exchangeRateDataDto);
    }
}
