package com.progressTracker.progress_tracker.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoProgressException.class)
    public ResponseEntity<Map<String, Object>> handleNoProgress(
            NoProgressException ex) {

        Map<String, Object> responseBody = Map.of(
                "status", 404,
                "error",  "Not Found",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(404).body(responseBody);   // HTTP 404
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleBadJson(HttpMessageNotReadableException ex) {
        Map<String, Object> body = Map.of(
                "status", 400,
                "error",  "Bad Request",
                "message", "Malformed JSON request"
        );
        return ResponseEntity.status(400).body(body);
    }
}
