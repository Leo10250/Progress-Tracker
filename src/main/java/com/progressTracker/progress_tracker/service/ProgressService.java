package com.progressTracker.progress_tracker.service;

import java.util.Map;

import com.progressTracker.progress_tracker.enums.Category;
import com.progressTracker.progress_tracker.exception.NoProgressException;
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
        Map<Category, Integer> categories = request.getCategories();
        Progress progress = Progress.builder()
                .userId(request.getUserId())
                .studyHours(categories.getOrDefault(Category.STUDY_HOURS, 0))
                .tvHours(categories.getOrDefault(Category.TV_HOURS, 0))
                .workHours(categories.getOrDefault(Category.WORK_HOURS, 0))
                .cookingHours(categories.getOrDefault(Category.COOKING_HOURS, 0))
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
