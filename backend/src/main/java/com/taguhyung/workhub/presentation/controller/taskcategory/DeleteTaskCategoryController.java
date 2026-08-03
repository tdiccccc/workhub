package com.taguhyung.workhub.presentation.controller.taskcategory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taguhyung.workhub.application.usecase.taskcategory.DeleteTaskCategoryUseCase;
import com.taguhyung.workhub.presentation.response.ApiResponse;

@RestController
@RequestMapping("/api/projects/{projectId}/task-categories")
public class DeleteTaskCategoryController {
    private final DeleteTaskCategoryUseCase usecase;

    public DeleteTaskCategoryController(
            DeleteTaskCategoryUseCase usecase) {
        this.usecase = usecase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {
        usecase.execute(id);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "TaskCategory deleted", null));
    }
}
