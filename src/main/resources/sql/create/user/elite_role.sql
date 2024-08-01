CREATE TABLE IF NOT EXISTS elite_role
(
    role_id           INT PRIMARY KEY,
    role_name         varchar(255) UNIQUE NOT NULL,
    role_description  varchar(255) NOT NULL,
    role_created_by   varchar(255) NOT NULL,
    role_updated_by   varchar(255) NOT NULL,
    role_created_date TIMESTAMP    NOT NULL,
    role_updated_date TIMESTAMP    NOT NULL
);

create sequence If Not EXISTS elite_role_seq
increment 1
start 1000000001;