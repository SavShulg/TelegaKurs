-- liquibase formatted sql

-- changeset SaveliyUser:1

create table notification_task
(
id BIGSERIAL primary key,
chat_id BIGINT,
text VARCHAR,
task_date TIMESTAMP
)