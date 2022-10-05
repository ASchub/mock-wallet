CREATE SCHEMA IF NOT EXISTS `mockdb`;

CREATE TABLE IF NOT EXISTS `mockdb`.`wallet` (
    `id` CHAR(36) NOT NULL DEFAULT uuid() PRIMARY KEY,
    `balance` NUMERIC(100, 2) NOT NULL,
    `created_at` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `mockdb`.`transaction` (
    `id` CHAR(36) NOT NULL DEFAULT uuid() PRIMARY KEY,
    `sender` CHAR(36) NOT NULL,
    `receiver` CHAR(36) NOT NULL,
    `amount` NUMERIC(100, 2) NOT NULL,
    `created_at` TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_sender_wallet` FOREIGN KEY (`sender`) REFERENCES `wallet` (`id`),
    CONSTRAINT `fk_receiver_wallet` FOREIGN KEY (`receiver`) REFERENCES `wallet` (`id`)
);
