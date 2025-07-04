package com.progressTracker.progress_tracker.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.progressTracker.progress_tracker.exception.UnknownCategoryException;

public enum Category {
    STUDY_HOURS("studyHours"),
    WORK_HOURS("tvHours"),
    TV_HOURS("workHours"),
    COOKING_HOURS("cookingHours");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static Category fromJson(String key) {
        for (Category c : values()) {
            if (c.value.equalsIgnoreCase(key)) {
                return c;
            }
        }
        throw new UnknownCategoryException(key);
    }
}
