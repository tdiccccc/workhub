CREATE TABLE role_users (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    role_id BIGINT UNSIGNED NOT NULL,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (id),
    UNIQUE KEY role_users_user_id_role_id_unique (user_id, role_id),
    KEY role_users_user_id_idx (user_id),
    KEY role_users_role_id_idx (role_id),
    CONSTRAINT role_users_user_id_foreign
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT role_users_role_id_foreign
        FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
