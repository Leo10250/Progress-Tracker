package com.progressTracker.progress_tracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.progressTracker.progress_tracker.dto.requests.ProgressRequest;
import com.progressTracker.progress_tracker.model.Progress;
import com.progressTracker.progress_tracker.repository.ProgressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository repository;

    @Transactional(readOnly = true)
    public Progress getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such id: " + id));
    }

    @Transactional
    public Progress saveProgress(ProgressRequest request) {
        Map<String, Integer> categories = request.getCategories();
        Progress progress = Progress.builder()
                .userId(request.getUserID())
                .study(categories.getOrDefault("study", 0))
                .tv(categories.getOrDefault("tv", 0))
                .work(categories.getOrDefault("work", 0))
                .build();

        repository.save(progress);

        return progress;
    }

    @Transactional(readOnly = true)
    public Progress getProgressByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("No progress found for userId: " + userId));
    }
}
