package com.example.main_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {

    @NotBlank
    private String from;

    @NotBlank
    private String to;

    @DecimalMin("0.0")
    private BigDecimal amount;
}
