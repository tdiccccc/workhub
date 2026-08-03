package com.taguhyung.workhub.presentation.controller.taskcategory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taguhyung.workhub.application.usecase.taskcategory.UpdateTaskCategoryUseCase;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.presentation.mapper.taskcategory.TaskCategoryPresentationMapper;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.taskcategory.TaskCategoryResponse;
import com.taguhyung.workhub.presentation.request.taskcategory.UpdateTaskCategoryRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects/{projectId}/task-categories")
public class UpdateTaskCategoryController {
    private final UpdateTaskCategoryUseCase usecase;
    private final TaskCategoryPresentationMapper mapper;

    public UpdateTaskCategoryController(
            UpdateTaskCategoryUseCase usecase,
            TaskCategoryPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskCategoryResponse>> update(
            @PathVariable Long id,
            @PathVariable Long projectId,
            @Valid @RequestBody UpdateTaskCategoryRequest request) {
        TaskCategory taskCategory = usecase.execute(mapper.toUpdateCommand(
                id,
                projectId,
                request));
        TaskCategoryResponse response = mapper.toResponse(taskCategory);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "TaskCategory updated", response));
    }
}
