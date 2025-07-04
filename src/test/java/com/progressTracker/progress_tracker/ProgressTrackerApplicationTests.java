package com.progressTracker.progress_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgressTrackerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainMethodRunsWithoutException() {
        ProgressTrackerApplication.main(new String[]{});
    }
}
