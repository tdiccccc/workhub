package com.taguhyung.workhub.domain.repository;

import java.util.List;
import java.util.Optional;

import com.taguhyung.workhub.domain.model.TaskCategory;

public interface TaskCategoryRepository {
    List<TaskCategory> findAll();

    List<TaskCategory> findByProjectId(Long projectId);

    Optional<TaskCategory> findById(Long id);

    TaskCategory save(TaskCategory taskCategory);

    void delete(TaskCategory taskCategory);
}