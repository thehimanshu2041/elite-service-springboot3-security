create sequence If Not EXISTS elite_role_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_role
(
    role_id BIGINT PRIMARY KEY DEFAULT nextval('elite_role_seq'),
    role_name VARCHAR(255) NOT NULL,
    role_description VARCHAR(255) NOT NULL,
    role_created_by VARCHAR(255) NOT NULL,
    role_updated_by VARCHAR(255) NOT NULL,
    role_created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);