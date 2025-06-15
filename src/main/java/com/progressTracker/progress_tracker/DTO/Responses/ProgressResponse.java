package com.progressTracker.progress_tracker.DTO.Responses;

import lombok.Data;

@Data
public class ProgressResponse {
    private String status;
    private String statusCode;
    private String statusMessage;
}
