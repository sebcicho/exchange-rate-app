package com.sebcicho.exchangerateprovider;


import com.sebcicho.databaseaccessor.DataRepo;
import com.sebcicho.databaseaccessor.entites.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExchangeRateCalculationServiceTests {

    private final DataRepo dataRepo = Mockito.mock(DataRepo.class);
    private BaseCurrencyProperty baseCurrencyProperty;

    @BeforeEach
    void setUp() {
        baseCurrencyProperty = new BaseCurrencyProperty();
        baseCurrencyProperty.setCurrency("EUR");
    }

    @Test
    void testMissingRateForInput() {
        Rate rate = new Rate();

        when(dataRepo.getRate(isNull(), eq("CHF"))).thenReturn(rate);
        when(dataRepo.getRate(isNull(), eq("POL"))).thenReturn(null);

        ExchangeRateCalculationService exchangeRateCalculationService = new ExchangeRateCalculationService(baseCurrencyProperty, dataRepo);
        Double result = exchangeRateCalculationService.getRate(null, "CHF", "POL");

        assertNull(result);
    }

    @Test
    void testReturnNotNullResultAndIncrementsCounters() {
        Rate rate = new Rate();
        rate.setRate(0.9);
        rate.setDate(new Date(1666361491L));
        when(dataRepo.getRate(isNull(), eq("CHF"))).thenReturn(rate);
        when(dataRepo.getRate(isNull(), eq("PLN"))).thenReturn(rate);

        ExchangeRateCalculationService exchangeRateCalculationService = new ExchangeRateCalculationService(baseCurrencyProperty, dataRepo);
        Double result = exchangeRateCalculationService.getRate(null, "CHF", "PLN");

        assertNotNull(result);
        verify(dataRepo).incrementCounters(any(), any(), any());
    }


}
