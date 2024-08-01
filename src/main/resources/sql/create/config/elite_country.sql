CREATE TABLE IF NOT EXISTS elite_country(
    country_id INT PRIMARY KEY,
    country_iso varchar(255) NOT NULL,
    country_name varchar(255) NOT NULL,
    country_nicename varchar(255) NOT NULL,
    country_iso3 varchar(255) ,
    country_numcode INT,
    country_phonecode INT NOT NULL
);

create sequence If Not EXISTS elite_country_seq
increment 1
start 1000000001;