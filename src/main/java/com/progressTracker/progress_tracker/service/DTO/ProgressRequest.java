package com.progressTracker.progress_tracker.service.DTO;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ProgressRequest {
    private int userID;
    private Map<String, Integer> categories;
}
