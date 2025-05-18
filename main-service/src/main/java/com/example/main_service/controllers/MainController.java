package com.example.main_service.controllers;

import com.example.main_service.dto.ConversionRequest;
import com.example.main_service.dto.ConversionResponse;
import com.example.main_service.services.ConversionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    private final ConversionService conversionService;

    public MainController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "UP");
    }
    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convertCurrency(@RequestBody @Valid ConversionRequest request) {
        ConversionResponse response = conversionService.performConversion(request);
        return ResponseEntity.ok(response);
    }
}
