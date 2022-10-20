package com.sebcicho.exchange.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Service
public class DataCollectorService {

    @Autowired
    private DataRepo dataRepo;

    public String getMessage() {
        return dataRepo.messageForPersist();
    }

//	public static void main(String[] args) {
//		SpringApplication.run(ExchangeRateApplication.class, args);
//	}

}
