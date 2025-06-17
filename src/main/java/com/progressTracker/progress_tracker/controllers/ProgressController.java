package com.progressTracker.progress_tracker.controllers;

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

    @PostMapping(value = "/set", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<ProgressResponse> setProgress(@RequestBody ProgressRequest progressRequest) {
        log.info("[setProgress] request {}", progressRequest.toString());

        progressService.saveProgress(progressRequest);

        ProgressResponse response = new ProgressResponse(
                "Success",
                "200",
                "Progress saved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getProgress(
            @RequestParam(Constants.USER_ID) Long userId,
            @RequestParam(value = Constants.CATEGORIES, required = false) String category            
    ) {
        log.info("[getProgress] id={}, category={}", userId, category);

        Progress p = progressService.getProgressByUserId(userId);
        if (p == null) {
            return ResponseEntity
                    .status(404)
                    .body(Collections.singletonMap("error", "User not found"));
        }

        if (category == null) {
            Map<String, Integer> all = new HashMap<>();
            all.put(Constants.STUDY_HOURS, p.getStudyHours());
            all.put(Constants.WORK_HOURS,  p.getWorkHours());
            all.put(Constants.TV_HOURS,    p.getTvHours());
            all.put(Constants.COOKING_HOURS,    p.getTvHours());

            Map<String, Object> body = new HashMap<>();
            body.put(Constants.CATEGORIES, all);
            return ResponseEntity.ok(body);
        }

        Integer val;
        switch (category) {
            case Constants.STUDY_HOURS: val = p.getStudyHours(); break;
            case Constants.WORK_HOURS:  val = p.getWorkHours();  break;
            case Constants.TV_HOURS:    val = p.getTvHours();    break;
            case Constants.COOKING_HOURS:    val = p.getTvHours();    break;
            default:
                return ResponseEntity
                        .badRequest()
                        .body(Collections.singletonMap(
                            "error", "Unknown category: " + category));
        }

        return ResponseEntity.ok(Collections.singletonMap(category, val));
    }
}
