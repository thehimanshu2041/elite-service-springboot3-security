create sequence If Not EXISTS elite_code_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_code
(
    code_id           BIGINT PRIMARY KEY DEFAULT nextval('elite_code_seq'),
    code_code_type_id   BIGINT   NOT NULL,
    code_code         varchar(255) UNIQUE NOT NULL,
    code_name         varchar(255) NOT NULL,
    code_description  varchar(255) NOT NULL,
    code_created_by   varchar(255) NOT NULL,
    code_updated_by   varchar(255) NOT NULL,
    code_created_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    code_updated_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);