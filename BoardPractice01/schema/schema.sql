DROP DATABASE IF EXISTS ssafy_board;
CREATE DATABASE ssafy_board DEFAULT CHARACTER SET utf8mb4;

USE ssafy_board;

CREATE TABLE board (
	board_id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    img VARCHAR(255),
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
  `curriculum_code` INT DEFAULT 0,
  #CONSTRAINT `curriculum_fk` FOREIGN KEY (`curriculum_code`) REFERENCES `curriculum`(`code`),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

INSERT INTO board(title, writer, content) 
VALUES ("아직은 괜찮아1","양띵균","Spring Boot 아직은 괜찮아~~~"),
	   ("아직은 괜찮아2","양띵균","Vue.js 곧 배운다. 커밍쑨~~"),
       ("이제는 알아야해1", "양띵균", "");

INSERT INTO `curriculum` 
VALUES (100, 'Python'), (200, 'Java'), (300, 'Embedded'), (400, 'Mobile');

INSERT INTO user VALUES ("ssafy", "1234", "김싸피", 200);
INSERT INTO user VALUES ("admin", "1234", "관리자users", 200);


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