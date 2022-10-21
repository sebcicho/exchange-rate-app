package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import com.google.common.collect.ImmutableList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DataRepoTest {

    private final RatesRepository ratesRepository = Mockito.mock(RatesRepository.class);

	@Test
	void rateRepoDoNotFindEntry() {
        when(ratesRepository.findByDateAndCurrency(any(), any())).thenReturn(new ArrayList<>());
        DataRepo dataRepo = new DataRepo(ratesRepository);

        Rate rate = dataRepo.getRate(new Date(1666305591L), "EUR");

        assertNull(rate);
	}

	@Test
	void rateRepoEntryFound() {
        when(ratesRepository.findByDateAndCurrency(any(), any())).thenReturn(ImmutableList.of(new Rate()));
        DataRepo dataRepo = new DataRepo(ratesRepository);

        Rate rate = dataRepo.getRate(new Date(1666305591L), "EUR");

        assertNotNull(rate);
	}
}
