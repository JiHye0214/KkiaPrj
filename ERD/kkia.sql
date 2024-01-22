SHOW tables;

SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'kkiatigers'
;

DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS diary;
DROP TABLE IF EXISTS favorite;
DROP TABLE IF EXISTS favoriteimg;
DROP TABLE IF EXISTS favoritelike;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS foodimg;
DROP TABLE IF EXISTS foodsave;
DROP TABLE IF EXISTS live_chat;
DROP TABLE IF EXISTS livechat;
DROP TABLE IF EXISTS market;
DROP TABLE IF EXISTS marketcomment;
DROP TABLE IF EXISTS marketimg;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS postcomment;
DROP TABLE IF EXISTS postimg;
DROP TABLE IF EXISTS postrecommend;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_img;
DROP TABLE IF EXISTS game_record;


SELECT * FROM user ;
SELECT * FROM user_img ;
SELECT * FROM live_chat ;
SELECT * FROM game_record ;

INSERT INTO user_img (fileName, sourceName, userId)
VALUES ("2017.jpg", "2017.jpg", 1); 

INSERT INTO game_record (userId, recordDate, recordMemo, recordPlace, recordResult) VALUES 
(1, "2024.1.2", "안녕", "잠실종합운동장", "win"),
(1, "2024.1.4", "기아", "잠실종합운동장", "win"),
(1, "2024.1.24", "우승", "잠실종합운동장", "win"),
(1, "2024.1.3", "무조건", "잠실종합운동장", "win");


SELECT loginid FROM USER;