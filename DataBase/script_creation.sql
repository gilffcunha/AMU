-- Lux App DataBase Script Creation

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema LuxDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LuxDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LuxDB` DEFAULT CHARACTER SET utf8 ;
USE `LuxDB` ;

-- -----------------------------------------------------
-- Table `LuxDB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuxDB`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LuxDB`.`Protocol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuxDB`.`Protocol` (
  `ID` INT NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  `Description` LONGTEXT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LuxDB`.`Experiment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuxDB`.`Experiment` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `AndroidVersion` VARCHAR(45) NOT NULL,
  `Brand` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `User_ID` INT NOT NULL,
  `Protocol_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Experiencia_Utilizador1_idx` (`User_ID` ASC),
  INDEX `fk_Experiment_Protocol1_idx` (`Protocol_ID` ASC),
  CONSTRAINT `fk_Experiencia_Utilizador1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `LuxDB`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Experiment_Protocol1`
    FOREIGN KEY (`Protocol_ID`)
    REFERENCES `LuxDB`.`Protocol` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LuxDB`.`Sample`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LuxDB`.`Sample` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Latitude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `Luminusity` DOUBLE NOT NULL,
  `Timestamp` TIMESTAMP NOT NULL,
  `Experiment_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Amostra_Experiencia_idx` (`Experiment_ID` ASC),
  CONSTRAINT `fk_Amostra_Experiencia`
    FOREIGN KEY (`Experiment_ID`)
    REFERENCES `LuxDB`.`Experiment` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
