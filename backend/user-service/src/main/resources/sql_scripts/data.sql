INSERT INTO users (id, military_rank, name, role, password)
SELECT u.id, u.military_rank, u.name, u.role, u.password
FROM (
    VALUES
        ('dd29a3c3-47b2-4196-85ee-63977999c9e3'::uuid, 'SOLDIER', 'Pierre', 'USER', '$2a$10$KzBggNyHQZntYY/aQfM.8ez3R6vYjBQQxMT5kJzYgVVlTz6jbLADK'),
        ('5f909a99-4c0c-4edd-af97-a6c6ee4ae8dd'::uuid, 'SENIOR_OFFICER', 'Albert', 'ADMIN', '$2a$10$2RqSQBzvcF7A1czL.u9JNu7qPccSRwK850g/6UKOfqir4dB2m5Kki'),
        ('ac5e1a6e-b310-4788-92bc-61a0654a488a'::uuid, 'OFFICER', 'Momo', 'HIERARCHY', '$2a$10$NeteM8FpeMSk9SuYabPLEe9Bylfb75FzApTPfrxU3V/JtVG6uT2yW')
) AS u(id, military_rank, name, role, password)
WHERE NOT EXISTS (SELECT 1 FROM users);