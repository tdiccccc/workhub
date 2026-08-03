package com.taguhyung.workhub.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.infrastructure.persistence.entity.TaskCategoryJpaEntity;

@Component
public class TaskCategoryPersistenceMapper {

    public TaskCategory toDomain(TaskCategoryJpaEntity entity) {
        return TaskCategory.reconstruct(
                entity.getId(),
                entity.getProjectId(),
                entity.getName(),
                entity.getDescription(),
                entity.getColor(),
                entity.getSortOrder(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt());
    }

    public TaskCategoryJpaEntity toEntity(TaskCategory taskCategory) {
        return new TaskCategoryJpaEntity(
                taskCategory.getId(),
                taskCategory.getProjectId(),
                taskCategory.getName(),
                taskCategory.getDescription(),
                taskCategory.getColor(),
                taskCategory.getSortOrder(),
                taskCategory.getCreatedAt(),
                taskCategory.getUpdatedAt(),
                taskCategory.getDeletedAt());
    }
}
