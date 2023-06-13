DROP TABLE IF EXISTS tasks_has_tags;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS milestones;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS project_status;
DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    task_id       BIGINT      NOT NULL AUTO_INCREMENT,
    project_id    BIGINT      NOT NULL,
    task_name     VARCHAR(45) NOT NULL,
    content       TEXT,
    register_date DATETIME,
    end_date      DATETIME,
    mile_stone_id BIGINT,
    PRIMARY KEY (task_id),
    FOREIGN KEY (project_id) REFERENCES projects (project_id),
    FOREIGN KEY (mile_stone_id) REFERENCES milestones (mile_stone_id)
);

CREATE TABLE project_status
(
    status_id   INT         NOT NULL AUTO_INCREMENT,
    status_name VARCHAR(45) NOT NULL,
    PRIMARY KEY (status_id)
);

CREATE TABLE projects
(
    project_id          BIGINT      NOT NULL AUTO_INCREMENT,
    project_name        VARCHAR(45) NOT NULL,
    project_description TEXT,
    status_id           INT         NOT NULL,
    PRIMARY KEY (project_id),
    FOREIGN KEY (status_id) REFERENCES project_status (status_id)
);

CREATE TABLE authorities
(
    user_id    VARCHAR(45) NOT NULL,
    project_id BIGINT      NOT NULL,
    role       VARCHAR(10) NOT NULL,
    PRIMARY KEY (user_id, project_id),
    FOREIGN KEY (project_id) REFERENCES projects (project_id)
);


CREATE TABLE milestones
(
    mile_stone_id   BIGINT      NOT NULL AUTO_INCREMENT,
    project_id      BIGINT      NOT NULL,
    mile_stone_name VARCHAR(45) NOT NULL,
    start_date      DATE,
    end_date        DATE,
    PRIMARY KEY (mile_stone_id),
    FOREIGN KEY (project_id) REFERENCES projects (project_id)
);



CREATE TABLE comments
(
    comments_id   BIGINT      NOT NULL AUTO_INCREMENT,
    task_id       BIGINT      NOT NULL,
    writer_id     VARCHAR(45) NOT NULL,
    content       TEXT        NOT NULL,
    register_date DATETIME,
    PRIMARY KEY (comments_id),
    FOREIGN KEY (task_id) REFERENCES tasks (task_id)
);


CREATE TABLE tags
(
    tag_id     BIGINT      NOT NULL AUTO_INCREMENT,
    tag_name   VARCHAR(45) NOT NULL,
    project_id BIGINT      NOT NULL,
    PRIMARY KEY (tag_id),
    FOREIGN KEY (project_id) REFERENCES projects (project_id)
);


CREATE TABLE tasks_has_tags
(
    task_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL,
    PRIMARY KEY (task_id, tag_id),
    FOREIGN KEY (task_id) REFERENCES tasks (task_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);
