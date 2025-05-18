package com.example.main_service.validation;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {

    @NotNull(message = "From currency is required: Please enter the base current")
    @Size(min = 3, max = 3, message = "From currency must be a 3-letter code")
    private String from;

    @NotNull(message = "To currency is required: Please Enter the currency you want to convert to")
    @Size(min = 3, max = 3, message = "To currency must be a 3-letter code")
    private String to;

    @NotNull(message = "Amount is required: You have not Entered the Amount to Convert")
    @DecimalMin(value = "0.01", message = "Amount MUST be greater than 0")
    private BigDecimal amount;

}
