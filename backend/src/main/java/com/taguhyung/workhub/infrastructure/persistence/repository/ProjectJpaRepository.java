package com.taguhyung.workhub.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taguhyung.workhub.infrastructure.persistence.entity.ProjectJpaEntity;

public interface ProjectJpaRepository
        extends JpaRepository<ProjectJpaEntity, Long> {

}
