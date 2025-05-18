package com.example.rate_service.controllers;

import com.example.rate_service.models.RateResponse;
import com.example.rate_service.services.RateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }
    @GetMapping("/rate")
    public RateResponse getRate(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return rateService.getExchangeRate(from.toUpperCase(), to.toUpperCase());
    }
}

/*
THESE ARE THE ENDPOINTS

Check status
http://localhost:8082/api/status
this will return converted amounts, you just have to replace with different currencies
http://localhost:8082/api/rate?from=USD&to=BMD
TO GET ALL EXCHANGES RELATED TO PARTICULAR CURRENCY
https://v6.exchangerate-api.com/v6/a2398286a2bebf4928107bab/latest/eur
OR
https://v6.exchangerate-api.com/v6/a2398286a2bebf4928107bab/latest/EUR
OR
https://v6.exchangerate-api.com/v6/a2398286a2bebf4928107bab/latest/USD
OR
https://v6.exchangerate-api.com/v6/a2398286a2bebf4928107bab/latest/usd

 */