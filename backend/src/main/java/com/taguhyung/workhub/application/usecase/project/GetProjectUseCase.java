package com.taguhyung.workhub.application.usecase.project;

import com.taguhyung.workhub.domain.exception.ProjectNotFoundException;
import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetProjectUseCase {
    private final ProjectRepository projectRepository;

    public GetProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public Project execute(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }
}