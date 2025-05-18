package com.example.rate_service.services;

import com.example.rate_service.models.RateResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Service
public class RateService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "a2398286a2bebf4928107bab";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public RateResponse getExchangeRate(String from, String to) {
        String url = BASE_URL + from;
        try {
            Map response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("conversion_rates")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid response from exchange rate API");
            }
            Map<String, Double> rates = (Map<String, Double>) response.get("conversion_rates");

            if (!rates.containsKey(to)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid target currency: " + to);
            }

            double rate = rates.get(to);
            return new RateResponse(from, to, rate);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error fetching exchange rate: " + e.getMessage());
        }
    }
}
