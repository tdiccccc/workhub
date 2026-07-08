package com.taguhyung.workhub.infrastructure.persistence.mapper;

import com.taguhyung.workhub.domain.model.User;
import com.taguhyung.workhub.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserJpaEntity entity) {
        return User.reconstruct(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getEmailVerifiedAt(),
                entity.getPassword(),
                entity.getRememberToken(),
                entity.getHourlyRate(),
                entity.getIsValid(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt());
    }

    public UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getEmailVerifiedAt(),
                user.getPassword(),
                user.getRememberToken(),
                user.getHourlyRate(),
                user.getIsValid(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt());
    }
}
