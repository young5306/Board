DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

USE ssafy_board;

CREATE TABLE board (
	board_id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY(board_id)
);

CREATE TABLE `curriculum` (
	`code` INT NOT NULL PRIMARY KEY,
    `name` VARCHAR(40) NOT NULL
)ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `curriculum_code` INT NOT NULL,
  CONSTRAINT `curriculum_fk` FOREIGN KEY (`curriculum_code`) REFERENCES `curriculum`(`code`),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

INSERT INTO board(title, writer, content) 
VALUES ("아직은 괜찮아1","양띵균","Spring Boot 아직은 괜찮아~~~"),
	   ("아직은 괜찮아2","양띵균","Vue.js 곧 배운다. 커밍쑨~~"),
       ("이제는 알아야해1", "양띵균", "");

INSERT INTO `curriculum` 
VALUES (100, 'Python'), (200, 'Java'), (300, 'Embedded'), (400, 'Mobile');

INSERT INTO user
VALUES ("ssafy", "1234", "김싸피", 200);


CREATE TABLE IF NOT EXISTS `files` (
  `file_id` VARCHAR(40) NOT NULL PRIMARY KEY, -- id와 name 나눔 (UUID(128비트)+내부적 처리로 유니크한 id 만들 수 있음)
  `file_name` VARCHAR(40) NOT NULL, -- UUID는 사용자에게 노출하지 X (보기 싫게 생겨서) -> name 따로 사용
  `board_id` INT NOT NULL,
  CONSTRAINT `board_fk` FOREIGN KEY(`board_id`) REFERENCES `board`(`board_id`)
); -- 게시글 하나당 파일 여러개 처리하기 위한 테이블

commit;

SELECT * FROM files;
