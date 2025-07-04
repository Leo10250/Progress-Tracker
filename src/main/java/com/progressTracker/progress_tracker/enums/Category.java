package com.progressTracker.progress_tracker.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.progressTracker.progress_tracker.constants.Constants;
import com.progressTracker.progress_tracker.exception.UnknownCategoryException;

public enum Category {
    STUDY_HOURS(Constants.STUDY_HOURS),
    WORK_HOURS(Constants.WORK_HOURS),
    TV_HOURS(Constants.TV_HOURS),
    COOKING_HOURS(Constants.COOKING_HOURS);

    private final String key;

    Category(String key) {
        this.key = key;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

    @JsonCreator
    public static Category fromJson(String key) {
        for (Category c : values()) {
            if (c.key.equalsIgnoreCase(key)) {
                return c;
            }
        }
        throw new UnknownCategoryException(key);
    }
}
