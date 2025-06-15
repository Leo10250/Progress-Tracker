package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.progressTracker.progress_tracker.dto_temp.responses_temp.HealthCheckResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
public class HealthCheckController {
    @GetMapping("/healthcheck")
    public HealthCheckResponse doHealthCheck() {
        log.info("[doHealthCheck] Getting healthCheck request");
        HealthCheckResponse response = new HealthCheckResponse();
        response.setStatus("Success");
        response.setStatusCode("200");
        response.setStatusMessage("Health check successful");
        return response;
    }
}