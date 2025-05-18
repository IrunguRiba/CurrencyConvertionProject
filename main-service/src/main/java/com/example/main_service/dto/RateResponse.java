package com.example.main_service.dto;

import lombok.Getter;

import java.math.BigDecimal;

public class RateResponse {
    @Getter
    private String from;
    @Getter
    private String to;
    private BigDecimal rate;

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate != null ? rate : BigDecimal.ZERO;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
