package com.example.main_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "conversions")
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "converted_amount", nullable = false)
    private BigDecimal convertedAmount;

    @Column(name = "exchange_rate", nullable = false)
    private BigDecimal exchangeRate;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Conversion(String fromCurrency, String toCurrency, BigDecimal amount,
                      BigDecimal convertedAmount, BigDecimal exchangeRate, LocalDateTime timestamp) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return fromCurrency;
    }

    public String getTo() {
        return toCurrency;
    }
}
