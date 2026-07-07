package com.taguhyung.workhub.presentation.controller.project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taguhyung.workhub.application.usecase.project.CreateProjectUseCase;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.presentation.mapper.project.ProjectPresentationMapper;
import com.taguhyung.workhub.presentation.request.project.CreateProjectRequest;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.project.ProjectResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class CreateProjectController {
    private final CreateProjectUseCase usecase;
    private final ProjectPresentationMapper mapper;

    public CreateProjectController(
            CreateProjectUseCase usecase,
            ProjectPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> create(
            @Valid @RequestBody CreateProjectRequest request) {
        Project project = usecase.execute(mapper.toCommand(request));

        ProjectResponse response = mapper.toResponse(project);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Project created", response));
    }

}
