-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ecom
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecom
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecom` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecom` ;

-- -----------------------------------------------------
-- Table `ecom`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `dob` DATE NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `fullname` VARCHAR(255) NULL DEFAULT NULL,
  `gender` CHAR(1) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  INDEX `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecom`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `img1` VARCHAR(255) NULL DEFAULT NULL,
  `long_description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` BIGINT NULL DEFAULT NULL,
  `short_description` VARCHAR(255) NULL DEFAULT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `img2` VARCHAR(100) NULL DEFAULT NULL,
  `img3` VARCHAR(200) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  `img4` VARCHAR(150) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK1mtsbur82frn64de7balymq9s` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s`
    FOREIGN KEY (`category_id`)
    REFERENCES `ecom`.`category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`cart_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`cart_line` (
  `quantity` INT NULL DEFAULT NULL,
  `unit_price` BIGINT NULL DEFAULT NULL,
  `cart_cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`cart_cart_id`, `product_id`),
  INDEX `FK6fx7athvka6ywajy8bsqc0mdh` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FK6fx7athvka6ywajy8bsqc0mdh`
    FOREIGN KEY (`product_id`)
    REFERENCES `ecom`.`product` (`id`),
  CONSTRAINT `FKoxftytjdq2360gy4xns81kd7r`
    FOREIGN KEY (`cart_cart_id`)
    REFERENCES `ecom`.`cart` (`cart_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `payment_mode` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `total_price` BIGINT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKel9kyl84ego2otj2accfd8mr7` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecom`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`order_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`order_line` (
  `quantity` INT NULL DEFAULT NULL,
  `unit_price` BIGINT NULL DEFAULT NULL,
  `order_id` BIGINT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `FKpf904tci8garypkvm32cqupye` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FKk9f9t1tmkbq5w27u8rrjbxxg6`
    FOREIGN KEY (`order_id`)
    REFERENCES `ecom`.`orders` (`id`),
  CONSTRAINT `FKpf904tci8garypkvm32cqupye`
    FOREIGN KEY (`product_id`)
    REFERENCES `ecom`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`user_roles` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FKrhfovtciq1l558cw6udg0h0d3` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecom`.`user` (`user_id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3`
    FOREIGN KEY (`role_id`)
    REFERENCES `ecom`.`role` (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecom`.`voucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom`.`voucher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `discount_percent` INT NULL DEFAULT NULL,
  `expired_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
