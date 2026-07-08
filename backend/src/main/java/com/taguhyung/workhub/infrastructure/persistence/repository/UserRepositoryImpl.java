package com.taguhyung.workhub.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.taguhyung.workhub.domain.model.User;
import com.taguhyung.workhub.domain.repository.UserRepository;
import com.taguhyung.workhub.infrastructure.persistence.entity.UserJpaEntity;
import com.taguhyung.workhub.infrastructure.persistence.mapper.UserPersistenceMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;

    public UserRepositoryImpl(
            UserJpaRepository userJpaRepository,
            UserPersistenceMapper mapper) {
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findByDeletedAtIsNull()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findByIdAndDeletedAtIsNull(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmailAndDeletedAtIsNull(email)
                .map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserJpaEntity savedEntity = userJpaRepository.save(mapper.toEntity(user));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(mapper.toEntity(user));
    }
}
