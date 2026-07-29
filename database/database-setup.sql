CREATE DATABASE IF NOT EXISTS devika_db;

USE devika_db;

CREATE TABLE IF NOT EXISTS students (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(100),
age INT
);

INSERT INTO students (name,email,age)
VALUES ('Devika','devika@email.com',21);

INSERT INTO students (name,email,age)
VALUES ('Rahul','rahul@email.com',23);
USE devika_db;
SELECT * FROM students;
CREATE TABLE menus (
menu_id INT AUTO_INCREMENT PRIMARY KEY,
menu_date DATE,
breakfast VARCHAR(100),
lunch VARCHAR(100),
dinner VARCHAR(100)
);
CREATE TABLE menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_item VARCHAR(100),
vote_date DATE
);
SHOW TABLES;
CREATE TABLE food_waste_logs (
log_id INT AUTO_INCREMENT PRIMARY KEY,
menu_id INT,
waste_quantity INT,
log_date DATE
);
SHOW TABLES;
INSERT INTO students (name,email,age) VALUES
('Devika','devika@email.com',21),
('Rahul','rahul@email.com',22),
('Anita','anita@email.com',20),
('Arjun','arjun@email.com',21),
('Priya','priya@email.com',22);
SELECT * FROM students;
INSERT INTO menus (menu_name) VALUES
('Veg Biryani'),
('Fried Rice'),
('Paneer Curry'),
('Dal Tadka'),
('Masala Dosa');
SELECT * FROM menus;
INSERT INTO menus (menu_date, breakfast, lunch, dinner)
VALUES
('2026-03-06','Idli','Veg Biryani','Chapati'),
('2026-03-07','Dosa','Fried Rice','Paneer Curry'),
('2026-03-08','Upma','Dal Tadka','Masala Dosa');
SELECT * FROM menus;
INSERT INTO menu_votes (student_id, menu_id, vote_date) VALUES
(1,1,CURDATE()),
(2,1,CURDATE()),
(3,2,CURDATE()),
(4,1,CURDATE()),
(5,3,CURDATE());
DESCRIBE menu_votes;
INSERT INTO menu_votes (student_id, menu_item, vote_date)
VALUES
(1,'Veg Biryani',CURDATE()),
(2,'Veg Biryani',CURDATE()),
(3,'Fried Rice',CURDATE()),
(4,'Veg Biryani',CURDATE()),
(5,'Dal Tadka',CURDATE());
SELECT * FROM menu_votes;
INSERT INTO food_waste_logs (menu_id, waste_quantity, log_date)
VALUES
(1,4,CURDATE()),
(2,2,CURDATE()),
(3,6,CURDATE());
SELECT * FROM food_waste_logs;
SELECT menu_item, COUNT(*) AS total_votes
FROM menu_votes
GROUP BY menu_item
ORDER BY total_votes DESC;
SELECT m.menu_date, SUM(w.waste_quantity) AS total_waste
FROM food_waste_logs w
JOIN menus m ON w.menu_id = m.menu_id
GROUP BY m.menu_date
ORDER BY total_waste DESC;
SELECT 
  v.menu_item,
  COUNT(v.vote_id) AS total_votes,
  COALESCE(SUM(w.waste_quantity),0) AS total_waste
FROM menu_votes v
LEFT JOIN menus m 
  ON v.vote_date = m.menu_date
LEFT JOIN food_waste_logs w 
  ON m.menu_id = w.menu_id
GROUP BY v.menu_item
ORDER BY total_votes DESC, total_waste ASC;
CREATE TABLE menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_id INT,
vote_date DATE,
FOREIGN KEY (menu_id) REFERENCES menus(menu_id)
);
DROP TABLE menu_votes;

CREATE TABLE menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_id INT,
vote_date DATE,
FOREIGN KEY (menu_id) REFERENCES menus(menu_id)
);
INSERT INTO menu_votes (student_id, menu_id, vote_date)
VALUES
(1,1,CURDATE()),
(2,1,CURDATE()),
(3,2,CURDATE()),
(4,1,CURDATE()),
(5,3,CURDATE());
SELECT * FROM menu_votes;
USE devika_db;
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    hostel VARCHAR(50)
);
SHOW TABLES;
DESCRIBE students;
DROP TABLE students;
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    hostel VARCHAR(50)
);
DESCRIBE students;
CREATE TABLE polls (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255),
    option1 VARCHAR(100),
    option2 VARCHAR(100),
    option3 VARCHAR(100),
    option4 VARCHAR(100),
    poll_date DATE
);
CREATE TABLE votes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    poll_id INT,
    selected_option VARCHAR(50),
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (poll_id) REFERENCES polls(id)
);
SHOW TABLES;
DESCRIBE votes;
SELECT * FROM votes;
SELECT * FROM students;
SELECT * FROM polls;
INSERT INTO students(name,email,password,hostel)
VALUES ('Devika','devika@email.com','123','A-Block');
SELECT * FROM students;
SELECT * FROM votes;
SELECT * FROM votes;
SELECT * FROM students;
SELECT * FROM polls;
SELECT * FROM students;
DESCRIBE votes;
DELETE FROM votes WHERE student_id=1 AND poll_id=1;
SELECT * FROM votes;
INSERT INTO students(name,email,password,hostel)
VALUES ('Rahul','rahul@email.com','123','A-block');
INSERT INTO students(name,email,password,hostel)
VALUES ('Anjali','anjali@email.com','123','B-block');
SELECT * FROM students;