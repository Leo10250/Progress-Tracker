package com.progressTracker.progress_tracker.dto_temp.responses_temp;

import lombok.Data;

@Data
public class HealthCheckResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}