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
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`user_id_s` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '联系人较小id',
	`user_id_l` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '联系人较大id',
	`status` INT(11) NOT NULL DEFAULT '1' COMMENT '状态1-添加 0-移除',
	`created_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
	`updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_id_pair` (`user_id_s`, `user_id_l`),
	INDEX `user_id_l` (`user_id_l`, `status`),
	INDEX `user_id_s` (`user_id_s`, `status`)
)
COMMENT='联系人'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE if not EXISTS `msg` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	`content` VARCHAR(50) NOT NULL COMMENT '信息内容',
	`src_id` BIGINT(20) NOT NULL COMMENT '信息发送方',
	`dest_id` BIGINT(20) NOT NULL COMMENT '信息接收方',
	`status` INT(11) NOT NULL DEFAULT '-1' COMMENT '-1-未读 1-已读',
	`is_delete` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未删除1-已删除 ',
	`created_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
	`updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`),
	INDEX `src_dest_status` (`src_id`, `dest_id`, `status`),
	INDEX `src_dest_delete` (`src_id`, `dest_id`, `is_delete`),
	INDEX `created_time` (`created_time`)
)
COMMENT='消息'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
