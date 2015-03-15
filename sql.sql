CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`APPLICATION`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`APPLICATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `content` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`APP_STATES`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`APP_STATES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `state_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`APP_HISTORY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`APP_HISTORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `state_id` INT NOT NULL ,
  `application_id` INT NOT NULL ,
  `reason` VARCHAR(255) NULL ,
  `mod_date` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_APP_HISTORY_APP_STATES_idx` (`state_id` ASC) ,
  INDEX `fk_APP_HISTORY_APPLICATION1_idx` (`application_id` ASC) ,
  CONSTRAINT `fk_APP_HISTORY_APP_STATES`
    FOREIGN KEY (`state_id` )
    REFERENCES `mydb`.`APP_STATES` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_APP_HISTORY_APPLICATION1`
    FOREIGN KEY (`application_id` )
    REFERENCES `mydb`.`APPLICATION` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


