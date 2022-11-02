package com.sebcicho.application;

import com.sebcicho.application.dto.ExchangeRateDto;
import com.sebcicho.databaseaccessor.RatesRepository;
import com.sebcicho.datacollector.DataCollectorStorageService;
import com.sebcicho.datacollector.dto.ExchangeRateDataDto;
import com.sebcicho.exchangerateprovider.ExchangeRateCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;

@SpringBootApplication()
@ComponentScan({"com.sebcicho.*"})
@EntityScan({"com.sebcicho.databaseaccessor"})
@EnableJpaRepositories(basePackageClasses= {RatesRepository.class})
public class ExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}
}
