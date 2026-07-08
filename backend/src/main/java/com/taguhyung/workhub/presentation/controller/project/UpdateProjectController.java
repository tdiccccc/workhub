package com.taguhyung.workhub.presentation.controller.project;

import com.taguhyung.workhub.application.usecase.project.UpdateProjectUseCase;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.presentation.mapper.project.ProjectPresentationMapper;
import com.taguhyung.workhub.presentation.request.project.UpdateProjectRequest;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.project.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class UpdateProjectController {
    private final UpdateProjectUseCase usecase;
    private final ProjectPresentationMapper mapper;

    public UpdateProjectController(
            UpdateProjectUseCase usecase,
            ProjectPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProjectRequest request) {
        Project project = usecase.execute(mapper.toUpdateCommand(id, request));

        ProjectResponse response = mapper.toResponse(project);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Project updated", response));
    }
}
