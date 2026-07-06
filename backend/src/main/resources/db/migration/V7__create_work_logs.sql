CREATE TABLE work_logs (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    project_id BIGINT UNSIGNED NOT NULL,
    task_category_id BIGINT UNSIGNED NULL,
    task_id BIGINT UNSIGNED NOT NULL,
    work_date DATE NOT NULL,
    work_minutes INT NOT NULL,
    comment TEXT NULL,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    PRIMARY KEY (id),
    KEY work_logs_user_id_idx (user_id),
    KEY work_logs_project_id_idx (project_id),
    KEY work_logs_task_category_id_idx (task_category_id),
    KEY work_logs_task_id_idx (task_id),
    KEY work_logs_work_date_idx (work_date),
    CONSTRAINT work_logs_user_id_foreign
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT work_logs_project_id_foreign
        FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT work_logs_task_category_id_foreign
        FOREIGN KEY (task_category_id) REFERENCES task_categories (id),
    CONSTRAINT work_logs_task_id_foreign
        FOREIGN KEY (task_id) REFERENCES tasks (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
