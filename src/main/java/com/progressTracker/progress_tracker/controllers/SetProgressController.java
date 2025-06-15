package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.progressTracker.progress_tracker.dto.Requests.ProgressRequest;
import com.progressTracker.progress_tracker.dto.Responses.ProgressResponse;
import com.progressTracker.progress_tracker.service.ProgressService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
                "Progress saved successful");
        return response;
    }
}
