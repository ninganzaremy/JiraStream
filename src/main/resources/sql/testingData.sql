-- Insert users
INSERT INTO users (username, password, email) VALUES ('user1', 'pass1', 'user1@example.com');
INSERT INTO users (username, password, email) VALUES ('user2', 'pass2', 'user2@example.com');

-- Insert projects
INSERT INTO projects (name, description, owner_id) VALUES ('Project 1', 'Description for Project 1', 1);
INSERT INTO projects (name, description, owner_id) VALUES ('Project 2', 'Description for Project 2', 2);

-- Insert issues
INSERT INTO issues (title, description, status, project_id, assignee_id) VALUES ('Issue 1', 'Description for Issue 1', 'NEW', 1, 1);
INSERT INTO issues (title, description, status, project_id, assignee_id) VALUES ('Issue 2', 'Description for Issue 2', 'IN_PROGRESS', 1, 2);