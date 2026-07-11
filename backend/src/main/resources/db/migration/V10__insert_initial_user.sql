INSERT INTO users (
    name,
    email,
    email_verified_at,
    password,
    remember_token,
    hourly_rate,
    is_valid,
    created_at,
    updated_at,
    deleted_at
) VALUES (
    'Admin User',
    'admin@example.com',
    CURRENT_TIMESTAMP,
    '$2a$10$Iv38tBbNUadpkj1enZF41uK5K990i7czSm2OLst5k7B51aJNa32vC',
    NULL,
    NULL,
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP,
    NULL
);
