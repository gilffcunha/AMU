-- Lux Android App - Database Script Creation

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema luxdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema luxdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `luxdb` DEFAULT CHARACTER SET utf8 ;
USE `luxdb` ;

-- -----------------------------------------------------
-- Table `luxdb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxdb`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARBINARY(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxdb`.`Protocol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxdb`.`Protocol` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  `Description` LONGTEXT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxdb`.`Experiment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxdb`.`Experiment` (
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
    REFERENCES `luxdb`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Experiment_Protocol1`
    FOREIGN KEY (`Protocol_ID`)
    REFERENCES `luxdb`.`Protocol` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `luxdb`.`Sample`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `luxdb`.`Sample` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Latitude` DOUBLE NOT NULL,
  `Longitude` DOUBLE NOT NULL,
  `Luminosity` DOUBLE NOT NULL,
  `Timestamp` TIMESTAMP NOT NULL,
  `Experiment_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Amostra_Experiencia_idx` (`Experiment_ID` ASC),
  CONSTRAINT `fk_Amostra_Experiencia`
    FOREIGN KEY (`Experiment_ID`)
    REFERENCES `luxdb`.`Experiment` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
