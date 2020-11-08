# Host: 127.0.0.1  (Version: 5.7.11-log)
# Date: 2020-11-08 23:37:22
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

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
  `avatar` varchar(2550) NOT NULL DEFAULT '' COMMENT '头像',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `data_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '通用状态,2正常,3删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'13381176580','e10adc3949ba59abbe56e057f20f883e','管理员1','喵喵','http://pic1.bbzhi.com/dongwubizhi/shijiegedidongwubizhidisanji/animal_2009_animal_1680_desktop_03_43570_10.jpg','2020-11-02 00:34:13','2020-11-02 00:34:13',2),(2,'13967541234','e10adc3949ba59abbe56e057f20f883e','管理员2','咪咪','http://pic1.bbzhi.com/dongwubizhi/shijiegedidongwubizhidisanji/animal_2009_animal_1680_desktop_03_43570_10.jpg','2020-11-02 21:42:12','2020-11-02 21:42:12',2),(4,'13398541235','cef598fb961cca7ca45254456e7d5a19','管理员3','哈哈','http://localhost:8527/upload/2020-11/e6fa7d6b-f441-4159-8d9f-927e678d56bd.PNG','2020-11-08 22:27:47','2020-11-08 22:27:47',2);

#
# Structure for table "appraisal"
#

DROP TABLE IF EXISTS `appraisal`;
CREATE TABLE `appraisal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `dishes_id` int(10) NOT NULL DEFAULT '0' COMMENT '菜式id',
  `appraisal` text NOT NULL COMMENT '评价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价表';

#
# Data for table "appraisal"
#

INSERT INTO `appraisal` VALUES (1,1,1,'很难吃，下次不会再去。','2020-11-08 22:32:11');

#
# Structure for table "attachment"
#

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `path` varchar(200) NOT NULL COMMENT '存储路径',
  `url` varchar(255) NOT NULL COMMENT '对外路径',
  `sha1` varchar(40) NOT NULL COMMENT '文件sha1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sha1` (`sha1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

#
# Data for table "attachment"
#


#
# Structure for table "collection"
#

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `dishes_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

#
# Data for table "collection"
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
  `cover` varchar(1000) NOT NULL DEFAULT '' COMMENT '菜式图片路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `like` int(10) DEFAULT '0' COMMENT '点赞数',
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
  `name` varchar(30) NOT NULL DEFAULT '0' COMMENT '窗口名称',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '窗口图片url',
  `floor` tinyint(2) DEFAULT '0' COMMENT '饭堂楼层,1：一楼，2：二楼',
  `canteen` int(4) NOT NULL DEFAULT '0' COMMENT '饭堂，1：一饭；2：二饭；3：三饭；4：四饭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='窗口表';

#
# Data for table "window"
#

INSERT INTO `window` VALUES (1,'自选','https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%87%AA%E9%80%89%E9%A4%90&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3688463768,194223188&os=2551242937,1808266533&simid=3355569104,116263238&pn=0&rn=1&di=141790&ln=1489&fr=&fmq=1604849780724_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fi3.s2.dpfile.com%2Fpc%2Fmc%2F874cf03f5f6781a7c9c6b7d580de76b7%2528450c280%2529%2FaD0yODAmaz0vcGMvbWMvODc0Y2YwM2Y1ZjY3ODFhN2M5YzZiN2Q1ODBkZTc2YjcmbG9nbz0wJm09YyZ3PTQ1MA.2535fc133174eacc28553e6688c4dfbe%2Fthumb.j&rpstart=0&rpnum=0&adpicid=0&force=undefined',1,3);
# Host: 127.0.0.1  (Version: 5.7.11-log)
# Date: 2020-11-08 23:37:22
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

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
  `avatar` varchar(2550) NOT NULL DEFAULT '' COMMENT '头像',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `data_status` tinyint(2) NOT NULL DEFAULT '2' COMMENT '通用状态,2正常,3删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员表';

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'13381176580','e10adc3949ba59abbe56e057f20f883e','管理员1','喵喵','http://pic1.bbzhi.com/dongwubizhi/shijiegedidongwubizhidisanji/animal_2009_animal_1680_desktop_03_43570_10.jpg','2020-11-02 00:34:13','2020-11-02 00:34:13',2),(2,'13967541234','e10adc3949ba59abbe56e057f20f883e','管理员2','咪咪','http://pic1.bbzhi.com/dongwubizhi/shijiegedidongwubizhidisanji/animal_2009_animal_1680_desktop_03_43570_10.jpg','2020-11-02 21:42:12','2020-11-02 21:42:12',2),(4,'13398541235','cef598fb961cca7ca45254456e7d5a19','管理员3','哈哈','http://localhost:8527/upload/2020-11/e6fa7d6b-f441-4159-8d9f-927e678d56bd.PNG','2020-11-08 22:27:47','2020-11-08 22:27:47',2);

#
# Structure for table "appraisal"
#

DROP TABLE IF EXISTS `appraisal`;
CREATE TABLE `appraisal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `dishes_id` int(10) NOT NULL DEFAULT '0' COMMENT '菜式id',
  `appraisal` text NOT NULL COMMENT '评价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评价表';

#
# Data for table "appraisal"
#

INSERT INTO `appraisal` VALUES (1,1,1,'很难吃，下次不会再去。','2020-11-08 22:32:11');

#
# Structure for table "attachment"
#

DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `path` varchar(200) NOT NULL COMMENT '存储路径',
  `url` varchar(255) NOT NULL COMMENT '对外路径',
  `sha1` varchar(40) NOT NULL COMMENT '文件sha1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sha1` (`sha1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

#
# Data for table "attachment"
#


#
# Structure for table "collection"
#

DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0',
  `dishes_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

#
# Data for table "collection"
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
  `cover` varchar(1000) NOT NULL DEFAULT '' COMMENT '菜式图片路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `like` int(10) DEFAULT '0' COMMENT '点赞数',
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
  `name` varchar(30) NOT NULL DEFAULT '0' COMMENT '窗口名称',
  `url` varchar(1000) NOT NULL DEFAULT '' COMMENT '窗口图片url',
  `floor` tinyint(2) DEFAULT '0' COMMENT '饭堂楼层,1：一楼，2：二楼',
  `canteen` int(4) NOT NULL DEFAULT '0' COMMENT '饭堂，1：一饭；2：二饭；3：三饭；4：四饭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='窗口表';

#
# Data for table "window"
#

INSERT INTO `window` VALUES (1,'自选','https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%87%AA%E9%80%89%E9%A4%90&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3688463768,194223188&os=2551242937,1808266533&simid=3355569104,116263238&pn=0&rn=1&di=141790&ln=1489&fr=&fmq=1604849780724_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fi3.s2.dpfile.com%2Fpc%2Fmc%2F874cf03f5f6781a7c9c6b7d580de76b7%2528450c280%2529%2FaD0yODAmaz0vcGMvbWMvODc0Y2YwM2Y1ZjY3ODFhN2M5YzZiN2Q1ODBkZTc2YjcmbG9nbz0wJm09YyZ3PTQ1MA.2535fc133174eacc28553e6688c4dfbe%2Fthumb.j&rpstart=0&rpnum=0&adpicid=0&force=undefined',1,3);
