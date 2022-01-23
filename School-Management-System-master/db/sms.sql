-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema school_managment_sys_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema school_managment_sys_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `school_managment_sys_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `school_managment_sys_db` ;

-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`admin` (
  `admin_id` INT NOT NULL AUTO_INCREMENT,
  `admin_name` VARCHAR(45) NOT NULL,
  `admin_password` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE INDEX `admin_id_UNIQUE` (`admin_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`grade` (
  `gradeId` INT NOT NULL,
  PRIMARY KEY (`gradeId`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_type` VARCHAR(100) NOT NULL,
  `user_name` VARCHAR(100) NOT NULL,
  `user_email` VARCHAR(100) NOT NULL,
  `user_contact` VARCHAR(50) NOT NULL,
  `user_gender` VARCHAR(100) NULL DEFAULT NULL,
  `user_nic` VARCHAR(100) NOT NULL,
  `user_dbo` VARCHAR(50) NOT NULL,
  `user_doa` VARCHAR(50) NOT NULL,
  `user_address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`teacher` (
  `teacher_id` INT NOT NULL,
  PRIMARY KEY (`teacher_id`),
  INDEX `fk_teacher_user1_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_teacher_user1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school_managment_sys_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`grade_has_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`grade_has_teacher` (
  `teacher_id` INT NOT NULL,
  `grade_gradeId` INT NOT NULL,
  PRIMARY KEY (`teacher_id`, `grade_gradeId`),
  INDEX `fk_teacher_has_grade_teacher1_idx` (`teacher_id` ASC) VISIBLE,
  INDEX `fk_grade_has_teacher_grade1_idx` (`grade_gradeId` ASC) VISIBLE,
  CONSTRAINT `fk_grade_has_teacher_grade1`
    FOREIGN KEY (`grade_gradeId`)
    REFERENCES `school_managment_sys_db`.`grade` (`gradeId`),
  CONSTRAINT `fk_teacher_has_grade_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school_managment_sys_db`.`teacher` (`teacher_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`principal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`principal` (
  `principal_id` INT NOT NULL,
  PRIMARY KEY (`principal_id`),
  INDEX `fk_principal_user1_idx` (`principal_id` ASC) VISIBLE,
  CONSTRAINT `fk_principal_user1`
    FOREIGN KEY (`principal_id`)
    REFERENCES `school_managment_sys_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`school_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`school_info` (
  `school_name` VARCHAR(45) NOT NULL,
  `school_address` VARCHAR(45) NOT NULL,
  `class_available` INT NULL DEFAULT NULL,
  `school_contact` VARCHAR(45) NOT NULL,
  `school_type` VARCHAR(45) NULL DEFAULT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `principal_id` INT NOT NULL,
  PRIMARY KEY (`principal_id`),
  UNIQUE INDEX `postal_code_UNIQUE` (`postal_code` ASC) VISIBLE,
  CONSTRAINT `fk_school_info_principal1`
    FOREIGN KEY (`principal_id`)
    REFERENCES `school_managment_sys_db`.`principal` (`principal_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `school_managment_sys_db`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school_managment_sys_db`.`student` (
  `student_id` INT NOT NULL,
  `grade_gradeId` INT NOT NULL,
  INDEX `fk_student_user2_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_student_grade1_idx1` (`grade_gradeId` ASC) VISIBLE,
  CONSTRAINT `fk_student_grade1`
    FOREIGN KEY (`grade_gradeId`)
    REFERENCES `school_managment_sys_db`.`grade` (`gradeId`),
  CONSTRAINT `fk_student_user2`
    FOREIGN KEY (`student_id`)
    REFERENCES `school_managment_sys_db`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
