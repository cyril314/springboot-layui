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

/*Table structure for table `lms_exam_type` */

DROP TABLE IF EXISTS `lms_exam_type`;

CREATE TABLE `lms_exam_type` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(30) DEFAULT NULL COMMENT '上级ID',
  `MOLD` int(11) DEFAULT NULL COMMENT '类型: 0-考试,1-考卷',
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `NOTES` text COLLATE utf8_unicode_ci COMMENT '备注',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ADMIN_AUTH` int(1) DEFAULT NULL COMMENT '管理权限',
  `GRADE_AUTH` int(1) DEFAULT NULL COMMENT '阅卷权限',
  `QUERY_AUTH` int(1) DEFAULT NULL COMMENT '查询权限',
  `SUPER_AUTH` int(1) DEFAULT NULL COMMENT '超级权限',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='考试类型表';

/*Data for the table `lms_exam_type` */

/*Table structure for table `lms_paper` */

DROP TABLE IF EXISTS `lms_paper`;

CREATE TABLE `lms_paper` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `UUID` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'uuid()',
  `EXAM_TYPE_ID` bigint(30) DEFAULT NULL COMMENT '业务分类ID',
  `ADVICE_TIME` int(11) DEFAULT NULL COMMENT '建议答题时间',
  `TITLE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '考卷名称',
  `INTRO` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '考卷简介',
  `NOTES` text COLLATE utf8_unicode_ci COMMENT '考卷说明',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='考卷';

/*Data for the table `lms_paper` */

insert  into `lms_paper`(`ID`,`CTIME`,`CUSER`,`UUID`,`EXAM_TYPE_ID`,`ADVICE_TIME`,`TITLE`,`INTRO`,`NOTES`,`SORT`,`ENABLED`,`ETIME`,`EUSER`) values (1,'2025-12-01 17:17:44',1,'97678948-c205-11f0-81f1-fcaa144e',NULL,10,'默认测试答卷','默认','默认',0,1,'2025-12-01 17:52:15',1),(2,'2025-12-01 17:17:44',1,'b239086a-c205-11f0-81f1-fcaa144e',NULL,NULL,'测试答卷','测试答卷','测试答卷',1,0,'2025-12-01 17:25:06',1);

/*Table structure for table `lms_room` */

DROP TABLE IF EXISTS `lms_room`;

CREATE TABLE `lms_room` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `UUID` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'uuid()',
  `IMG_ID` bigint(30) DEFAULT NULL COMMENT '考场图标ID',
  `NAME` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '考场标题',
  `CONTENT` text COLLATE utf8_unicode_ci COMMENT '考场说明',
  `NOTES` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '考场备注',
  `ENABLED` int(1) DEFAULT '0' COMMENT '考场状态: 0-停用,1-新建,2-发布,3-结束,4-归档''',
  `EXAM_TYPE_ID` bigint(30) DEFAULT NULL COMMENT '考试类型ID',
  `EXAM_MODE` int(1) DEFAULT '0' COMMENT '答卷模式: 0-标准答题模式,1-随机抽取模式,2-习题练习模式,3-只读学习模式',
  `TIME_MODE` int(1) DEFAULT '0' COMMENT '时间类型: 0-永久,1-限时',
  `TIME_LEN` int(5) DEFAULT NULL COMMENT '答题时长(分钟)',
  `TIME_ON` datetime DEFAULT NULL COMMENT '开始时间',
  `TIME_END` datetime DEFAULT NULL COMMENT '结束时间',
  `EXAMINEE_TYPE` int(1) DEFAULT NULL COMMENT '答题类型',
  `EXAMINEE_MODE` int(1) DEFAULT '0' COMMENT '答题人员模式: 0-任何人员,1-指定人员,2-匿名答题',
  `EXAMINEE_COUNT` int(1) DEFAULT '1' COMMENT '答题次数',
  `SUBJECT_SORT_MODE` tinyint(1) DEFAULT '0' COMMENT '答卷展示题目排序: 0-固定,1-随机',
  `SUBJECT_OPT_SORT_MODE` tinyint(1) DEFAULT '0' COMMENT '答卷展示选项排序: 0-固定,1-随机',
  `ADJUDGE_MODE` int(1) DEFAULT '0' COMMENT '判卷类型: 0-用户交卷后,1-全场收卷后',
  `MARK_MODE` int(1) DEFAULT '0' COMMENT '阅卷类型: 0-自动/人工,1-自动,2-人工',
  `MARK_SHOW_MODE` int(1) DEFAULT '0' COMMENT '成绩类型: 0-得分&答卷,1-得分,2-答卷',
  `MARK_TIME_MODE` int(1) DEFAULT '0' COMMENT '成绩时间类型: 0-不发布,1-全场阅卷后,2-答卷阅卷后',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='答题室表';

/*Data for the table `lms_room` */

insert  into `lms_room`(`ID`,`CTIME`,`CUSER`,`UUID`,`IMG_ID`,`NAME`,`CONTENT`,`NOTES`,`ENABLED`,`EXAM_TYPE_ID`,`EXAM_MODE`,`TIME_MODE`,`TIME_LEN`,`TIME_ON`,`TIME_END`,`EXAMINEE_TYPE`,`EXAMINEE_MODE`,`EXAMINEE_COUNT`,`SUBJECT_SORT_MODE`,`SUBJECT_OPT_SORT_MODE`,`ADJUDGE_MODE`,`MARK_MODE`,`MARK_SHOW_MODE`,`MARK_TIME_MODE`,`SORT`,`ETIME`,`EUSER`) values (1,NULL,1,'2c084b62-c24a-11f0-81f1-fcaa144eb7f0',NULL,'测试答题室',NULL,NULL,0,NULL,0,1,10,NULL,NULL,NULL,0,1,0,1,0,0,0,0,NULL,NULL,NULL);

/*Table structure for table `lms_subject` */

DROP TABLE IF EXISTS `lms_subject`;

CREATE TABLE `lms_subject` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `UUID` varchar(50) COLLATE utf8_unicode_ci DEFAULT 'uuid()' COMMENT 'UUID',
  `TYPE_ID` bigint(30) NOT NULL COMMENT '考题类型ID',
  `TYPE_NAME` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '考题类型名称',
  `TITLE` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '题目',
  `CONTENT` text COLLATE utf8_unicode_ci COMMENT '题目描述',
  `ANSWER_ID` bigint(30) DEFAULT NULL COMMENT '答案ID',
  `NOTES` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `VERSION` int(5) DEFAULT NULL COMMENT '版本号',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='考题表';

/*Data for the table `lms_subject` */

insert  into `lms_subject`(`ID`,`CTIME`,`CUSER`,`UUID`,`TYPE_ID`,`TYPE_NAME`,`TITLE`,`CONTENT`,`ANSWER_ID`,`NOTES`,`VERSION`,`SORT`,`ENABLED`,`ETIME`,`EUSER`) values (1,'2025-12-01 11:31:34',1,'UUID()',1,'默认题库','考试系统可以正常答题？',NULL,NULL,NULL,0,1,0,NULL,NULL);

/*Table structure for table `lms_subject_answers` */

DROP TABLE IF EXISTS `lms_subject_answers`;

CREATE TABLE `lms_subject_answers` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `UUID` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'UUID',
  `QUESTION_ID` bigint(30) NOT NULL COMMENT '考题ID',
  `CORRECT` tinyint(1) DEFAULT '0' COMMENT '是否正确',
  `ANSWER` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '答案',
  `ANSWER_CONTENT` text COLLATE utf8_unicode_ci COMMENT '答案结果',
  `SCORE` int(5) DEFAULT NULL COMMENT '得分权重',
  `NOTES` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='考题答案表';

/*Data for the table `lms_subject_answers` */

/*Table structure for table `lms_subject_know` */

DROP TABLE IF EXISTS `lms_subject_know`;

CREATE TABLE `lms_subject_know` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(30) DEFAULT '0' COMMENT '上级ID',
  `NAME` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '科目名称',
  `CRUK` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '关键字',
  `NOTES` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='题库科目表';

/*Data for the table `lms_subject_know` */

insert  into `lms_subject_know`(`ID`,`CTIME`,`CUSER`,`PID`,`NAME`,`CRUK`,`NOTES`,`SORT`,`ENABLED`,`ETIME`,`EUSER`) values (1,NULL,NULL,0,'演示科目','material',NULL,NULL,1,'2025-12-01 22:28:07',1),(2,NULL,NULL,1,'演示知识点','KNOW',NULL,NULL,1,'2025-12-02 10:49:13',1);

/*Table structure for table `lms_subject_material` */

DROP TABLE IF EXISTS `lms_subject_material`;

CREATE TABLE `lms_subject_material` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `UUID` char(36) COLLATE utf8_unicode_ci DEFAULT 'uuid()',
  `TITLE` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `CONTENT` text COLLATE utf8_unicode_ci COMMENT '正文',
  `NOTES` text COLLATE utf8_unicode_ci COMMENT '备注',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='题库材料';

/*Data for the table `lms_subject_material` */

insert  into `lms_subject_material`(`ID`,`CTIME`,`CUSER`,`UUID`,`TITLE`,`CONTENT`,`NOTES`,`ENABLED`,`ETIME`,`EUSER`) values (1,'2017-11-11 00:00:00',1,'1dbb53f6-ceba-11f0-8745-f8cab851439b','演示材料','<p>\r\n	白日依山尽，\r\n</p>\r\n<p>\r\n	黄河入海流。\r\n</p>\r\n<p>\r\n	欲穷千里目，\r\n</p>\r\n<p>\r\n	更上一层楼。\r\n</p>','演示材料',1,NULL,NULL),(2,'2017-11-11 00:00:00',1,'2df6c93e-cebc-11f0-8745-f8cab851439b','望庐山瀑布','<p>\r\n	日照香炉生紫烟，\r\n</p>\r\n<p>\r\n	遥看瀑布挂前川。\r\n</p>\r\n<p>\r\n	飞流直下三千尺，\r\n</p>\r\n<p>\r\n	疑是银河落九天。\r\n</p>',NULL,0,'2025-12-01 22:06:04',1);

/*Table structure for table `lms_subject_type` */

DROP TABLE IF EXISTS `lms_subject_type`;

CREATE TABLE `lms_subject_type` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(30) DEFAULT '0' COMMENT '上级ID',
  `TYPE_NAME` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `NOTES` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='考题分类';

/*Data for the table `lms_subject_type` */

insert  into `lms_subject_type`(`ID`,`CTIME`,`CUSER`,`PID`,`TYPE_NAME`,`NOTES`,`SORT`,`ENABLED`,`ETIME`,`EUSER`) values (1,'2025-12-01 10:48:43',1,0,'默认题库','默认题库',1,1,NULL,NULL),(2,'2025-12-01 10:57:04',1,1,'练习一','练习一',1,1,'2025-12-01 11:11:53',1);

/*Table structure for table `lms_top` */

DROP TABLE IF EXISTS `lms_top`;

CREATE TABLE `lms_top` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `MODEL` int(1) NOT NULL COMMENT '模型: 0-横幅,1-课程,',
  `TITLE` varchar(128) DEFAULT NULL COMMENT '标题',
  `CONTENT` text COMMENT '内容',
  `URL` text COMMENT '访问地址',
  `IMG_ID` bigint(30) DEFAULT NULL COMMENT '图片ID',
  `MOLD` int(1) DEFAULT NULL COMMENT '类型: 1-首页推荐,2-横幅推荐',
  `VISITS` int(11) DEFAULT '0' COMMENT '访问量',
  `SORT` int(1) DEFAULT NULL COMMENT '排序',
  `ENABLED` tinyint(1) DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='首页顶部内容';

/*Data for the table `lms_top` */

insert  into `lms_top`(`ID`,`CTIME`,`CUSER`,`MODEL`,`TITLE`,`CONTENT`,`URL`,`IMG_ID`,`MOLD`,`VISITS`,`SORT`,`ENABLED`,`ETIME`,`EUSER`) values (1,'2020-08-27 17:03:11',1,1,'使用Prometheus实践基于Spring Boot监控告警体系','课程须知本课程适合有一定Java Web开发经验，熟悉Spring Boot的开发者。老师告诉你能学到什么？\r\n\r\n1、MDD的理论和微服务四层轻量监控体系 \r\n\r\n2、Prometheus架构设计 \r\n\r\n3、Prometheus+Grafana安装搭建 \r\n\r\n4、常用Metrics命令及使用技巧 \r\n\r\n5、SpringBoot 2.X集成Prometheus+Grafana \r\n\r\n6、如何配置可视化监控大盘及告警 \r\n\r\n7、Prometheus集群部署方式和一些踩坑经验',NULL,NULL,1,0,1,1,NULL,0),(2,'2020-08-27 17:03:11',1,0,'banner1','test',NULL,NULL,2,0,1,1,NULL,0),(3,'2020-08-27 17:03:11',1,0,'banner2','test2',NULL,NULL,2,0,2,1,NULL,0);

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `NAME` varchar(40) DEFAULT NULL COMMENT '权限名称',
  `NOTES` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `ISYS` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_authorities` */

insert  into `sys_authorities`(`ID`,`CTIME`,`CUSER`,`NAME`,`NOTES`,`ENABLED`,`ISYS`,`ETIME`,`EUSER`) values (1,'2017-11-11 00:00:00',NULL,'ROOT','超级管理员',1,1,NULL,NULL),(2,'2017-11-11 00:00:00',NULL,'SYSTEM','系统管理员',1,0,NULL,NULL),(3,'2017-11-11 00:00:00',NULL,'ADMIN','管理员',1,0,NULL,NULL),(4,'2017-11-11 00:00:00',NULL,'USER','用户',1,0,NULL,NULL);

/*Table structure for table `sys_authorities_res` */

DROP TABLE IF EXISTS `sys_authorities_res`;

CREATE TABLE `sys_authorities_res` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `AUTH_ID` bigint(30) DEFAULT NULL COMMENT '权限id',
  `RES_ID` bigint(30) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`ID`),
  KEY `auth_id` (`AUTH_ID`),
  KEY `res_id` (`RES_ID`),
  CONSTRAINT `sys_authorities_res_ibfk_1` FOREIGN KEY (`AUTH_ID`) REFERENCES `sys_authorities` (`ID`),
  CONSTRAINT `sys_authorities_res_ibfk_2` FOREIGN KEY (`RES_ID`) REFERENCES `sys_resources` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_authorities_res` */

insert  into `sys_authorities_res`(`ID`,`AUTH_ID`,`RES_ID`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23);

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(30) DEFAULT '0' COMMENT '父部门id',
  `PIDS` varchar(512) DEFAULT NULL COMMENT '父级ids',
  `SIMPLE_NAME` varchar(45) DEFAULT NULL COMMENT '简称',
  `FULL_NAME` varchar(255) DEFAULT NULL COMMENT '全称',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '描述',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`ID`,`CTIME`,`CUSER`,`PID`,`PIDS`,`SIMPLE_NAME`,`FULL_NAME`,`NOTES`,`VERSION`,`SORT`,`ETIME`,`EUSER`) values (1,'2019-04-01 00:00:00',1,0,'-1','总公司','总公司','总公司',NULL,1,'2025-11-27 17:44:21',1),(2,'2019-04-01 00:00:00',1,1,'1','开发部','开发部','开发部',NULL,2,'2025-11-27 17:44:12',1),(3,'2019-04-01 00:00:00',1,1,'1','运营部','运营部','运营部',NULL,3,'2025-11-27 17:44:04',1),(4,'2019-04-01 00:00:00',1,1,'1','战略部','战略部','战略部',NULL,4,'2025-11-27 17:43:55',1),(5,'2019-05-05 13:03:21',1,0,NULL,'财务部','财务部','财务部',NULL,5,NULL,NULL);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `ID` bigint(30) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(20) DEFAULT NULL COMMENT '父级字典id',
  `NAME` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `CODE` varchar(255) DEFAULT NULL COMMENT '字典的编码',
  `SIGN` varchar(1) DEFAULT NULL COMMENT '是否系统标识',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '描述',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`ID`,`CTIME`,`CUSER`,`PID`,`NAME`,`CODE`,`SIGN`,`NOTES`,`SORT`,`ETIME`,`EUSER`) values (1,'2019-04-01 00:00:00',NULL,0,'性别','SEX',NULL,NULL,0,NULL,NULL),(2,'2019-04-01 00:00:00',NULL,1,'男','M',NULL,NULL,1,NULL,NULL),(3,'2019-04-01 00:00:00',NULL,1,'女','F',NULL,NULL,2,NULL,NULL),(4,'2019-04-01 00:00:00',NULL,0,'状态','STATUS',NULL,NULL,0,NULL,NULL),(5,'2019-04-01 00:00:00',NULL,4,'启用','ENABLE',NULL,NULL,1,NULL,NULL),(6,'2019-04-01 00:00:00',NULL,4,'禁用','DISABLE',NULL,NULL,2,NULL,NULL),(7,'2019-04-01 00:00:00',NULL,0,'账号状态','ACCOUNT_STATUS',NULL,NULL,0,NULL,NULL),(8,'2019-04-01 00:00:00',NULL,7,'启用','ENABLE',NULL,NULL,1,NULL,NULL),(9,'2019-04-01 00:00:00',NULL,7,'冻结','FREEZE',NULL,NULL,2,NULL,NULL),(10,'2019-04-01 00:00:00',NULL,7,'已删除','DELETED',NULL,NULL,3,NULL,NULL),(11,'2019-04-01 00:00:00',1,0,'是否删除','DEL_FLAG',NULL,'用于数据库中是否删除的标记',0,NULL,NULL),(12,'2019-04-01 00:00:00',1,11,'已经删除','Y',NULL,NULL,1,NULL,NULL),(13,'2019-04-01 00:00:00',1,11,'未删除','N',NULL,NULL,2,NULL,NULL),(14,'2019-04-01 00:00:00',1,1,'保密','O','N','性别保密',3,NULL,NULL),(15,'2019-04-01 00:00:00',1,0,'系统配置类型','SYSTEM_TYPE','Y',NULL,0,NULL,NULL),(16,'2019-04-01 00:00:00',1,15,'系统服务类型','1','N',NULL,1,'2019-05-23 18:25:48',1),(17,'2019-04-01 00:00:00',1,15,'系统云端类型','2','N',NULL,2,'2019-05-24 10:34:32',1),(19,'2022-06-16 17:09:54',1,0,'获奖项','AWARDS','N','',0,NULL,NULL),(20,'2022-06-16 17:15:35',1,19,'一等奖','FIRST_PRIZE','N','',1,NULL,NULL),(21,'2022-06-16 17:15:52',1,19,'二等奖','SECOND_PRIZE','N','',2,NULL,NULL),(22,'2022-06-16 17:16:07',1,19,'三等奖','THIRD_PRIZE','N','',3,NULL,NULL),(23,'2022-06-16 17:16:22',1,19,'四等奖','FOURTH_PRIZE','N','',4,NULL,NULL),(24,'2022-06-16 17:16:37',1,19,'五等奖','FIFTH_PRIZE','N','',5,NULL,NULL),(25,'2022-06-16 17:16:55',1,19,'特等奖','SPECIAL_PRIZE','N','',6,NULL,NULL),(26,'2022-06-17 17:27:19',1,0,'活动类型','RECORD_TYPE','N','',0,NULL,NULL),(27,'2022-06-17 17:28:42',1,26,'抽奖','1','N','',1,NULL,NULL);

/*Table structure for table `sys_operation_log` */

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `LOG_TYPE` varchar(32) DEFAULT NULL COMMENT '日志类型(字典)',
  `LOG_NAME` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `USER_ID` bigint(65) DEFAULT NULL COMMENT '用户id',
  `CLASS_NAME` varchar(255) DEFAULT NULL COMMENT '类名称',
  `METHOD` text COMMENT '方法名称',
  `SUCCEED` varchar(32) DEFAULT NULL COMMENT '是否成功(字典)',
  `MESSAGE` text COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志';

/*Data for the table `sys_operation_log` */

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `PID` bigint(30) NOT NULL DEFAULT '0' COMMENT '父ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `ICON` varchar(40) DEFAULT NULL COMMENT '图标',
  `TYPE` varchar(1) DEFAULT NULL COMMENT '类型,1:url 2:method',
  `URL` varchar(200) DEFAULT NULL COMMENT '链接',
  `SORT` int(10) DEFAULT NULL COMMENT '优先权',
  `NOTES` varchar(200) DEFAULT NULL COMMENT '描述',
  `LEVELS` int(1) DEFAULT '0' COMMENT '层级',
  `ISMENU` varchar(1) DEFAULT 'N' COMMENT '是否是菜单',
  `ENABLED` int(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `ISYS` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `sys_resources` */

insert  into `sys_resources`(`ID`,`CTIME`,`CUSER`,`PID`,`NAME`,`ICON`,`TYPE`,`URL`,`SORT`,`NOTES`,`LEVELS`,`ISMENU`,`ENABLED`,`ISYS`,`ETIME`,`EUSER`) values (1,'2017-11-11 00:00:00',NULL,0,'后台管理',NULL,'U','#',1,'用户登陆后台跳转页',1,'Y',1,0,NULL,NULL),(2,'2017-11-11 00:00:00',NULL,1,'系统管理',NULL,'U','#',1,'系统管理',2,'Y',1,0,NULL,NULL),(3,'2017-11-11 00:00:00',NULL,2,'用户列表',NULL,'U','/admin/user/list',1,'用户管理列表页',3,'Y',1,0,NULL,NULL),(4,'2017-11-11 00:00:00',NULL,3,'用户保存',NULL,'U','/admin/user/save',1,'用户管理保存更新',4,'N',1,0,NULL,NULL),(5,'2017-11-11 00:00:00',NULL,2,'角色列表',NULL,'U','/admin/role/list',2,'角色管理的列表',3,'Y',1,0,NULL,NULL),(6,'2017-11-11 00:00:00',NULL,5,'角色保存',NULL,'U','/admin/role/save',1,'角色保存与更新',4,'N',1,0,NULL,NULL),(7,'2017-11-11 00:00:00',NULL,2,'资源列表',NULL,'U','/admin/res/list',3,'资源管理列表',3,'Y',1,0,NULL,NULL),(8,'2017-11-11 00:00:00',NULL,7,'资源保存',NULL,'U','/admin/res/save',1,'资源管理的保存',4,'N',1,0,NULL,NULL),(9,'2017-11-11 00:00:00',NULL,2,'部门列表',NULL,'U','/admin/dept/list',4,'部门管理列表',3,'Y',1,0,NULL,NULL),(10,'2017-11-11 00:00:00',NULL,9,'部门保存',NULL,'U','/admin/dept/save',1,'部门保存与更新',4,'N',1,0,NULL,NULL),(11,'2017-11-11 00:00:00',NULL,2,'字典列表',NULL,'U','/admin/dict/list',5,'字典管理列表',3,'Y',1,0,NULL,NULL),(12,'2017-11-11 00:00:00',NULL,11,'字典保存',NULL,'U','/admin/dict/save',1,'字典保存与更新',4,'N',1,0,NULL,NULL),(13,'2017-11-11 00:00:00',NULL,2,'日志列表',NULL,'U','/admin/log/list',6,'日志列表列表',3,'Y',1,0,NULL,NULL),(14,'2017-11-11 00:00:00',NULL,1,'首页管理',NULL,'U','#',2,'首页管理',2,'Y',1,0,NULL,NULL),(15,'2017-11-11 00:00:00',NULL,14,'顶部列表',NULL,'U','/admin/lms/top/list',1,'模型列表',3,'Y',1,0,NULL,NULL),(16,'2017-11-11 00:00:00',NULL,1,'题库管理',NULL,'U','#',4,'题库管理',2,'Y',1,0,NULL,NULL),(17,'2017-11-11 00:00:00',NULL,16,'考题列表',NULL,'U','/admin/lms/subject/list',1,'考题列表',3,'Y',1,0,NULL,NULL),(18,'2017-11-11 00:00:00',NULL,16,'答案列表',NULL,'U','/admin/lms/subject/answer/list',2,'答案列表',3,'Y',1,0,NULL,NULL),(19,'2017-11-11 00:00:00',NULL,16,'类型列表',NULL,'U','/admin/lms/subject/type/list',3,'考题类型列表',3,'Y',1,0,NULL,NULL),(20,'2017-11-11 00:00:00',NULL,16,'题库科目',NULL,'U','/admin/lms/subject/know/list',4,'题库科目列表',3,'Y',1,0,NULL,NULL),(21,'2017-11-11 00:00:00',NULL,16,'题库材料',NULL,'U','/admin/lms/subject/material/list',5,'题库材料列表',3,'Y',1,0,NULL,NULL),(22,'2017-11-11 00:00:00',NULL,1,'答题室管理',NULL,'U','#',5,'答题室管理',2,'Y',1,0,NULL,NULL),(23,'2017-11-11 00:00:00',NULL,22,'答题室列表',NULL,'U','/admin/lms/room/list',1,'答题室列表',3,'Y',1,0,NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名字',
  `NOTES` varchar(100) DEFAULT NULL COMMENT '角色说明',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被禁用 0禁用1正常',
  `ISYS` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级权限 0非1是',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`ID`,`CTIME`,`CUSER`,`ROLE_NAME`,`NOTES`,`ENABLED`,`ISYS`,`ETIME`,`EUSER`) values (1,'2017-11-11 00:00:00',NULL,'ROLE_ROOT','拥有管理后台最高权限',1,1,NULL,NULL),(2,'2017-11-11 00:00:00',NULL,'ROLE_SYSTEM','拥有管理后台系统权限',1,1,NULL,NULL),(3,'2017-11-11 00:00:00',NULL,'ROLE_ADMIN','拥有管理后台操作权限',1,1,NULL,NULL),(4,'2017-11-11 00:00:00',NULL,'ROLE_USER_TEST','用来测试的用户角色',1,0,NULL,NULL),(5,'2017-11-11 00:00:00',NULL,'ROLE_USER','普通用户角色',1,0,NULL,NULL);

/*Table structure for table `sys_role_auth` */

DROP TABLE IF EXISTS `sys_role_auth`;

CREATE TABLE `sys_role_auth` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_ID` bigint(30) DEFAULT NULL COMMENT '角色id',
  `AUTH_ID` bigint(30) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`ID`),
  KEY `role_id` (`ROLE_ID`),
  KEY `auth_id` (`AUTH_ID`),
  CONSTRAINT `sys_role_auth_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`),
  CONSTRAINT `sys_role_auth_ibfk_2` FOREIGN KEY (`AUTH_ID`) REFERENCES `sys_authorities` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

/*Data for the table `sys_role_auth` */

insert  into `sys_role_auth`(`ID`,`ROLE_ID`,`AUTH_ID`) values (1,1,1),(2,4,4);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CTIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CUSER` bigint(30) DEFAULT NULL COMMENT '创建人',
  `RID` bigint(10) DEFAULT NULL COMMENT '角色ID',
  `NAME` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `USERNAME` varchar(30) DEFAULT NULL COMMENT '登陆用户名(登陆号)',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `NOTES` varchar(50) DEFAULT NULL COMMENT '描述',
  `ENABLED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '禁用状态: 0-禁用,1-正常',
  `ISYS` int(1) NOT NULL DEFAULT '0' COMMENT '是否是超级用户 0非1是',
  `ETIME` datetime DEFAULT NULL COMMENT '修改时间',
  `EUSER` bigint(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`ID`,`CTIME`,`CUSER`,`RID`,`NAME`,`USERNAME`,`PASSWORD`,`NOTES`,`ENABLED`,`ISYS`,`ETIME`,`EUSER`) values (1,'2017-11-11 00:00:00',NULL,1,'管理员','admin','21232f297a57a5a743894a0e4a801fc3','系统超级管理员',1,1,NULL,NULL),(2,'2017-11-11 00:00:00',NULL,4,'测试用户','user','21232f297a57a5a743894a0e4a801fc3','测试用户',1,0,NULL,NULL),(3,'2017-11-11 00:00:00',NULL,NULL,'3','3','21232f297a57a5a743894a0e4a801fc3','1123333',1,0,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
