package com.taguhyung.workhub.application.usecase.project;

import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetProjectListUseCase {
    private final ProjectRepository projectRepository;

    public GetProjectListUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public List<Project> execute() {
        return projectRepository.findAll();
    }
}