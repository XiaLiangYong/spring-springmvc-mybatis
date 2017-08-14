-- auto Generated on 2017-08-14 23:45:27 
-- DROP TABLE IF EXISTS `user`; 
CREATE TABLE `user`(
    `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'username',
    `password` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'password',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`user`';
