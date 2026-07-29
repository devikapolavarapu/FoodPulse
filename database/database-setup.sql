-- =====================================================
-- DATABASE SETUP
-- Project: Wasteless / FoodPulse
-- Version: Extended Schema (Menu System + Poll System)
-- =====================================================

-- Create database
CREATE DATABASE IF NOT EXISTS devika_db;
USE devika_db;

-- =====================================================
-- STUDENTS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS students (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(100),
age INT
);

-- =====================================================
-- MENUS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS menus (
menu_id INT AUTO_INCREMENT PRIMARY KEY,
menu_date DATE,
breakfast VARCHAR(100),
lunch VARCHAR(100),
dinner VARCHAR(100)
);

-- =====================================================
-- MENU VOTES TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_item VARCHAR(100),
vote_date DATE
);

-- =====================================================
-- FOOD WASTE LOGS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS food_waste_logs (
log_id INT AUTO_INCREMENT PRIMARY KEY,
menu_id INT,
waste_quantity INT,
log_date DATE
);

-- =====================================================
-- SAMPLE STUDENTS
-- =====================================================

INSERT INTO students (name,email,age) VALUES
('Devika','[devika@email.com](mailto:devika@email.com)',21),
('Rahul','[rahul@email.com](mailto:rahul@email.com)',22),
('Anita','[anita@email.com](mailto:anita@email.com)',20),
('Arjun','[arjun@email.com](mailto:arjun@email.com)',21),
('Priya','[priya@email.com](mailto:priya@email.com)',22);

-- =====================================================
-- SAMPLE MENUS
-- =====================================================

INSERT INTO menus (menu_date, breakfast, lunch, dinner)
VALUES
('2026-03-06','Idli','Veg Biryani','Chapati'),
('2026-03-07','Dosa','Fried Rice','Paneer Curry'),
('2026-03-08','Upma','Dal Tadka','Masala Dosa');

-- =====================================================
-- STUDENT MENU VOTES
-- =====================================================

INSERT INTO menu_votes (student_id, menu_item, vote_date)
VALUES
(1,'Veg Biryani',CURDATE()),
(2,'Veg Biryani',CURDATE()),
(3,'Fried Rice',CURDATE()),
(4,'Veg Biryani',CURDATE()),
(5,'Dal Tadka',CURDATE());

-- =====================================================
-- FOOD WASTE LOGS
-- =====================================================

INSERT INTO food_waste_logs (menu_id, waste_quantity, log_date)
VALUES
(1,4,CURDATE()),
(2,2,CURDATE()),
(3,6,CURDATE());

-- =====================================================
-- ANALYTICS QUERIES
-- =====================================================

-- Most voted dish
SELECT menu_item, COUNT(*) AS total_votes
FROM menu_votes
GROUP BY menu_item
ORDER BY total_votes DESC;

-- Waste analysis by menu date
SELECT m.menu_date, SUM(w.waste_quantity) AS total_waste
FROM food_waste_logs w
JOIN menus m ON w.menu_id = m.menu_id
GROUP BY m.menu_date
ORDER BY total_waste DESC;

-- Vote vs Waste insight
SELECT
v.menu_item,
COUNT(v.vote_id) AS total_votes,
COALESCE(SUM(w.waste_quantity),0) AS total_waste
FROM menu_votes v
LEFT JOIN menus m ON v.vote_date = m.menu_date
LEFT JOIN food_waste_logs w ON m.menu_id = w.menu_id
GROUP BY v.menu_item
ORDER BY total_votes DESC, total_waste ASC;

-- =====================================================
-- NEW FEATURE: POLL BASED VOTING SYSTEM
-- (Used by Spring Boot Backend APIs)
-- =====================================================

CREATE TABLE IF NOT EXISTS polls (
id INT AUTO_INCREMENT PRIMARY KEY,
question VARCHAR(255),
option1 VARCHAR(100),
option2 VARCHAR(100),
option3 VARCHAR(100),
option4 VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS votes (
id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
poll_id INT,
selected_option VARCHAR(255)
);

-- =====================================================
-- SAMPLE POLL
-- =====================================================

INSERT INTO polls
(question,option1,option2,option3,option4)
VALUES
(
'Tomorrow Lunch Menu',
'Biryani',
'Veg Meals',
'Chicken Curry',
'Paneer Curry'
);

-- =====================================================
-- SAMPLE POLL VOTES
-- =====================================================

INSERT INTO votes (student_id,poll_id,selected_option)
VALUES
(1,1,'Biryani'),
(2,1,'Veg Meals'),
(3,1,'Veg Meals');

-- =====================================================
-- POLL RESULT ANALYTICS
-- =====================================================

SELECT
selected_option,
COUNT(*) AS vote_count
FROM votes
WHERE poll_id = 1
GROUP BY selected_option;
