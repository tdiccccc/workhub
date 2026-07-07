package com.taguhyung.workhub.presentation.mapper.project;

import com.taguhyung.workhub.application.command.project.CreateProjectCommand;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.presentation.request.project.CreateProjectRequest;
import com.taguhyung.workhub.presentation.response.project.ProjectResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProjectPresentationMapper {

    public CreateProjectCommand toCommand(CreateProjectRequest request) {
        return new CreateProjectCommand(
                request.name(),
                request.amount(),
                request.description(),
                request.startedAt(),
                request.endedAt(),
                request.isActive());
    }

    public ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getAmount(),
                project.getDescription(),
                project.getStartedAt(),
                project.getEndedAt(),
                project.getIsActive());
    }

    public List<ProjectResponse> toResponseList(List<Project> projects) {
        return projects.stream()
                .map(this::toResponse)
                .toList();
    }
}
