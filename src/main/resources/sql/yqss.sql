/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - yqss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yqss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yqss`;

/*Table structure for table `ads` */

DROP TABLE IF EXISTS `ads`;

CREATE TABLE `ads` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '广告标题',
  `content` varchar(500) DEFAULT '' COMMENT '广告内容',
  `product_id` int(32) DEFAULT '0' COMMENT '商品ID',
  `image_url` varchar(500) DEFAULT '' COMMENT '图片url',
  `link_url` varchar(100) DEFAULT '' COMMENT '链接地址',
  `type` int(2) DEFAULT '1' COMMENT '广告类型 1:首页 2:商城 3:二手商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `ads` */

insert  into `ads`(`id`,`title`,`content`,`product_id`,`image_url`,`link_url`,`type`) values (1,'广告1','广告1',1,'','',1),(2,'广告2','广告2',2,'','',1),(3,'广告3','广告3',3,'','',1),(4,'广告4','广告4',4,'','',1);

/*Table structure for table `bank_type` */

DROP TABLE IF EXISTS `bank_type`;

CREATE TABLE `bank_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(30) NOT NULL COMMENT '银行名称',
  `create_user` int(32) NOT NULL COMMENT '操作用户',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `bank_type` */

insert  into `bank_type`(`id`,`bank_name`,`create_user`,`create_date`) values (1,'中国银行',0,20151214153225),(2,'中国工商银行',0,20151214153225),(3,'中国建设银行',0,20151214153225),(4,'中国农业银行',0,20151214153225),(5,'招商银行',0,20151214153225);

/*Table structure for table `borrow_info` */

DROP TABLE IF EXISTS `borrow_info`;

CREATE TABLE `borrow_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `limit_money` int(4) DEFAULT '0' COMMENT '贷款额度',
  `max_day` int(2) DEFAULT '0' COMMENT '最大期限',
  `period` int(2) DEFAULT '0' COMMENT '期数',
  `type` int(32) DEFAULT '0' COMMENT '借款原因 ',
  `username` varchar(50) DEFAULT '' COMMENT '姓名',
  `id_cartd` varchar(30) DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) DEFAULT '' COMMENT '手机',
  `school_name` varchar(50) DEFAULT '' COMMENT '学校',
  `address` varchar(500) DEFAULT '' COMMENT '收货地址',
  `product_id` int(32) DEFAULT '0' COMMENT '商品ID',
  `stage_id` int(32) DEFAULT '0' COMMENT '分期ID',
  `repay_date` bigint(20) DEFAULT NULL COMMENT '还款时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '借款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrow_info` */

/*Table structure for table `buy_borrow_info` */

DROP TABLE IF EXISTS `buy_borrow_info`;

CREATE TABLE `buy_borrow_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `image_url` varchar(200) DEFAULT '' COMMENT '商品图片',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `period` int(2) DEFAULT '0' COMMENT '期数',
  `residue_money` double DEFAULT NULL COMMENT '剩余钱数',
  `next_date` bigint(20) DEFAULT NULL COMMENT '下次还款时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `buy_borrow_info` */

/*Table structure for table `buy_repay_record` */

DROP TABLE IF EXISTS `buy_repay_record`;

CREATE TABLE `buy_repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间/还款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `buy_repay_record` */

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `product_id` int(32) NOT NULL COMMENT '收藏商品ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collection` */

/*Table structure for table `helper` */

DROP TABLE IF EXISTS `helper`;

CREATE TABLE `helper` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '问题类型',
  `question` varchar(100) DEFAULT '' COMMENT '问题',
  `answer` varchar(500) DEFAULT '' COMMENT '答案',
  `sys_user_id` int(32) NOT NULL,
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `helper` */

insert  into `helper`(`id`,`type`,`question`,`answer`,`sys_user_id`,`create_date`) values (1,'','305751572','123456789',0,NULL),(2,'申请条件','问题1','答案',0,NULL),(3,'账单还款','问题1','答案',0,NULL),(4,'办理流程','问题1','答案',0,NULL),(5,'售后服务','问题1','答案',0,NULL);

/*Table structure for table `hr_message` */

DROP TABLE IF EXISTS `hr_message`;

CREATE TABLE `hr_message` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '人力信息标题',
  `content` varchar(500) DEFAULT '' COMMENT '人力信息内容',
  `salary_min` int(4) DEFAULT '0' COMMENT '工资下限',
  `salary_max` int(4) DEFAULT '0' COMMENT '工资上线',
  `tel` varchar(20) DEFAULT '' COMMENT '电话',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `city_id` int(4) DEFAULT '0' COMMENT '城市ID',
  `area_id` int(4) DEFAULT '0' COMMENT '区域ID',
  `street_id` int(4) DEFAULT '0' COMMENT '街道ID',
  `address` varchar(200) DEFAULT '' COMMENT '就职地点',
  `company` varchar(50) DEFAULT '' COMMENT '公司名字',
  `position` varchar(50) DEFAULT '' COMMENT '职位',
  `counts` int(2) DEFAULT '1' COMMENT '招聘人数',
  `is_list` int(2) DEFAULT '1' COMMENT '是否发布 1:发布 2:不发布',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `hr_message` */

insert  into `hr_message`(`id`,`title`,`content`,`salary_min`,`salary_max`,`tel`,`mobile`,`city_id`,`area_id`,`street_id`,`address`,`company`,`position`,`counts`,`is_list`,`create_date`) values (1,'标题1','内容1',2000,5000,'','13476107756',0,0,0,'创业基地','6mai','java程序员',3,1,NULL),(2,'标题2','内容2',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL),(3,'标题3','内容3',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL),(4,'标题3','内容3',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '消息标题',
  `content` varchar(500) DEFAULT '' COMMENT '消息内容',
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `is_list` int(2) DEFAULT '1' COMMENT '是否显示 1显示 2不显示',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `username` varchar(50) DEFAULT '' COMMENT '购买人姓名',
  `id_card` varchar(50) DEFAULT '' COMMENT '购买人身份证',
  `mobile` varchar(50) DEFAULT '' COMMENT '购买人手机号',
  `school_id` int(4) DEFAULT '0' COMMENT '购买人学校ID',
  `school_name` varchar(20) DEFAULT '0' COMMENT '购买人学校名称',
  `address` varchar(200) DEFAULT '0' COMMENT '收货地址',
  `order_date` bigint(20) DEFAULT NULL COMMENT '下单时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `content` varchar(500) DEFAULT '' COMMENT '商品描述',
  `product_type` int(32) NOT NULL COMMENT '商品所属类型',
  `is_list` int(2) DEFAULT '1' COMMENT '是否上架 1:上架 2:下架',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`price`,`content`,`product_type`,`is_list`,`create_date`) values (1,'苹果',6088.00,'介绍',1,1,NULL),(2,'三星',6088.00,'介绍222',1,1,NULL),(3,'thinkpad',6088.00,'介绍222',2,1,NULL),(4,'冰箱',6088.00,'介绍222',3,1,NULL),(5,'音响',6088.00,'介绍222',4,1,NULL);

/*Table structure for table `product_comment` */

DROP TABLE IF EXISTS `product_comment`;

CREATE TABLE `product_comment` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `from_user_id` int(32) NOT NULL COMMENT '评论用户ID',
  `to_user_id` int(32) DEFAULT '0' COMMENT '被评论用户ID',
  `content` varchar(500) DEFAULT '' COMMENT '评论内容',
  `score` int(2) DEFAULT '5' COMMENT '评论等级 1-5',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `product_comment` */

insert  into `product_comment`(`id`,`product_id`,`from_user_id`,`to_user_id`,`content`,`score`,`create_date`) values (1,1,5,1,'评论1111',5,NULL),(2,1,5,0,'评论222',5,NULL),(3,1,5,0,'评论333',5,NULL);

/*Table structure for table `product_images` */

DROP TABLE IF EXISTS `product_images`;

CREATE TABLE `product_images` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `image_url` varchar(200) DEFAULT '' COMMENT '图片url',
  `height` double(10,2) DEFAULT '0.00' COMMENT '图片高度',
  `width` double(10,2) DEFAULT '0.00' COMMENT '图片宽度',
  `small_url` varchar(200) DEFAULT '' COMMENT '缩略图',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_images` */

/*Table structure for table `product_type` */

DROP TABLE IF EXISTS `product_type`;

CREATE TABLE `product_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '' COMMENT '商品类型',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `product_type` */

insert  into `product_type`(`id`,`name`,`create_date`) values (1,'手机',20151214115048),(2,'电脑',20151214115048),(3,'生活用品',20151214115048),(4,'图书音响',20151214115048);

/*Table structure for table `repay_record` */

DROP TABLE IF EXISTS `repay_record`;

CREATE TABLE `repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `repay_record` */

/*Table structure for table `second_hand` */

DROP TABLE IF EXISTS `second_hand`;

CREATE TABLE `second_hand` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT '' COMMENT '二手商品描述',
  `price` double(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `second_hand` */

insert  into `second_hand`(`id`,`title`,`content`,`price`,`user_id`,`create_date`) values (1,'二手商品','水水水水',100.00,5,NULL),(2,'二手商品11','fsadfsadf',1000.00,5,NULL),(3,'二手商品222','fsadfsadf',1000.00,5,NULL),(4,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(5,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(6,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(7,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(8,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(9,'二手商品222','fsadfsadfasdf',1000.00,5,NULL),(10,'二手商品222','fsadfsadfasdf',1000.00,5,NULL);

/*Table structure for table `second_hand_images` */

DROP TABLE IF EXISTS `second_hand_images`;

CREATE TABLE `second_hand_images` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `second_hand_id` int(32) NOT NULL COMMENT '所属二手物品',
  `image_url` varchar(200) DEFAULT '' COMMENT '图片url',
  `height` double(10,2) DEFAULT '0.00' COMMENT '图片高度',
  `width` double(10,2) DEFAULT '0.00' COMMENT '图片宽度',
  `small_url` varchar(200) DEFAULT '' COMMENT '缩略图',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `second_hand_images` */

/*Table structure for table `users_bank_card` */

DROP TABLE IF EXISTS `users_bank_card`;

CREATE TABLE `users_bank_card` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `bank_type` int(32) DEFAULT '0' COMMENT '银行卡类型',
  `bank_name` varchar(30) DEFAULT '' COMMENT '银行名称',
  `card_no` varchar(30) NOT NULL COMMENT '卡号',
  `mobile` varchar(20) DEFAULT '' COMMENT '预留手机号',
  `is_default` int(2) DEFAULT '1' COMMENT '是否默认 1默认 2非默认',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `users_bank_card` */

insert  into `users_bank_card`(`id`,`user_id`,`bank_type`,`bank_name`,`card_no`,`mobile`,`is_default`,`create_date`) values (2,5,1,'','9876544321','13476107753',1,20151214154048);

/*Table structure for table `users_info` */

DROP TABLE IF EXISTS `users_info`;

CREATE TABLE `users_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` int(2) NOT NULL DEFAULT '1' COMMENT '性别 0:不限 1:男 2:女',
  `head_url` varchar(200) DEFAULT '' COMMENT '头像url',
  `id_card` varchar(30) DEFAULT '' COMMENT '身份证',
  `school_id` int(4) DEFAULT '0' COMMENT '学校ID',
  `school_name` varchar(20) DEFAULT '' COMMENT '学校名称',
  `address` varchar(500) DEFAULT '' COMMENT '详细地址',
  `status` int(2) DEFAULT '1' COMMENT '用户状态 1正常 2:禁用',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `users_info` */

insert  into `users_info`(`id`,`user_id`,`mobile`,`name`,`gender`,`head_url`,`id_card`,`school_id`,`school_name`,`address`,`status`,`create_date`) values (1,5,'13429806156','测试',1,'','4208888888888',0,'测试学校','武汉',1,20151214121638);

/*Table structure for table `users_login` */

DROP TABLE IF EXISTS `users_login`;

CREATE TABLE `users_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `users_login` */

insert  into `users_login`(`id`,`username`,`password`) values (5,'13429806156','654321');

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '视频标题',
  `content` varchar(500) DEFAULT '' COMMENT '视频介绍',
  `url` varchar(200) DEFAULT '' COMMENT '视频URL',
  `image_url` varchar(100) DEFAULT '' COMMENT '图片URL',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `video` */

insert  into `video`(`id`,`title`,`content`,`url`,`image_url`,`create_date`) values (1,'默认','默认视频','','',NULL),(2,'视频1','视频1','http://www.baidu.com','',NULL),(3,'视频2','视频2','http://www.baidu.com','',NULL),(4,'视频3','视频3','http://www.baidu.com','',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
