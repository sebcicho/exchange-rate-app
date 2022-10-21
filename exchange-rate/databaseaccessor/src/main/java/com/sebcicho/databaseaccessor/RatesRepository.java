package com.sebcicho.databaseaccessor;

import com.sebcicho.databaseaccessor.entites.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface RatesRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findByDateAndCurrency(Date date, String currency);
    List<Rate> findTop1ByCurrencyOrderByDateDesc(String currency);

    @Modifying
    @Transactional
    @Query("update Rate r set r.counter = r.counter + 1 where r.date = :date and (r.currency = :from or r.currency = :to)")
    void incrementCounters(Date date, String from, String to);
}
