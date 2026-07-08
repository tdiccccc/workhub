package com.taguhyung.workhub.application.usecase.project;

import com.taguhyung.workhub.application.command.project.UpdateProjectCommand;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProjectUseCase {
    private final ProjectRepository projectRepository;

    public UpdateProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project execute(UpdateProjectCommand command) {
        Project project = projectRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("プロジェクトが見つかりません: " + command.id()));

        project.update(
                command.name(),
                command.amount(),
                command.description(),
                command.startedAt(),
                command.endedAt(),
                command.isActive());

        return projectRepository.save(project);
    }
}
