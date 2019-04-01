create table `miaosha_user` (
	`id` bigint(20) NOT NULL COMMENT '用户id，手机号码',
	`nickname` varchar(255) NOT NULL COMMENT '昵称',
	`password` varchar(32) DEFAULT NULL COMMENT 'MD5(MMD5(pass明文 + 固定salt)+ salt)',
	`sal` varchar(10) DEFAULT NULL COMMENT '盐',
	`head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
	`register_date` datetime DEFAULT NULL COMMENT '注册时间',
	`last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
	`login_count` int(11) DEFAULT '0' COMMENT '登录次数',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;