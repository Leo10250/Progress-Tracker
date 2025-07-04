package com.progressTracker.progress_tracker.exception;

public class UnknownCategoryException extends RuntimeException {
    public UnknownCategoryException(String category) {
        super("Unknown category: " + category);
    }
}
