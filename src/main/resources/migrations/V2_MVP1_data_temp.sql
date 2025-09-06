-- Company
insert into tbl_company (company_code, name_th, name_en, tax_id, is_active, created_by)
values ('ACME', 'แอคมี จำกัด', 'ACME Co., Ltd.', '0105559999999', 'Y', 1);

-- Branch
insert into tbl_branch (company_code, branch_code, name_th, name_en, lat, lng, geofence_radius_m, is_active, created_by)
values
('ACME','BKK01','สาขากรุงเทพ-สีลม','Bangkok Silom',13.7279,100.5210,50,'Y',1),
('ACME','CNX01','สาขาเชียงใหม่-นิมมาน','Chiang Mai Nimman',18.7961,98.9793,50,'Y',1);

-- Settings
insert into tbl_company_setting (company_code, key_code, value_text) values
('ACME','AUTO_APPROVE_OT_HOURS','24'),
('ACME','ALLOW_CLOCKIN_IF_BRANCH_CLOSED','N');

insert into tbl_branch_setting (company_code, branch_code, key_code, value_text) values
('ACME','BKK01','CHECKIN_GEOFENCE_REQUIRED','Y'),
('ACME','CNX01','CHECKIN_GEOFENCE_REQUIRED','N');


insert into tbl_param_group (company_code, group_code, name_th, name_en, is_active) values
('GLOBAL','employment_type','ประเภทพนักงาน','Employment Type','Y');

insert into tbl_param_value (company_code, group_code, param_code, name_th, name_en, param_value, sort_order) values
('GLOBAL','employment_type','fulltime','พนักงานประจำ','Full Time','fulltime',10),
('GLOBAL','employment_type','parttime','พนักงานพาร์ตไทม์','Part Time','parttime',20);

insert into tbl_param_group (company_code, group_code, name_th, name_en, is_active) values
('GLOBAL','rate_type','ประเภทเรต','Rate Type','Y');

insert into tbl_param_value (company_code, group_code, param_code, name_th, name_en, param_value, sort_order) values
('GLOBAL','rate_type','ot_1_5x','OT 1.5 เท่า','OT 1.5x','1.5',10),
('GLOBAL','rate_type','ot_2_0x','OT 2.0 เท่า','OT 2.0x','2.0',20);


insert into tbl_param_group (company_code, group_code, name_th, name_en, is_active) values
('GLOBAL','holiday_type','ประเภทวันหยุด','Holiday Type','Y');

insert into tbl_param_value (company_code, group_code, param_code, name_th, name_en, param_value, sort_order) values
('GLOBAL','holiday_type','government_public','วันหยุดราชการ','Government Public Holiday','government_public',10),
('GLOBAL','holiday_type','company_special','วันหยุดประจำร้าน','Company Special Holiday','company_special',20),
('GLOBAL','holiday_type','celebration','วันหยุดฉลอง','Celebration Holiday','celebration',30);


insert into tbl_param_group (company_code, group_code, name_th, name_en, is_active)
values ('GLOBAL','province','จังหวัด','Province','Y');

insert into tbl_param_value (company_code, group_code, param_code, name_th, name_en, param_value, sort_order) values
-- ภาคกลาง/กรุงเทพฯ
('GLOBAL','province','bangkok','กรุงเทพมหานคร','Bangkok','BANGKOK',10),
('GLOBAL','province','samut_prakan','สมุทรปราการ','Samut Prakan','SAMUT_PRAKAN',11),
('GLOBAL','province','nonthaburi','นนทบุรี','Nonthaburi','NONTHABURI',12),
('GLOBAL','province','pathum_thani','ปทุมธานี','Pathum Thani','PATHUM_THANI',13),
('GLOBAL','province','phra_nakhon_si_ayutthaya','พระนครศรีอยุธยา','Phra Nakhon Si Ayutthaya','AYUTTHAYA',14),
('GLOBAL','province','ang_thong','อ่างทอง','Ang Thong','ANG_THONG',15),
('GLOBAL','province','lop_buri','ลพบุรี','Lop Buri','LOP_BURI',16),
('GLOBAL','province','sing_buri','สิงห์บุรี','Sing Buri','SING_BURI',17),
('GLOBAL','province','chai_nat','ชัยนาท','Chai Nat','CHAI_NAT',18),
('GLOBAL','province','saraphi_nakhon_pathom','นครปฐม','Nakhon Pathom','NAKHON_PATHOM',19),
('GLOBAL','province','samut_sakhon','สมุทรสาคร','Samut Sakhon','SAMUT_SAKHON',20),
('GLOBAL','province','samut_songkhram','สมุทรสงคราม','Samut Songkhram','SAMUT_SONGKHRAM',21),
('GLOBAL','province','suphan_buri','สุพรรณบุรี','Suphan Buri','SUPHAN_BURI',22),
('GLOBAL','province','nakhon_nayok','นครนายก','Nakhon Nayok','NAKHON_NAYOK',23),
('GLOBAL','province','prachin_buri','ปราจีนบุรี','Prachin Buri','PRACHIN_BURI',24),
('GLOBAL','province','sa_kaeo','สระแก้ว','Sa Kaeo','SA_KAEO',25),
-- ภาคตะวันออก
('GLOBAL','province','chachoengsao','ฉะเชิงเทรา','Chachoengsao','CHACHOENGSAO',26),
('GLOBAL','province','chon_buri','ชลบุรี','Chon Buri','CHON_BURI',27),
('GLOBAL','province','rayong','ระยอง','Rayong','RAYONG',28),
('GLOBAL','province','chanthaburi','จันทบุรี','Chanthaburi','CHANTHABURI',29),
('GLOBAL','province','trat','ตราด','Trat','TRAT',30),
-- ภาคเหนือ
('GLOBAL','province','chiang_mai','เชียงใหม่','Chiang Mai','CHIANG_MAI',31),
('GLOBAL','province','chiang_rai','เชียงราย','Chiang Rai','CHIANG_RAI',32),
('GLOBAL','province','lamphun','ลำพูน','Lamphun','LAMPHUN',33),
('GLOBAL','province','lampang','ลำปาง','Lampang','LAMPANG',34),
('GLOBAL','province','uttaradit','อุตรดิตถ์','Uttaradit','UTTARADIT',35),
('GLOBAL','province','phrae','แพร่','Phrae','PHRAE',36),
('GLOBAL','province','nan','น่าน','Nan','NAN',37),
('GLOBAL','province','phayao','พะเยา','Phayao','PHAYAO',38),
('GLOBAL','province','mae_hong_son','แม่ฮ่องสอน','Mae Hong Son','MAE_HONG_SON',39),
-- ภาคตะวันออกเฉียงเหนือ
('GLOBAL','province','nakhon_ratchasima','นครราชสีมา','Nakhon Ratchasima','NAKHON_RATCHASIMA',40),
('GLOBAL','province','buri_ram','บุรีรัมย์','Buri Ram','BURI_RAM',41),
('GLOBAL','province','surin','สุรินทร์','Surin','SURIN',42),
('GLOBAL','province','si_sa_ket','ศรีสะเกษ','Si Sa Ket','SI_SA_KET',43),
('GLOBAL','province','ubon_ratchathani','อุบลราชธานี','Ubon Ratchathani','UBON_RATCHATHANI',44),
('GLOBAL','province','yasothon','ยโสธร','Yasothon','YASOTHON',45),
('GLOBAL','province','chaiyaphum','ชัยภูมิ','Chaiyaphum','CHAIYAPHUM',46),
('GLOBAL','province','amnat_charoen','อำนาจเจริญ','Amnat Charoen','AMNAT_CHAROEN',47),
('GLOBAL','province','bueng_kan','บึงกาฬ','Bueng Kan','BUENG_KAN',48),
('GLOBAL','province','nakhon_phanom','นครพนม','Nakhon Phanom','NAKHON_PHANOM',49),
('GLOBAL','province','sakonnakhon','สกลนคร','Sakon Nakhon','SAKON_NAKHON',50),
('GLOBAL','province','khon_kaen','ขอนแก่น','Khon Kaen','KHON_KAEN',51),
('GLOBAL','province','udon_thani','อุดรธานี','Udon Thani','UDON_THANI',52),
('GLOBAL','province','loei','เลย','Loei','LOEI',53),
('GLOBAL','province','nong_bua_lamphu','หนองบัวลำภู','Nong Bua Lam Phu','NONG_BUA_LAMPHU',54),
('GLOBAL','province','nong_khai','หนองคาย','Nong Khai','NONG_KHAI',55),
('GLOBAL','province','mukdahan','มุกดาหาร','Mukdahan','MUKDAHAN',56),
('GLOBAL','province','kalasin','กาฬสินธุ์','Kalasin','KALASIN',57),
-- ภาคตะวันตก
('GLOBAL','province','kanchanaburi','กาญจนบุรี','Kanchanaburi','KANCHANABURI',58),
('GLOBAL','province','tak','ตาก','Tak','TAK',59),
('GLOBAL','province','ratchaburi','ราชบุรี','Ratchaburi','RATCHABURI',60),
('GLOBAL','province','phetchaburi','เพชรบุรี','Phetchaburi','PHETCHABURI',61),
('GLOBAL','province','prachuap_khiri_khan','ประจวบคีรีขันธ์','Prachuap Khiri Khan','PRACHUAP_KHIRI_KHAN',62),
-- ภาคใต้
('GLOBAL','province','chumphon','ชุมพร','Chumphon','CHUMPHON',63),
('GLOBAL','province','ranong','ระนอง','Ranong','RANONG',64),
('GLOBAL','province','surat_thani','สุราษฎร์ธานี','Surat Thani','SURAT_THANI',65),
('GLOBAL','province','phang_nga','พังงา','Phang Nga','PHANG_NGA',66),
('GLOBAL','province','phuket','ภูเก็ต','Phuket','PHUKET',67),
('GLOBAL','province','krabi','กระบี่','Krabi','KRABI',68),
('GLOBAL','province','nakhon_si_thammarat','นครศรีธรรมราช','Nakhon Si Thammarat','NAKHON_SI_THAMMARAT',69),
('GLOBAL','province','trang','ตรัง','Trang','TRANG',70),
('GLOBAL','province','phatthalung','พัทลุง','Phatthalung','PHATTHALUNG',71),
('GLOBAL','province','songkhla','สงขลา','Songkhla','SONGKHLA',72),
('GLOBAL','province','satun','สตูล','Satun','SATUN',73),
('GLOBAL','province','pattani','ปัตตานี','Pattani','PATTANI',74),
('GLOBAL','province','yala','ยะลา','Yala','Yala',75),
('GLOBAL','province','narathiwat','นราธิวาส','Narathiwat','NARATHIWAT',76);
-- รวม 77 จังหวัด (กรุงเทพฯ + 76 จังหวัด)


insert into tbl_role (company_code, role_code, name_th, name_en, level_rank, is_active) values
('GLOBAL','owner_system','เจ้าของระบบ','Owner System', 999,'Y'),
('ACME','owner_company','เจ้าของบริษัท','Owner Company', 900,'Y'),
('ACME','director','ผู้อำนวยการ','Director', 800,'Y'),
('ACME','manager_branch','ผู้จัดการสาขา','Branch Manager', 500,'Y'),
('ACME','admin','ผู้ดูแลระบบ','Admin', 700,'Y');



-- สร้าง permission สำหรับทุก OBJECT x ACTION
with objs(object_code) as (values
 ('API_COMPANY'),('API_BRANCH'),('API_COMPANY_SETTING'),('API_BRANCH_SETTING'),
 ('API_PARAM_GROUP'),('API_PARAM_VALUE'),
 ('API_HOLIDAY_COMPANY'),('API_HOLIDAY_BRANCH'),('API_RATE_MULTIPLIER'),
 ('API_PERMISSION'),('API_ROLE'),('API_ROLE_PERMISSION'),
 ('API_EMPLOYEE'),('API_EMPLOYEE_ROLE_HISTORY'),('API_EMPLOYEE_COMP_HISTORY'),('API_DEVICE'),
 ('API_FACE'),('API_REGISTER')
),
acts(action_code) as (values ('CREATE'),('READ'),('UPDATE'),('DELETE'),('LIST'))
insert into tbl_permission (object_code, action_code, description)
select o.object_code, a.action_code, o.object_code || ' ' || a.action_code
from objs o cross join acts a;



-- map ทุก role → ทุก permission ที่ action_code='CREATE'
insert into tbl_role_permission (role_id, permission_id, allowed, created_at, is_deleted)
select r.id, p.id, 'Y', now(), 'N'
from tbl_role r
join tbl_permission p on p.action_code='CREATE';

delete from tbl_role_permission where  role_id=(select tbl_role.id from tbl_role where role_code='owner_system');

insert into tbl_role_permission (role_id, permission_id, allowed, created_at, is_deleted)
select r.id, p.id, 'Y', now(), 'N'
from tbl_role r
join tbl_permission p on 1=1
where r.role_code='owner_system';

