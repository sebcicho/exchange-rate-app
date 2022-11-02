package com.sebcicho.application;

import com.sebcicho.application.dto.ExchangeRateDto;
import com.sebcicho.datacollector.DataCollectorStorageService;
import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import com.sebcicho.exchangerateprovider.ExchangeRateCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;

@RestController
public class ApplicationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRestController.class);
    private final DataCollectorStorageService dataCollectorStorageService;

    private final ExchangeRateCalculationService exchangeRateCalculationService;

    @Autowired
    public ApplicationRestController(DataCollectorStorageService dataCollectorStorageService,
                                     ExchangeRateCalculationService exchangeRateCalculationService) {
        this.dataCollectorStorageService = dataCollectorStorageService;
        this.exchangeRateCalculationService = exchangeRateCalculationService;
    }

    @PostMapping("/exchange")
    public @ResponseBody ResponseEntity<ExchangeRateDataDto> createExchangeData (@RequestBody ExchangeRateDataDto exchangeRateDataDto) {
        LOGGER.info(String.format("New data accepted, exchange rates for currency: %s, on: %s", exchangeRateDataDto.getBase(), exchangeRateDataDto.getDate()));
        dataCollectorStorageService.storeExchangeDataEntry(exchangeRateDataDto);
        return ResponseEntity.ok(exchangeRateDataDto);
    }

    @GetMapping("/exchange")
    public @ResponseBody ResponseEntity<ExchangeRateDto> getExchangeRate(@RequestParam("from") String from,
                                                                         @RequestParam("to") String to,
                                                                         @RequestParam(name = "date", required = false) Date date) throws InterruptedException {

        BigDecimal exchangeRate =  exchangeRateCalculationService.getRate(date, from, to);
        if (exchangeRate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ExchangeRateDto(from, to, exchangeRate));
    }
}
