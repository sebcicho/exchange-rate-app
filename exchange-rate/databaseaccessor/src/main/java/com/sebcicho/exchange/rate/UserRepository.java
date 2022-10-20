package com.sebcicho.exchange.rate;

import com.sebcicho.exchange.rate.entites.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
