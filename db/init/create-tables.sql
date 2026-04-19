CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(15) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    user_id INT REFERENCES users(id) ON DELETE cascade -- if the user got deleted, their tasks also will be deleted
);