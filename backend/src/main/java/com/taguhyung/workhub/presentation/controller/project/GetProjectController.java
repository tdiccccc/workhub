package com.taguhyung.workhub.presentation.controller.project;

import com.taguhyung.workhub.application.usecase.project.GetProjectUseCase;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.presentation.response.project.ProjectResponse;
import com.taguhyung.workhub.presentation.mapper.project.ProjectPresentationMapper;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class GetProjectController {
    private final GetProjectUseCase usecase;
    private final ProjectPresentationMapper mapper;

    public GetProjectController(
            GetProjectUseCase usecase,
            ProjectPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> show(
            @PathVariable Long id) {
        Project project = usecase.execute(id);
        ProjectResponse response = mapper.toResponse(project);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Project fetched", response));
    }
}
