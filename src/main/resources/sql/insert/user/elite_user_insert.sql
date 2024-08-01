-- credentials : himanshusingh2041/@ABC123abc@

insert into elite_user (user_id, user_name, user_email,
                        user_password, user_first_name, user_last_name, user_gender, user_address, user_phone,
                        user_country,
                        user_created_by, user_updated_by, user_created_date, user_updated_date)
values (nextval('elite_user_seq'), 'himanshusingh2041', 'himanshusingh2041@gmail.com',
        '$2a$10$qGAuDmHwKuG80n1fcBhwU.mNY.ZC93Jie/Ay5Ncrphdtr9V16xEPC', 'Himanshu', 'kumar',
        (select code_id from elite_code where code_code = 'MALE'), 'New Delhi', 9810249301,
        (select country_id from elite_country where country_iso = 'IN'),
        'ADMIN', 'ADMIN', current_timestamp, current_timestamp);

insert into elite_role (role_id, role_name, role_description, role_created_by, role_updated_by, role_created_date,
                        role_updated_date)
values (nextval('elite_role_seq'), 'ADMIN', 'Admin', 'ADMIN', 'ADMIN', current_timestamp, current_timestamp);
insert into elite_role (role_id, role_name, role_description, role_created_by, role_updated_by, role_created_date,
                        role_updated_date)
values (nextval('elite_role_seq'), 'USER', 'User', 'ADMIN', 'ADMIN', current_timestamp, current_timestamp);

insert into elite_user_role (user_id, role_id)
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'ADMIN'));

insert into elite_user_role (user_id, role_id)
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'USER'));