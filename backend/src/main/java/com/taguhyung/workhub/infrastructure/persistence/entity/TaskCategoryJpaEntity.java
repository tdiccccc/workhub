package com.taguhyung.workhub.infrastructure.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "task_categories")
public class TaskCategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "project_id")
    private Long projectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(nullable = false, name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    protected TaskCategoryJpaEntity() {
    }

    public TaskCategoryJpaEntity(
            Long id,
            Long projectId,
            String name,
            String description,
            String color,
            Integer sortOrder,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.sortOrder = sortOrder;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
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
