package com.taguhyung.workhub.domain.repository;

import java.util.List;
import java.util.Optional;

import com.taguhyung.workhub.domain.model.Project;

public interface ProjectRepository {
    List<Project> findAll();

    Optional<Project> findById(Long id);

    Project save(Project project);

    void delete(Project project);
}