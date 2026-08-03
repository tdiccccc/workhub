ALTER TABLE task_categories
    ADD COLUMN deleted_at DATETIME NULL AFTER updated_at;
