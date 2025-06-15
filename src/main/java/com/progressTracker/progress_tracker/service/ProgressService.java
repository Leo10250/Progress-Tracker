package com.progressTracker.progress_tracker.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.progressTracker.progress_tracker.dto_temp.requests_temp.ProgressRequest;
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
        Progress progress = new Progress();
        progress.setUserId(request.getUserID());

        Map<String, Integer> categories = request.getCategories();
        progress.setStudy(categories.getOrDefault("study", 0));
        progress.setTv(categories.getOrDefault("tv", 0));
        progress.setWork(categories.getOrDefault("work", 0));

        repository.save(progress);

        return progress;
    }
}
