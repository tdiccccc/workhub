CREATE TABLE tasks (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    project_id BIGINT UNSIGNED NOT NULL,
    task_category_id BIGINT UNSIGNED NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'open',
    estimated_hours DECIMAL(5, 2) NULL,
    created_by BIGINT UNSIGNED NOT NULL,
    assigned_to BIGINT UNSIGNED NULL,
    due_date DATE NULL,
    archived_at DATETIME NULL,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    PRIMARY KEY (id),
    KEY tasks_project_id_idx (project_id),
    KEY tasks_task_category_id_idx (task_category_id),
    KEY tasks_created_by_idx (created_by),
    KEY tasks_assigned_to_idx (assigned_to),
    CONSTRAINT tasks_project_id_foreign
        FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT tasks_task_category_id_foreign
        FOREIGN KEY (task_category_id) REFERENCES task_categories (id),
    CONSTRAINT tasks_created_by_foreign
        FOREIGN KEY (created_by) REFERENCES users (id),
    CONSTRAINT tasks_assigned_to_foreign
        FOREIGN KEY (assigned_to) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
