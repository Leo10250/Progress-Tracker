package com.progressTracker.progress_tracker.service.VO;

import org.springframework.stereotype.Component;
import lombok.Data;


@Component
@Data
public class HealthCheckResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}