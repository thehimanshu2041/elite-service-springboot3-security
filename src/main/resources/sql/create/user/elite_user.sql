create sequence If Not EXISTS elite_user_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_user
(
    user_id BIGINT PRIMARY KEY DEFAULT nextval('elite_user_seq'),
    user_name VARCHAR(255) NOT NULL UNIQUE,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_first_name VARCHAR(255) NOT NULL,
    user_last_name VARCHAR(255) NOT NULL,
    user_gender BIGINT NOT NULL,
    user_address VARCHAR(255) NOT NULL,
    user_phone BIGINT NOT NULL,
    user_country BIGINT NOT NULL,
    user_created_by VARCHAR(255) NOT NULL,
    user_updated_by VARCHAR(255) NOT NULL,
    user_created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);