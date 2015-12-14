/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - aal
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

/*Table structure for table `adspublish` */

DROP TABLE IF EXISTS `users_login`;

CREATE TABLE `users_login` (
  `id` int(32) not null auto_increment,
  `username` varchar(20) not null default '' comment '登录名',
  `password` varchar(50) not null default '' comment '密码',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users_info`;

create table `users_info`(
    `id` int(32) not null auto_increment,
   `user_id` int(32) not null comment '用户ID',
   `mobile` varchar(20) not null default '' comment '手机号',
   `name` varchar(50) not null default '' comment '昵称',
   `gender` int(2) not null default 1 comment '性别 0:不限 1:男 2:女',
   `head_url` varchar(200) default '' comment '头像url',
   `id_card` varchar(30) default '' comment '身份证',
   `school_id` int(4) default 0 comment '学校ID',
   `school_name` varchar(20) default '' comment '学校名称',
   `address` varchar(500) default '' comment '详细地址',
  
  `status` int(2) default 1 comment '用户状态 1正常 2:禁用',
  `create_date` bigint comment '创建时间',
  
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `bank_type`(
	`id` int(32) not null auto_increment,
	`bank_name` varchar(30) not null comment '银行名称',
    
	`create_user` int(32) not null comment '操作用户',	
	`create_date` bigint comment '创建时间'
	PRIMARY KEY (`id`),
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into bank_type (bank_name,create_date) values ('中国银行',NOW()),('中国工商银行',NOW()),('中国建设银行',NOW()),('中国农业银行',NOW()),('招商银行',NOW());

DROP TABLE IF EXISTS `users_bank_card`;

create table `users_bank_card` (
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '所属用户',
  `bank_type` int(32) default 0 comment '银行卡类型',
  `bank_name` varchar(30) default '' comment '银行名称',
  `card_no` varchar(30) not null comment '卡号',
  `mobile` varchar(20) default '' comment '手机',
  `is_default` int(2) default 1 comment '是否默认 1默认 2非默认',
  `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `borrow_info`;

create table `borrow_info`(
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '所属用户ID',
  `limit_money` int(4) default 0 comment '贷款额度',
  `max_day` int(2) default 0 comment '最大期限',
  `period` int(2) default 0 comment '期数',
  `type` int(32) default 0 comment '借款原因 ',
	
  `next_residue_date` bingint comment '下次还款时间',
  
  `username` varchar(50) default '' comment '姓名',
  `id_cartd`varchar(30) default '' comment '身份证',
  `phone` varchar(15) default '' comment '手机',
  `school_name` varchar(50) default '' comment '学校',
  `address` varchar(500) default '' comment '收货地址',
  `product_id` int(32) default 0 comment '商品ID',
  `stage_id` int(32) default 0 comment '分期ID',
  `stage` int(32) default 0 comment '分期',
  
  `repay_date` bigint comment '还款时间',
  `create_date` bigint comment '借款时间',
  
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `repay_record`;

create table `repay_record`(
   `id` int(32) not null auto_increment,
   `borrow_info_id` int(32) not null comment '还款记录',
   `money` double(10,2) default 0 not null comment '还款额度',
   `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `buy_borrow_info`;

create table `buy_borrow_info`(
   `id` int(32) not null auto_increment,
   `user_id` int(32) not null comment '所属用户ID',
   `product_id` int(32) not null comment '商品ID',
   
   `name` varchar(200) not null default '' comment '商品名称',
   `image_url` varchar(200) default '' comment '商品图片',
   `price` double(10,2) not null default 0 comment '商品价格',
   `period` int(2) default 0 comment '期数',
   
   `residue_money` double comment '剩余钱数',
   `next_date` bigint comment '下次还款时间',
   
   `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `buy_repay_record`;

create table `buy_repay_record`(
   `id` int(32) not null auto_increment,
   `borrow_info_id` int(32) not null comment '还款记录',
   `money` double(10,2) default 0 not null comment '还款额度',
   `create_date` bigint comment '创建时间/还款时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message`;

create table `message` (
  `id` int(32) not null auto_increment,
  `title` varchar(50) default '' comment '消息标题',
  `content` varchar(500) default '' comment '消息内容',
  `user_id` int(32) not null comment '所属用户',
  `is_list` int(2) default 1 comment '是否显示 1显示 2不显示',
  `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_type`;

create table `product_type`(
    `id` int(32) not null auto_increment,
    `name` varchar(20) default '' comment '商品类型',
    
    `create_date` bigint comment '创建时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `product_type` (`name`,`create_date`) values ('手机',NOW()),('电脑',now()),('生活用品',now()),('图书音响',now());

DROP TABLE IF EXISTS `product`;

create table `product` (
	`id` int(32) not null auto_increment,
	`name` varchar(200) not null default '' comment '商品名称',
	`price` double(10,2) not null default 0 comment '商品价格',
	`content` varchar(500) default '' comment '商品描述',
	`product_type` int(32) not null comment '商品所属类型',
	`is_list`int(2) default 1 comment '是否上架 1:上架 2:下架',
	
	`create_date` bigint comment '创建时间',
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into product (name,price,content,product_type,is_list) values ('苹果','6088','介绍',1,1),('三星','6088','介绍222',1,1),('thinkpad','6088','介绍222',2,1),('冰箱','6088','介绍222',3,1),('音响','6088','介绍222',4,1);

DROP TABLE IF EXISTS `product_images`;

create table `product_images`(
	`id` int(32) not null auto_increment,
	`product_id` int(32) not null comment '商品ID',
	`image_url` varchar(200) default '' comment '图片url',
	`height` double(10,2) default 0 comment '图片高度',
	`width` double(10,2) default 0 comment '图片宽度',
	`small_url` varchar(200) default '' comment '缩略图',
	
	`create_date` bigint comment '创建时间',
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_comment`;

create table `product_comment`(
	`id` int(32) not null auto_increment,
	`product_id` int(32) not null comment '商品ID',
	`from_user_id` int(32) not null comment '评论用户ID',
	`to_user_id` int(32) default 0 comment '被评论用户ID',
	`content` varchar(500) default '' comment '评论内容',
	`score` int(2) default 5 comment '评论等级 1-5',
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;

create table `orders`(
	`id` int(32) not null auto_increment,
	`user_id` int(32) not null comment '所属用户',
	`product_id` int(32) not null comment '商品ID',
	
	`username` varchar(50) default '' comment '购买人姓名',
	`id_card` varchar(50) default '' comment '购买人身份证',
	`mobile`varchar(50) default '' comment '购买人手机号',
	`school_id` int(4) default 0 comment '购买人学校ID',
	`school_name` varchar(20) default 0 comment '购买人学校名称',
	`address` varchar(200) default 0 comment '收货地址',
	
	`order_date` bigint comment '下单时间',
	
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `hr_message`;

create table `hr_message` (
	`id` int(32) not null auto_increment,
	`title` varchar(100) not null default '' comment '人力信息标题',
	`content` varchar(500) default '' comment '人力信息内容',
	`salary_min` int(4) default 0 comment '工资下限',
	`salary_max` int(4) default 0 comment '工资上线',
	`tel` varchar(20) default '' comment '电话',
	`mobile`varchar(20) default '' comment '手机',
	`city_id` int(4) default 0 comment '城市ID',
	`area_id` int(4) default 0 comment '区域ID',
	`street_id` int(4) default 0 comment '街道ID',
	`address` varchar(200) default '' comment '就职地点',
	`company` varchar(50) default '' comment '公司名字',
	`position` varchar(50) default '' comment '职位',
	`counts` int(2) default 1 comment '招聘人数',
	`is_list` int(2) default 1 comment '是否发布 1:发布 2:不发布',
	
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `hr_message` (title,content,salary_min,salary_max,mobile,address,company,position,counts,is_list)
values ('标题1','内容1',2000,5000,'13476107756','创业基地','6mai','java程序员',3,1),
('标题2','内容2',2000,5000,'13476107756','创业基地111','6mai11','java程序员111',3,1),
('标题3','内容3',2000,5000,'13476107756','创业基地111','6mai11','java程序员111',3,1),
('标题3','内容3',2000,5000,'13476107756','创业基地111','6mai11','java程序员111',3,1);

DROP TABLE IF EXISTS `collection`;

create table `collection` (
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '用户ID',
  `product_id` int(32) not null comment '收藏商品ID',
  
  `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `second_hand`;

create table `second_hand` (
	`id` int(32) not null auto_increment,
	`title` varchar(50) not null comment '标题',
	`content`varchar(500) default '' comment '二手商品描述',
	`price` double(10,2) default 0 comment '商品价格',
	`user_id` int(32) not null comment '所属用户ID',
	
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `second_hand_images`;

create table `second_hand_images` (
	`id` int(32) not null auto_increment,
	`second_hand_id` int(32) not null comment '所属二手物品',
	`image_url` varchar(200) default '' comment '图片url',
	`height` double(10,2) default 0 comment '图片高度',
	`width` double(10,2) default 0 comment '图片宽度',
	`small_url` varchar(200) default '' comment '缩略图',
	
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `video`;

create table `video` (
	`id` int(32) not null auto_increment,
	`title` varchar(50) not null comment '视频标题',
	`content`varchar(500) default '' comment '视频介绍',
	`url` varchar(200) default '' comment '视频URL',
	`image_url` varchar(100) default '' comment '图片URL',
	
	`create_date` bigint comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into video (title,content,url,image_url) values ('默认','默认视频','',''),('视频1','视频1','http://www.baidu.com',''),('视频2','视频2','http://www.baidu.com',''),('视频3','视频3','http://www.baidu.com','');

DROP TABLE IF EXISTS `ads`;

create table `ads` (
	`id` int(32) not null auto_increment,
	`title` varchar(50) default '' comment '广告标题',
	`content` varchar(500) default '' comment '广告内容',
	`product_id` int(32) default 0 comment '商品ID',
	`image_url` varchar(500) default '' comment '图片url',
	`link_url` varchar(100) default '' comment '链接地址',
	`type` int(2) default 1 comment '广告类型 1:首页 2:商城 3:二手商品'
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `ads` (title,content,product_id,image_url,link_url) values ('广告1','广告1',1,'','',1),('广告2','广告2',2,'','',2),('广告3','广告3',3,'','',3),('广告4','广告4',4,'','',1)

DROP TABLE IF EXISTS `helper`;

create table `helper` (
  `id` int(32) not null auto_increment,
  `type` varchar(20) default '' comment '问题类型',
  `question` varchar(100) default '' comment '问题',
  `answer` varchar(500) default '' comment '答案',
  `sys_user_id` int(32) not null comment '',
  
  `create_date` bigint comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into helper (`type`,`question`,`answer`) values ('','305751572','123456789'),('申请条件','问题1','答案'),('账单还款','问题1','答案'),('办理流程','问题1','答案'),('售后服务','问题1','答案')
