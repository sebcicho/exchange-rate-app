package com.sebcicho.exchange.rate;

import com.sebcicho.exchange.rate.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication()
@EnableJpaRepositories(basePackageClasses= {UserRepository.class})
@RestController
public class ExchangeRateApplication {

	@Autowired
	private DataCollectorService dataCollectorService;

	@Autowired
	private ExchangeRateCalculationService exchangeRateCalculationService;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRateApplication.class, args);
	}


	@GetMapping("/collector")
	public String getCollectorMsg() {
		return dataCollectorService.getMessage();
	}

	@GetMapping("/calculator")
	public String getCalculatorMsg() {
		return exchangeRateCalculationService.getMessage();
	}


	@PostMapping("/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		return exchangeRateCalculationService.storeTheUser(name, email);
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return exchangeRateCalculationService.getAll();
	}
}
