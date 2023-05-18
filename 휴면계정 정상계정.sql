-- 12개월 이상 접속 안 한 회원(SELECT) -> 휴면 테이블에 저장(INSERT)
-- INSERT INTO 테이블(칼럼 리스트...)(SELECT 칼럼 리스트... FROM 테이블); 칼럼수가 일치해야 한다.   
    INSERT INTO SLEEP_USER_T (
        USER_NO
      , ID
      , PW
      , NAME
      , GENDER
      , EMAIL
      , MOBILE
      , BIRTHYEAR
      , BIRTHDATE
      , POSTCODE
      , ROAD_ADDRESS
      , JIBUN_ADDRESS
      , DETAIL_ADDRESS
      , EXTRA_ADDRESS
      , AGREECODE
      , JOINED_AT
      , PW_MODIFIED_AT
      , SLEPT_AT
) (SELECT
        U.USER_NO
      , U.ID
      , U.PW
      , U.NAME
      , U.GENDER
      , U.EMAIL
      , U.MOBILE
      , U.BIRTHYEAR
      , U.BIRTHDATE
      , U.POSTCODE
      , U.ROAD_ADDRESS
      , U.JIBUN_ADDRESS
      , U.DETAIL_ADDRESS
      , U.EXTRA_ADDRESS
      , U.AGREECODE
      , U.JOINED_AT
      , U.PW_MODIFIED_AT
      , SYSDATE
     FROM USER_T U LEFT OUTER JOIN USER_ACCESS_T UA
       ON U.ID = UA.ID
    WHERE MONTHS_BETWEEN(SYSDATE, UA.LAST_LOGIN_AT) >= 12 -- 오늘 날짜와 마지막 로그인 날짜(조인 해서 가져온)를 뺄셈으로 계산해서 조건으로 붙인다, 12개월 이상 로그인 이력이 없는 회원
       OR (MONTHS_BETWEEN(SYSDATE, U.JOINED_AT) >= 12 AND UA.LAST_LOGIN_AT IS NULL));-- 가입일이 12개월 이상 지나고, 로그인 이력이 아예 없는 회원