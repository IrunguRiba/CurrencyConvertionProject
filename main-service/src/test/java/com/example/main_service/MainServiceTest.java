package com.example.main_service;

import com.example.main_service.dto.ConversionRequest;
import com.example.main_service.dto.ConversionResponse;
import com.example.main_service.dto.RateResponse;
import com.example.main_service.model.Conversion;
import com.example.main_service.repository.ConversionRepository;
import com.example.main_service.services.ConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ConversionRepository repository;

    @InjectMocks
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testForPerformConversionSuccess() {
        ConversionRequest request = new ConversionRequest();
        RateResponse rateResponse = new RateResponse();
        when(restTemplate.getForObject(anyString(), eq(RateResponse.class))).thenReturn(rateResponse);
        when(repository.save(any())).thenReturn(new Conversion("USD", "EUR",
                new BigDecimal("100"), new BigDecimal("85.00"), new BigDecimal("0.85"),
                LocalDateTime.now()));
        ConversionResponse response = conversionService.performConversion(request);
        assertNotNull(response);
        assertEquals(new BigDecimal("85.00"), response.getConvertedAmount());
    }
    @Test
    void testForPerformConversionWithZeroAmount() {
        ConversionRequest request = new ConversionRequest();
        assertThrows(ResponseStatusException.class, () -> conversionService.performConversion(request));
    }
}
