CREATE TABLE IF NOT EXISTS elite_user
(
    user_id           INT PRIMARY KEY,
    user_name         varchar(255) UNIQUE NOT NULL,
    user_email        varchar(255) UNIQUE NOT NULL,
    user_password     varchar(255)        NOT NULL,
    user_first_name   varchar(255)        NOT NULL,
    user_last_name    varchar(255)        NOT NULL,
    user_created_by   varchar(255)        NOT NULL,
    user_updated_by   varchar(255)        NOT NULL,
    user_created_date TIMESTAMP           NOT NULL,
    user_updated_date TIMESTAMP           NOT NULL
);

create sequence If Not EXISTS elite_user_seq
increment 1
start 1000000001;