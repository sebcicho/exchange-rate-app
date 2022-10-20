package com.sebcicho.exchange.rate;

import com.sebcicho.exchange.rate.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Service
public class ExchangeRateCalculationService {
    @Autowired
    private DataRepo dataRepo;

    public String getMessage() {
        return dataRepo.messageForAccess();
    }

    public String storeTheUser(String name, String mail) {
        return dataRepo.addNewUser(name, mail);
    }

    public Iterable<User> getAll() {
        return dataRepo.getAllUsers();
    }

}
