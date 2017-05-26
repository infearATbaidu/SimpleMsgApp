CREATE TABLE if not EXISTS `user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`name` VARCHAR(50) NOT NULL DEFAULT '""' COMMENT '用户名',
	`password` VARCHAR(100) NOT NULL DEFAULT '""' COMMENT '密码',
	`ext_info` VARCHAR(2048) NOT NULL DEFAULT '""' COMMENT '扩展信息',
	`created_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	`updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)
)
COMMENT='用户信息'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE if not EXISTS `contact` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id_s` BIGINT(20) NOT NULL DEFAULT '0',
	`user_id_l` BIGINT(20) NOT NULL DEFAULT '0',
	`status` INT(11) NOT NULL DEFAULT '1',
	`created_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
	`updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_id_pair` (`user_id_s`, `user_id_l`),
	INDEX `user_id_l` (`user_id_l`, `status`),
	INDEX `user_id_s` (`user_id_s`, `status`)
)
COMMENT='联系人'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
