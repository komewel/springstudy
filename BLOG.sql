DROP TABLE GOOD_T;
DROP TABLE COMMENT_T;
DROP TABLE SUMMERNOTE_IMAGE_T;
DROP TABLE BLOG_T;
DROP TABLE MEMBER_T;

-- 사용자
CREATE TABLE MEMBER_T (
    MEMBER_NO NUMBER NOT NULL,
    ID        VARCHAR2(30 BYTE) NOT NULL UNIQUE,
    PW        VARCHAR2(30 BYTE) NOT NULL,
    NAME      VARCHAR2(30 BYTE)
);

-- 블로그 
CREATE TABLE BLOG_T (
    BLOG_NO     NUMBER NOT NULL,
    TITLE       VARCHAR2(100 BYTE) NOT NULL,
    CONTENT     CLOB,
    HIT         NUMBER NOT NULL,
    CREATED_AT  DATE,
    MODIFIED_AT DATE,
    MEMBER_NO   NUMBER -- FK
);

-- 블로그 본문 작성 에디터 SUMMERNOTE에서 사용한 이미지의 목록 
CREATE TABLE SUMMERNOTE_IMAGE_T (
     -- 이미지 저장 경로가 자주 바뀔 경우 따로 PATH칼럼이 필요하다(NULL인 이유)
    FILESYSTEM_NAME VARCHAR2(50 BYTE),
    BLOG_NO         NUMBER -- FK
);

-- 댓글 (1단 계층형, 대댓글 불가한 형태)
CREATE TABLE COMMENT_T (
   COMMENT_NO NUMBER NOT NULL,
   CONTENT    VARCHAR2(4000 BYTE) NOT NULL,
   STATE      NUMBER, -- 정상 1, 삭제 -1
   DEPTH      NUMBER, -- 댓글 0, 댓글에 달린 댓글 1, 댓글에 댓글을 다는 계층형을 위한 칼럼
   GROUP_NO   NUMBER, -- 댓글과 댓글에 달린 댓글은 같은 그룹
   CREATED_AT DATE,
   BLOG_NO    NUMBER, -- FK, 한 블로그에 여러개의 댓글이 달릴수 있으므로 1 대 다 관계이다.
   MEMBER_NO  NUMBER  -- FK, 회원들만 달수있으므로 
);    

-- 좋아요
CREATE TABLE GOOD_T (
    MEMBER_NO NUMBER, -- FK
    BLOG_NO   NUMBER, -- FK
    MARKED_AT DATE
);    

-- 사용자 기본키
ALTER TABLE MEMBER_T
    ADD CONSTRAINT PK_MEMBER
        PRIMARY KEY(MEMBER_NO);

-- 블로그 기본키
ALTER TABLE BLOG_T 
    ADD CONSTRAINT PK_BLOG
        PRIMARY KEY(BLOG_NO);
        
-- 댓글 기본키
ALTER TABLE COMMENT_T
    ADD CONSTRAINT PK_COMMENT
        PRIMARY KEY(COMMENT_NO);
        
-- 블로그 외래키 : 작성자가 삭제되면 블로그도 함께 삭제된다.
ALTER TABLE BLOG_T
    ADD CONSTRAINT FK_BLOG_MEMBER
        FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER_T(MEMBER_NO)
            ON DELETE CASCADE;

-- 써머노트 이미지 외래키 : 블로그가 삭제되면 이미지도 함께 삭제된다.
ALTER TABLE SUMMERNOTE_IMAGE_T
    ADD CONSTRAINT FK_BLOG_SUMMERNOTE 
         FOREIGN KEY(BLOG_NO) REFERENCES BLOG_T(BLOG_NO)
            ON DELETE CASCADE;
            
-- 댓글 외래키(블로그) : 블로그가 삭제되면 댓글도 삭제된다.
ALTER TABLE COMMENT_T 
    ADD CONSTRAINT FK_BLOG_COMMENT
        FOREIGN KEY(BLOG_NO) REFERENCES BLOG_T(BLOG_NO)
            ON DELETE CASCADE;
            
-- 댓글 외래키(사용자) : 댓글 작성자가 삭제되면 댓글의 MEMBER_NO를 NULL 처리한다, 진짜로 지워진게 아니라 지원진것처럼 표시를 하면 되므로 NULL처리한다.
ALTER TABLE COMMENT_T 
    ADD CONSTRAINT FK_COMMENT_COMMENT
        FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER_T(MEMBER_NO)
            ON DELETE SET NULL;
            
-- 좋아요 외래키(블로그) : 블로그가 삭제되면 좋아요도 삭제된다.
ALTER TABLE GOOD_T 
    ADD CONSTRAINT FK_BLOG_GOOD
        FOREIGN KEY(BLOG_NO) REFERENCES BLOG_T(BLOG_NO)
            ON DELETE CASCADE;
            
-- 좋아요 외래키(회원) : 좋아요를 누른 사람이 삭제되면 좋아요도 삭제된다.
ALTER TABLE GOOD_T
    ADD CONSTRAINT FK_MEMBER_GOOD
        FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER_T(MEMBER_NO)
            ON DELETE CASCADE;
-- 시퀀스
DROP SEQUENCE MEMBER_SEQ;
DROP SEQUENCE BLOG_SEQ;
DROP SEQUENCE COMMENT_SEQ;
CREATE SEQUENCE MEMBER_SEQ NOCACHE;
CREATE SEQUENCE BLOG_SEQ NOCACHE;
CREATE SEQUENCE COMMENT_SEQ NOCACHE;

-- 회원 데이터
INSERT INTO MEMBER_T VALUES(MEMBER_SEQ.NEXTVAL, 'ryan', '1111', '라이언');
INSERT INTO MEMBER_T VALUES(MEMBER_SEQ.NEXTVAL, 'prodo', '1111', '프로도');
INSERT INTO MEMBER_T VALUES(MEMBER_SEQ.NEXTVAL, 'apeach', '1111', '어피치');
COMMIT;

