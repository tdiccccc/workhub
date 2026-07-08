package com.taguhyung.workhub.domain.repository;

import java.util.List;
import java.util.Optional;

import com.taguhyung.workhub.domain.model.User;

public interface UserRepository {
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    void delete(User user);
}
