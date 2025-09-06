create table tbl_company (
  id                 serial primary key,
  company_code       varchar(64) not null,
  name_th            varchar(255) not null,
  name_en            varchar(255) not null,
  tax_id             varchar(64) null,
  address_line1      varchar(255) null,
  address_line2      varchar(255) null,
  province_code      varchar(64) null,
  postal_code        varchar(16) null,
  phone              varchar(64) null,
  email              varchar(128) null,
  is_active          varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, is_deleted)
);

create table tbl_branch (
  id                   serial primary key,
  company_code         varchar(64) not null,
  branch_code          varchar(64) not null,
  name_th              varchar(255) not null,
  name_en              varchar(255) not null,
  lat                  numeric(9,6) null,
  lng                  numeric(9,6) null,
  geofence_radius_m    numeric(8,2) null,
  onsite_required_pct  numeric(5,2) null,
  require_branch_checkin_count int null,
  is_active            varchar(1) not null default 'Y',
  -- audit
  is_deleted           varchar(1) not null default 'N',
  created_at           timestamptz not null default now(),
  created_by           int null,
  updated_at           timestamptz not null default now(),
  updated_by           int null,
  unique (company_code, branch_code, is_deleted)
);

-- Company-level KV settings (single text value)
create table tbl_company_setting (
  id                 serial primary key,
  company_code       varchar(64) not null,
  key_code           varchar(128) not null,  -- e.g. 'SST_RATE','AUTO_APPROVE_OT_HOURS'
  value_text         varchar(512) null,
  is_active          varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, key_code, is_deleted)
);

-- Branch-level overrides
create table tbl_branch_setting (
  id                 serial primary key,
  company_code       varchar(64) not null,
  branch_code        varchar(64) not null,
  key_code           varchar(128) not null,
  value_text         varchar(512) null,
  is_active          varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, branch_code, key_code, is_deleted)
);


create table tbl_param_group (
  id               serial primary key,
  company_code     varchar(64) not null default 'GLOBAL',
  group_code       varchar(64) not null,      -- e.g., 'rate_type','holiday_type','employment_type','province','position'
  name_th          varchar(255) not null,
  name_en          varchar(255) not null,
  description      varchar(1000) null,
  is_active        varchar(1) not null default 'Y',
  -- audit
  is_deleted       varchar(1) not null default 'N',
  created_at       timestamptz not null default now(),
  created_by       int null,
  updated_at       timestamptz not null default now(),
  updated_by       int null,
  unique (company_code, group_code, is_deleted)
);

create table tbl_param_value (
  id               serial primary key,
  company_code     varchar(64) not null default 'GLOBAL',
  group_code       varchar(64) not null,
  param_code       varchar(64) not null,      -- e.g., 'rate_001'
  name_th          varchar(255) not null,
  name_en          varchar(255) not null,
  param_value      varchar(255) null,         -- single string value if needed
  sort_order       int not null default 100,
  is_active        varchar(1) not null default 'Y',
  -- audit
  is_deleted       varchar(1) not null default 'N',
  created_at       timestamptz not null default now(),
  created_by       int null,
  updated_at       timestamptz not null default now(),
  updated_by       int null,
  unique (company_code, group_code, param_code, is_deleted)
);


create table tbl_holiday_company (
  id                 serial primary key,
  company_code       varchar(64) not null,
  holiday_date       date not null,
  holiday_type_code  varchar(64) not null,
  rate_type_code     varchar(64) null,
  multiplier         numeric(12,4) null,
  name_th            varchar(255) null,
  name_en            varchar(255) null,
  is_paid            varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, holiday_date, is_deleted)
);

create table tbl_holiday_branch (
  id                 serial primary key,
  company_code       varchar(64) not null,
  branch_code        varchar(64) not null,
  holiday_date       date not null,
  holiday_type_code  varchar(64) not null,
  rate_type_code     varchar(64) null,
  multiplier         numeric(12,4) null,
  name_th            varchar(255) null,
  name_en            varchar(255) null,
  is_paid            varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, branch_code, holiday_date, is_deleted)
);

create table tbl_rate_multiplier_calendar (
  id                 serial primary key,
  company_code       varchar(64) not null,
  branch_code        varchar(64) null,
  rate_date          date not null,
  rate_type_code     varchar(64) not null,
  multiplier         numeric(12,4) not null,
  reason             varchar(255) null,
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (branch_code, company_code, rate_date, rate_type_code, is_deleted)
);


create table tbl_permission (
  id                 serial primary key,
  object_code        varchar(128) not null,
  action_code        varchar(64) not null,
  description        varchar(255) null,
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (object_code, action_code, is_deleted)
);

create table tbl_role (
  id                 serial primary key,
  company_code       varchar(64) not null,
  role_code          varchar(64) not null,
  name_th            varchar(255) not null,
  name_en            varchar(255) not null,
  level_rank         int not null,
  is_active          varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, role_code, is_deleted)
);

create table tbl_role_permission (
  id                 serial primary key,
  role_id            int not null,
  permission_id      int not null,
  allowed            varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (role_id, permission_id, is_deleted)
);


create table tbl_employee (
  id                   serial primary key,
  company_code         varchar(64) not null,
  default_branch_code  varchar(64) null,
  employee_code        varchar(64) not null,
  first_name_th        varchar(128) not null,
  last_name_th         varchar(128) not null,
  first_name_en        varchar(128) null,
  last_name_en         varchar(128) null,
  email                varchar(128) null,
  phone                varchar(64) null,
  employment_type_code varchar(64) not null,   -- e.g., fulltime/parttime
  username             varchar(128) not null,
  password_hash        varchar(255) not null,
  profile_photo_key    varchar(255) null,
  face_template_key    varchar(255) null,
  signature_image_key  varchar(255) null,
  base_salary          numeric(12,2) null,
  base_hourly_rate     numeric(12,2) null,
  currency_code        varchar(3) not null default 'THB',
  hire_date            date not null,
  end_date             date null,
  is_active            varchar(1) not null default 'Y',
  -- audit
  is_deleted           varchar(1) not null default 'N',
  created_at           timestamptz not null default now(),
  created_by           int null,
  updated_at           timestamptz not null default now(),
  updated_by           int null,
  unique (company_code, employee_code, is_deleted),
  unique (company_code, username, is_deleted)
);

create table tbl_employee_branch_assignment (
  id                 serial primary key,
  company_code       varchar(64) not null,
  employee_id        int not null,
  branch_code        varchar(64) null,
  start_date         date not null,
  end_date           date null,
  note               varchar(255) null,
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null
);

create table tbl_employee_role_history (
  id                 serial primary key,
  company_code       varchar(64) not null,
  employee_id        int not null,
  role_id            int not null,
  start_date         date not null,
  end_date           date null,
  approved_by        int null,
  approved_at        timestamptz null,
  note               varchar(500) null,
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null
);

create table tbl_employee_compensation_history (
  id                 serial primary key,
  company_code       varchar(64) not null,
  employee_id        int not null,
  effective_date     date not null,
  base_salary        numeric(12,2) null,
  base_hourly_rate   numeric(12,2) null,
  currency_code      varchar(3) not null default 'THB',
  change_reason      varchar(255) null,
  request_ref_type   varchar(64) null,
  request_ref_id     int null,
  approved_by        int null,
  approved_at        timestamptz null,
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null
);

create table tbl_device (
  id                 serial primary key,
  company_code       varchar(64) not null,
  branch_code        varchar(64) null,
  device_code        varchar(64) not null,
  name               varchar(255) not null,
  type_code          varchar(64) not null,  -- kiosk, mobile
  ip_address         varchar(64) null,
  last_seen_at       timestamptz null,
  is_active          varchar(1) not null default 'Y',
  -- audit
  is_deleted         varchar(1) not null default 'N',
  created_at         timestamptz not null default now(),
  created_by         int null,
  updated_at         timestamptz not null default now(),
  updated_by         int null,
  unique (company_code, device_code, is_deleted)
);

drop table if EXISTS tbl_audit;
CREATE TABLE tbl_audit (
                           id SERIAL PRIMARY KEY,
                           table_name VARCHAR(255),
                           primary_key VARCHAR(255),
                           update_date TIMESTAMP,
                           update_by VARCHAR(255),
                            status VARCHAR(40),
                           detail TEXT
);
