create sequence If Not EXISTS elite_user_setting_seq
increment 1
start 1000000001;

CREATE TABLE IF NOT EXISTS elite_user_setting
(
    user_setting_id BIGINT PRIMARY KEY DEFAULT nextval('elite_user_setting_seq'),
    user_setting_user_id BIGINT NOT NULL UNIQUE,
    user_setting_uid VARCHAR(255) NOT NULL,
    user_setting_secret VARCHAR(255) NOT NULL,
    user_setting_token VARCHAR(255) NOT NULL,
    user_setting_notification BOOLEAN NOT NULL,
    user_setting_email BOOLEAN NOT NULL,
    user_setting_night_mode BOOLEAN NOT NULL,
    user_setting_created_by VARCHAR(255) NOT NULL,
    user_setting_updated_by VARCHAR(255) NOT NULL,
    user_setting_created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_setting_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);