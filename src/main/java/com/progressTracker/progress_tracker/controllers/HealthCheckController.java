package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.progressTracker.progress_tracker.service.VO.HealthCheckResponse;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthCheckController {
    @GetMapping("/healthcheck")
    public HealthCheckResponse doHealthCheck() {
        HealthCheckResponse response = new HealthCheckResponse();
        response.setStatus("Success");
        response.setStatusCode("200");
        response.setStatusMessage("Health check successful");
        return response;
    }
}