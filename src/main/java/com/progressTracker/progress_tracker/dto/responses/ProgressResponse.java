package com.progressTracker.progress_tracker.dto.responses;

import lombok.Data;

@Data
public class ProgressResponse {
    private final String status;
    private final String statusCode;
    private final String statusMessage;
}
