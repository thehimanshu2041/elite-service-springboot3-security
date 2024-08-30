create sequence If Not EXISTS elite_country_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_country(
    country_id BIGINT PRIMARY KEY DEFAULT nextval('elite_country_seq'),
    country_iso varchar(255) UNIQUE NOT NULL,
    country_name varchar(255) UNIQUE NOT NULL,
    country_nicename varchar(255) UNIQUE NOT NULL,
    country_iso3 varchar(255) ,
    country_numcode BIGINT,
    country_phonecode BIGINT UNIQUE NOT NULL
);