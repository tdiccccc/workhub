package com.taguhyung.workhub.application.usecase.project;

import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteProjectUseCase {
    private final ProjectRepository projectRepository;

    public DeleteProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public void execute(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("プロジェクトが見つかりません: " + id));

        project.delete(LocalDateTime.now());
        projectRepository.save(project);
    }
}
