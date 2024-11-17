DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;
USE ssafy_board;

CREATE TABLE `User` (
	`user_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255)	NOT NULL,
	`email`	VARCHAR(255) NOT NULL UNIQUE,
	`profile_img` VARCHAR(255) DEFAULT NULL, 
	`refresh_token`	VARCHAR(255) NOT NULL,
	`social_type` ENUM('ZERO', 'KAKAO') NOT NULL
);

CREATE TABLE `Plan` (
	`plan_id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`start_date` TIMESTAMP NOT NULL,
	`end_date` TIMESTAMP NOT NULL,
	`title`	VARCHAR(100) NOT NULL,
	`content` TEXT NOT NULL,
	`location` VARCHAR(255) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- TIMESTAMP DEFAULT now() 가능 
	`updated_at` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`is_shared`	TINYINT NOT NULL,
    PRIMARY KEY(plan_id),
    CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

CREATE TABLE `Files` (
	`file_id` VARCHAR(255) NOT NULL PRIMARY KEY, -- 고유한 이름(이대로 저장하면 확장자 날라감)
	`plan_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`file_name`	VARCHAR(255) NOT NULL, -- 실제파일 이름
	FOREIGN KEY (`plan_id`) REFERENCES `Plan` (`plan_id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE
    -- id와 name 나눔 
    -- id : UUID(128비트)+내부적 처리로 유니크한 id 만들 수 있음
    -- UUID는 사용자에게 노출하지 X (보기 싫게 생겨서) -> name 따로 사용
);

CREATE TABLE `Likes` (
	`like_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id` INT NOT NULL,
	`plan_id` INT NOT NULL,
	FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`plan_id`) REFERENCES `Plan` (`plan_id`) ON DELETE CASCADE
);

CREATE TABLE `SearchCondition` (
	`key` VARCHAR(255) NULL	COMMENT 'title, content, location, is_boolean',
	`word` VARCHAR(255)	NULL,
	`orderBy` VARCHAR(255) NULL	COMMENT '좋아요순',
	`orderByDir` VARCHAR(255) NULL COMMENT 'asc, desc'
	-- gpt 추천
    -- `orderBy` ENUM('title', 'content', 'location', 'is_boolean') NULL COMMENT '검색 기준',
	-- `orderByDir` ENUM('asc', 'desc') NULL COMMENT '정렬 방향'
);


INSERT INTO `User` (`username`, `password`, `email`, `profile_img`, `refresh_token`, `social_type`)
VALUES ('user1', '1234', 'user1@example.com', 'https://example.com/user1.jpg', 'refresh_token_123', 'KAKAO'),
('user2', '1234', 'user2@example.com', 'https://example.com/user2.jpg', 'refresh_token_456', 'KAKAO'),
('user3', '1234', 'user3@example.com', 'https://example.com/user3.jpg', 'refresh_token_789', 'ZERO');
