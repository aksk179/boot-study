insert into member (id, name, passwd, new_passwd, cell_phone, email, zip_code, address, status, uuid, role ) values ( 'admin', 'admin', '$2a$10$2XklmD425IEg8oVClW/JdelNbs8k5l0YkQTpIJB1XX49Goh9HXQ6e', 'admin', '010-1111-1111', 'admin@test.com', '111-111', '서울시 강남구', 'Y', '', 'ADMIN');
insert into member (id, name, passwd, new_passwd, cell_phone, email, zip_code, address, status, uuid, role ) values ( 'test1', 'test1', '$2a$10$YKrdsN3hzme0t/E648yZ2eLF7Y207xlledRneNtFQQbdfWzOH6ORu', 'test1', '010-2222-2222', 'test1@test.com', '111-222', '서울시 관악구', 'Y', '', 'USER');
insert into member (id, name, passwd, new_passwd, cell_phone, email, zip_code, address, status, uuid, role ) values ( 'test2', 'test2', '$2a$10$MP.WMXhxgweYcFkGkHEttusaRJ6kp1NVgzoXP860iayCGrY/1Zr7W', 'test2', '010-3333-3333', 'test2@test.com', '111-333', '서울시 서초구', 'Y', '', 'USER');
insert into member (id, name, passwd, new_passwd, cell_phone, email, zip_code, address, status, uuid, role ) values ( 'test3', 'test3', '$2a$10$v1Co7ncJmswlWnTe/2Z9Y.dqNV/2xtNIFis1DWJnjauvcELoam7qe', 'test3', '010-4444-4444', 'test3@test.com', '111-444', '서울시 광진구', 'Y', '', 'USER');

insert into role (role_id, role_name) values ('ADMIN',   'Admin');
insert into role (role_id, role_name) values ('MANAGER', 'Manager');
insert into role (role_id, role_name) values ('USER',    'User');

insert into menu (menu_id, menu_name) values ('ADMIN',   'Admin 메뉴');
insert into menu (menu_id, menu_name) values ('MANAGER', '관리자 메뉴');
insert into menu (menu_id, menu_name) values ('USER',    '사용자 메뉴');

-- ADMIN
insert into menu_detail ( menu_id, menu_url, menu_detail_name ) values ('ADMIN', '/admin/member_list.page', '회원관리');
insert into menu_detail ( menu_id, menu_url, menu_detail_name ) values ('ADMIN', '/admin/bbs_master_list.page', '게시판관리');
insert into menu_detail ( menu_id, menu_url, menu_detail_name ) values ('ADMIN', '/menu/menu_list.page', '메뉴관리');

-- USER
insert into menu_detail ( menu_id, menu_url, menu_detail_name ) values ('USER', '/bbs/bbs_main.page/BBS001', '공지사항');
insert into menu_detail ( menu_id, menu_url, menu_detail_name ) values ('USER', '/bbs/bbs_main.page/BBS002', '자유게시판');

insert into role_menu_rel values (1,1);
insert into role_menu_rel values (1,2);
insert into role_menu_rel values (2,1);
insert into role_menu_rel values (2,2);
insert into role_menu_rel values (3,3);

insert into bbs_master (bbs_id, bbs_name, bbs_reply_yn, bbs_file_yn, bbs_file_size, bbs_new_hour, bbs_writer_view_yn, bbs_user_write_yn) values
('BBS001', '공지사항', 'N', 'N', 50, 48, 'Y', 'N' );
insert into bbs_master (bbs_id, bbs_name, bbs_reply_yn, bbs_file_yn, bbs_file_size, bbs_new_hour, bbs_writer_view_yn, bbs_user_write_yn) values
('BBS002', '자유게시판', 'Y', 'Y', 50, 48, 'Y', 'Y' );
