-- -----------------------------------------------------
-- Schema badmintoncompetition
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `badmintoncompetition` ;

-- -----------------------------------------------------
-- Schema badmintoncompetition
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `badmintoncompetition` DEFAULT CHARACTER SET utf8 ;
USE `badmintoncompetition` ;

-- Set user for badmintoncompetition
DROP USER IF EXISTS 'user'@'localhost';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'userpw';
GRANT ALL ON badmintoncompetition.* to 'user'@'localhost';