package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface RatesRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findByDateAndCurrency(Date date, String currency);
}
