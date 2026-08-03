package com.taguhyung.workhub.presentation.controller.taskcategory;

import com.taguhyung.workhub.application.usecase.taskcategory.GetTaskCategoryUseCase;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.presentation.mapper.taskcategory.TaskCategoryPresentationMapper;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.taskcategory.TaskCategoryResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/projects/{projectId}/task-categories")
public class GetTaskCategoryController {
    private final GetTaskCategoryUseCase usecase;
    private final TaskCategoryPresentationMapper mapper;

    public GetTaskCategoryController(
            GetTaskCategoryUseCase usecase,
            TaskCategoryPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskCategoryResponse>> show(
            @PathVariable Long id) {
        TaskCategory taskCategory = usecase.execute(id);
        TaskCategoryResponse response = mapper.toResponse(taskCategory);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "TaskCategory fetched", response));
    }
}
