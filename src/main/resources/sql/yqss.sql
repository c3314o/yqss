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

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(32) not null auto_increment,
  `mobile` varchar(20) not null default '' comment '手机号',
  `password` varchar(50) not null default '' comment '密码',
  `name` varchar(50) not null default '' comment '昵称',
  `gender` int(2) not null default 1 comment '性别 0:不限 1:男 2:女',
  `head_url` varchar(200) default '' comment '头像url',
  `id_card` varchar(30) default '' comment '身份证',
  `school_id` int(4) default 0 comment '学校ID',
  `school_name` varchar(20) default '' comment '学校名称',
  `address` varchar(500) default '' comment '详细地址'
  
  `status` int(2) default 1 comment '用户状态 1正常 2:禁用'
  `create_date` datetime comment '创建时间',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_bank_card`;

create table `users_bank_card` (
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '所属用户',
  `card_no` varchar(30) not null comment '卡号',
  `is_default` int(2) default 1 comment '是否默认 1默认 2非默认',
  `create_date` datetime comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `borrow_info`;

create table `borrow_info`(
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '所属用户ID',
  `limit_money` int(4) default 0 comment '贷款额度',
  ``
  `max_day` int(2) default 0 comment '最大期限',
  `period` int(2) default 0 comment '期数',
  `type` int(32) default 0 comment '借款原因 ',
  
  `repay_date` datetime comment '还款时间',
  `repay_date_app` bigint comment ''
  
  `create_date` datetime comment '创建时间',
  
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `repay_record`;

create table `repay_record`(
   `id` int(32) not null auto_increment,
   `borrow_info_id` int(32) not null comment '还款记录',
   `money` double default not null comment '还款额度',
   `repay_date` bigint comment '还款时间',
   
   `create_date` datetime comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `buy_borrow_info`;

create table `buy_borrow_info`(
   `id` int(32) not null auto_increment,
   `user_id` int(32) not null comment '所属用户ID',
   `product_id` int(32) not null comment '商品ID',
   
   `name` varchar(200) not null default '' comment '商品名称',
   `price` double not null default 0 comment '商品价格',
   `period` int(2) default 0 comment '期数',
   
   `residue_money` double comment '剩余钱数',
   `next_date` datetime comment '下次还款时间',
   
   `next_date_app` bigint comment '下次还款时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `buy_repay_record`;

create table `buy_repay_record`(
   `id` int(32) not null auto_increment,
   `borrow_info_id` int(32) not null comment '还款记录',
   `money` double default not null comment '还款额度',
   `repay_date` bigint comment '还款时间',
   
   `create_date` datetime comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `message`;

create table `message` (
  `id` int(32) not null auto_increment,
  `title` varchar(50) default '' comment '消息标题',
  `content` varchar(500) default '' comment '消息内容',
  `user_id` int(32) not null comment '所属用户',
  `is_list` int(2) default 1 comment '是否显示 1显示 2不显示',
  `publish_date` bigint comment '发布时间',
  
  `create_date` datetime comment '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_type`;

create table `product_type`(
    `id` int(32) not null auto_increment,
    `name` varchar(20) default comment '商品类型',
    
    `create_date` datetime comment '创建时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;

create table `product` (
	`id` int(32) not null auto_increment,
	`name` varchar(200) not null default '' comment '商品名称',
	`price` double not null default 0 comment '商品价格',
	`content` varchar(500) default '' comment '商品描述',
	
	`is_list`int(2) default 1 comment '是否上架 1:上架 2:下架',
	`create_date` datetime comment '创建时间',
	
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_images`;

create table `product_images`(
	`id` int(32) not null auto_increment,
	`product_id` int(32) not null comment '商品ID',
	`image_url` varchar(200) default '' comment '图片url',
	`height` double default 0 comment '图片高度',
	`width` double default 0 comment '图片宽度',
	`small_url` varchar(200) default '' comment '缩略图',
	
	`create_date` datetime comment '创建时间',
	 PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_comment`;

create table `product_comment`(
	`id` int(32) not null auto_increment,
	`product_id` int(32) not null comment '商品ID',
	`user_id` int(32) not null comment '评论用户ID',
	`content` varchar (500) default '' comment '评论内容',
	`score` int(2) default 5 comment '评论等级 1-5'
	
	`comment_date` bigint comment '评论事件',
	
	`create_date` datatime comment '创建时间',
	PRIMARY KEY (`id`)
)NGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;

create table `orders`(
	`id` int(32) not null auto_increment,
	`user_id` int(32) not null comment '所属用户',
	`product_id` int(32) not null comment '商品ID',
	
	`username` varchar(50) default '' comment '购买人姓名',
	`id_card` varchar(50) default '' comment '购买人身份证',
	`mobile`varchar(50) default '' comment '购买人手机号',
	`school_id` int(4) default 0 comment '购买人学校ID',
	`school_name` varchar(20) default 0 comment '购买人学校名称'
	`address` varchar(200) default 0 comment '收货地址'
	
	`order_date` bigint comment '下单时间',
	
	`create_date` datetime comment '创建时间',
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
	`company` varchar(50) default '' comment '公司名字',
	`position` varchar(50) default '' comment '职位',
	`counts` int(2) default 1 comment '招聘人数',
	`is_list` int(2) default 1 comment '是否发布 1:发布 2:不发布'
	
	`create_date` datetime comment '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `collection`;

create table `collection` (
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '用户ID',
  `product_id` int(32) not null comment '收藏商品ID',
  
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ads`;

create table `ads` (

)

DROP TABLE IF EXISTS `second_hand`;

create table `second_hand` (
	`id` int(32) not null auto_increment,
	`title` varchar(50) not null comment '标题',
	`content`varchar(500) default '' comment '二手商品描述',
	`price` double(2) default 0 comment '商品价格',
	`user_id` int(32) not null comment '所属用户ID',
	
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `second_hand_images` (
	`id` int(32) not null auto_increment,
	`second_hand_id` not null comment '所属二手物品',
	`image_url` varchar(200) default '' comment '图片url',
	`height` double default 0 comment '图片高度',
	`width` double default 0 comment '图片宽度',
	`small_url` varchar(200) default '' comment '缩略图',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `video`;

create table `video` (
	`id` int(32) not null auto_increment,
	`title` varchar(50) not null comment '视频标题',
	`content`varchar(500) default '' comment '视频介绍',
	`url` varchar(200) default '' comment '视频URL',
	`image_url` varchar(100) default '' comment '图片URL',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `helper`;

create table `helper` (
  `id` int(32) not null auto_increment,
  `type_id` int(2) not null comment '问题类型ID',
  `question` varchar(100) default '' comment '问题',
  `answer` varchar(500) default '' comment '答案',
  
  `sys_user_id` int(32) not null comment '',
  `create_date` datatime comment '创建时间',
  
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


