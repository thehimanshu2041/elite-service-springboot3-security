insert into elite_code
(code_id, code_code, code_name, code_description,
 code_created_by, code_updated_by, code_created_date, code_updated_date,
 code_code_type_id)
values (nextval('elite_code_seq'), 'MALE', 'Male', 'Male',
        'ADMIN', 'ADMIN', current_timestamp, current_timestamp,
        (select code_type_id from elite_code_type where code_type_code = 'GENDER')),
       (nextval('elite_code_seq'), 'FEMALE', 'Female', 'Female',
        'ADMIN', 'ADMIN', current_timestamp, current_timestamp,
        (select code_type_id from elite_code_type where code_type_code = 'GENDER')),
       (nextval('elite_code_seq'), 'OTHER', 'Other', 'Other',
        'ADMIN', 'ADMIN', current_timestamp, current_timestamp,
        (select code_type_id from elite_code_type where code_type_code = 'GENDER'));