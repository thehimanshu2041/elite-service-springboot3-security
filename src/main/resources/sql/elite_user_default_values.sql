-- credentials : himanshusingh2041/@ABC123abc@

insert into elite_user
values (nextval('elite_user_seq'), 'himanshusingh2041', 'himanshusingh2041@gmail.com',
        '$2a$10$qGAuDmHwKuG80n1fcBhwU.mNY.ZC93Jie/Ay5Ncrphdtr9V16xEPC', 'Himanshu', 'kumar', 'ADMIN', 'ADMIN',
        current_timestamp, current_timestamp);

insert into elite_role
values (nextval('elite_role_seq'), 'ADMIN', 'Admin', 'ADMIN', 'ADMIN', current_timestamp, current_timestamp);
insert into elite_role
values (nextval('elite_role_seq'), 'USER', 'User', 'ADMIN', 'ADMIN', current_timestamp, current_timestamp);

insert into elite_user_role
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'ADMIN'));

insert into elite_user_role
values ((select user_id from elite_user where user_name = 'himanshusingh2041'),
        (select role_id from elite_role where role_name = 'USER'));