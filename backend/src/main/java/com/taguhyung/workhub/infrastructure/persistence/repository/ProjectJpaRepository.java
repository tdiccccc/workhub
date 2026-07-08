package com.taguhyung.workhub.infrastructure.persistence.repository;

import com.taguhyung.workhub.infrastructure.persistence.entity.ProjectJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository
        extends JpaRepository<ProjectJpaEntity, Long> {

    List<ProjectJpaEntity> findByDeletedAtIsNull();

    Optional<ProjectJpaEntity> findByIdAndDeletedAtIsNull(Long id);
}
