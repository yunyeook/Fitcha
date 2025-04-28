DROP database fitcha;
CREATE DATABASE IF NOT EXISTS fitcha;
USE fitcha;
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
file_upload_name VARCHAR(300) NOT NULL,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
CONSTRAINT user_file_pk FOREIGN KEY (user_board_id) REFERENCES user_board (user_board_id)
ON DELETE CASCADE
);

-- challenge--
CREATE TABLE challenge_board(
challenge_board_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(300) ,
title VARCHAR(300) NOT NULL,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
exercise_type VARCHAR(300) NOT NULL DEFAULT '전체',
body_part VARCHAR(300) NOT NULL DEFAULT '전체',
level VARCHAR(300) NOT NULL DEFAULT '초급',
duration TIMESTAMP NOT NULL,
participant_count INT DEFAULT 1 CHECK(participant_count BETWEEN 1 AND 100),
view_count INT DEFAULT 0,
like_count INT DEFAULT 0,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT challenge_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
ON DELETE CASCADE
);

CREATE TABLE challenge_file(
challenge_file_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
file_upload_name VARCHAR(300) NOT NULL,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
CONSTRAINT challenge_file_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id)
ON DELETE CASCADE 
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
CONSTRAINT proof_board_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id) 
ON DELETE SET NULL,
CONSTRAINT proof_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
ON DELETE SET NULL
);

CREATE TABLE proof_file(
proof_file_id INT PRIMARY KEY AUTO_INCREMENT,
proof_board_id INT,
file_upload_name VARCHAR(300) NOT NULL,
file_original_name VARCHAR(300) NOT NULL,
file_url VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
CONSTRAINT proof_file_pk FOREIGN KEY (proof_board_id) REFERENCES proof_board (proof_board_id) 
ON DELETE CASCADE 
);

-- comment --
CREATE TABLE challenge_comment (
challenge_comment_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
user_id VARCHAR(300) ,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT challenge_comment_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id) 
ON DELETE CASCADE ,
CONSTRAINT challenge_comment_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id) 
ON DELETE CASCADE 

);

CREATE TABLE proof_comment (
proof_comment_id INT PRIMARY KEY AUTO_INCREMENT,
proof_board_id INT,
user_id VARCHAR(300) ,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW(),
CONSTRAINT proof_comment_pk FOREIGN KEY (proof_board_id) REFERENCES proof_board (proof_board_id)
ON DELETE CASCADE,
CONSTRAINT proof_comment_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
ON DELETE CASCADE 
);

USE fitcha;

-- 1. user_board (2명)
INSERT INTO user_board (user_id, password, email, name, nick_name, age, gender)
VALUES
('fituser1', 'pass1234', 'fituser1@example.com', '홍길동', '길동이', 27, 'M'),
('fituser2', 'pass5678', 'fituser2@example.com', '김영희', '영희짱', 24, 'F');

-- 2. challenge_board (10개)
INSERT INTO challenge_board (user_id, title, content, writer, exercise_type, body_part, level, duration)
VALUES
('fituser1', '아침 스트레칭 챌린지', '매일 아침 10분 스트레칭 도전!', '길동이', '스트레칭', '전신', '초급', NOW()),
('fituser2', '하루 만보 걷기', '매일 10,000보 걷기 인증 도전해요.', '영희짱', '걷기', '하체', '초급', NOW()),
('fituser1', '홈트 30일 챌린지', '집에서 간단히 하는 운동 모음입니다.', '길동이', '근력운동', '상체', '중급', NOW()),
('fituser2', '플랭크 1분 유지하기', '매일 플랭크 1분씩 버텨봅시다.', '영희짱', '코어운동', '복근', '초급', NOW()),
('fituser1', '자전거 타기 챌린지', '하루 5km 자전거 타기!', '길동이', '유산소', '하체', '초급', NOW()),
('fituser2', '줄넘기 100개씩 하기', '줄넘기 매일 100개 도전!', '영희짱', '유산소', '전신', '초급', NOW()),
('fituser1', '상체 집중 운동', '상체 근력 강화 목표입니다.', '길동이', '근력운동', '상체', '중급', NOW()),
('fituser2', '저녁 러닝 챌린지', '저녁마다 러닝 3km!', '영희짱', '러닝', '하체', '중급', NOW()),
('fituser1', '요가 초급 챌린지', '하루 15분 요가로 몸 풀어요.', '길동이', '요가', '전신', '초급', NOW()),
('fituser2', '하체 강화 스쿼트', '스쿼트 50개씩 매일 하기!', '영희짱', '근력운동', '하체', '초급', NOW());

-- 3. challenge_file (10개)
INSERT INTO challenge_file (challenge_board_id, file_upload_name, file_original_name, file_url, writer)
VALUES
(1, 'morning_stretch.jpg', 'stretch.jpg', '/static/file/challenge/morning_stretch.jpg', '길동이'),
(2, 'walking_10000.jpg', 'walk.jpg', '/static/file/challenge/walking_10000.jpg', '영희짱'),
(3, 'home_training.jpg', 'home_training.jpg', '/static/file/challenge/home_training.jpg', '길동이'),
(4, 'plank_1min.jpg', 'plank.jpg', '/static/file/challenge/plank_1min.jpg', '영희짱'),
(5, 'cycling_challenge.jpg', 'cycling.jpg', '/static/file/challenge/cycling_challenge.jpg', '길동이'),
(6, 'jump_rope.jpg', 'jumprope.jpg', '/static/file/challenge/jump_rope.jpg', '영희짱'),
(7, 'upper_body.jpg', 'upper.jpg', '/static/file/challenge/upper_body.jpg', '길동이'),
(8, 'evening_run.jpg', 'run.jpg', '/static/file/challenge/evening_run.jpg', '영희짱'),
(9, 'yoga_beginner.jpg', 'yoga.jpg', '/static/file/challenge/yoga_beginner.jpg', '길동이'),
(10, 'squat_training.jpg', 'squat.jpg', '/static/file/challenge/squat_training.jpg', '영희짱');

-- 4. proof_board (10개)
INSERT INTO proof_board (challenge_board_id, user_id, title, content, writer)
VALUES
(1, 'fituser1', '아침 스트레칭 완료!', '오늘도 기지개 크게 펴고 시작합니다!', '길동이'),
(2, 'fituser2', '만보 완료 인증', '발바닥이 얼얼하지만 기분은 상쾌해요!', '영희짱'),
(3, 'fituser1', '홈트 3일차', '팔이 덜덜 떨리지만 계속 합니다.', '길동이'),
(4, 'fituser2', '플랭크 1분 성공', '땀이 비오듯 흘렀어요!', '영희짱'),
(5, 'fituser1', '자전거 5km 탔어요', '오랜만에 상쾌한 라이딩.', '길동이'),
(6, 'fituser2', '줄넘기 성공', '중간에 끊겼지만 100개 채웠어요!', '영희짱'),
(7, 'fituser1', '상체 운동 완료', '오늘은 등 운동 추가했습니다.', '길동이'),
(8, 'fituser2', '러닝 3km', '해질녘 러닝 너무 좋네요.', '영희짱'),
(9, 'fituser1', '요가로 몸 풀기', '심신 안정되는 하루였어요.', '길동이'),
(10, 'fituser2', '하체 강화 도전!', '스쿼트 50개 완료!', '영희짱');

-- 5. proof_file (10개)
INSERT INTO proof_file (proof_board_id, file_upload_name, file_original_name, file_url, writer)
VALUES
(1, 'stretch_proof.jpg', 'stretch_proof.jpg', '/static/file/proof/stretch_proof.jpg', '길동이'),
(2, 'walk_proof.jpg', 'walk_proof.jpg', '/static/file/proof/walk_proof.jpg', '영희짱'),
(3, 'home_proof.jpg', 'home_proof.jpg', '/static/file/proof/home_proof.jpg', '길동이'),
(4, 'plank_proof.jpg', 'plank_proof.jpg', '/static/file/proof/plank_proof.jpg', '영희짱'),
(5, 'cycling_proof.jpg', 'cycling_proof.jpg', '/static/file/proof/cycling_proof.jpg', '길동이'),
(6, 'jumprope_proof.jpg', 'jumprope_proof.jpg', '/static/file/proof/jumprope_proof.jpg', '영희짱'),
(7, 'upper_proof.jpg', 'upper_proof.jpg', '/static/file/proof/upper_proof.jpg', '길동이'),
(8, 'running_proof.jpg', 'running_proof.jpg', '/static/file/proof/running_proof.jpg', '영희짱'),
(9, 'yoga_proof.jpg', 'yoga_proof.jpg', '/static/file/proof/yoga_proof.jpg', '길동이'),
(10, 'squat_proof.jpg', 'squat_proof.jpg', '/static/file/proof/squat_proof.jpg', '영희짱');

-- 6. challenge_comment (10개)
INSERT INTO challenge_comment (challenge_board_id, user_id, content, writer)
VALUES
(1, 'fituser2', '아침 스트레칭 하고 나니까 몸이 가벼워요!', '영희짱'),
(2, 'fituser1', '만보 걷기 생각보다 힘드네요ㅋㅋ', '길동이'),
(3, 'fituser2', '홈트 루틴 너무 좋아요, 추천합니다!', '영희짱'),
(4, 'fituser1', '플랭크 진짜 어렵네요ㅠㅠ 그래도 해냈어요!', '길동이'),
(5, 'fituser2', '자전거 타니까 기분 전환 제대로에요.', '영희짱'),
(6, 'fituser1', '줄넘기 너무 오랜만에 했더니 숨 넘어가는 줄.', '길동이'),
(7, 'fituser2', '상체 운동 덕분에 자세 교정됐어요.', '영희짱'),
(8, 'fituser1', '러닝 코스 추천받고 싶어요~', '길동이'),
(9, 'fituser2', '요가 초급 챌린지, 몸이 말랑말랑해졌어요.', '영희짱'),
(10, 'fituser1', '하체 강화 스쿼트! 도전 성공했습니다!', '길동이');

-- 7. proof_comment (10개)
INSERT INTO proof_comment (proof_board_id, user_id, content, writer)
VALUES
(1, 'fituser2', '스트레칭 하고 하루를 시작하니까 다르네요!', '영희짱'),
(2, 'fituser1', '걷기 정말 힘들었지만 해냈어요.', '길동이'),
(3, 'fituser2', '홈트 인증합니다! 화이팅!', '영희짱'),
(4, 'fituser1', '플랭크 하고 근육통 왔어요ㅠ', '길동이'),
(5, 'fituser2', '자전거 라이딩 최고입니다.', '영희짱'),
(6, 'fituser1', '줄넘기 100개 인증 완료!!', '길동이'),
(7, 'fituser2', '상체 운동 오늘도 성공!', '영희짱'),
(8, 'fituser1', '저녁 러닝 최고에요!', '길동이'),
(9, 'fituser2', '요가 덕분에 스트레스 해소됐어요.', '영희짱'),
(10, 'fituser1', '하체 스쿼트 챌린지, 완주했습니다!', '길동이');

