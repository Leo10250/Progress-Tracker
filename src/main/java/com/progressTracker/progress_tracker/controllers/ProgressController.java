package com.progressTracker.progress_tracker.controllers;

import com.progressTracker.progress_tracker.enums.Category;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.progressTracker.progress_tracker.dto.requests.ProgressRequest;
import com.progressTracker.progress_tracker.dto.responses.ProgressResponse;

import com.progressTracker.progress_tracker.service.ProgressService;
import com.progressTracker.progress_tracker.model.Progress;
import com.progressTracker.progress_tracker.constants.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping(value = "/set", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ProgressResponse> setProgress(@RequestBody ProgressRequest progressRequest) {
        log.info("[setProgress] request {}", progressRequest.toString());

        Progress progress = progressService.saveProgress(progressRequest);

        log.info("[setProgress] Progress saved successfully with ID: {}", progress.getId());

        ProgressResponse response = new ProgressResponse(
                "Success",
                "200",
                "Progress saved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getProgress(
            @RequestParam(Constants.USER_ID) Long userId,
            @RequestParam(value = Constants.CATEGORIES, required = false) Category category
    ) {
        log.info("[getProgress] [id={}] [category={}]", userId,
                category == null ? "All categories" : category.toString());

        Progress p = progressService.getProgressByUserId(userId);
        if (p == null) {
            log.warn("[getProgress] [id={}] User not found", userId);
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("error", "User not found"));
        }

        boolean allCategoriesRequested = category == null;
        if (allCategoriesRequested) {
            Map<Category, Integer> all = Map.of(
                    Category.STUDY_HOURS, p.getStudyHours(),
                    Category.WORK_HOURS, p.getWorkHours(),
                    Category.TV_HOURS, p.getTvHours(),
                    Category.COOKING_HOURS, p.getCookingHours()
            );
            log.info("[getProgress] [id={}] All categories retrieved successfully", userId);
            return ResponseEntity.ok(all);
        }

        log.info("[getProgress] [id={}] [category={}] Retrieving individual category", userId, category);
        Integer hours;
        switch (category.toString()) {
            case Constants.STUDY_HOURS:
                hours = p.getStudyHours();
                break;
            case Constants.WORK_HOURS:
                hours = p.getWorkHours();
                break;
            case Constants.TV_HOURS:
                hours = p.getTvHours();
                break;
            case Constants.COOKING_HOURS:
                hours = p.getCookingHours();
                break;
            default:
                return ResponseEntity
                        .badRequest()
                        .body(Collections.singletonMap(
                                "error", "Unknown category: " + category));
        }
        return ResponseEntity.ok(Collections.singletonMap(category, hours));
    }
}
