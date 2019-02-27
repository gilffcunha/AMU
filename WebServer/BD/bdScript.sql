-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bdAMU
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdAMU
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdAMU` DEFAULT CHARACTER SET utf8 ;
USE `bdAMU` ;

-- -----------------------------------------------------
-- Table `bdAMU`.`Utilizador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdAMU`.`Utilizador` (
  `idUtilizador` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Altura` DECIMAL NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtilizador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdAMU`.`Experiencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdAMU`.`Experiencia` (
  `idExperiencia` INT NOT NULL,
  `Protocolo` VARCHAR(45) NOT NULL,
  `SO` VARCHAR(45) NOT NULL,
  `MarcaTlm` VARCHAR(45) NOT NULL,
  `ModeloTlm` VARCHAR(45) NOT NULL,
  `Utilizador_idUtilizador` INT NOT NULL,
  PRIMARY KEY (`idExperiencia`),
  INDEX `fk_Experiencia_Utilizador_idx` (`Utilizador_idUtilizador` ASC),
  CONSTRAINT `fk_Experiencia_Utilizador`
    FOREIGN KEY (`Utilizador_idUtilizador`)
    REFERENCES `bdAMU`.`Utilizador` (`idUtilizador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdAMU`.`Amostra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdAMU`.`Amostra` (
  `idAmostra` INT NOT NULL,
  `Latitude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `Luminosidade` VARCHAR(45) NOT NULL,
  `Timestamp` VARCHAR(45) NOT NULL,
  `Experiencia_idExperiencia` INT NOT NULL,
  PRIMARY KEY (`idAmostra`),
  INDEX `fk_Amostra_Experiencia1_idx` (`Experiencia_idExperiencia` ASC),
  CONSTRAINT `fk_Amostra_Experiencia1`
    FOREIGN KEY (`Experiencia_idExperiencia`)
    REFERENCES `bdAMU`.`Experiencia` (`idExperiencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
