package com.sebcicho.datacollector.dto;

import java.sql.Date;
import java.util.Map;

public class ExchangeRateDataDto {

    private long timestamp;
    private String base;
    private Date date;
    private Map<String, Double> rates;

    public ExchangeRateDataDto(long timestamp, String base, Date date, Map<String, Double> rates) {
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Date getDate() {
        return this.date;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Map<String, Double> getRates() {
        return this.rates;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
