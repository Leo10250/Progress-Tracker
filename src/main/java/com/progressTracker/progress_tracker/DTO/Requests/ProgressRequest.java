package com.progressTracker.progress_tracker.dto.Requests;

import java.util.Map;
import lombok.Data;

@Data
public class ProgressRequest {
    private int userID;
    private Map<String, Integer> categories;
}
