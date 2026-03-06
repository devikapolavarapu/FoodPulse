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
CREATE DATABASE IF NOT EXISTS devika_db;

USE devika_db;

CREATE TABLE IF NOT EXISTS students (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
email VARCHAR(100),
age INT
);

CREATE TABLE IF NOT EXISTS menus (
menu_id INT AUTO_INCREMENT PRIMARY KEY,
menu_date DATE,
breakfast VARCHAR(100),
lunch VARCHAR(100),
dinner VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS menu_votes (
vote_id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
menu_item VARCHAR(100),
vote_date DATE
);

CREATE TABLE food_waste_logs (
log_id INT AUTO_INCREMENT PRIMARY KEY,
menu_id INT,
waste_quantity INT,
log_date DATE
);
