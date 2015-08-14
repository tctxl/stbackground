/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-14 13:51:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ability
-- ----------------------------
DROP TABLE IF EXISTS `t_ability`;
CREATE TABLE `t_ability` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `ability_name` varchar(255) DEFAULT NULL,
  `ability_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `_id_ability_name` (`_id`,`ability_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ability
-- ----------------------------
INSERT INTO `t_ability` VALUES ('1', 'MAIL_SEND', '邮件发送功能');

-- ----------------------------
-- Table structure for t_configure
-- ----------------------------
DROP TABLE IF EXISTS `t_configure`;
CREATE TABLE `t_configure` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `configure_name` varchar(255) DEFAULT NULL,
  `configure_value` text,
  `description` text,
  `version` int(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_configure
-- ----------------------------
INSERT INTO `t_configure` VALUES ('2', 'SSDB_IP', 'localhost', 'ssdb地址', '100');
INSERT INTO `t_configure` VALUES ('3', 'SSDB_PWD', '5432112345', 'ssdb密码', '100');
INSERT INTO `t_configure` VALUES ('4', 'SSDB_PORT', '8888', 'ssdb端口', '100');
INSERT INTO `t_configure` VALUES ('20', 'PROJECT_NAME', 'Seed后台模板', '项目名称', '100');
INSERT INTO `t_configure` VALUES ('21', 'CURRENT_THEME', 'seed.background', '当前主题', '100');
INSERT INTO `t_configure` VALUES ('22', 'BASE_URL', 'http://192.168.1.178:8080', '资源基础路径', '100');

-- ----------------------------
-- Table structure for t_level
-- ----------------------------
DROP TABLE IF EXISTS `t_level`;
CREATE TABLE `t_level` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) DEFAULT NULL,
  `level_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `_id` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_level
-- ----------------------------
INSERT INTO `t_level` VALUES ('1', '1', '最低等级');
INSERT INTO `t_level` VALUES ('2', '2', null);
INSERT INTO `t_level` VALUES ('3', '3', null);
INSERT INTO `t_level` VALUES ('4', '4', null);
INSERT INTO `t_level` VALUES ('5', '5', null);
INSERT INTO `t_level` VALUES ('6', '6', null);
INSERT INTO `t_level` VALUES ('7', '7', null);
INSERT INTO `t_level` VALUES ('8', '8', null);
INSERT INTO `t_level` VALUES ('9', '9', '最高等级');

-- ----------------------------
-- Table structure for t_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE `t_rule` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(255) DEFAULT NULL,
  `rule_description` varchar(255) DEFAULT NULL COMMENT '规则描述',
  `rule_parent` int(255) DEFAULT NULL COMMENT '父级ID',
  `rule_icon` text COMMENT '图标',
  `rule_type` smallint(1) DEFAULT '0' COMMENT '1为末尾项',
  `rule_action` text COMMENT '连接',
  `accessory` text COMMENT '附加项',
  `accessory_name` varchar(255) DEFAULT NULL COMMENT '附加项名字',
  `module` varchar(255) DEFAULT NULL,
  `seq` int(255) DEFAULT NULL,
  `disable` int(1) DEFAULT '0' COMMENT '1为无效',
  PRIMARY KEY (`_id`),
  KEY `_id` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rule
-- ----------------------------
INSERT INTO `t_rule` VALUES ('1', 'GROUP_MANAGE', '群组管理', '1', 'Public/imgs/icon_group_1.png', '0', null, 'Group/find', '选择群租：', 'sidebar', '1', '0');
INSERT INTO `t_rule` VALUES ('2', 'ADD_GROUP', '新增群租', '1', 'Public/imgs/icon_group_1.png', '1', 'group/add', null, null, 'sidebar', '1', '0');
INSERT INTO `t_rule` VALUES ('3', 'DEL_GROUP', '解散群租', '1', 'Public/imgs/icon_group_1.png', '1', 'group/break', null, null, 'sidebar', '2', '0');
INSERT INTO `t_rule` VALUES ('4', 'MEMBER_MANAGE', '成员管理', '1', 'Public/imgs/icon_group_1.png', '1', 'group/member', null, null, 'sidebar', '3', '0');
INSERT INTO `t_rule` VALUES ('5', 'SELF_INFO', '个人信息', '9', 'Public/imgs/default_user_img.png', '1', 'group/member', '', null, 'sidebar', '2', '0');
INSERT INTO `t_rule` VALUES ('6', 'LOGOUT', '注销登录', '9', 'Public/imgs/icon_group_logout.png', '1', 'login/logout', null, null, 'sidebar', '9', '0');
INSERT INTO `t_rule` VALUES ('8', 'CONFIGURATION', '配置管理', '9', 'Public/imgs/icon_group_1.png', '1', 'dashboard/tables/configure.html', null, null, 'sidebar', '8', '0');
INSERT INTO `t_rule` VALUES ('9', 'SYSTEM', '系统设置', '9', null, '0', null, null, null, 'sidebar', '1', '0');
INSERT INTO `t_rule` VALUES ('10', 'USERS', '用户管理', '9', null, '0', 'dashboard/tables/user.html', null, null, 'sidebar', '5', '0');
INSERT INTO `t_rule` VALUES ('11', 'THEME', '主题管理', '11', null, '0', '', null, null, 'sidebar', '0', '0');
INSERT INTO `t_rule` VALUES ('12', 'THEME_CONGIURE', '主题配置', '11', null, '1', 'dashboard/theme.html', null, null, 'sidebar', '1', '0');

-- ----------------------------
-- Table structure for t_rule_level
-- ----------------------------
DROP TABLE IF EXISTS `t_rule_level`;
CREATE TABLE `t_rule_level` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_id` int(11) DEFAULT NULL,
  `level_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `t_rule_level_ibfk_1` (`rule_id`),
  KEY `t_rule_level_ibfk_2` (`level_id`),
  CONSTRAINT `t_rule_level_ibfk_1` FOREIGN KEY (`rule_id`) REFERENCES `t_rule` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_rule_level_ibfk_2` FOREIGN KEY (`level_id`) REFERENCES `t_level` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_rule_level
-- ----------------------------
INSERT INTO `t_rule_level` VALUES ('1', '1', '1');
INSERT INTO `t_rule_level` VALUES ('2', '4', '1');
INSERT INTO `t_rule_level` VALUES ('3', '2', '9');
INSERT INTO `t_rule_level` VALUES ('4', '3', '9');
INSERT INTO `t_rule_level` VALUES ('5', '5', '1');
INSERT INTO `t_rule_level` VALUES ('6', '6', '1');
INSERT INTO `t_rule_level` VALUES ('8', '8', '8');
INSERT INTO `t_rule_level` VALUES ('9', '9', '1');
INSERT INTO `t_rule_level` VALUES ('10', '10', '9');
INSERT INTO `t_rule_level` VALUES ('11', '11', '1');
INSERT INTO `t_rule_level` VALUES ('12', '12', '1');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `_id` bigint(21) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `level_id` int(255) DEFAULT NULL COMMENT '级别',
  `group_mask` int(255) DEFAULT NULL COMMENT '组掩码',
  `last_login_time` datetime DEFAULT NULL,
  `proportion` int(255) DEFAULT '0',
  `hidein` int(1) DEFAULT '0' COMMENT '0显示 1不显示',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `_id` (`_id`) USING BTREE,
  KEY `level` (`level_id`),
  CONSTRAINT `t_users_ibfk_1` FOREIGN KEY (`level_id`) REFERENCES `t_level` (`_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('10000', 'shijunfan@163.com', 'e10adc3949ba59abbe56e057f20f883e', '小石头', 'Public/imgs/w4.jpg', '13818500083', '9', '0', '2015-03-11 18:46:27', '100', '0');
