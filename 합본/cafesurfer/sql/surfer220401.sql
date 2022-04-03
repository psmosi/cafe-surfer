-- 테이블 삭제
DROP TABLE bookmark CASCADE CONSTRAINTS;
DROP TABLE coffeeshop CASCADE CONSTRAINTS;
DROP TABLE hashtag_classification CASCADE CONSTRAINTS;
DROP TABLE membership CASCADE CONSTRAINTS;
DROP TABLE mycoffeeshop CASCADE CONSTRAINTS;
DROP TABLE photo CASCADE CONSTRAINTS;
DROP TABLE report CASCADE CONSTRAINTS;
DROP TABLE report_type CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE shop_hashtag CASCADE CONSTRAINTS;

-- 시퀀스 삭제
DROP SEQUENCE membership_member_id_seq;
DROP SEQUENCE coffeeshop_shop_id_seq;
DROP SEQUENCE review_review_id_seq;
DROP SEQUENCE hashtag_menu_id_seq;
DROP SEQUENCE hashtag_loc_id_seq;
DROP SEQUENCE hashtag_mood_id_seq;
DROP SEQUENCE hashtag_view_id_seq;

-- 시퀀스 생성
create sequence membership_member_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;

 create sequence coffeeshop_shop_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;

create sequence review_review_id_seq
    start with 1
    increment by 1
    minvalue 0
    maxvalue 99999999
    nocycle
    nocache;
drop sequence hashtag_cmenu_id_seq;
 create sequence hashtag_cmenu_id_seq
    start with 1001
    increment by 1
    minvalue 1000
    maxvalue 1200
    nocycle
    nocache;
drop sequence hashtag_vmenu_id_seq;
  create sequence hashtag_vmenu_id_seq
    start with 1201
    increment by 1
    minvalue 1200
    maxvalue 1400
    nocycle
    nocache;
drop sequence hashtag_tmenu_id_seq;
  create sequence hashtag_tmenu_id_seq
    start with 1401
    increment by 1
    minvalue 1400
    maxvalue 1600
    nocycle
    nocache;
drop sequence hashtag_dmenu_id_seq;
 create sequence hashtag_dmenu_id_seq
    start with 1601
    increment by 1
    minvalue 1600
    maxvalue 1800
    nocycle
    nocache;
drop sequence hashtag_gmenu_id_seq;
  create sequence hashtag_gmenu_id_seq
    start with 1801
    increment by 1
    minvalue 1800
    maxvalue 2000
    nocycle
    nocache;

 create sequence hashtag_loc_id_seq
    start with 2001
    increment by 1
    minvalue 2000
    maxvalue 3000
    nocycle
    nocache;

 create sequence hashtag_mood_id_seq
    start with 3001
    increment by 1
    minvalue 3001
    maxvalue 4000
    nocycle
    nocache;

 create sequence hashtag_view_id_seq
    start with 4001
    increment by 1
    minvalue 4001
    maxvalue 5000
    nocycle
    nocache;

-- 테이블 생성
CREATE TABLE bookmark (
    bookmark_chkdate TIMESTAMP DEFAULT systimestamp NOT NULL,
    member_id        NUMBER(5) NOT NULL,
    shop_id          NUMBER(5) NOT NULL
);

COMMENT ON COLUMN bookmark.bookmark_chkdate IS
    '북마크 체크 일자';

COMMENT ON COLUMN bookmark.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN bookmark.shop_id IS
    '커피숍 ID';

CREATE TABLE coffeeshop (
    shop_id             NUMBER(5) NOT NULL,
    shop_name           NVARCHAR2(30) NOT NULL,
    shop_address        VARCHAR2(105) NOT NULL,
    shop_tel            VARCHAR2(15) NOT NULL,
    view_count          NUMBER(5) DEFAULT 0,
    shop_bookmark_count NUMBER(5) DEFAULT 0,
    shop_review_count   NUMBER(5) DEFAULT 0,
    parking             VARCHAR2(10) DEFAULT '아니오' NOT NULL,
    allday              VARCHAR2(10) DEFAULT '아니오' NOT NULL,
    shop_cdate          TIMESTAMP DEFAULT systimestamp NOT NULL
);

ALTER TABLE coffeeshop
    ADD CHECK ( parking IN ( '예', '아니오' ) );

ALTER TABLE coffeeshop
    ADD CHECK ( allday IN ( '예', '아니오' ) );

COMMENT ON COLUMN coffeeshop.shop_id IS
    '커피숍 ID';

COMMENT ON COLUMN coffeeshop.shop_name IS
    '커피숍 이름';

COMMENT ON COLUMN coffeeshop.shop_address IS
    '커피숍 주소';

COMMENT ON COLUMN coffeeshop.shop_tel IS
    '커피숍 전화번호 (''-'' 포함)';

COMMENT ON COLUMN coffeeshop.view_count IS
    '커피숍 조회 카운트';

COMMENT ON COLUMN coffeeshop.shop_bookmark_count IS
    '북마크 등록 카운트';

COMMENT ON COLUMN coffeeshop.shop_review_count IS
    '등록된 리뷰 개수 카운트';

COMMENT ON COLUMN coffeeshop.parking IS
    '주차시설 여부';

COMMENT ON COLUMN coffeeshop.allday IS
    '24시간 운영 여부';

COMMENT ON COLUMN coffeeshop.shop_cdate IS
    '커피숍 등록 일자';

ALTER TABLE coffeeshop ADD CONSTRAINT coffeeshop_pk PRIMARY KEY ( shop_id );

CREATE TABLE hashtag_classification (
    hashtag_id           NUMBER(5) NOT NULL,
    hashtag_name         VARCHAR2(100) NOT NULL,
    hashtag_search_count NUMBER(5) DEFAULT 0 NOT NULL
);

COMMENT ON COLUMN hashtag_classification.hashtag_id IS
    '해시태그 ID';

COMMENT ON COLUMN hashtag_classification.hashtag_name IS
    '해시태그 이름';

COMMENT ON COLUMN hashtag_classification.hashtag_search_count IS
    '해시태그 검색에 사용된 횟수';

ALTER TABLE hashtag_classification ADD CONSTRAINT hashtag_classification_pk PRIMARY KEY ( hashtag_id );

CREATE TABLE membership (
    member_id     NUMBER(5) NOT NULL,
    member_email  VARCHAR2(40) NOT NULL,
    member_passwd VARCHAR2(20) NOT NULL,
    member_name   VARCHAR2(15) NOT NULL,
    member_gender CHAR(3) NOT NULL,
    member_age    NUMBER(8) NOT NULL,
    member_tel    VARCHAR2(15) NOT NULL,
    member_owner  NUMBER DEFAULT 0
);

ALTER TABLE membership
    ADD CHECK ( member_gender IN ( '남', '여' ) );

COMMENT ON COLUMN membership.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN membership.member_email IS
    '멤버 이메일';

COMMENT ON COLUMN membership.member_passwd IS
    '멤버 비밀번호';

COMMENT ON COLUMN membership.member_name IS
    '멤버 이름';

COMMENT ON COLUMN membership.member_gender IS
    '멤버 성별';

COMMENT ON COLUMN membership.member_age IS
    '멤버 생년월일';

COMMENT ON COLUMN membership.member_tel IS
    '멤버 연락처 (''-'' 포함)';

COMMENT ON COLUMN membership.member_owner IS
    '점주회원 식별';

ALTER TABLE membership ADD CONSTRAINT membership_pk PRIMARY KEY ( member_id );

ALTER TABLE membership ADD CONSTRAINT membership_uk UNIQUE ( member_email );

CREATE TABLE mycoffeeshop (
    member_id   NUMBER(5) NOT NULL,
    shop_id     NUMBER(5) NOT NULL
);

COMMENT ON COLUMN mycoffeeshop.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN mycoffeeshop.shop_id IS
    '커피숍 ID';

CREATE TABLE photo (
    photo   BLOB,
    shop_id NUMBER(5) NOT NULL
);

COMMENT ON COLUMN photo.photo IS
    '사진';

CREATE TABLE report (
    review_id        NUMBER(5) NOT NULL,
    report_type_code NUMBER(1) NOT NULL
);

COMMENT ON COLUMN report.review_id IS
    '리뷰 식별 ID';

COMMENT ON COLUMN report.report_type_code IS
    '신고 사유 코드
0=스팸(부적절한 홍보), 1=음란물, 2=혐오표현, 3=욕설 및 비방';

CREATE TABLE report_type (
    report_type_code NUMBER(1) NOT NULL,
    report_type_name VARCHAR2(30) NOT NULL
);

COMMENT ON COLUMN report_type.report_type_code IS
    '신고 사유 코드
0=스팸(부적절한 홍보), 1=음란물, 2=혐오표현, 3=욕설 및 비방';

ALTER TABLE report_type ADD CONSTRAINT reporttype_code_pk PRIMARY KEY ( report_type_code );

CREATE TABLE review (
    review_id      NUMBER(5) NOT NULL,
    review_content CLOB NOT NULL,
    review_cdate   TIMESTAMP DEFAULT systimestamp NOT NULL,
    report_count   NUMBER(3) DEFAULT 0,
    member_id      NUMBER(5) NOT NULL,
    shop_id        NUMBER(5) NOT NULL
);

COMMENT ON COLUMN review.review_id IS
    '리뷰 식별 ID';

COMMENT ON COLUMN review.review_content IS
    '리뷰 내용';

COMMENT ON COLUMN review.review_cdate IS
    '리뷰 작성 일자';

COMMENT ON COLUMN review.report_count IS
    '리뷰의 신고된 횟수';

COMMENT ON COLUMN review.member_id IS
    '멤버 식별 ID';

COMMENT ON COLUMN review.shop_id IS
    '커피숍 ID';

ALTER TABLE review ADD CONSTRAINT review_pk PRIMARY KEY ( review_id );

CREATE TABLE shop_hashtag (
    shop_id    NUMBER(5) NOT NULL,
    hashtag_id NUMBER(5) NOT NULL
);

COMMENT ON COLUMN shop_hashtag.shop_id IS
    '커피숍 ID';

COMMENT ON COLUMN shop_hashtag.hashtag_id IS
    '해시태그 ID';

ALTER TABLE bookmark
    ADD CONSTRAINT bookmark_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id );

ALTER TABLE bookmark
    ADD CONSTRAINT bookmark_membership_fk FOREIGN KEY ( member_id )
        REFERENCES membership ( member_id );

ALTER TABLE mycoffeeshop
    ADD CONSTRAINT mycoffeeshop_membership_fk FOREIGN KEY ( member_id )
        REFERENCES membership ( member_id );

ALTER TABLE mycoffeeshop
    ADD CONSTRAINT mycoffeeshop_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id );

ALTER TABLE photo
    ADD CONSTRAINT photo_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id );

ALTER TABLE report
    ADD CONSTRAINT report_reporttype_code_fk FOREIGN KEY ( report_type_code )
        REFERENCES report_type ( report_type_code );

ALTER TABLE report
    ADD CONSTRAINT report_review_fk FOREIGN KEY ( review_id )
        REFERENCES review ( review_id );

ALTER TABLE review
    ADD CONSTRAINT review_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id );

ALTER TABLE review
    ADD CONSTRAINT review_membership_fk FOREIGN KEY ( member_id )
        REFERENCES membership ( member_id );

ALTER TABLE shop_hashtag
    ADD CONSTRAINT shop_hashtag_classification_fk FOREIGN KEY ( hashtag_id )
        REFERENCES hashtag_classification ( hashtag_id );

ALTER TABLE shop_hashtag
    ADD CONSTRAINT shop_hashtag_coffeeshop_fk FOREIGN KEY ( shop_id )
        REFERENCES coffeeshop ( shop_id );

commit;