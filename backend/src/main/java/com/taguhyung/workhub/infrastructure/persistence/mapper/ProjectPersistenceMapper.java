package com.taguhyung.workhub.infrastructure.persistence.mapper;

import com.taguhyung.workhub.domain.model.Project;
import com.taguhyung.workhub.infrastructure.persistence.entity.ProjectJpaEntity;

import org.springframework.stereotype.Component;

@Component
public class ProjectPersistenceMapper {

    public Project toDomain(ProjectJpaEntity entity) {
        return Project.reconstruct(
                entity.getId(),
                entity.getName(),
                entity.getAmount(),
                entity.getDescription(),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getIsActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt());
    }

    public ProjectJpaEntity toEntity(Project project) {
        return new ProjectJpaEntity(
                project.getId(),
                project.getName(),
                project.getAmount(),
                project.getDescription(),
                project.getStartedAt(),
                project.getEndedAt(),
                project.getIsActive(),
                project.getCreatedAt(),
                project.getUpdatedAt(),
                project.getDeletedAt());
    }
}
