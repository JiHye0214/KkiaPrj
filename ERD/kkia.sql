SHOW tables;

SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'kkiatigers'
;

DROP TABLE IF EXISTS diary;
DROP TABLE IF EXISTS favoritelike;
DROP TABLE IF EXISTS favoriteimg;
DROP TABLE IF EXISTS favorite;
DROP TABLE IF EXISTS live_chat;
DROP TABLE IF EXISTS livechat;
DROP TABLE IF EXISTS marketcomment;
DROP TABLE IF EXISTS marketimg;
DROP TABLE IF EXISTS market;
DROP TABLE IF EXISTS postcomment;
DROP TABLE IF EXISTS postrecommend;
DROP TABLE IF EXISTS postimg;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_img;
DROP TABLE IF EXISTS game_record;
DROP TABLE IF EXISTS today_game;
DROP TABLE IF EXISTS today;
DROP TABLE IF EXISTS game_details;
DROP TABLE IF EXISTS game_schedule;
DROP TABLE IF EXISTS game_player;
DROP TABLE IF EXISTS food_save;
DROP TABLE IF EXISTS food_comment;
DROP TABLE IF EXISTS food_item;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS `user`;

SELECT * FROM user;
SELECT * FROM user_img;
SELECT * FROM live_chat;
SELECT * FROM game_record;
SELECT * FROM game_schedule;
SELECT * FROM game_player;

INSERT INTO user_img (fileName, sourceName, userId)
VALUES ("2017.jpg", "2017.jpg", 1); 

INSERT INTO game_record (userId, recordDate, recordMemo, recordPlace, recordResult) VALUES 
(1, "2024.1.2", "안녕", "잠실종합운동장", "win"),
(1, "2024.1.4", "기아", "잠실종합운동장", "win"),
(1, "2024.1.24", "우승", "잠실종합운동장", "win"),
(1, "2024.1.3", "무조건", "잠실종합운동장", "win");

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




