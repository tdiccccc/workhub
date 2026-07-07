package com.taguhyung.workhub.presentation.controller.project;

import com.taguhyung.workhub.application.usecase.project.GetProjectListUseCase;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.presentation.mapper.project.ProjectPresentationMapper;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.project.ProjectResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class GetProjectListController {

    private final GetProjectListUseCase usecase;
    private final ProjectPresentationMapper mapper;

    public GetProjectListController(
            GetProjectListUseCase usecase,
            ProjectPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> index() {
        List<Project> projects = usecase.execute();

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Projects fetched", mapper.toResponseList(projects)));
    }
}
