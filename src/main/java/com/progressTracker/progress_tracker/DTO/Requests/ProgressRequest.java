package com.progressTracker.progress_tracker.DTO.Requests;

import java.util.Map;
import lombok.Data;

@Data
public class ProgressRequest {
    private int userID;
    private Map<String, Integer> categories;
}
