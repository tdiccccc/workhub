package com.taguhyung.workhub.application.usecase.project;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taguhyung.workhub.application.command.project.CreateProjectCommand;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;

@Service
public class CreateProjectUseCase {
    private final ProjectRepository projectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project execute(CreateProjectCommand command) {
        Project project = Project.create(
                command.name(),
                command.amount(),
                command.description(),
                command.startedAt(),
                command.endedAt(),
                command.isActive());

        return projectRepository.save(project);
    }
}