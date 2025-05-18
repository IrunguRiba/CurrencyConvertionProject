package com.example.main_service.validation;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class ConversionRequest {

    @NotBlank(message = "From currency is required")
    private String from;

    @NotBlank(message = "To currency is required")
    private String to;

    @Positive(message = "Amount must be positive")
    private double amount;
}
