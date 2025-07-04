package com.progressTracker.progress_tracker.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.progressTracker.progress_tracker.enums.Category;
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

@ExtendWith(MockitoExtension.class)
public class ProgressServiceTest {
    @Mock
    private ProgressRepository repository;

    @InjectMocks
    private ProgressService service;

    private Map<Category, Integer> categories;

    @BeforeEach
    void setUp() {

        // default categories map for tests
        categories = new HashMap<>();
        categories.put(Category.STUDY_HOURS, 5);
        categories.put(Category.TV_HOURS, 2);
        categories.put(Category.WORK_HOURS, 3);
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
        request.setUserId(42L);
        Map<Category, Integer> cats = new HashMap<>();
        cats.put(Category.STUDY_HOURS, 3);
        cats.put(Category.TV_HOURS, 1);
        // WORK_HOURS 不传，默认 0；COOKING_HOURS 不传，默认 0
        request.setCategories(cats);

        Progress progress = service.saveProgress(request);

        assertEquals(42L, progress.getUserId());
    }
}
