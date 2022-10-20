package com.sebcicho.exchange.rate;

import com.sebcicho.exchange.rate.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Service;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Service
public class DataRepo {

    @Autowired
    UserRepository userRepository;
    public String messageForPersist() {
        return "PERSIST: helllo from the data base";
    }

    public String messageForAccess() {
        return "ACCESS: helllo from the data base";
    }
//	public static void main(String[] args) {
//		SpringApplication.run(ExchangeRateApplication.class, args);
//	}

    public String addNewUser(String name, String mail) {
        User n = new User();
        n.setName(name);
        n.setEmail(mail);
        userRepository.save(n);
        return "Saved";
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
