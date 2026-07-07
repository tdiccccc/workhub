package com.taguhyung.workhub.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Project {
    private Long id;
    private String name;
    private BigDecimal amount;
    private String description;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    protected Project() {
    }

    public static Project create(
            String name,
            BigDecimal amount,
            String description,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            Boolean isActive) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("プロジェクト名は必須です");
        }

        if (amount == null) {
            throw new IllegalArgumentException("受注金額は必須です");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("案件概要は必須です");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("受注金額は0以上で入力してください");
        }

        if (startedAt != null && endedAt != null && endedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("終了日は開始日以降で入力してください");
        }

        Project project = new Project();

        project.name = name;
        project.amount = amount;
        project.description = description;
        project.startedAt = startedAt;
        project.endedAt = endedAt;
        project.isActive = isActive != null ? isActive : false;

        project.deletedAt = null;

        return project;
    }

    public static Project reconstruct(
            Long id,
            String name,
            BigDecimal amount,
            String description,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            Boolean isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt) {

        Project project = new Project();

        project.id = id;
        project.name = name;
        project.amount = amount;
        project.description = description;
        project.startedAt = startedAt;
        project.endedAt = endedAt;
        project.isActive = isActive;
        project.createdAt = createdAt;
        project.updatedAt = updatedAt;
        project.deletedAt = deletedAt;

        return project;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public Boolean getIsActive() {
        return isActive;
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
