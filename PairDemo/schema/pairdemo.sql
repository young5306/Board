DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

USE ssafy_board;

-- CREATE TABLE board (
-- 	board_id INT AUTO_INCREMENT,
--     writer VARCHAR(20) NOT NULL,
--     title VARCHAR(50) NOT NULL,
--     content TEXT,
--     img VARCHAR(255),
--     view_cnt INT DEFAULT 0,
--     reg_date TIMESTAMP DEFAULT now(),
--     PRIMARY KEY(board_id)
-- );


CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  #profile_img
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `habit` (
  `habit_id` INT AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `content` varchar(40) NOT NULL,
  CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
  PRIMARY KEY (`habit_id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `record` (
  `record_id` INT AUTO_INCREMENT,
  `user_id` varchar(40) NOT NULL,
  `habit_id` INT NOT NULL,
  `date` date NOT NULL,
  CONSTRAINT `user_fk2` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
  CONSTRAINT `habit_fk` FOREIGN KEY (`habit_id`) REFERENCES `habit`(`habit_id`),
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB;

INSERT INTO user VALUES ("id1", "홍길동", "1234", "hong@naver.com");
INSERT INTO user VALUES ("id2", "아무개", "1234", "ah@naver.com");

INSERT INTO habit (user_id, content) 
VALUES ("id1", "독서"),
	   ("id1", "운동"),
       ("id1", "물2L"),
       ("id2", "독서"),
       ("id2", "알고리즘");

INSERT INTO `record` (user_id, habit_id, date) 
VALUES ("id1", 1, '2024-11-01'), ("id1", 1, '2024-11-02'), ("id1", 1, '2024-11-04'),
("id1", 2, '2024-11-03'), ("id1", 2, '2024-11-04'),
("id2", 1, '2024-11-01'), ("id2", 1, '2024-11-02'),
("id2", 2, '2024-11-02'), ("id1", 2, '2024-11-03');

-- CREATE TABLE board_img (
-- 	img_id INT AUTO_INCREMENT,
--     board_id INT,
--     file_name VARCHAR(255),
--     FOREIGN KEY (`board_id`) REFERENCES `board`(`board_id`) ON DELETE CASCADE, -- 부모 레코드 삭제 시 자식 레코드도 삭제
--     PRIMARY KEY(img_id)
-- );
-- INSERT INTO board_img(board_id, file_name) VALUES ("1", "zzang.png");
-- INSERT INTO board_img(board_id, file_name) VALUES ("2", "zzang.png");

commit;

-- SELECT * FROM user;