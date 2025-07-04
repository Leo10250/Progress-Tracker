package com.progressTracker.progress_tracker.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.progressTracker.progress_tracker.dto.responses.HealthCheckResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
public class HealthCheckController {
    @GetMapping("/healthcheck")
    public ResponseEntity<HealthCheckResponse> doHealthCheck() {
        log.info("[doHealthCheck] Getting healthCheck request");
        HealthCheckResponse response = new HealthCheckResponse(
                "Success",
                "200",
                "Health check successful"
        );
        return ResponseEntity.ok(response);
    }
}