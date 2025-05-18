package com.example.rate_service.models;

import lombok.Data;

@Data

public class RateResponse {
    private final String from;

    private final String to;
    private final double rate;

    public RateResponse(String from, String to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

}
