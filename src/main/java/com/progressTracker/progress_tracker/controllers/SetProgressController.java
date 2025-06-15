package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.progressTracker.progress_tracker.dto.Requests.ProgressRequest;
import com.progressTracker.progress_tracker.dto.Responses.ProgressResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class SetProgressController {
    @PostMapping(value="/setProgress", consumes={"application/json"}, produces = {"application/json"})
    public ProgressResponse setProgress(@RequestBody ProgressRequest progressRequest) {
        log.info("[setProgress] requestL {}", progressRequest.toString());
        ProgressResponse response = new ProgressResponse();
        response.setStatus("Success");
        response.setStatusCode("200");
        response.setStatusMessage("Health check successful");
        return response;
    }
}
