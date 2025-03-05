-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema webquiz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webquiz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webquiz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `webquiz` ;

-- -----------------------------------------------------
-- Table `webquiz`.`subjects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`subjects` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`subjects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `subject_text` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_4skjhivae050jwcbys53vfqth` (`subject_text` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`chapters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`chapters` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`chapters` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `chapter_name` VARCHAR(255) NOT NULL,
  `subject_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_3v44s1yv6sgyv6mhn45ie4b5` (`chapter_name` ASC) VISIBLE,
  INDEX `FK3rm6snrkx0k8xyqn7017b0v41` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `FK3rm6snrkx0k8xyqn7017b0v41`
    FOREIGN KEY (`subject_id`)
    REFERENCES `webquiz`.`subjects` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`questions` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`questions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `level` ENUM('HARD', 'MEDIUM', 'EASY') NULL DEFAULT NULL,
  `question_text` VARCHAR(255) NULL DEFAULT NULL,
  `chapter_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKd1wulherkir0s9abbqr195fr4` (`chapter_id` ASC) VISIBLE,
  CONSTRAINT `FKd1wulherkir0s9abbqr195fr4`
    FOREIGN KEY (`chapter_id`)
    REFERENCES `webquiz`.`chapters` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`answers` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `answer_text` VARCHAR(255) NOT NULL,
  `correct_answer` BIT(1) NULL DEFAULT NULL,
  `question_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id` ASC) VISIBLE,
  CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1`
    FOREIGN KEY (`question_id`)
    REFERENCES `webquiz`.`questions` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`quizs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`quizs` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`quizs` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `is_time` BIT(1) NOT NULL,
  `status` BIT(1) NOT NULL,
  `time_quiz` VARCHAR(255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `total_marks` INT NOT NULL,
  `total_question` INT NOT NULL,
  `quizzes` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKd0wm8hwr5siodqiygsax2qx5q` (`quizzes` ASC) VISIBLE,
  CONSTRAINT `FKd0wm8hwr5siodqiygsax2qx5q`
    FOREIGN KEY (`quizzes`)
    REFERENCES `webquiz`.`chapters` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`users` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `full_name` VARCHAR(255) NULL DEFAULT NULL,
  `gender` ENUM('MALE', 'FEMALE', 'OTHER') NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'INACTIVE', 'NONE') NULL DEFAULT NULL,
  `user_name` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  `user_img` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE,
  UNIQUE INDEX `UK_du5v5sr43g5bfnji4vb8hg5s3` (`phone` ASC) VISIBLE,
  UNIQUE INDEX `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`quiz_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`quiz_result` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`quiz_result` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `end_quiz` DATETIME(6) NULL DEFAULT NULL,
  `page_turns` INT NULL DEFAULT NULL,
  `score` DOUBLE NULL DEFAULT NULL,
  `start_quiz` DATETIME(6) NULL DEFAULT NULL,
  `submit_at` DATETIME(6) NULL DEFAULT NULL,
  `total_of_correct` INT NULL DEFAULT NULL,
  `total_of_failed` INT NULL DEFAULT NULL,
  `total_of_skip` INT NULL DEFAULT NULL,
  `quiz_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7xym5c9faqdtvyno2kbgc6s3m` (`quiz_id` ASC) VISIBLE,
  INDEX `FKnuben697e3lorbhgsbif22f2t` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK7xym5c9faqdtvyno2kbgc6s3m`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `webquiz`.`quizs` (`id`),
  CONSTRAINT `FKnuben697e3lorbhgsbif22f2t`
    FOREIGN KEY (`user_id`)
    REFERENCES `webquiz`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`answers_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`answers_user` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`answers_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `answer_text` VARCHAR(255) NULL DEFAULT NULL,
  `is_correct` BIT(1) NULL DEFAULT NULL,
  `quiz_result_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKs3qobbwv7q0luk0rhwc4v6t4w` (`quiz_result_id` ASC) VISIBLE,
  CONSTRAINT `FKs3qobbwv7q0luk0rhwc4v6t4w`
    FOREIGN KEY (`quiz_result_id`)
    REFERENCES `webquiz`.`quiz_result` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`lobbies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`lobbies` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`lobbies` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `code_invite` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `total_user` INT NULL DEFAULT NULL,
  `user_create_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`group_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`group_user` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`group_user` (
  `group_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  INDEX `FKrqeo92wyuy7jcc54mfbln3wme` (`user_id` ASC) VISIBLE,
  INDEX `FK173ps0n9cms0domhpnlvsqg0b` (`group_id` ASC) VISIBLE,
  CONSTRAINT `FK173ps0n9cms0domhpnlvsqg0b`
    FOREIGN KEY (`group_id`)
    REFERENCES `webquiz`.`lobbies` (`id`),
  CONSTRAINT `FKrqeo92wyuy7jcc54mfbln3wme`
    FOREIGN KEY (`user_id`)
    REFERENCES `webquiz`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`invalid_token`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`invalid_token` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`invalid_token` (
  `id` VARCHAR(255) NOT NULL,
  `expire_time` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`notifications` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`notifications` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `context` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `group_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK99vlshxha54y57qgp3dd9dkov` (`group_id` ASC) VISIBLE,
  INDEX `FK9y21adhxn0ayjhfocscqox7bh` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK99vlshxha54y57qgp3dd9dkov`
    FOREIGN KEY (`group_id`)
    REFERENCES `webquiz`.`lobbies` (`id`),
  CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh`
    FOREIGN KEY (`user_id`)
    REFERENCES `webquiz`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`permissions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`permissions` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`permissions` (
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`quiz_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`quiz_detail` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`quiz_detail` (
  `quiz_id` BIGINT NOT NULL,
  `question_id` BIGINT NOT NULL,
  PRIMARY KEY (`quiz_id`, `question_id`),
  INDEX `FK3dc3y7q28ngjd72k5e6q9gq9c` (`question_id` ASC) VISIBLE,
  CONSTRAINT `FK3dc3y7q28ngjd72k5e6q9gq9c`
    FOREIGN KEY (`question_id`)
    REFERENCES `webquiz`.`questions` (`id`),
  CONSTRAINT `FKj1uvjgddt4r8j08qxtus7078p`
    FOREIGN KEY (`quiz_id`)
    REFERENCES `webquiz`.`quizs` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`roles` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`role_permission` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`role_permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `update_at` DATETIME(6) NULL DEFAULT NULL,
  `object_type` VARCHAR(255) NULL DEFAULT NULL,
  `permission_name` VARCHAR(255) NULL DEFAULT NULL,
  `role_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKlvoulw1hiq60nj3016oe7gljj` (`permission_name` ASC) VISIBLE,
  INDEX `FKtfgq8q9blrp0pt1pvggyli3v9` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKlvoulw1hiq60nj3016oe7gljj`
    FOREIGN KEY (`permission_name`)
    REFERENCES `webquiz`.`permissions` (`name`),
  CONSTRAINT `FKtfgq8q9blrp0pt1pvggyli3v9`
    FOREIGN KEY (`role_id`)
    REFERENCES `webquiz`.`roles` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `webquiz`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webquiz`.`user_role` ;

CREATE TABLE IF NOT EXISTS `webquiz`.`user_role` (
  `user_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx`
    FOREIGN KEY (`user_id`)
    REFERENCES `webquiz`.`users` (`id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q`
    FOREIGN KEY (`role_id`)
    REFERENCES `webquiz`.`roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
