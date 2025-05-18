package com.example.main_service.services;

import com.example.main_service.dto.ConversionRequest;
import com.example.main_service.dto.ConversionResponse;
import com.example.main_service.dto.RateResponse;
import com.example.main_service.model.Conversion;
import com.example.main_service.repository.ConversionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ConversionService {

    private static final Logger logger = LoggerFactory.getLogger(ConversionService.class);

    @Value("${rate-service.url}")
    private String rateServiceUrl;

    private final RestTemplate restTemplate;
    private final ConversionRepository repository;

    public ConversionService(RestTemplate restTemplate, ConversionRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }
    public BigDecimal getExchangeRate(String from, String to) {
        try {
            String url = String.format("%s/rate?from=%s&to=%s", rateServiceUrl, from, to);
            RateResponse response = restTemplate.getForObject(url, RateResponse.class);
            logger.info("Calling rate-service with URL: {}", url);
            if (response != null && response.getRate() != null) {
                return response.getRate();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rate response from the rate service.");
            }
        } catch (Exception e) {
            logger.error("Error fetching exchange rate", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch exchange rate: Check The Inputs and Try Again", e);
        }
    }
    public Conversion saveConversion(String from, String to, BigDecimal amount,
                                     BigDecimal convertedAmount, BigDecimal rate) {

        Conversion conversion = new Conversion(from, to, amount, convertedAmount, rate, LocalDateTime.now());
        return repository.save(conversion);
    }
    public ConversionResponse performConversion(ConversionRequest request) {

        //THIS VALIDATES THE REQUEST WHEN TRYING TO CONVERT
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount Entered Invalid: Amount must be greater than zero.");
        }
        BigDecimal rate = getExchangeRate(request.getFrom(), request.getTo());
        BigDecimal convertedAmount = request.getAmount().multiply(rate);
        Conversion conversion = saveConversion(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                convertedAmount,
                rate
        );
        return new ConversionResponse(
                conversion.getFrom(),
                conversion.getTo(),
                conversion.getAmount(),
                conversion.getConvertedAmount(),
                conversion.getExchangeRate()
        );
    }

}
