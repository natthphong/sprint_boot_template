alter table public.tbl_audit
    alter column update_by type int using update_by::int;

