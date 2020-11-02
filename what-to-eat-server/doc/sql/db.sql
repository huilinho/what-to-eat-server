
#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '管理员姓名',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `data_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '通用状态,2正常,3删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

#
# Data for table "admin"
#
INSERT INTO `admin` VALUES (1,'13381176580','e10adc3949ba59abbe56e057f20f883e','管理员','admin','','2020-11-02 00:34:13','2020-11-02 00:34:13',2),(2,'13967541234','e10adc3949ba59abbe56e057f20f883e','admin1','管理员1','','2020-11-02 21:42:12','2020-11-02 21:42:12',2);

#
# Structure for table "appraisal"
#

DROP TABLE IF EXISTS `appraisal`;
CREATE TABLE `appraisal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `dishes_id` int(10) NOT NULL DEFAULT '0' COMMENT '订单id',
  `appraisal_grade` int(10) NOT NULL DEFAULT '3' COMMENT '评价等级，1.超难吃，2.难吃，3.一般，4.好吃，5.超好吃',
  `appraisal` text NOT NULL COMMENT '评价',
  `creatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价表';

#
# Data for table "appraisal"
#


#
# Structure for table "dishes"
#

DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(30) NOT NULL DEFAULT '0' COMMENT '菜式名',
  `window_id` int(10) NOT NULL DEFAULT '0' COMMENT '所属窗口id',
  `type_id` int(10) NOT NULL DEFAULT '0' COMMENT '种类id',
  `comment_id` varchar(2550) NOT NULL DEFAULT '0' COMMENT '该菜式对应的所有评价id',
  `score` decimal(5,2) NOT NULL COMMENT '星级分',
  `cover` varchar(1000) NOT NULL DEFAULT '' COMMENT '菜式图片路径',
  `creatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜式表';

#
# Data for table "dishes"
#


#
# Structure for table "type"
#

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` int(10) NOT NULL DEFAULT '0' COMMENT '种类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='种类表';

#
# Data for table "type"
#


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `openid` varchar(64) NOT NULL DEFAULT '' COMMENT '微信openid',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '微信昵称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `collections` varchar(300) NOT NULL DEFAULT '' COMMENT '收藏菜式的id集合(字符串形式存储)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `data_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '通用状态,2正常,3删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

#
# Data for table "user"
#


#
# Structure for table "user_session"
#

DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
  `openid` varchar(64) NOT NULL COMMENT '主键',
  `session_key` varchar(64) NOT NULL COMMENT '微信login后的session_key',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户会话表';

#
# Data for table "user_session"
#


#
# Structure for table "window"
#

DROP TABLE IF EXISTS `window`;
CREATE TABLE `window` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` int(10) NOT NULL DEFAULT '0' COMMENT '窗口名称',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '窗口图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='窗口表';

#
# Data for table "window"
#

