alter table public.tbl_employee
    add status varchar(50) default 'GUEST';

alter table public.tbl_employee
    add face_verify_at timestamptz;

alter table public.tbl_employee
    drop column employee_code;


alter table public.tbl_employee
    alter column currency_code drop not null;

alter table public.tbl_employee
    alter column hire_date drop not null;

alter table public.tbl_employee
    alter column is_active drop not null;

alter table public.tbl_employee
    alter column is_deleted drop not null;

alter table public.tbl_employee
    alter column updated_at drop not null;



