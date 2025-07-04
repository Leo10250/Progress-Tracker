package com.progressTracker.progress_tracker.dto.requests;

import java.util.Map;

import com.progressTracker.progress_tracker.enums.Category;
import lombok.Data;

@Data
public class ProgressRequest {
    private Long userId;
    private Map<Category, Integer> categories;
}
