package com.taguhyung.workhub.presentation.controller.project;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taguhyung.workhub.application.usecase.project.DeleteProjectUseCase;
import com.taguhyung.workhub.presentation.response.ApiResponse;

@RestController
@RequestMapping("/api/projects")
public class DeleteProjectController {
    private final DeleteProjectUseCase usecase;

    public DeleteProjectController(
            DeleteProjectUseCase usecase) {
        this.usecase = usecase;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {
        usecase.execute(id);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Project deleted", null));
    }
}
