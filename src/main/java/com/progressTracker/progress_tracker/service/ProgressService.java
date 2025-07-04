package com.progressTracker.progress_tracker.service;

import java.util.List;
import java.util.Map;

import com.progressTracker.progress_tracker.enums.Category;
import com.progressTracker.progress_tracker.exception.NoProgressException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.progressTracker.progress_tracker.constants.Constants;
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
        Map<Category, Integer> categories = request.getCategories();
        Progress progress = Progress.builder()
                .userId(request.getUserID())
                .studyHours(categories.getOrDefault(Constants.STUDY_HOURS, 0))
                .tvHours(categories.getOrDefault(Constants.TV_HOURS, 0))
                .workHours(categories.getOrDefault(Constants.WORK_HOURS, 0))
                .cookingHours(categories.getOrDefault(Constants.COOKING_HOURS, null))
                .build();

        repository.save(progress);

        return progress;
    }

    @Transactional(readOnly = true)
    public Progress getProgressByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new NoProgressException(userId));
    }
}
