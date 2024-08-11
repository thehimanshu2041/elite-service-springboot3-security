insert into elite_code
(code_code, code_name, code_description, code_code_type_id, code_created_by, code_updated_by)
values ('MALE', 'Male', 'Male',
        (select code_type_id
         from elite_code_type
         where code_type_code = 'GENDER'),
            'DEFAULT', 'DEFAULT'),
       ('FEMALE', 'Female', 'Female',
        (select code_type_id
         from elite_code_type
         where code_type_code = 'GENDER'),
            'DEFAULT', 'DEFAULT'),
       ('OTHER', 'Other', 'Other',
        (select code_type_id
         from elite_code_type
         where code_type_code = 'GENDER'),
            'DEFAULT', 'DEFAULT');