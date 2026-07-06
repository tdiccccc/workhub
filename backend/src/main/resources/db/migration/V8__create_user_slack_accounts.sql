CREATE TABLE user_slack_accounts (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    slack_user_id VARCHAR(100) NULL,
    slack_bot_token TEXT NOT NULL,
    slack_channel_id VARCHAR(100) NOT NULL,
    notification_interval_minutes INT NOT NULL DEFAULT 0,
    last_notified_at DATETIME NULL,
    is_valid BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NULL,
    updated_at DATETIME NULL,
    PRIMARY KEY (id),
    UNIQUE KEY user_slack_accounts_user_id_unique (user_id),
    KEY user_slack_accounts_user_id_idx (user_id),
    CONSTRAINT user_slack_accounts_user_id_foreign
        FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
