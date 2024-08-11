create sequence If Not EXISTS elite_app_setting_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_app_setting
(
    app_setting_id BIGINT PRIMARY KEY DEFAULT nextval('elite_app_setting_seq'),
    app_setting_name VARCHAR(255) NOT NULL,
    app_setting_version VARCHAR(255) NOT NULL,
    app_setting_uid VARCHAR(255) NOT NULL,
    app_setting_secret VARCHAR(255) NOT NULL,
    app_setting_token VARCHAR(255) NOT NULL,
    app_setting_notification BOOLEAN NOT NULL,
    app_setting_email BOOLEAN NOT NULL,
    app_setting_night_mode BOOLEAN NOT NULL,
    app_setting_created_by VARCHAR(255) NOT NULL,
    app_setting_updated_by VARCHAR(255) NOT NULL,
    app_setting_created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    app_setting_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);