package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RatesRepository extends CrudRepository<Rate, Integer> {
}
