package com.sebcicho.application;

import com.sebcicho.application.dto.ExchangeRateDto;
import com.sebcicho.databaseaccessor.RatesRepository;
import com.sebcicho.databaseaccessor.entites.Rate;
import com.sebcicho.datacollector.DataCollectorService;
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

import java.sql.Date;

@SpringBootApplication()
@ComponentScan({"com.sebcicho.*"})
@EntityScan({"com.sebcicho.databaseaccessor"})
@EnableJpaRepositories(basePackageClasses= {RatesRepository.class})
@RestController
public class ExchangeRateApplication {

	Logger logger = LoggerFactory.getLogger(ExchangeRateApplication.class);
	@Autowired
	private DataCollectorService dataCollectorService;

	@Autowired
	private ExchangeRateCalculationService exchangeRateCalculationService;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}

	@PostMapping("/exchange")
	public @ResponseBody ResponseEntity<ExchangeRateDataDto> createExchangeData (@RequestBody ExchangeRateDataDto exchangeRateDataDto) {
		logger.info(String.format("New data accepted, exchange rates for currency: %s, on: %s", exchangeRateDataDto.getBase(), exchangeRateDataDto.getDate()));
		dataCollectorService.storeExchangeDataEntry(exchangeRateDataDto);
		return ResponseEntity.ok(exchangeRateDataDto);
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<Rate> getAll() {
		return exchangeRateCalculationService.getAll();
	}

	@GetMapping("/exchange")
	public @ResponseBody ResponseEntity<ExchangeRateDto> getExchangeRate(@RequestParam("from") String from,
														 @RequestParam("to") String to,
														 @RequestParam("date") Date date) {
		Double exchangeRate =  exchangeRateCalculationService.getRate(date, from, to);
		if (exchangeRate == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new ExchangeRateDto(from, to, exchangeRate));

	}

}
