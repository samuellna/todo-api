-- Inserting users
INSERT INTO users (name, email) VALUES
    ('Ana Silva', 'ana@email.com'),
    ('Bruno Souza', 'bruno@email.com'),
    ('Carlos Lima', 'carlos@email.com'),
    ('Daniela Alves', 'daniela@email.com'),
    ('Eduardo Pereira', 'eduardo@email.com'),
    ('Fernanda Costa', 'fernanda@email.com'),
    ('Gabriel Rocha', 'gabriel@email.com'),
    ('Helena Martins', 'helena@email.com'),
    ('Igor Santos', 'igor@email.com'),
    ('Juliana Freitas', 'juliana@email.com');

-- Inserting tasks
INSERT INTO tasks (title, description, status, created_at, user_id) VALUES
-- User 1
('Estudar Spring', 'Revisar conceitos de Spring Boot', 'PENDING', NOW(), 1),
('Fazer exercício', 'Academia 1h', 'DONE', NOW(), 1),

-- User 2
('Ler livro', 'Capítulo 5 de Clean Code', 'PENDING', NOW(), 2),

-- User 4
('Trabalhar no projeto', 'Implementar API', 'IN_PROGRESS', NOW(), 4),
('Revisar PR', 'Code review do time', 'PENDING', NOW(), 4),
('Atualizar documentação', 'Swagger', 'DONE', NOW(), 4),

-- User 5
('Estudar Docker', 'Subir container com Postgres', 'PENDING', NOW(), 5),

-- User 6
('Organizar tarefas', 'Planejar semana', 'DONE', NOW(), 6),
('Responder emails', 'Inbox zero', 'PENDING', NOW(), 6),

-- User 8
('Assistir aula', 'Spring Security', 'PENDING', NOW(), 8),

-- User 9
('Corrigir bugs', 'Issues abertas no GitHub', 'IN_PROGRESS', NOW(), 9),
('Deploy aplicação', 'Subir no servidor', 'PENDING', NOW(), 9),

-- User 10
('Planejar viagem', 'Ver passagens', 'PENDING', NOW(), 10);