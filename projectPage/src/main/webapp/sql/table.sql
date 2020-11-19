---------- MEMBER ----------

create table promember(
    m_num number PRIMARY KEY,
    m_id varchar2(20) not null,
    m_nickname varchar2(30),
    m_auth number default 2 not null
);

create table promember_detail(
    m_num number primary key,
    m_name varchar2(30) not null,
    m_passwd varchar2(40) not null,
    m_image BLOB,
    m_address varchar2(100),
    m_phone varchar2(15),
    m_regdate date default sysdate not null,
    constraint promember_detail_fk foreign key (m_num) references promember (m_num)
);

create sequence promember_seq;

create table profollow(
    f_num number primary key,
    f_activeuser number not null,
    f_passiveuser number not null,
    constraint profollow_fk foreign key (f_activeuser) references promember (m_num)
);

create sequence profollow_seq;

---------- MAINBOARD ----------
create table promainboard(
    mb_num number primary key,
    mb_title varchar2(100) not null,
    m_num number not null,
    mb_photo BLOB,
    mb_filename varchar2(300),
    mb_content CLOB,
    mb_regdate date default sysdate not null,
    mb_modifydate date default sysdate not null,
    constraint promainboard_fk foreign key (m_num) references promember (m_num)
);

create table promaininfo(
    mb_num number primary key,
    mb_topinfo varchar2(100),
    mb_bottominfo varchar2(100),
    mb_hatinfo varchar2(100),
    mb_shoseinfo varchar2(100),
    constraint promaininfo_fk foreign key (mb_num) references promainboard (mb_num)
);

create sequence promainboard_seq;

create table promainreple(
    mb_num number not null,
    m_num number not null,
    mr_content varchar2(200) not null,
    mr_regdate date default sysdate not null,
    constraint promainreple_fk1 foreign key (m_num) references promember (m_num),
    constraint promainreple_fk2 foreign key (mb_num) references promainboard (mb_num)
);

create table prolikes(
    mb_num number not null,
    m_num number not null,
    mb_likecheck number default 0 not null,
    constraint prolikes_fk1 foreign key (m_num) references promember (m_num),
    constraint prolikes_fk2 foreign key (mb_num) references promainboard (mb_num)
);

---------- QNA ----------

create table proqnaboard(
   qb_num number primary key,
   qb_title varchar2(100) not null,
   qb_usernum number not null,
   qb_photo BLOB,
   qb_content CLOB,
   qb_regdate DATE default sysdate,
   qb_modifydate DATE default sysdate,
   constraint proqna_fk foreign key (qb_usernum) references promember (m_num)
);

create table proqnainfo(
   qb_num number primary key,
   qb_topinfo number default 0 not null,
   qb_bottominfo number default 0 not null,
   qb_hatinfo number default 0 not null,
   qb_shoseinfo number default 0 not null,
   constraint proqnainfo_fk foreign key (qb_num) references proqnaboard (qb_num)
);

create sequence proqna_seq;

create table proqnareple(
   qb_num number not null primary key,
   m_num number not null,
   qr_content varchar2(150) not null,
   qr_regdate DATE default sysdate not null,
   constraint proqnareple_fk1 foreign key (qb_num) references proqnaboard (qb_num),
   constraint proqnareple_fk2 foreign key (m_num) references promember (m_num)
);

---------- NOTICE ----------

create table pronotice(
    nb_num number primary key,
    m_num not null,
    nb_title varchar2(100) not null,
    nb_content CLOB not null,
    nb_regdate date default sysdate not null,
    constraint pronotice_fk1 foreign key (m_num) references promember (m_num)
);
drop table pronotice;

---------- FLEA ----------

create table profleaboard(
    fb_num number primary key,
    fb_title varchar2(100) not null,
    fb_usernum number not null,
    fb_photo BLOB,
    fb_price number,
    fb_content CLOB,
    fb_regdate date default sysdate not null,
    fb_modifydate date default sysdate not null,
    constraint proflea_fk foreign key (fb_usernum) references promember (m_num)
);

create table profleainfo(
    fb_num number primary key,
    fb_topcheck number default 0 not null,
    fb_bottomcheck number default 0 not null,
    fb_hatcheck number default 0 not null,
    fb_shoescheck number default 0 not null,
    constraint profleainfo_fk foreign key (fb_num) references profleaboard (fb_num)
);

create sequence proflea_seq;

create table profleareple(
    fb_num number not null,
    m_num number not null,
    fr_content varchar2(150) not null,
    fr_regdate date default sysdate not null,
    constraint profleareple_fk1 foreign key (m_num) references promember (m_num),
    constraint profleareple_fk2 foreign key (fb_num) references profleaboard (fb_num)
);

create table profleaimages(
    i_num number primary key,
    m_num number not null,
    fb_num number not null,
    i_photo BLOB,
    i_filename varchar2(200),
    constraint profleaimages_fk1 foreign key (m_num) references promember (m_num)
);

create sequence profleaimages_seq;

---------- STORE ----------

create table prostore (
   s_num number primary key,
   s_title varchar2(100) not null,
   s_usernum number not null,
   s_photo BLOB not null,
   s_price number not null,
   s_content CLOB,
   s_regdate date default sysdate not null,
   constraint prostore_fk foreign key (s_usernum) references promember_detail (m_num)
);

create sequence prostore_seq;

create table prostorereview (
   sr_num number primary key,
   m_num number not null,
   sr_content CLOB not null,
   sr_regdate date default sysdate,
   constraint prostorereview_fk foreign key (m_num) references promember_detail (m_num)
);

create sequence prostorereview_seq;

create table prostorebasket(
    m_num number not null,
    s_num number not null,
    constraint prostorebasket_fk1 foreign key (m_num) references promember (m_num),
    constraint prostorebasket_fk2 foreign key (s_num) references prostore (s_num)
);

-- 댓글 테이블
CREATE TABLE PROCOMMENT 
(
  COMMENT_NUM NUMBER NOT NULL,
  MB_NUM NUMBER NOT NULL,
  COMMENT_ID VARCHAR2(15),
  COMMENT_DATE DATE,
  COMMENT_CONTENT VARCHAR2(1000) NOT NULL,
  CONSTRAINT PK_comment PRIMARY KEY(COMMENT_NUM),
  CONSTRAINT FK_comment FOREIGN KEY(MB_NUM) REFERENCES promainboard (mb_num)
);
 
-- MEMBER_BOARD는 게시판 테이블을, BOARD_NUM은 글번호를 나타낸다.
 
-- 댓글 시퀀스 
create sequence COMMENT_SEQ;

-- 이미지
create table promainimages(
    i_num number primary key,
    m_num not null,
    mb_num not null,
    i_photo BLOB,
    i_filename varchar2(200),
    constraint promainimages_fk1 foreign key (m_num) references promember (m_num),
    constraint promainimages_fk2 foreign key (mb_num) references promainboard (mb_num)
);

COMMIT;