insert into elite_code_type
(code_type_id, code_type_code, code_type_name, code_type_description,
 code_type_created_by, code_type_updated_by, code_type_created_date, code_type_updated_date)
values (nextval('elite_code_type_seq'), 'GENDER', 'Gender', 'Gender',
        'ADMIN', 'ADMIN', current_timestamp, current_timestamp);