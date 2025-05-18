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
