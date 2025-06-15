package com.progressTracker.progress_tracker.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.progressTracker.progress_tracker.service.DTO.ProgressRequest;
import com.progressTracker.progress_tracker.service.VO.ProgressResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SetProgressController {
    @PostMapping(value="/setProgress", consumes={"application/json"}, produces = {"application/json"})
    public ProgressResponse setProgress(@RequestBody ProgressRequest progressRequest) {
        System.out.println(progressRequest.toString());
        ProgressResponse response = new ProgressResponse();
        response.setStatus("Success");
        response.setStatusCode("200");
        response.setStatusMessage("Health check successful");
        return response;
    }
}
