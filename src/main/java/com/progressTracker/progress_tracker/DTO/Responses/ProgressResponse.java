package com.progressTracker.progress_tracker.dto.Responses;

import lombok.Data;

@Data
public class ProgressResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}
