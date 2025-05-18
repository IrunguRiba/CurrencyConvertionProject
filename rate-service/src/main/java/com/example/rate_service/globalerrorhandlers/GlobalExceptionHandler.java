package com.example.rate_service.globalerrorhandlers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        assert ex.getReason() != null;
        return new ResponseEntity<>(Map.of("error", ex.getReason()), ex.getStatusCode());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception) {
        return new ResponseEntity<>(Map.of("error", "Internal Server Error: There was a problem connecting to the Database"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
