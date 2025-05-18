package com.example.rate_service;
import com.example.rate_service.models.RateResponse;
import com.example.rate_service.services.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class RateServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private RateService rateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToGetExchangeRateSuccess() {
    String from = "USD";
       String to = "EUR";
     Map<String, Object> mockResponse = new HashMap<>();
 Map<String, Double> rates = new HashMap<>();
    rates.put(to, 0.85);
  mockResponse.put("conversion_rates", rates);
      when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);
      RateResponse response = rateService.getExchangeRate(from, to);
    assertNotNull(response);
      assertEquals(0.85, response.getRate());
    }
    @Test
    public void testToGetExchangeRateInvalidTargetCurrency() {
        String from = "USD";
 String to = "XYZ";
     Map<String, Object> mockResponse = new HashMap<>();
      mockResponse.put("conversion_rates", new HashMap<>());

  when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);
       assertThrows(ResponseStatusException.class, () -> rateService.getExchangeRate(from, to));
    }
}
