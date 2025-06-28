package com.progressTracker.progress_tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.progressTracker.progress_tracker.constants.Constants;
import com.progressTracker.progress_tracker.dto.requests.ProgressRequest;
import com.progressTracker.progress_tracker.model.Progress;
import com.progressTracker.progress_tracker.repository.ProgressRepository;
import com.progressTracker.progress_tracker.service.ProgressService;

@ExtendWith(MockitoExtension.class)
public class ProgressServiceTest {
    @Mock
    private ProgressRepository repository;

    @InjectMocks
    private ProgressService service;

    private Map<String, Integer> categories;

    @BeforeEach
    void setUp() {

        // default categories map for tests
        categories = new HashMap<>();
        categories.put(Constants.STUDY_HOURS, 5);
        categories.put(Constants.TV_HOURS, 2);
        categories.put(Constants.WORK_HOURS, 3);
    }

    @Test
    void getByIdWhenFoundReturnsProgress() {
        Progress p = Progress.builder()
                .id(99L)
                .userId(42L)
                .studyHours(1)
                .tvHours(2)
                .workHours(3)
                .cookingHours(4)
                .build();

        when(repository.findById(99L)).thenReturn(Optional.of(p));

        Progress result = service.getById(99L);
        assertSame(p, result);
        verify(repository).findById(99L);
    }

    @Test
    public void saveProgressTest() {
        // —— 准备请求参数
        ProgressRequest request = new ProgressRequest();
        request.setUserID(42L);
        Map<String, Integer> cats = new HashMap<>();
        cats.put(Constants.STUDY_HOURS, 3);
        cats.put(Constants.TV_HOURS, 1);
        // WORK_HOURS 不传，默认 0；COOKING_HOURS 不传，默认 null
        request.setCategories(cats);

        Progress progress = service.saveProgress(request);

        assertEquals(42L, progress.getUserId());
    }
}
