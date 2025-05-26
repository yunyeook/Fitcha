DROP database IF EXISTS fitcha;
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
age INT CHECK (age >= 0),
gender VARCHAR(300),
follower_count INT DEFAULT 0 CHECK (follower_count >= 0), -- 팔로우 하는 사람 (나) 
following_count INT DEFAULT 0 CHECK (following_count >= 0), -- 팔로우 당하는 사람 (상대방)
profile_img_url VARCHAR(500) -- 프로필 이미지 경로 저장
);

select * from user_board;

CREATE TABLE user_file(
user_file_id INT PRIMARY KEY AUTO_INCREMENT,
user_board_id INT,
file_upload_name VARCHAR(3000) NOT NULL,
file_original_name VARCHAR(3000) NOT NULL,
file_url VARCHAR(3000) NOT NULL,
CONSTRAINT user_file_pk FOREIGN KEY (user_board_id) REFERENCES user_board (user_board_id)
ON DELETE CASCADE
);


-- challenge--
CREATE TABLE challenge_board(
challenge_board_id INT PRIMARY KEY AUTO_INCREMENT,
user_id VARCHAR(300) ,
title VARCHAR(300) NOT NULL,
content TEXT NOT NULL,
writer VARCHAR(300) NOT NULL,
exercise_type VARCHAR(300) NOT NULL DEFAULT '전체',
body_part VARCHAR(300) NOT NULL DEFAULT '전체',
`level` VARCHAR(300) NOT NULL DEFAULT '초급',
duration INT NOT NULL,
participant_count INT DEFAULT 1 CHECK (participant_count >= 1 AND participant_count <= 100),
total_participant_count INT DEFAULT 1 CHECK (total_participant_count >= 1 AND total_participant_count <= 100),
view_count INT DEFAULT 0 CHECK(view_count >= 0) ,
like_count INT DEFAULT 0 CHECK(like_count >= 0),
reg_date TIMESTAMP DEFAULT NOW(),
finish BOOLEAN NOT NULL DEFAULT FALSE,
subhead VARCHAR(1000) DEFAULT "",
CONSTRAINT challenge_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
ON DELETE CASCADE
);
CREATE TABLE challenge_file(
challenge_file_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
file_upload_name VARCHAR(3000) NOT NULL,
file_original_name VARCHAR(3000) NOT NULL,
file_url VARCHAR(3000) NOT NULL,
writer VARCHAR(3000) NOT NULL,
CONSTRAINT challenge_file_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id)
ON DELETE CASCADE 
);

-- proof --
CREATE TABLE proof_board(
proof_board_id INT PRIMARY KEY AUTO_INCREMENT,
challenge_board_id INT,
user_id VARCHAR(300) ,
title VARCHAR(300) NOT NULL,
content TEXT NOT NULL,
writer VARCHAR(300) NOT NULL,
view_count INT DEFAULT 0 CHECK (view_count >= 0),
like_count INT DEFAULT 0 CHECK (like_count >= 0),
reg_date TIMESTAMP DEFAULT NOW(), 
exercise_type VARCHAR(300) NOT NULL DEFAULT '전체',
body_part VARCHAR(300) NOT NULL DEFAULT '전체',
`level` VARCHAR(300) NOT NULL DEFAULT '초급',
CONSTRAINT proof_board_pk FOREIGN KEY (challenge_board_id) REFERENCES challenge_board (challenge_board_id) 
ON DELETE SET NULL,
CONSTRAINT proof_board_user_pk FOREIGN KEY (user_id) REFERENCES user_board(user_id)
ON DELETE SET NULL
);

select * from proof_board;


CREATE TABLE proof_board_hashtag (
    id INT PRIMARY KEY AUTO_INCREMENT,
    proof_board_id INT,
    hashtag VARCHAR(1000),
    CONSTRAINT fk_proof_board FOREIGN KEY (proof_board_id)
        REFERENCES proof_board(proof_board_id)
        ON DELETE CASCADE
);
select * from proof_board_hashtag;

CREATE TABLE proof_file(
proof_file_id INT PRIMARY KEY AUTO_INCREMENT,
proof_board_id INT,
file_upload_name VARCHAR(3000) NOT NULL,
file_original_name VARCHAR(3000) NOT NULL,
file_url VARCHAR(3000) NOT NULL,
writer VARCHAR(3000) NOT NULL,
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

CREATE TABLE fittube_comment(
comment_id INT PRIMARY KEY AUTO_INCREMENT,
video_id VARCHAR(300),
user_id VARCHAR(300) ,
content VARCHAR(300) NOT NULL,
writer VARCHAR(300) NOT NULL,
reg_date TIMESTAMP DEFAULT NOW()
);

-- 좋아요 -------------------------------------------------

CREATE TABLE challenge_like(
board_id INT,
writer varchar(300),
CONSTRAINT challenge_like_pk FOREIGN KEY (board_id) REFERENCES challenge_board (challenge_board_id)
ON DELETE CASCADE 
);

CREATE TABLE proof_like(
proof_board_id int,
writer varchar(300),
CONSTRAINT proof_like_pk FOREIGN KEY (proof_board_id) REFERENCES proof_board (proof_board_id) 
ON DELETE CASCADE 
);

CREATE TABLE fittube_like(
video_id varchar(300),
writer varchar(300)
);

-- 팔로우 ------------------

CREATE TABLE user_follow (
    follower_nick_name VARCHAR(300),  -- 팔로우하는 사람 (나)
    following_nick_name VARCHAR(300), -- 팔로우당하는 사람 (상대)
    PRIMARY KEY (follower_nick_name, following_nick_name),
    CONSTRAINT fk_follower FOREIGN KEY (follower_nick_name) REFERENCES user_board(nick_name) ON DELETE CASCADE,
    CONSTRAINT fk_following FOREIGN KEY (following_nick_name) REFERENCES user_board(nick_name) ON DELETE CASCADE
);



-------- 메세지 ---
CREATE TABLE message(
message_id INT PRIMARY KEY AUTO_INCREMENT,
sender VARCHAR(300) NOT NULL, -- 보낸 사람 --
recipient VARCHAR(300) NOT NULL, -- 받는 사람 --
title VARCHAR(300) NOT NULL, 
content TEXT NOT NULL,
who_delete VARCHAR(50) DEFAULT 'nobody',
is_read BOOLEAN DEFAULT FALSE -- TRUE = 1, FALSE = 0 으로 저장된다함.. --
);

-- 유저들의 참여중인 챌린지 저장 --
CREATE TABLE participant_challenge(
challenge_board_id INT,
participant VARCHAR(300) NOT NULL 
);

-- 더미 데이터----------------------------------------------------------------------

-- 1. user_board (2명)
INSERT INTO user_board (user_id, password, email, name, nick_name, age, gender)
VALUES
('fituser1', 'pass1234', 'fituser1@example.com', '홍길동', '길동이', 27, 'M'),
('fituser2', 'pass5678', 'fituser2@example.com', '김영희', '영희짱', 24, 'F'),
('fituser3', 'pass9012', 'fituser3@example.com', '이철수', '철수킹', 29, 'M'),
('fituser4', 'pass3456', 'fituser4@example.com', '박민지', '민지짱', 22, 'F'),
('fituser5', 'pass7890', 'fituser5@example.com', '정우성', '우성이형', 35, 'M'),
('fituser6', 'pass2345', 'fituser6@example.com', '한소희', '소희누나', 26, 'F'),
('fituser7', 'pass6789', 'fituser7@example.com', '장기용', '기용맨', 31, 'M'),
('fituser8', 'pass1122', 'fituser8@example.com', '김지원', '지원이', 28, 'F'),
('fituser9', 'pass3344', 'fituser9@example.com', '최준혁', '준혁쓰', 23, 'M'),
('fituser10', 'pass5566', 'fituser10@example.com', '윤지은', '지은짱', 30, 'F');



-- 2. challenge_board (10개)
INSERT INTO challenge_board (user_id, title, content, writer, exercise_type, body_part, level, duration, total_participant_count)
VALUES
('fituser1', '아침 스트레칭 챌린지', '매일 아침 10분 스트레칭 도전!', '길동이', '스트레칭', '전신', '초급',2,10),
('fituser2', '하루 만보 걷기', '매일 10,000보 걷기 인증 도전해요.', '영희짱', '걷기', '하체', '초급', 7,20),
('fituser1', '홈트 30일 챌린지', '집에서 간단히 하는 운동 모음입니다.', '길동이', '근력운동', '상체', '중급',10,30),
('fituser2', '플랭크 1분 유지하기', '매일 플랭크 1분씩 버텨봅시다.', '영희짱', '코어운동', '복근', '초급', 20,15),
('fituser1', '자전거 타기 챌린지', '하루 5km 자전거 타기!', '길동이', '유산소', '하체', '초급', 30,15),
('fituser2', '줄넘기 100개씩 하기', '줄넘기 매일 100개 도전!', '영희짱', '유산소', '전신', '초급', 7,15),
('fituser1', '상체 집중 운동', '상체 근력 강화 목표입니다.', '길동이', '근력운동', '상체', '중급', 10,10),
('fituser2', '저녁 러닝 챌린지', '저녁마다 러닝 3km!', '영희짱', '러닝', '하체', '중급', 30,15),
('fituser1', '요가 초급 챌린지', '하루 15분 요가로 몸 풀어요.', '길동이', '요가', '전신', '초급', 20,25),
('fituser2', '하체 강화 스쿼트', '스쿼트 50개씩 매일 하기!', '영희짱', '근력운동', '하체', '초급',10,20);

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
(10, 'fituser2', '하체 강화 도전!', '스쿼트 50개 완료!', '영희짱'),
(10, 'fituser1', '하체 강화 도전!', '스쿼트 50개 완료!', '길동이');

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
(10, 'squat_proof.jpg', 'squat_proof.jpg', '/static/file/proof/squat_proof.jpg', '영희짱'),
(10, 'squat_proof.jpg', 'squat_proof.jpg', '/static/file/proof/squat_proof.jpg', '길동이');

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

 
 -- 메세지 --
-- 길동 ↔ 영희짱
INSERT INTO message (sender, recipient, title, content, who_delete, is_read) VALUES
('길동이', '영희짱', '안녕하세요', '처음 연락드립니다. 잘 부탁드려요.', 'nobody', FALSE),
('영희짱', '길동이', 'Re: 안녕하세요', '반가워요, 길동님!', 'nobody', TRUE),
('길동이', '영희짱', '회의 일정 확인', '오늘 오후 2시 회의 괜찮으신가요?', 'nobody', FALSE),
('영희짱', '길동이', 'Re: 회의 일정 확인', '좋아요. 시간 맞춰서 들어갈게요.', 'nobody', TRUE);

-- 길동 ↔ 냥냥이
INSERT INTO message (sender, recipient, title, content, who_delete, is_read) VALUES
('길동이', '냥냥이', '업무 문의', '냥냥이님, 이 부분 확인 부탁드립니다.', 'nobody', FALSE),
('냥냥이', '길동이', 'Re: 업무 문의', '확인했습니다. 수정해서 다시 전달드릴게요.', 'nobody', TRUE),
('냥냥이', '길동이', '간단한 질문', '길동님, 어제 내용 중 궁금한 게 있어요.', '길동', FALSE),
('길동이', '냥냥이', 'Re: 간단한 질문', '네, 전화로 설명드릴게요.', 'nobody', TRUE);

-- 영희짱 ↔ 냥냥이
INSERT INTO message (sender, recipient, title, content, who_delete, is_read) VALUES
('영희짱', '냥냥이', '자료 요청', '금일 회의 자료 요청드립니다.', 'nobody', FALSE),
('냥냥이', '영희짱', 'Re: 자료 요청', '방금 전달드렸습니다.', 'nobody', TRUE),
('영희짱', '냥냥이', '회의 취소 안내', '내일 회의는 취소되었습니다.', '영희짱', FALSE),
('냥냥이', '영희짱', '확인 완료', '회의 취소 건 확인했습니다.', 'nobody', TRUE);

-- participant_challenge 더미 데이터 
INSERT INTO participant_challenge (challenge_board_id, participant) VALUES
(1, '길동이'),
(2, '영희짱'),
(3, '길동이'),
(4, '영희짱'),
(5, '길동이'),
(6, '영희짱'),
(7, '길동이'),
(8, '영희짱'),
(9, '길동이'),
(10, '영희짱');
 
-- 좋아요 트리거 ---------------------------------------------------
 
DROP TRIGGER IF EXISTS trg_increase_like_count;

DELIMITER $$
DROP TRIGGER IF EXISTS trg_increase_challenge_like_count;
DELIMITER $$
CREATE TRIGGER trg_increase_challenge_like_count
AFTER INSERT ON challenge_like
FOR EACH ROW
BEGIN
  UPDATE challenge_board
  SET like_count = like_count + 1
  WHERE challenge_board_id = NEW.board_id;
END $$
DELIMITER ;


DROP TRIGGER IF EXISTS trg_decrease_challenge_like_count;
DELIMITER $$
CREATE TRIGGER trg_decrease_challenge_like_count
AFTER DELETE ON challenge_like
FOR EACH ROW
BEGIN
  UPDATE challenge_board
  SET like_count = like_count - 1
  WHERE challenge_board_id = OLD.board_id;
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trg_increase_proof_like_count
AFTER INSERT ON proof_like
FOR EACH ROW
BEGIN UPDATE proof_board
		   SET like_count = like_count+1
		WHERE proof_board_id = New.proof_board_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_decrease_proof_like_count
AFTER DELETE ON proof_like
FOR EACH ROW
BEGIN UPDATE proof_board
		   SET like_count = like_count-1
		WHERE proof_board_id = OLD.proof_board_id;
END $$
DELIMITER ;

-- 팔로우 트리거 ----------------------------------
DROP TRIGGER IF EXISTS trg_increase_follow_count;
DROP TRIGGER IF EXISTS trg_decrease_follow_count;

DELIMITER  $$
CREATE TRIGGER trg_increase_user_follow_count -- 팔로우 시 증가 트리거 
AFTER INSERT ON user_follow
FOR EACH ROW 
BEGIN 
	UPDATE user_board SET following_count = following_count + 1
    WHERE nick_name = NEW.follower_nick_name;
    
    UPDATE user_board SET follower_count = follower_count + 1
    WHERE nick_name = NEW.following_nick_name;
END $$
DELIMITER ;

DELIMITER  $$
CREATE TRIGGER trg_decrease_user_follow_count -- 팔로우 시 감소 트리거 
AFTER DELETE ON user_follow
FOR EACH ROW 
BEGIN 
	UPDATE user_board SET following_count = following_count - 1
    WHERE nick_name = OLD.follower_nick_name;
    
    UPDATE user_board SET follower_count = follower_count - 1
    WHERE nick_name = OLD.following_nick_name;
END $$
DELIMITER ;


