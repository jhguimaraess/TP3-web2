INSERT INTO tb_roles (role_id, name) VALUES (1, 'ADMIN')
    ON CONFLICT (role_id) DO UPDATE SET name = EXCLUDED.name;
INSERT INTO tb_roles (role_id, name) VALUES (2, 'BASIC')
    ON CONFLICT (role_id) DO UPDATE SET name = EXCLUDED.name;