package com.progressTracker.progress_tracker.DTO.Responses;

import lombok.Data;

@Data
public class HealthCheckResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}