-- 제약조건 무시하고 테이블 삭제
SET foreign_key_checks = 0;
DROP TABLE favorite;
SET foreign_key_checks = 1;

DROP TABLE IF EXISTS favorite_like;
DROP TABLE IF EXISTS favorite_comment;
DROP TABLE IF EXISTS favorite_img;
DROP TABLE IF EXISTS favorite;
DROP TABLE IF EXISTS food_save;
DROP TABLE IF EXISTS food_comment;
DROP TABLE IF EXISTS food_item;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS post_comment;
DROP TABLE IF EXISTS post_img;
DROP TABLE IF EXISTS post;
-- DROP TABLE IF EXISTS market_comment;
DROP TABLE IF EXISTS market_img;
DROP TABLE IF EXISTS market_talk;
DROP TABLE IF EXISTS market_talk_list;
DROP TABLE IF EXISTS market;
DROP TABLE IF EXISTS live_chat;
DROP TABLE IF EXISTS game_schedule;
DROP TABLE IF EXISTS game_record;
DROP TABLE IF EXISTS game_player;
DROP TABLE IF EXISTS team;
-- DROP TABLE IF EXISTS user_authority;
-- DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS user_img;

SHOW tables;

SELECT * FROM user_img;
SELECT * FROM user;
SELECT * FROM team;
SELECT * FROM game_player;
SELECT * FROM game_record;
SELECT * FROM game_schedule;
SELECT * FROM live_chat;
SELECT * FROM market;
SELECT * FROM market_talk;
SELECT * FROM market_talk_list;
SELECT * FROM market_img;
SELECT * FROM food;
SELECT * FROM food_item;
SELECT * FROM food_comment;
SELECT * FROM food_save;
SELECT * FROM favorite;
SELECT * FROM favorite_img;
SELECT * FROM favorite_comment;
SELECT * FROM favorite_like;
SELECT * FROM post;
SELECT * FROM post_img;
SELECT * FROM post_comment;







