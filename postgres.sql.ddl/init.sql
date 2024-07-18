CREATE SCHEMA app
   AUTHORIZATION course_manager;

CREATE TABLE app.trainer
(
    id serial,
    name text NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.trainer
    OWNER to course_manager;

CREATE TABLE app.course
(
    id serial,
    title text NOT NULL,
    description text,
    trainer_id integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (trainer_id)
        REFERENCES app.trainer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS app.course
    OWNER to course_manager;