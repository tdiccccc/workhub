package com.taguhyung.workhub.infrastructure.persistence.repository;

import com.taguhyung.workhub.infrastructure.persistence.entity.TaskCategoryJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryJpaRepository
        extends JpaRepository<TaskCategoryJpaEntity, Long> {
    List<TaskCategoryJpaEntity> findByDeletedAtIsNull();

    List<TaskCategoryJpaEntity> findByProjectIdAndDeletedAtIsNullOrderBySortOrderAsc(Long projectId);

    Optional<TaskCategoryJpaEntity> findByIdAndDeletedAtIsNull(Long id);
}
