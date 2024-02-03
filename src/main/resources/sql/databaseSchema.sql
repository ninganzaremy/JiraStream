CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          owner_id INTEGER REFERENCES users(id)
);

CREATE TABLE issues (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        status VARCHAR(50),
                        project_id INTEGER REFERENCES projects(id),
                        assignee_id INTEGER REFERENCES users(id)
);