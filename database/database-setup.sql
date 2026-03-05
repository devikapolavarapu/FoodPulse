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