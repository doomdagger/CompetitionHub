/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : competitionhub

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-09-02 10:12:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_compt
-- ----------------------------
DROP TABLE IF EXISTS `t_compt`;
CREATE TABLE `t_compt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `comptIntro` text,
  `content` text,
  `sponsor` varchar(200) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `comptDate` varchar(200) DEFAULT NULL,
  `deadline_s` datetime DEFAULT NULL,
  `deadline_e` datetime DEFAULT NULL,
  `isSupport` tinyint(1) DEFAULT NULL,
  `supportIntro` text,
  `sp_intro` text,
  `needFileIntro` text,
  `sp_type` tinyint(1) DEFAULT NULL,
  `sp_num` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusIntro` text,
  `sk_t_userAdmin` int(11) DEFAULT NULL,
  `is_vaild` tinyint(1) DEFAULT NULL,
  `valid_result` varchar(1000) DEFAULT NULL COMMENT '审核意见',
  `is_delete` tinyint(1) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `isNeedFile` tinyint(1) DEFAULT NULL,
  `isTop` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt
-- ----------------------------

-- ----------------------------
-- Table structure for t_compt_attchment
-- ----------------------------
DROP TABLE IF EXISTS `t_compt_attchment`;
CREATE TABLE `t_compt_attchment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sk_t_compt` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `introduction` text,
  `createtime` datetime DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '0为学生报名，1为赛事',
  `sk_t_signup` int(11) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  `is_valid` tinyint(1) DEFAULT NULL,
  `savepath` varchar(2000) DEFAULT NULL COMMENT '保存路径',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt_attchment
-- ----------------------------
INSERT INTO `t_compt_attchment` VALUES ('1', '1', 'Chrysanthemum.jpg', '0', '2014-08-28 14:31:20', '1', '0', '0', '0', '\\resources\\UserUpload\\docs\\Chrysanthemum.jpg');
INSERT INTO `t_compt_attchment` VALUES ('2', '1', 'Penguins.jpg', '0', '2014-08-28 14:32:17', '1', '0', '0', '0', '\\resources\\UserUpload\\docs\\Penguins.jpg');
INSERT INTO `t_compt_attchment` VALUES ('3', '11', 'Chrysanthemum.jpg', '0', '2014-08-28 14:47:41', '1', '0', '0', '0', '\\resources\\UserUpload\\docs\\Chrysanthemum.jpg');

-- ----------------------------
-- Table structure for t_compt_calendar
-- ----------------------------
DROP TABLE IF EXISTS `t_compt_calendar`;
CREATE TABLE `t_compt_calendar` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sk_t_compt` int(11) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` text,
  `createtime` date DEFAULT NULL,
  `is_valid` tinyint(1) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt_calendar
-- ----------------------------

-- ----------------------------
-- Table structure for t_compt_reward
-- ----------------------------
DROP TABLE IF EXISTS `t_compt_reward`;
CREATE TABLE `t_compt_reward` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sk_t_compt` int(11) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `is_valid` tinyint(1) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt_reward
-- ----------------------------

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `sk_t_userAdmin` int(11) DEFAULT NULL,
  `content` text,
  `is_delete` tinyint(1) DEFAULT NULL,
  `is_top` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('2', 'hello two', '2014-08-20 15:51:01', '0', '<p>test case toe<br/></p>', '0', '0');
INSERT INTO `t_news` VALUES ('3', 'my competition', '2014-08-20 15:51:05', '0', '<p>let me try<br/></p>', '0', '0');
INSERT INTO `t_news` VALUES ('4', 'new news', '2014-08-21 16:41:29', '0', '<p>this is not toTop news<br/></p>', '0', '1');
INSERT INTO `t_news` VALUES ('5', '提交成功了吗', '2014-08-20 14:24:49', '0', '你到底想怎么样', '0', '0');
INSERT INTO `t_news` VALUES ('6', '最后一次测试', '2014-08-21 16:41:11', '0', '没错 这是最后一次测试', '0', '0');
INSERT INTO `t_news` VALUES ('7', 'new news', '2014-08-27 14:37:51', '1', '<img src=\"\\resources\\kindedtorUploads\\img\\20140827143750_220.jpg\" alt=\"\" />', '0', '1');

-- ----------------------------
-- Table structure for t_signup
-- ----------------------------
DROP TABLE IF EXISTS `t_signup`;
CREATE TABLE `t_signup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sk_t_userStudent` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `number` varchar(200) DEFAULT NULL,
  `cellphone` varchar(200) DEFAULT NULL,
  `creditCard` varchar(500) DEFAULT NULL,
  `reward` tinyint(1) DEFAULT NULL,
  `worksName` varchar(200) DEFAULT NULL,
  `worksIntro` varchar(200) DEFAULT NULL,
  `advisor` varchar(200) DEFAULT NULL,
  `is_valid` tinyint(1) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_signup
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_user_admin`;
CREATE TABLE `t_user_admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `u_username` varchar(200) DEFAULT NULL,
  `u_usertype` varchar(200) DEFAULT NULL,
  `u_image` varchar(200) DEFAULT NULL,
  `u_cellphone` varchar(200) DEFAULT NULL,
  `u_userPwd` varchar(200) DEFAULT NULL,
  `a_email` varchar(200) DEFAULT NULL,
  `a_type` int(11) DEFAULT NULL,
  `a_isSuper` tinyint(1) DEFAULT NULL,
  `a_isActive` tinyint(1) NOT NULL,
  `a_isDelete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_user_admin
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_student
-- ----------------------------
DROP TABLE IF EXISTS `t_user_student`;
CREATE TABLE `t_user_student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `u_username` varchar(200) DEFAULT NULL,
  `u_image` varchar(200) DEFAULT NULL,
  `u_cellphone` varchar(200) DEFAULT NULL,
  `a_userNo` varchar(200) DEFAULT NULL,
  `a_userPwd` varchar(200) DEFAULT NULL,
  `u_creditCard` varchar(200) DEFAULT NULL,
  `u_academy` varchar(200) DEFAULT NULL,
  `u_profession` varchar(200) DEFAULT NULL,
  `a_isActive` tinyint(1) DEFAULT NULL,
  `a_activateCode` varchar(200) DEFAULT NULL,
  `a_isDelete` tinyint(1) DEFAULT NULL,
  `a_email` varchar(200) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_user_student
-- ----------------------------
INSERT INTO `t_user_student` VALUES ('4', '周荣辉', '0', '18640886602', '1018160229', '123456', '0', '0', '软件工程', '0', 'bbe22a5e-f96c-4351-94be-6eb4e821edb7', '0', '787449527@qq.com');
