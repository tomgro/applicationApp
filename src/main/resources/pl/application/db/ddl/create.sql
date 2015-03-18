
-- -----------------------------------------------------
-- Table `mydb`.`APPLICATION`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `APPLICATION` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `content` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `mydb`.`APP_STATES`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `APP_STATES` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `state_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `mydb`.`APP_HISTORY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `APP_HISTORY` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `state_id` INT NOT NULL ,
  `application_id` INT NOT NULL ,
  `reason` VARCHAR(255) NULL ,
  `mod_date` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_APP_HISTORY_APP_STATES`
    FOREIGN KEY (`state_id` )
    REFERENCES `APP_STATES` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_APP_HISTORY_APPLICATION1`
    FOREIGN KEY (`application_id` )
    REFERENCES `APPLICATION` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


