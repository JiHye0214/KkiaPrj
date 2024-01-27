-- 기존 데이터 삭제
DELETE FROM favorite_like;
DELETE FROM favorite_comment;
DELETE FROM favorite_img;
DELETE FROM favorite;
DELETE FROM food_save;
DELETE FROM food_comment;
DELETE FROM food_item;
DELETE FROM food;
-- DELETE FROM market_comment;
DELETE FROM market_img;
DELETE FROM market;
DELETE FROM live_chat;
DELETE FROM game_schedule;
DELETE FROM game_record;
DELETE FROM game_player;
DELETE FROM team;
DELETE FROM user;
DELETE FROM user_img;

ALTER TABLE favorite_like AUTO_INCREMENT = 1;
ALTER TABLE favorite_comment AUTO_INCREMENT = 1;
ALTER TABLE favorite_img AUTO_INCREMENT = 1;
ALTER TABLE favorite AUTO_INCREMENT = 1;
ALTER TABLE food_save AUTO_INCREMENT = 1;
ALTER TABLE food_comment AUTO_INCREMENT = 1;
ALTER TABLE food_item AUTO_INCREMENT = 1;
ALTER TABLE food AUTO_INCREMENT = 1;
-- ALTER TABLE market_comment AUTO_INCREMENT = 1;
ALTER TABLE market_img AUTO_INCREMENT = 1;
ALTER TABLE market AUTO_INCREMENT = 1;
ALTER TABLE live_chat AUTO_INCREMENT = 1;
ALTER TABLE game_schedule AUTO_INCREMENT = 1;
ALTER TABLE game_record AUTO_INCREMENT = 1;
ALTER TABLE game_player AUTO_INCREMENT = 1;
ALTER TABLE team AUTO_INCREMENT = 1;
ALTER TABLE user_img AUTO_INCREMENT = 1;
ALTER TABLE user AUTO_INCREMENT = 1;

-- 유저 프사
-- 얘 먼저 하고 user 하면 됨 !
INSERT INTO user_img (fileName, sourceName, userId) VALUES
("2017.jpg", "2017.jpg", 1),
("2017.jpg", "2017.jpg", 2),
("2017.jpg", "2017.jpg", 3),
("2017.jpg", "2017.jpg", 4)
;

-- 유저
INSERT INTO user(loginId, password, name, nickname, email, birth, gender, createdDate, userImgId) VALUES
('1234', '$2a$10$K6ipjV2LUKV2ncw3FE9wwe1PEn3lHepog5kKu/vutJ2K9HFLQ/12m', '김우승', '어우기', 'djdnrl@mail.com', "2000-4-12", "여자", now(), 1),
('apple1234', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '박사과', '말랑한고구마', 'apple@mail.com', "2001-6-3", "여자", now(), 2),
('melon1234', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '김멜론', '맑은아침햇살', 'melon@mail.com', "2002-2-24", "남자", now(), 3),
('cherry1234', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '이체리', '마라탕먹고싶다', 'cherry@mail.com', "2003-10-19", "남자", now(), 4)
;

-- 맛집 글
INSERT INTO food(title, region, saveCnt, viewCnt, createdDate, userId) VALUES
('맛집1', '광주', 0, 0, now(), 2),
('맛집2', '광주', 0, 0, now(), 2),
('맛집3', '광주', 0, 0, now(), 3),
('맛집4', '대구', 0, 0, now(), 3),
('맛집5', '부산', 0, 0, now(), 4),
('맛집6', '대구', 0, 0, now(), 4),
('맛집7', '광주', 0, 0, now(), 2),
('맛집8', '광주', 0, 0, now(), 2),
('맛집9', '대전', 0, 0, now(), 3),
('맛집10', '수원', 0, 0, now(), 3),
('맛집11', '광주', 0, 0, now(), 4),
('맛집12', '대전', 0, 0, now(), 4),
('맛집13', '인천', 0, 0, now(), 2),
('맛집14', '잠실', 0, 0, now(), 2),
('맛집15', '광주', 0, 0, now(), 3),
('맛집16', '광주', 0, 0, now(), 3),
('맛집17', '고척', 0, 0, now(), 4),
('맛집18', '수원', 0, 0, now(), 4),
('맛집19', '광주', 0, 0, now(), 2),
('맛집20', '광주', 0, 0, now(), 2)
;

-- 페이징 테스트용 다량 데이터
-- INSERT INTO food(title, region, saveCnt, viewCnt, isSaveClicked, createdDate, userId)
-- SELECT title, region, saveCnt, viewCnt, isSaveClicked, now(), userId
-- FROM food;

-- 맛집 글 항목
INSERT INTO food_item(restaurantName, content, address, lat, lng, foodId) VALUES
('라스트춘선 안양점', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1045-9', 37.3908408022861, 126.953373087799, 1),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 1),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 2),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 3),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 4),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 5),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 6),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 7),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 8),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 9),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 10),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 11),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 12),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 13),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 14),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 15),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 16),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 17),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 18),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 19),
('호랑이굴', '로컬 식당이라 주민들밖에 모르는데 택시 기사님 추천받아서 갔다.', '경기 안양시 동안구 호계동 1043-1', 37.39201269687231, 126.95482912267362, 20)
;

-- 맛집 글 댓글
INSERT INTO food_comment(content, createdDate, userId, foodId) VALUES
('말랑한고구마가 1번 글에 댓글 작성', now(), 2, 1),
('말랑한고구마가 2번 글에 댓글 작성', now(), 2, 2),
('말랑한고구마가 3번 글에 댓글 작성', now(), 2, 3),
('말랑한고구마가 4번 글에 댓글 작성', now(), 2, 4),
('맑은아침햇살이 1번 글에 댓글 작성', now(), 3, 1),
('맑은아침햇살이 2번 글에 댓글 작성', now(), 3, 2),
('맑은아침햇살이 3번 글에 댓글 작성', now(), 3, 3),
('맑은아침햇살이 4번 글에 댓글 작성', now(), 3, 4),
('마라탕먹고싶다가 1번 글에 댓글 작성', now(), 4, 1),
('마라탕먹고싶다가 2번 글에 댓글 작성', now(), 4, 2),
('마라탕먹고싶다가 3번 글에 댓글 작성', now(), 4, 3),
('마라탕먹고싶다가 4번 글에 댓글 작성', now(), 4, 4)
;

-- 최애 글
INSERT INTO favorite(title, playerName, playerNum, likeCnt, viewCnt, createdDate, userId) VALUES
('최애글1', '유지성', '4',  0, 0, now(), 2),
('최애글2', '곽도규', '9', 0, 0, now(), 2),
('최애글3', '황동하', '10', 0, 0, now(), 3),
('최애글4', '윤영철', '13', 0, 0, now(), 3),
('최애글5', '김현수', '15', 0, 0, now(), 4),
('최애글6', '임기영', '17', 0, 0, now(), 4),
('최애글7', '유지성', '4',  0, 0, now(), 2),
('최애글8', '곽도규', '9', 0, 0, now(), 2),
('최애글9', '황동하', '10', 0, 0, now(), 3),
('최애글10', '윤영철', '13', 0, 0, now(), 3),
('최애글11', '김현수', '15', 0, 0, now(), 4),
('최애글12', '임기영', '17', 0, 0, now(), 4),
('최애글13', '황동하', '10', 0, 0, now(), 2),
('최애글14', '윤영철', '13', 0, 0, now(), 2),
('최애글15', '김현수', '15', 0, 0, now(), 3),
('최애글16', '임기영', '17', 0, 0, now(), 3)
;

-- 페이징 테스트용 다량 데이터
-- INSERT INTO favorite(title, playerName, playerNum, content, likeCnt, viewCnt, isLikeClicked, createdDate, userId)
-- SELECT title, playerName, playerNum, content, likeCnt, viewCnt, isLikeClicked, now(), userId
-- FROM favorite;

-- 최애 글 이미지
INSERT INTO favorite_img (fileName, sourceName, favoriteId) VALUES
("player1.jpg", "player1.jpg", 1),
("player2.jpg", "player2.jpg", 2),
("브이 도영.jpg", "브이 도영.jpg", 3),
("잘생긴 도영.jpg", "잘생긴 도영.jpg", 4),
("하트 영철.jpg", "하트 영철.jpg", 5),
("goods.jpg", "goods.jpg", 6),
("tiger.png", "tiger.png", 7),
("player1.jpg", "player1.jpg", 8),
("player2.jpg", "player2.jpg", 9),
("브이 도영.jpg", "브이 도영.jpg", 10),
("잘생긴 도영.jpg", "잘생긴 도영.jpg", 11),
("하트 영철.jpg", "하트 영철.jpg", 12),
("goods.jpg", "goods.jpg", 13),
("tiger.png", "tiger.png", 14),
("브이 도영.jpg", "브이 도영.jpg", 15),
("잘생긴 도영.jpg", "잘생긴 도영.jpg", 16),
("player2.jpg", "player2.jpg", 16)
;

-- 최애 글 댓글
INSERT INTO favorite_comment(content, createdDate, userId, favoriteId) VALUES
('말랑한고구마가 1번 글에 댓글 작성', now(), 2, 1),
('말랑한고구마가 2번 글에 댓글 작성', now(), 2, 2),
('말랑한고구마가 3번 글에 댓글 작성', now(), 2, 3),
('말랑한고구마가 4번 글에 댓글 작성', now(), 2, 4),
('말랑한고구마가 5번 글에 댓글 작성', now(), 2, 5),
('말랑한고구마가 6번 글에 댓글 작성', now(), 2, 6),
('맑은아침햇살이 1번 글에 댓글 작성', now(), 3, 1),
('맑은아침햇살이 2번 글에 댓글 작성', now(), 3, 2),
('맑은아침햇살이 3번 글에 댓글 작성', now(), 3, 3),
('맑은아침햇살이 4번 글에 댓글 작성', now(), 3, 4),
('맑은아침햇살이 5번 글에 댓글 작성', now(), 3, 5),
('맑은아침햇살이 6번 글에 댓글 작성', now(), 3, 6),
('마라탕먹고싶다가 1번 글에 댓글 작성', now(), 4, 1),
('마라탕먹고싶다가 2번 글에 댓글 작성', now(), 4, 2),
('마라탕먹고싶다가 3번 글에 댓글 작성', now(), 4, 3),
('마라탕먹고싶다가 4번 글에 댓글 작성', now(), 4, 4),
('마라탕먹고싶다가 5번 글에 댓글 작성', now(), 4, 5),
('마라탕먹고싶다가 6번 글에 댓글 작성', now(), 4, 6)
;

-- 마켓 글 작성
INSERT INTO market (product, content, price, region, userId, createdDate) VALUES
('김도영 유니폼 자수 마킹 3번 입은 거 팝니다', '김도영 유니폼 자수 마킹 3번 입은 거 a팝니다', 100000, '광주광역시 북구', 1, now()),
('호통이 2묶음', '호통이 2묶음', 50000, '광주광역시 북구', 2, now()),
('윤영철 싸인볼', '윤영철 싸인볼', 50000, '광주광역시 북구', 3, now()),
('이의리 아이콘 유니폼', '이의리 아이콘 유니폼', 100000, '광주광역시 북구', 4, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now()),
('영철이의 미소', '영철이 내년에는 10승 하고 신인상 받자', 9999999, '서울특별시 송파구', 1, now())
;

-- 마켓 글 이미지
INSERT INTO market_img (fileName, sourceName, marketId) VALUES
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('goods.jpg', 'good.jpg', 1),
('player2.jpg', 'player2.jpg', 2),
('tiger.png', 'tiger.png', 2),
('브이 도영.jpg', '브이 도영.jpg', 3),
('잘생긴 도영.jpg', '잘생긴 도영.jpg', 4),
('하트 영철.jpg', '하트 영철.jpg', 5),
('하트 영철.jpg', '하트 영철.jpg', 6),
('하트 영철.jpg', '하트 영철.jpg', 7),
('하트 영철.jpg', '하트 영철.jpg', 8),
('하트 영철.jpg', '하트 영철.jpg', 9),
('하트 영철.jpg', '하트 영철.jpg', 10),
('하트 영철.jpg', '하트 영철.jpg', 11),
('하트 영철.jpg', '하트 영철.jpg', 12)
;

-- 구단 정보
INSERT INTO team (code, name, img, place, latitude, longitude) VALUES
("NCHT", "Dinos", "/img/team-dinos.png", "창원NC파크", 35.2228007, 128.5820053),
("HTSK", "Landers", "/img/team-landers.png", "인천SSG랜더스필드", 37.4369986, 126.6932732),
("OBHT", "Bears", "/img/team-bears.png", "잠실종합운동장", 37.5121518, 127.0719083),
("HHHT", "Eagles", "/img/team-eagles.png", "한화생명이글스파크", 36.3170827, 127.4291626),
("HTWO", "Heros",  "/img/team-heros.png", "고척스카이돔", 37.4982302,  126.8671030),
("SSHT", "Lions", "/img/team-lions.png", "대구삼성라이온즈파크", 35.8410568, 128.6816571),
("HTLT", "Giants", "/img/team-giants.png", "부산사직종합운동장", 35.1940153, 129.0615412),
("HTLG", "Twins", "/img/team-twins.png", "잠실종합운동장", 37.5121518, 127.0719083),
("KTHT", "Wiz", "/img/team-wiz.png", "수원KT위즈파크", 37.2997194, 127.0097703),
("KKIA", "Tigers", "/img/team-tigers.png", "광주기아챔피언스필드", 35.1682047, 126.8891093)
;

-- 직관 기록
INSERT INTO game_record (userId, recordDate, recordMemo, recordPlace, recordResult) VALUES 
(1, "2024.1.2", "안녕", "잠실종합운동장", "win"),
(1, "2024.1.4", "기아", "잠실종합운동장", "win"),
(1, "2024.1.24", "우승", "잠실종합운동장", "win"),
(1, "2024.1.3", "무조건", "잠실종합운동장", "win")
;

-- 선수 정보
-- api 대신 
INSERT INTO game_player (name, number) VALUES
("박찬호", 1),
("김선빈", 3),
("김도영", 5),
("홍종표", 6),
("김태군", 8),
("윤영철", 13),
("김규성", 14),
("최원준", 16),
("임기영", 17),
("윤중현", 19),
("이준영", 20),
("최정용", 23),
("이우성", 25),
("김호령", 27),
("소크라테스", 30),
("박준표", 31),
("최형우", 34),
("최지민", 39),
("김건국", 43),
("나성범", 47),
("이의리", 48),
("장현식", 50),
("전상현", 51),
("김기훈", 53),
("양현종", 54),
("한준수", 55),
("고종욱", 57),
("오선우", 59),
("정해영", 62),
("이창진", 66),
("진갑용", 70),
("이범호", 71),
("김상훈", 72),
("조재영", 75),
("박기남", 76),
("이현곤", 80),
("곽정철", 96),
("서재응", 98)
;

-- 공식 일정
-- api 대신
INSERT INTO game_schedule (gameDate, homeGame, opponent) VALUES
-- boolean 0 / 1
("2024.3.23", TRUE, "HTWO"),
("2024.3.24", TRUE, "HTWO"),
("2024.3.26", TRUE, "HTLT"),
("2024.3.27", TRUE, "HTLT"),
("2024.3.28", TRUE, "HTLT"),
("2024.3.29", false, "OBHT"),
("2024.3.30", false, "OBHT"),
("2024.3.31", false, "OBHT"),
("2024.4.2", false, "KTHT"),
("2024.4.3", false, "KTHT"),
("2024.4.4", false, "KTHT"),
("2024.4.5", TRUE, "SSHT"),
("2024.4.6", TRUE, "SSHT"),
("2024.4.7", TRUE, "SSHT"),
("2024.4.9", TRUE, "HTLG"),
("2024.4.10", TRUE, "HTLG"),
("2024.4.11", TRUE, "HTLG"),
("2024.4.12", false, "HHHT"),
("2024.4.13", false, "HHHT"),
("2024.4.14", false, "HHHT"),
("2024.4.16", false, "HTSK"),
("2024.4.17", false, "HTSK"),
("2024.4.18", false, "HTSK"),
("2024.4.19", TRUE, "NCHT"),
("2024.4.20", TRUE, "NCHT"),
("2024.4.21", TRUE, "NCHT"),
("2024.4.23", false, "HTWO"),
("2024.4.24", false, "HTWO"),
("2024.4.25", false, "HTWO"),
("2024.4.26", false, "HTLG"),
("2024.4.27", false, "HTLG"),
("2024.4.28", false, "HTLG"),
("2024.4.30", true, "KTHT"),
("2024.5.1", true, "KTHT"),
("2024.5.2", true, "KTHT"),
("2024.5.3", true, "HHHT"),
("2024.5.4", true, "HHHT"),
("2024.5.5", true, "HHHT"),
("2024.5.7", false, "SSHT"),
("2024.5.8", false, "SSHT"),
("2024.5.9", false, "SSHT"),
("2024.5.10", true, "HTSK"),
("2024.5.11", true, "HTSK"),
("2024.5.12", true, "HTSK"),
("2024.5.14", true, "OBHT"),
("2024.5.15", true, "OBHT"),
("2024.5.16", true, "OBHT"),
("2024.5.17", false, "NCHT"),
("2024.5.18", false, "NCHT"),
("2024.5.19", false, "NCHT"),
("2024.5.21", false, "HTLT"),
("2024.5.22", false, "HTLT"),
("2024.5.23", false, "HTLT"),
("2024.5.24", true, "OBHT"),
("2024.5.25", true, "OBHT"),
("2024.5.26", true, "OBHT"),
("2024.5.28", false, "NCHT"),
("2024.5.29", false, "NCHT"),
("2024.5.30", false, "NCHT"),
("2024.5.31", true, "KTHT"),
("2024.6.1", true, "KTHT"),
("2024.6.2", true, "KTHT"),
("2024.6.4", TRUE, "HTLT"),
("2024.6.5", TRUE, "HTLT"),
("2024.6.6", TRUE, "HTLT"),
("2024.6.7", false, "OBHT"),
("2024.6.8", false, "OBHT"),
("2024.6.9", false, "OBHT"),
("2024.6.11", false, "HTSK"),
("2024.6.12", false, "HTSK"),
("2024.6.13", false, "HTSK"),
("2024.6.14", false, "KTHT"),
("2024.6.15", false, "KTHT"),
("2024.6.16", false, "KTHT"),
("2024.6.18", TRUE, "HTLG"),
("2024.6.19", TRUE, "HTLG"),
("2024.6.20", TRUE, "HTLG"),
("2024.6.21", true, "HHHT"),
("2024.6.22", true, "HHHT"),
("2024.6.23", true, "HHHT"),
("2024.6.25", false, "HTLT"),
("2024.6.26", false, "HTLT"),
("2024.6.27", false, "HTLT"),
("2024.6.28", TRUE, "HTWO"),
("2024.6.29", TRUE, "HTWO"),
("2024.6.30", TRUE, "HTWO"),
("2024.7.2", false, "SSHT"),
("2024.7.3", false, "SSHT"),
("2024.7.4", false, "SSHT"),
("2024.7.9", false, "HTLG"),
("2024.7.10", false, "HTLG"),
("2024.7.11", false, "HTLG"),
("2024.7.12", true, "HTSK"),
("2024.7.13", true, "HTSK"),
("2024.7.14", true, "HTSK"),
("2024.7.16", true, "SSHT"),
("2024.7.17", true, "SSHT"),
("2024.7.18", true, "SSHT"),
("2024.7.19", false, "HHHT"),
("2024.7.20", false, "HHHT"),
("2024.7.21", false, "HHHT"),
("2024.7.23", TRUE, "NCHT"),
("2024.7.24", TRUE, "NCHT"),
("2024.7.25", TRUE, "NCHT"),
("2024.7.26", false, "HTWO"),
("2024.7.27", false, "HTWO"),
("2024.7.28", false, "HTWO"),
("2024.7.30", true, "OBHT"),
("2024.7.31", true, "OBHT"),
("2024.8.1", true, "OBHT"),
("2024.8.2", false, "HHHT"),
("2024.8.3", false, "HHHT"),
("2024.8.4", false, "HHHT"),
("2024.8.6", true, "KTHT"),
("2024.8.7", true, "KTHT"),
("2024.8.8", true, "KTHT"),
("2024.8.9", true, "SSHT"),
("2024.8.10", true, "SSHT"),
("2024.8.11", true, "SSHT"),
("2024.8.13", false, "HTWO"),
("2024.8.14", false, "HTWO"),
("2024.8.15", false, "HTWO"),
("2024.8.16", false, "HTLG"),
("2024.8.17", false, "HTLG"),
("2024.8.18", false, "HTLG"),
("2024.8.20", TRUE, "HTLT"),
("2024.8.21", TRUE, "HTLT"),
("2024.8.22", TRUE, "HTLT"),
("2024.8.23", false, "NCHT"),
("2024.8.24", false, "NCHT"),
("2024.8.25", false, "NCHT"),
("2024.8.27", true, "HTSK"),
("2024.8.28", true, "HTSK"),
("2024.8.29", true, "HTSK")
;




