package com.taguhyung.workhub.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;

import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;
import com.taguhyung.workhub.infrastructure.persistence.entity.TaskCategoryJpaEntity;
import com.taguhyung.workhub.infrastructure.persistence.mapper.TaskCategoryPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskCategoryRepositoryImpl implements TaskCategoryRepository {
    private final TaskCategoryJpaRepository taskCategoryJpaRepository;
    private final TaskCategoryPersistenceMapper mapper;

    public TaskCategoryRepositoryImpl(
            TaskCategoryJpaRepository taskCategoryJpaRepository,
            TaskCategoryPersistenceMapper mapper) {
        this.taskCategoryJpaRepository = taskCategoryJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaskCategory> findAll() {
        return taskCategoryJpaRepository.findByDeletedAtIsNull()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<TaskCategory> findByProjectId(Long projectId) {
        return taskCategoryJpaRepository.findByProjectIdAndDeletedAtIsNull(projectId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TaskCategory> findById(Long id) {
        return taskCategoryJpaRepository.findByIdAndDeletedAtIsNull(id)
                .map(mapper::toDomain);
    }

    @Override
    public TaskCategory save(TaskCategory taskCategory) {
        TaskCategoryJpaEntity savedEntity = taskCategoryJpaRepository.save(mapper.toEntity(taskCategory));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(TaskCategory taskCategory) {
        taskCategoryJpaRepository.delete(mapper.toEntity(taskCategory));
    }
}
