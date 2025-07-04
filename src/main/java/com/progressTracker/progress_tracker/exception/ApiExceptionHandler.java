package com.progressTracker.progress_tracker.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoProgressException.class)
    public ResponseEntity<Map<String, Object>> handleNoProgress(
            NoProgressException ex, HttpServletRequest req) {

        Map<String, Object> body = Map.of(
                "status", 404,
                "error",  "Not Found",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(404).body(body);   // HTTP 404
    }
}
