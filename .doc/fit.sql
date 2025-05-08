/*
SQLyog v10.2 
MySQL - 5.7.9 : Database - fit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fit` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `fit`;

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(40) DEFAULT NULL COMMENT '权限名称',
  `desc` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `etime` datetime DEFAULT NULL COMMENT '修改时间',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `isys` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_authorities` */

insert  into `sys_authorities`(`id`,`ctime`,`name`,`desc`,`etime`,`enabled`,`isys`) values (1,'2017-11-11 00:00:00','ROOT','超级管理员',NULL,1,1),(2,'2017-11-11 00:00:00','SYSTEM','系统管理员',NULL,1,0),(3,'2017-11-11 00:00:00','ADMIN','管理员',NULL,1,0),(4,'2017-11-11 00:00:00','USER','用户',NULL,1,0);

/*Table structure for table `sys_authorities_res` */

DROP TABLE IF EXISTS `sys_authorities_res`;

CREATE TABLE `sys_authorities_res` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_id` bigint(30) DEFAULT NULL COMMENT '权限id',
  `res_id` bigint(30) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `auth_id` (`auth_id`),
  KEY `res_id` (`res_id`),
  CONSTRAINT `sys_authorities_res_ibfk_1` FOREIGN KEY (`auth_id`) REFERENCES `sys_authorities` (`id`),
  CONSTRAINT `sys_authorities_res_ibfk_2` FOREIGN KEY (`res_id`) REFERENCES `sys_resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_authorities_res` */

insert  into `sys_authorities_res`(`id`,`auth_id`,`res_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16);

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(30) NOT NULL DEFAULT '0' COMMENT '父ID',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `icon` varchar(40) DEFAULT NULL COMMENT '图标',
  `type` varchar(1) DEFAULT NULL COMMENT '类型,1:url 2:method',
  `url` varchar(200) DEFAULT NULL COMMENT '链接',
  `sort` int(10) DEFAULT NULL COMMENT '优先权',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `levels` int(1) DEFAULT '0' COMMENT '层级',
  `ismenu` varchar(1) DEFAULT 'N' COMMENT '是否是菜单',
  `etime` datetime DEFAULT NULL COMMENT '修改时间',
  `enabled` int(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `isys` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `sys_resources` */

insert  into `sys_resources`(`id`,`pid`,`ctime`,`name`,`icon`,`type`,`url`,`sort`,`description`,`levels`,`ismenu`,`etime`,`enabled`,`isys`) values (1,0,'2017-11-11 00:00:00','后台管理',NULL,'U','#',1,'用户登陆后台跳转页',1,'Y',NULL,1,0),(2,1,'2017-11-11 00:00:00','系统管理','glyphicon glyphicon-cog','U','#',1,'系统管理',2,'Y',NULL,1,0),(3,2,'2017-11-11 00:00:00','用户列表',NULL,'U','/admin/user/list',1,'用户管理列表页',3,'Y',NULL,1,0),(4,3,'2017-11-23 23:35:56','用户保存',NULL,'U','/admin/user/save',1,'用户管理保存更新',4,'N',NULL,1,0),(5,2,'2017-11-23 23:40:24','角色列表',NULL,'U','/admin/role/list',1,'角色管理的列表',3,'Y',NULL,1,0),(6,5,'2017-12-01 23:58:52','角色保存',NULL,'U','/admin/role/save',1,'角色保存与更新',4,'N',NULL,1,0),(7,2,'2017-12-02 01:14:14','资源列表',NULL,'U','/admin/res/list',1,'资源管理列表',3,'Y',NULL,1,0),(8,7,'2017-12-02 02:10:32','资源保存',NULL,'U','/admin/res/save',1,'资源管理的保存',4,'N',NULL,1,0),(9,1,'2017-11-11 00:00:00','课程管理','glyphicon  glyphicon-book','U','#',3,'课程管理',2,'Y',NULL,1,0),(10,9,'2017-11-11 00:00:00','课程列表',NULL,'U','/admin/class/list',1,'课程列表',3,'Y',NULL,1,0),(11,1,'2017-11-11 00:00:00','首页管理','glyphicon  glyphicon-heart','U','#',2,'首页管理',2,'Y',NULL,1,0),(12,11,'2017-11-11 00:00:00','顶部列表',NULL,'U','/admin/top/list',1,'模型列表',3,'Y',NULL,1,0),(13,1,'2024-10-25 12:50:24','小说管理',NULL,'U','#',4,'小说管理',2,'Y',NULL,1,0),(14,13,'2024-10-25 12:51:23','任务列表',NULL,'U','/admin/crawl/singleTask/list',1,'小说爬虫任务列表',3,'Y',NULL,1,0),(15,13,'2024-10-25 12:52:25','书源列表',NULL,'U','/admin/crawl/source/list',2,'爬虫书源',3,'Y',NULL,1,0),(16,13,'2024-10-25 12:52:25','小说类列表',NULL,'U','/admin/crawl/category/list',NULL,'小说类列表',3,'Y',NULL,1,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名字',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色说明',
  `etime` datetime DEFAULT NULL COMMENT '修改时间',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `isys` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`ctime`,`role_name`,`role_desc`,`etime`,`enabled`,`isys`) values (1,'2017-11-11 00:00:00','ROLE_ROOT','拥有管理后台最高权限',NULL,1,1),(2,'2017-11-11 00:00:00','ROLE_SYSTEM','拥有管理后台系统权限',NULL,1,1),(3,'2017-11-11 00:00:00','ROLE_ADMIN','拥有管理后台操作权限',NULL,1,1),(4,'2017-11-11 00:00:00','ROLE_USER_TEST','用来测试的用户角色',NULL,1,0),(5,'2017-11-11 00:00:00','ROLE_USER','普通用户角色',NULL,1,0);

/*Table structure for table `sys_role_auth` */

DROP TABLE IF EXISTS `sys_role_auth`;

CREATE TABLE `sys_role_auth` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(30) DEFAULT NULL COMMENT '角色id',
  `auth_id` bigint(30) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `auth_id` (`auth_id`),
  CONSTRAINT `sys_role_auth_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_auth_ibfk_2` FOREIGN KEY (`auth_id`) REFERENCES `sys_authorities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_role_auth` */

insert  into `sys_role_auth`(`id`,`role_id`,`auth_id`) values (1,1,1),(2,4,4);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(10) DEFAULT NULL COMMENT '角色ID',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `username` varchar(30) DEFAULT NULL COMMENT '登陆用户名(登陆号)',
  `password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `desc` varchar(50) DEFAULT NULL COMMENT '描述',
  `etime` datetime DEFAULT NULL COMMENT '修改时间',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `isys` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级用户 0非1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`rid`,`ctime`,`name`,`username`,`password`,`desc`,`etime`,`enabled`,`isys`) values (1,1,'2017-11-11 00:00:00','管理员','admin','21232f297a57a5a743894a0e4a801fc3','系统超级管理员',NULL,1,1),(2,4,'2017-11-11 00:00:00','测试用户','user','21232f297a57a5a743894a0e4a801fc3','测试用户',NULL,1,0),(3,NULL,'2018-03-31 18:43:40','3333','111',NULL,'1123333',NULL,0,0),(4,NULL,'2018-03-31 18:52:39','444','444',NULL,'44111',NULL,0,0),(5,NULL,'2018-03-31 22:34:25','11','11',NULL,'11',NULL,0,0),(6,NULL,'2018-03-31 22:34:36','222','22',NULL,'22',NULL,0,0),(7,NULL,'2018-03-31 22:35:19','666','666',NULL,'666',NULL,0,0),(8,NULL,'2018-03-31 22:35:37','555','555',NULL,'555',NULL,0,0),(9,NULL,'2018-03-31 22:35:48','777','777',NULL,'777',NULL,1,0),(10,NULL,'2018-03-31 22:36:05','888','888',NULL,'888',NULL,0,0),(11,NULL,'2018-03-31 22:36:13','999','999',NULL,'999',NULL,0,0),(12,NULL,'2018-04-02 10:12:31','0022','000',NULL,'000<br>',NULL,0,0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(30) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(30) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色用户关系表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`role_id`,`user_id`) values (1,1,1),(2,4,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
