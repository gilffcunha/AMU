-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Height` DOUBLE NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Experiment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Experiment` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Protocol` VARCHAR(45) NOT NULL,
  `AndroidVersion` VARCHAR(45) NOT NULL,
  `Brand` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `User_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Experiencia_Utilizador1_idx` (`User_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Experiencia_Utilizador1`
    FOREIGN KEY (`User_ID`)
    REFERENCES `mydb`.`User` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Sample`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Sample` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Latitude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `Luminusity` DOUBLE NOT NULL,
  `Timestamp` TIMESTAMP NOT NULL,
  `Experiment_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Amostra_Experiencia_idx` (`Experiment_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Amostra_Experiencia`
    FOREIGN KEY (`Experiment_ID`)
    REFERENCES `mydb`.`Experiment` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
