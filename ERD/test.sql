SELECT *
FROM user;

SELECT *
FROM food
WHERE userId = 2;

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
