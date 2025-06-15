package com.progressTracker.progress_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progressTracker.progress_tracker.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
