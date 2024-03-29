use enjoytrip;

Drop table if exists trip_member;
CREATE TABLE trip_member (
                             user_id       VARCHAR(20),
                             user_name     VARCHAR(20),
                             user_nickname	VARCHAR(20),
                             user_password    VARCHAR(20),
                             user_email		VARCHAR(20),
                             token			VARCHAR(500) NULL,
                             PRIMARY KEY(user_id)
);

Drop table if exists free_board;
CREATE TABLE `free_board` (
                              `article_no` int NOT NULL AUTO_INCREMENT,
                              `user_id` varchar(16) DEFAULT NULL,
                              `subject` varchar(100) DEFAULT NULL,
                              `content` varchar(1000) DEFAULT NULL,
                              `article_hit` int DEFAULT '1',
                              `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

Drop table if exists plan_board;
CREATE TABLE `plan_board` (
                              `article_no` int NOT NULL AUTO_INCREMENT,
                              `user_id` varchar(16) DEFAULT NULL,
                              `article_title` varchar(100) DEFAULT NULL,
                              `article_content` VARCHAR(500) DEFAULT NULL,
                              `article_hit` int DEFAULT '1',
                              `bookmark_cnt` int DEFAULT '0',
                              `plan_no`    int NOT NULL UNIQUE,
                              `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

drop table if exists review;
create table review
(
    article_no int          not null,
    content_id int          not null,
    content    varchar(200) null,
    primary key (article_no, content_id)
);

Drop table if exists plan_list;
CREATE TABLE plan_list (
                           user_id         VARCHAR(20),
                           plan_no         int NOT NULL AUTO_INCREMENT,
                           plan_title        VARCHAR(100),
                           `update_time`   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY(plan_no, user_id)
);

Drop table if exists plan_seq;
CREATE TABLE plan_seq (
                          plan_no       int,
                          plan_seq        int,
                          content_id    int,
                          PRIMARY KEY(plan_no, plan_seq)
);

Drop table if exists bookmark;
CREATE TABLE bookmark (
                          user_id         VARCHAR(20),
                          plan_no        int,
                          PRIMARY KEY(user_id, plan_no)
);

Drop table if exists file_info;
CREATE TABLE file_info (
                           idx int not null auto_increment,
                           article_no int NULL,
                           save_folder VARCHAR(45) NULL,
                           original_file VARCHAR(50) NULL,
                           save_file VARCHAR(50) NULL,
                           PRIMARY KEY (`idx`)
);

drop table if exists contents;
CREATE TABLE contents (
                          content_type_id    int,
                          content_name       VARCHAR(20),
                          PRIMARY KEY(content_type_id)
);

INSERT INTO trip_member (user_id, user_name, user_nickname, user_password, user_email)
VALUES ("ssafy", "송싸피", "싸피", "1234", "email@domain.com"),
		("testId", "이름", "닉네임", "1234", "이메일@도메인.com");

INSERT INTO contents (content_type_id, content_name)
VALUES  (12, "관광지"),
        (14, "문화시설"),
        (15, "축제공연행사"),
        (25, "여행코스"),
        (28, "레포츠"),
        (32, "숙박"),
        (38, "쇼핑"),
        (39, "음식점");