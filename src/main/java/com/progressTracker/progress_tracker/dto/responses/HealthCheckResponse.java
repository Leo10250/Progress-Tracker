package com.progressTracker.progress_tracker.dto.responses;

import lombok.Data;

@Data
public class HealthCheckResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}