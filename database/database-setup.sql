-- Create database
CREATE DATABASE IF NOT EXISTS devika_db;
USE devika_db;

-- Students table
CREATE TABLE IF NOT EXISTS students (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(100),
age INT
);

-- Menus table
CREATE TABLE IF NOT EXISTS menus (
menu_id INT AUTO_INCREMENT PRIMARY KEY,
menu_date DATE,
breakfast VARCHAR(100),
lunch VARCHAR(100),
dinner VARCHAR(100)
);

-- Menu votes table
CREATE TABLE IF NOT EXISTS menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_item VARCHAR(100),
vote_date DATE
);

-- Food waste logs table
CREATE TABLE IF NOT EXISTS food_waste_logs (
log_id INT AUTO_INCREMENT PRIMARY KEY,
menu_id INT,
waste_quantity INT,
log_date DATE
);

-- Insert sample students
INSERT INTO students (name,email,age) VALUES
('Devika','devika@email.com',21),
('Rahul','rahul@email.com',22),
('Anita','anita@email.com',20),
('Arjun','arjun@email.com',21),
('Priya','priya@email.com',22);

-- Insert sample menus
INSERT INTO menus (menu_date, breakfast, lunch, dinner)
VALUES
('2026-03-06','Idli','Veg Biryani','Chapati'),
('2026-03-07','Dosa','Fried Rice','Paneer Curry'),
('2026-03-08','Upma','Dal Tadka','Masala Dosa');

-- Student voting data
INSERT INTO menu_votes (student_id, menu_item, vote_date)
VALUES
(1,'Veg Biryani',CURDATE()),
(2,'Veg Biryani',CURDATE()),
(3,'Fried Rice',CURDATE()),
(4,'Veg Biryani',CURDATE()),
(5,'Dal Tadka',CURDATE());

-- Food waste logs
INSERT INTO food_waste_logs (menu_id, waste_quantity, log_date)
VALUES
(1,4,CURDATE()),
(2,2,CURDATE()),
(3,6,CURDATE());

-- Query: Most voted dish
SELECT menu_item, COUNT(*) AS total_votes
FROM menu_votes
GROUP BY menu_item
ORDER BY total_votes DESC;

-- Query: Waste analysis by menu date
SELECT m.menu_date, SUM(w.waste_quantity) AS total_waste
FROM food_waste_logs w
JOIN menus m ON w.menu_id = m.menu_id
GROUP BY m.menu_date
ORDER BY total_waste DESC;

-- Query: Vote vs Waste insight
SELECT 
v.menu_item,
COUNT(v.vote_id) AS total_votes,
COALESCE(SUM(w.waste_quantity),0) AS total_waste
FROM menu_votes v
LEFT JOIN menus m ON v.vote_date = m.menu_date
LEFT JOIN food_waste_logs w ON m.menu_id = w.menu_id
GROUP BY v.menu_item
ORDER BY total_votes DESC, total_waste ASC;
