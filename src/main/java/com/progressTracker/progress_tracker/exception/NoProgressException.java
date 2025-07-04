package com.progressTracker.progress_tracker.exception;

public class NoProgressException extends RuntimeException {
    public NoProgressException(Long userId) {
        super("No progress found for userId: " + userId);
    }
}
