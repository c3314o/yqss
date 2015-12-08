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
  
  PRIMARY KEY (`adspublishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_bank_card`;

create table `users_bank_card` (
  `id` int(32) not null auto_increment,
  `user_id` int(32) not null comment '所属用户',
  `card_no` varchar(30) not null comment '卡号',
  `is_default` int(2) default 1 comment '是否默认 1默认 2非默认',
  `create_date` datetime comment '创建时间'
)

DROP TABLE IF EXISTS `message`;

create table `message` (
  `id` int(32) not null auto_increment,
  `title` varchar(50) default '' comment '消息标题',
  `content` varchar(500) default '' comment '消息内容',
  `user_id` int(32) not null comment '所属用户',
  `is_list` int(2) default 1 comment '是否显示 1显示 2不显示',
  `create_date` datetime comment '创建时间'
)

DROP TABLE IF EXISTS `product`;

create table `product` (
	`id` int(32) not null auto_increment,
	`name` varchar(200) not null default '' comment '商品名称',
	`price` double not null default 0 comment '商品价格',
	`content` varchar(500) default '' comment '商品描述',
	
	`is_list`int(2) default 1 comment '是否上架 1:上架 2:下架',
	`create_date` datetime comment '创建时间'
)

DROP TABLE IF EXISTS `product_images`;

create table `product_images`(
	`id` int(32) not null auto_increment,
	`product_id` int(32) not null comment '商品ID',
	`image_url` varchar(200) default '' comment '图片url',
	`height` double default 0 comment '图片高度',
	`width` double default 0 comment '图片宽度',
	`small_url` varchar(200) default '' comment '缩略图',
)

DROP TABLE IF EXISTS `orders`;

create table `orders`(
	`id` int(32) not null auto_increment,
	`user_id` int(32) not null comment '所属用户',
	`product_id` int(32) not null comment '商品ID',
	
	``
)
