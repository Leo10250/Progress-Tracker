package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;               
import org.springframework.web.bind.annotation.GetMapping;    
import org.springframework.web.bind.annotation.RequestParam; 
import com.progressTracker.progress_tracker.dto.requests.ProgressRequest;
import com.progressTracker.progress_tracker.dto.responses.ProgressResponse;

import com.progressTracker.progress_tracker.service.ProgressService;
import com.progressTracker.progress_tracker.model.Progress;   
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
public class SetProgressController {

    private final ProgressService progressService;

    @PostMapping(value = "/set", consumes = { "application/json" }, produces = { "application/json" })
    public ProgressResponse setProgress(@RequestBody ProgressRequest progressRequest) {
        log.info("[setProgress] requestL {}", progressRequest.toString());

        progressService.saveProgress(progressRequest);

        ProgressResponse response = new ProgressResponse(
                "Success",
                "200",
                "Progress saved successfully");
        return response;
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<?> getProgress(
            @RequestParam("userId") Long userId,
            @RequestParam(value = "category", required = false) String category            
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
            all.put("study", p.getStudy());
            all.put("work",  p.getWork());
            all.put("tv",    p.getTv());

            Map<String, Object> body = new HashMap<>();
            body.put("categories", all);
            return ResponseEntity.ok(body);
        }

        Integer val;
        switch (category) {
            case "study": val = p.getStudy(); break;
            case "work":  val = p.getWork();  break;
            case "tv":    val = p.getTv();    break;
            default:
                return ResponseEntity
                        .badRequest()
                        .body(Collections.singletonMap(
                            "error", "Unknown category: " + category));
        }

        return ResponseEntity.ok(Collections.singletonMap(category, val));
    }
}
