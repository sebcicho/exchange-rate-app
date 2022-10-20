package com.sebcicho.databaseaccessor.entites;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(indexes = {
    @Index(name = "index", columnList = "currency, date", unique = true)
})
public class Rate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer counter;
    private Date date;
    private String currency;

    private Double rate;

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
