CREATE TABLE IF NOT EXISTS member
(
    id         VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    passwd     VARCHAR(255) NOT NULL,
    new_passwd VARCHAR(255) NULL,
    cell_phone VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    zip_code   VARCHAR(255) NULL,
    address    VARCHAR(255) NULL,
    status     VARCHAR(255) NULL,
    uuid       VARCHAR(255) NULL,
    role       VARCHAR(255) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role
(
    id            INTEGER NOT NULL AUTO_INCREMENT,
    role_id       VARCHAR(255) NOT NULL,
    role_name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS menu
(
    id            INTEGER NOT NULL AUTO_INCREMENT,
    menu_id       VARCHAR(255) NOT NULL,
    menu_name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS menu_detail
(
    id               INTEGER NOT NULL AUTO_INCREMENT,
    menu_id          VARCHAR(255) NOT NULL,
    menu_url         VARCHAR(255) NOT NULL,
    menu_detail_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS role_menu_rel
(
    id_role      INTEGER,
    id_menu      INTEGER
);


CREATE TABLE IF NOT EXISTS bbs_master
(
    bbs_id              VARCHAR(10) NOT NULL,
    bbs_name            VARCHAR(255) NOT NULL,
    bbs_reply_yn        VARCHAR(255) NULL,
    bbs_file_yn         VARCHAR(255) NULL,
    bbs_file_size       INTEGER,
    bbs_new_hour        INTEGER,
    bbs_writer_view_yn  VARCHAR(1) NULL,
    bbs_user_write_yn   VARCHAR(1) NULL,
    PRIMARY KEY (bbs_id)
);

CREATE TABLE IF NOT EXISTS bbs_main
(
    bbs_id              VARCHAR(10) NOT NULL,
    bbs_no              INTEGER NOT NULL,
    bbs_title           VARCHAR(255) NULL,
    bbs_contents        VARCHAR(255) NULL,
    bbs_wtime           TIMESTAMP NULL,
    bbs_writer_name     VARCHAR(255) NULL,
    bbs_view_count      INTEGER NULL,
    bbs_writer_id       VARCHAR(255) NULL,
    PRIMARY KEY (bbs_id, bbs_no)
);

CREATE TABLE IF NOT EXISTS bbs_comment
(
    bbs_id               VARCHAR(10) NOT NULL,
    bbs_no               INTEGER NOT NULL,
    bbs_cmt_seq          INTEGER NULL,
    bbs_cmt_writer       VARCHAR(255) NOT NULL,
    bbs_cmt_wtime        VARCHAR(255) NOT NULL,
    bbs_comment          VARCHAR(255) NOT NULL,
    bbs_cmt_upseq        INTEGER NULL,
    PRIMARY KEY (bbs_id, bbs_no, bbs_cmt_seq)
);
