-- credentials : himanshusingh2041/@ABC123abc@

insert into elite_user (user_name, user_email,
                        user_password, user_first_name, user_last_name, user_gender, user_address, user_phone,
                        user_country,
                        user_created_by, user_updated_by)
values ('himanshusingh2041', 'himanshusingh2041@gmail.com',
        '$2a$10$qGAuDmHwKuG80n1fcBhwU.mNY.ZC93Jie/Ay5Ncrphdtr9V16xEPC', 'Himanshu', 'kumar',
        (select code_id from elite_code where code_code = 'MALE'), 'New Delhi', 9810249301,
        (select country_id from elite_country where country_iso = 'IN'),
        'DEFAULT', 'DEFAULT');

insert into elite_role (role_name, role_description, role_created_by, role_updated_by)
values ('ADMIN', 'Admin', 'DEFAULT', 'DEFAULT');

insert into elite_role (role_name, role_description, role_created_by, role_updated_by)
values ('USER', 'User', 'DEFAULT', 'DEFAULT');

insert into elite_user_role (user_id, role_id)
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'ADMIN'));

insert into elite_user_role (user_id, role_id)
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'USER'));


INSERT INTO elite_user_setting (user_setting_user_id, user_setting_uid, user_setting_secret,
                                user_setting_token, user_setting_notification, user_setting_email,
                                user_setting_night_mode, user_setting_created_by, user_setting_updated_by)
VALUES ((select user_id from elite_user where user_name = 'himanshusingh2041'), '5yF1xm01hCI=',
        'iG6cEWWrEmJNjkK0jwRvrTxAsQQ60HEOHXWqpEqAa7k=', 'Gw6tc0jyIg39ynGHGgKhoD1O2Taehcyv6rDyHdamsNw=', FALSE, FALSE, FALSE, 'DEFAULT', 'DEFAULT');