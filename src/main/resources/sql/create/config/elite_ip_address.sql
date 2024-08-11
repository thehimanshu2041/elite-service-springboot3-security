create sequence If Not EXISTS elite_ip_address_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_ip_address
(
    ip_id BIGINT PRIMARY KEY DEFAULT nextval('elite_ip_address_seq'),
    ip_username VARCHAR(255),
    ip_token VARCHAR(255),
    ip_port VARCHAR(255),
    ip_context_path VARCHAR(255),
    ip_request_path VARCHAR(255),
    ip_address VARCHAR(255),
    ip_method VARCHAR(255),
    ip_postal_code VARCHAR(255),
    ip_latitude VARCHAR(255),
    ip_longitude VARCHAR(255),
    ip_city VARCHAR(255),
    ip_state VARCHAR(255),
    ip_country VARCHAR(255),
    ip_metro_code VARCHAR(255),
    ip_area_code VARCHAR(255),
    ip_num VARCHAR(255),
    ip_url VARCHAR(255),
    ip_referer VARCHAR(255),
    ip_created_by VARCHAR(255) NOT NULL,
    ip_updated_by VARCHAR(255) NOT NULL,
    ip_created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);