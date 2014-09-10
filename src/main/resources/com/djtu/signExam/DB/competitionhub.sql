/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : competitionhub

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-09-10 17:32:16
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt
-- ----------------------------
INSERT INTO `t_compt` VALUES ('1', '这是测试', '这是参赛说明', '这是赛事内容', '单位1', '1', '比赛地点', '2014年9月xx号', '2014-09-03 00:00:00', '2014-09-30 00:00:00', '0', '有学校资助哦', '0', '需要上传很多作品', '0', '3', '3', '0', '0', '0', '0', '0', '2014-09-10 15:44:32', '1', '0');

-- ----------------------------
-- Table structure for t_compt_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_compt_attachment`;
CREATE TABLE `t_compt_attachment` (
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
  `teamNo` varchar(200) DEFAULT NULL COMMENT '队伍唯一标识',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_compt_attachment
-- ----------------------------

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
INSERT INTO `t_news` VALUES ('6', '最后一次测试', '2014-09-10 15:44:12', '0', '没错 这是最后一次测试', '0', '0');
INSERT INTO `t_news` VALUES ('7', 'new news', '2014-08-27 14:37:51', '1', '<img src=\"\\resources\\kindedtorUploads\\img\\20140827143750_220.jpg\" alt=\"\" />', '0', '1');

-- ----------------------------
-- Table structure for t_signin
-- ----------------------------
DROP TABLE IF EXISTS `t_signin`;
CREATE TABLE `t_signin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sk_t_compt` int(11) DEFAULT NULL COMMENT '外键：赛事表ID',
  `sk_t_student` int(11) DEFAULT NULL,
  `comptName` varchar(1000) DEFAULT NULL COMMENT '赛事名称',
  `teamNo` varchar(200) DEFAULT NULL COMMENT '小组编号，用于区分每个小组',
  `name` varchar(200) DEFAULT NULL,
  `number` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `cellphone` varchar(200) DEFAULT NULL,
  `creditCard` varchar(500) DEFAULT NULL,
  `worksName` varchar(200) DEFAULT NULL,
  `worksIntro` varchar(1000) DEFAULT NULL,
  `advisor` varchar(1000) DEFAULT NULL,
  `isValid` tinyint(1) DEFAULT NULL COMMENT '是否已经通过审核',
  `isDelete` tinyint(1) DEFAULT NULL,
  `isLeader` tinyint(1) DEFAULT NULL COMMENT '是否为组长',
  `isReward` tinyint(1) DEFAULT NULL COMMENT '是否获奖',
  `isHelpCredit` tinyint(1) DEFAULT NULL COMMENT '对消除学分有帮助',
  `createtime` datetime DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL COMMENT '用于学院选择排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_signin
-- ----------------------------
INSERT INTO `t_signin` VALUES ('3', '1', '1', '这是测试3', '78fba91e-6d05-4ff2-81e2-1f01d4b9731a', 'JOECHOW', '1018160229', '787449527@qq.com', '18640886602', '0', '0', '0', '0', '0', '0', '0', '0', '1', '2014-09-10 13:44:31', '0');
INSERT INTO `t_signin` VALUES ('6', '1', '1', '这是测试6', '78fba91e-6d05-4ff2-81e2-1f01d4b9731a', 'JOECHOW', '1018160229', '787449527@qq.com', '18640886602', '0', '0', '0', '0', '0', '0', '0', '0', '1', '2014-09-10 13:44:31', '0');
INSERT INTO `t_signin` VALUES ('7', '1', '1', '这是测试7', '78fba91e-6d05-4ff2-81e2-1f01d4b9731a', 'JOECHOW', '1018160229', '787449527@qq.com', '18640886602', '0', '0', '0', '0', '0', '0', '0', '0', '1', '2014-09-10 13:44:31', '0');
INSERT INTO `t_signin` VALUES ('9', '1', '5', '这是测试', 'ff86b5d5-b9f6-4ea2-9f55-4f190c235d37', 'JOE', '1018160229', '123@qq.com', '18640886602', '6222310064225560', '0', '0', '0', '0', '0', '1', '0', '0', '2014-09-10 16:42:50', '0');

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
  `a_userPwd` varchar(200) DEFAULT NULL,
  `a_email` varchar(200) DEFAULT NULL,
  `a_type` int(11) DEFAULT NULL,
  `isSuper` tinyint(1) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_user_admin
-- ----------------------------
INSERT INTO `t_user_admin` VALUES ('1', 'JOECHOW', '1', 'nn', '18640886602', '123456', '787449527@qq.com', '1', '1', '1', '0');

-- ----------------------------
-- Table structure for t_user_student
-- ----------------------------
DROP TABLE IF EXISTS `t_user_student`;
CREATE TABLE `t_user_student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cellphone` varchar(200) DEFAULT NULL,
  `userNo` varchar(200) DEFAULT NULL,
  `userPwd` varchar(200) DEFAULT NULL,
  `creditCard` varchar(200) DEFAULT NULL,
  `academy` varchar(200) DEFAULT NULL,
  `profession` varchar(200) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `activateCode` varchar(200) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `email` varchar(200) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='latin1_swedish_ci';

-- ----------------------------
-- Records of t_user_student
-- ----------------------------
INSERT INTO `t_user_student` VALUES ('1', 'JOECHOW', '0', '18640886602', '1018160229', '123456', '0', '软件学院', '软件工程', '0', '5a65a613-7d19-4180-9766-d9a97efc5d14', '0', '787449527@qq.com');
INSERT INTO `t_user_student` VALUES ('5', 'JOE', '0', '18640886602', '1018160229', '123456', '0', '软件学院', '软件工程', '0', '5a65a613-7d19-4180-9766-d9a97efc5d14', '0', '123@qq.com');
