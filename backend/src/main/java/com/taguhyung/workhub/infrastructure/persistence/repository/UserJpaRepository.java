package com.taguhyung.workhub.infrastructure.persistence.repository;

import com.taguhyung.workhub.infrastructure.persistence.entity.UserJpaEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository
        extends JpaRepository<UserJpaEntity, Long> {

    List<UserJpaEntity> findByDeletedAtIsNull();

    Optional<UserJpaEntity> findByEmailAndDeletedAtIsNull(String email);

    Optional<UserJpaEntity> findByIdAndDeletedAtIsNull(Long id);

}
