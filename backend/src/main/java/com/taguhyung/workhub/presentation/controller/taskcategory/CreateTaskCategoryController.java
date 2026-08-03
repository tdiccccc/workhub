package com.taguhyung.workhub.presentation.controller.taskcategory;

import com.taguhyung.workhub.application.usecase.taskcategory.CreateTaskCategoryUseCase;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.presentation.mapper.taskcategory.TaskCategoryPresentationMapper;
import com.taguhyung.workhub.presentation.request.taskcategory.CreateTaskCategoryRequest;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.taskcategory.TaskCategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/task-categories")
public class CreateTaskCategoryController {
    private final CreateTaskCategoryUseCase usecase;
    private final TaskCategoryPresentationMapper mapper;

    public CreateTaskCategoryController(
            CreateTaskCategoryUseCase usecase,
            TaskCategoryPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskCategoryResponse>> create(
            @PathVariable Long projectId,
            @Valid @RequestBody CreateTaskCategoryRequest request) {
        TaskCategory taskCategory = usecase.execute(mapper.toCommand(projectId, request));

        TaskCategoryResponse response = mapper.toResponse(taskCategory);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "TaskCategory created", response));
    }
}
