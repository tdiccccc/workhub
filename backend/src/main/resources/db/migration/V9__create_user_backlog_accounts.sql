CREATE TABLE user_backlog_accounts (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    api_key TEXT NOT NULL,
    backlog_user_id VARCHAR(100) NULL,
    backlog_space_key VARCHAR(100) NOT NULL,
    backlog_project_key VARCHAR(100) NULL,
    is_valid BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    PRIMARY KEY (id),
    UNIQUE KEY user_backlog_accounts_user_id_unique (user_id),
    KEY user_backlog_accounts_user_id_idx (user_id),
    CONSTRAINT user_backlog_accounts_user_id_foreign
        FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
