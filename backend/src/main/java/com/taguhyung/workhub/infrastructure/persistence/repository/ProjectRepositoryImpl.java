package com.taguhyung.workhub.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.domain.repository.ProjectRepository;
import com.taguhyung.workhub.infrastructure.persistence.entity.ProjectJpaEntity;
import com.taguhyung.workhub.infrastructure.persistence.mapper.ProjectPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectPersistenceMapper mapper;

    public ProjectRepositoryImpl(
            ProjectJpaRepository projectJpaRepository,
            ProjectPersistenceMapper mapper) {
        this.projectJpaRepository = projectJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findByDeletedAtIsNull()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findByIdAndDeletedAtIsNull(id)
                .map(mapper::toDomain);
    }

    @Override
    public Project save(Project project) {
        ProjectJpaEntity savedEntity = projectJpaRepository.save(mapper.toEntity(project));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Project project) {
        projectJpaRepository.delete(mapper.toEntity(project));
    }
}