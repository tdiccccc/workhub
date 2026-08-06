package com.taguhyung.workhub.domain.model;

import java.time.LocalDateTime;

public class TaskCategory {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private String color;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    protected TaskCategory() {
    }

    public static TaskCategory create(
            Long projectId,
            String name,
            String description,
            String color,
            Integer sortOrder) {
        if (projectId == null) {
            throw new IllegalArgumentException("案件IDは必須です");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("タスクカテゴリー名は必須です");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("タスクカテゴリー概要は必須です");
        }

        TaskCategory taskCategory = new TaskCategory();

        taskCategory.projectId = projectId;
        taskCategory.name = name;
        taskCategory.description = description;
        taskCategory.color = color;
        taskCategory.sortOrder = sortOrder;
        taskCategory.sortOrder = sortOrder != null ? sortOrder : 0;

        taskCategory.deletedAt = null;

        return taskCategory;
    }

    public void update(
            Long projectId,
            String name,
            String description,
            String color,
            Integer sortOrder) {
        if (projectId == null) {
            throw new IllegalArgumentException("案件IDは必須です");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("タスクカテゴリー名は必須です");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("タスクカテゴリー概要は必須です");
        }

        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.sortOrder = sortOrder != null ? sortOrder : 0;
    }

    public void delete(LocalDateTime deletedAt) {
        if (deletedAt == null) {
            throw new IllegalArgumentException("削除日時は必須です");
        }

        this.deletedAt = deletedAt;
    }

    public static TaskCategory reconstruct(
            Long id,
            Long projectId,
            String name,
            String description,
            String color,
            Integer sortOrder,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt) {

        TaskCategory taskCategory = new TaskCategory();

        taskCategory.id = id;
        taskCategory.projectId = projectId;
        taskCategory.name = name;
        taskCategory.description = description;
        taskCategory.color = color;
        taskCategory.sortOrder = sortOrder;
        taskCategory.createdAt = createdAt;
        taskCategory.updatedAt = updatedAt;
        taskCategory.deletedAt = deletedAt;

        return taskCategory;
    }

    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public Integer getSortOrder() {
        return sortOrder;
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
