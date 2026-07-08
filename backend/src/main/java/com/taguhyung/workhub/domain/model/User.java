package com.taguhyung.workhub.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime emailVerifiedAt;
    private String password;
    private String rememberToken;
    private BigDecimal hourlyRate;
    private Boolean isValid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    protected User() {

    }

    public static User create(
            String name,
            String email,
            LocalDateTime emailVerifiedAt,
            String password,
            String rememberToken,
            BigDecimal hourlyRate,
            Boolean isValid) {
        User user = new User();

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("ユーザー名は必須です");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("メールアドレスは必須です");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("パスワードは必須です");
        }

        user.name = name;
        user.email = email;
        user.emailVerifiedAt = emailVerifiedAt;
        user.password = password;
        user.rememberToken = rememberToken;
        user.hourlyRate = hourlyRate;
        user.isValid = isValid != null ? isValid : true;

        user.deletedAt = null;

        return user;
    }

    public static User reconstruct(
            Long id,
            String name,
            String email,
            LocalDateTime emailVerifiedAt,
            String password,
            String rememberToken,
            BigDecimal hourlyRate,
            Boolean isValid,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt) {
        User user = new User();

        user.id = id;
        user.name = name;
        user.email = email;
        user.emailVerifiedAt = emailVerifiedAt;
        user.password = password;
        user.rememberToken = rememberToken;
        user.hourlyRate = hourlyRate;
        user.isValid = isValid;
        user.createdAt = createdAt;
        user.updatedAt = updatedAt;
        user.deletedAt = deletedAt;

        return user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
