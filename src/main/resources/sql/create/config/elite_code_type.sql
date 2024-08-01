CREATE TABLE IF NOT EXISTS elite_code_type
(
    code_type_id           INT PRIMARY KEY,
    code_type_code         varchar(255) UNIQUE NOT NULL,
    code_type_name          varchar(255) NOT NULL,
    code_type_description varchar(255) NOT NULL,
    code_type_created_by   varchar(255) NOT NULL,
    code_type_updated_by   varchar(255) NOT NULL,
    code_type_created_date TIMESTAMP    NOT NULL,
    code_type_updated_date TIMESTAMP    NOT NULL
    );

create sequence If Not EXISTS elite_code_type_seq
increment 1
start 1000000001;