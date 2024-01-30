SELECT *
FROM user;

SELECT *
FROM favorite_comment 
WHERE favoriteId = 1;

SELECT *
FROM favorite_img 
WHERE favoriteId = 1;

SELECT *
FROM food_save  
WHERE userId = 2;

SELECT *
FROM favorite_like 
WHERE userId = 2;

SELECT *
FROM favorite
ORDER BY likeCnt DESC, id ASC
LIMIT 5;
