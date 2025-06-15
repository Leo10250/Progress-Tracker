package com.progressTracker.progress_tracker.dto_temp.requests_temp;

import java.util.Map;
import lombok.Data;

@Data
public class ProgressRequest {
    private Long userID;
    private Map<String, Integer> categories;
}
