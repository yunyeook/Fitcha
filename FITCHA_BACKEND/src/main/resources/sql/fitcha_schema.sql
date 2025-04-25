CREATE DATABASE fitcha;
USE fitcha;

-- challenge--
CREATE TABLE challenge_board(
challenge_board_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(300) ,
title VARCHAR(300) NOT NULL,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
exercise_type VARCHAR(300) NOT NULL,
body_part VARCHAR(300) NOT NULL,
level VARCHAR(300) NOT NULL,
duration INT NOT NULL,
participant_count INT DEFAULT 1,
view_count INT DEFAULT 0,
like_count INT DEFAULT 0,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT challenge_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
);

CREATE TABLE challenge_file(
challenge_file_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
CONSTRAINT challenge_file_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id)

);

-- proof --
CREATE TABLE proof_board(
proof_board_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
user_id VARCHAR(300) ,
title VARCHAR(300) NOT NULL,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT proof_board_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id) ,
CONSTRAINT proof_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)

);

CREATE TABLE proof_file(
proof_file_id INT PRIMARY KEY AUTO_INCREMENT,
proof_board_id INT,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
CONSTRAINT proof_file_pk FOREIGN KEY (proof_board_id) REFERENCES proof_board (proof_board_id) 
);

-- comment --
CREATE TABLE challenge_comment (
challenge_comment_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
user_id VARCHAR(300) ,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT challenge_comment_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id) ,
CONSTRAINT challenge_comment_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)

);

CREATE TABLE proof_comment (
proof_comment_id INT PRIMARY KEY AUTO_INCREMENT,
proof_board_id INT,
user_id VARCHAR(300) ,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT proof_comment_pk FOREIGN KEY (proof_board_id) REFERENCES proof_board (proof_board_id),
CONSTRAINT proof_comment_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)

);

-- user --
CREATE TABLE user_board(
user_board_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(300) NOT NULL UNIQUE, -- 회원가입시 아이디 --
password VARCHAR(300) NOT NULL,
email VARCHAR(300) NOT NULL,
name VARCHAR(300) NOT NULL,
nick_name VARCHAR(300) NOT NULL UNIQUE,
age INT,
gender VARCHAR(300)
);

CREATE TABLE user_file(
user_file_id INT PRIMARY KEY AUTO_INCREMENT,
user_board_id INT,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
CONSTRAINT user_file_pk FOREIGN KEY (user_board_id) REFERENCES user_board (user_board_id) 

)



