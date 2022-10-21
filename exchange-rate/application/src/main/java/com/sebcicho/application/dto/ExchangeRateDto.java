package com.sebcicho.application.dto;

public class ExchangeRateDto {
    private String from;
    private String to;
    private Double exchange;

    public ExchangeRateDto(String from, String to, Double exchange) {
        this.from = from;
        this.to = to;
        this.exchange = exchange;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getExchange() {
        return exchange;
    }

    public void setExchange(Double exchange) {
        this.exchange = exchange;
    }
}
