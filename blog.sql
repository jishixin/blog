/*
Navicat MySQL Data Transfer

Source Server         : 47.100.138.136
Source Server Version : 50727
Source Host           : 47.100.138.136:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-18 18:21:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('13', '', '', 'asdasdasdas', '2019-09-05 09:23:00', 'https://picsum.photos/id/10/800/450', '原创', '', '', '', 'asdasd111', '2019-09-05 09:23:00', '9', '32', '1', '描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `t_blog` VALUES ('14', '', '', 'asddddddddddd', '2019-09-05 10:17:12', 'https://picsum.photos/id/10/800/450', '原创', '', '\0', '', 'asdasdas', '2019-09-05 10:17:12', '8', '18', '1', null);

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('14', '4');
INSERT INTO `t_blog_tags` VALUES ('14', '3');
INSERT INTO `t_blog_tags` VALUES ('13', '4');
INSERT INTO `t_blog_tags` VALUES ('13', '3');
INSERT INTO `t_blog_tags` VALUES ('13', '2');
INSERT INTO `t_blog_tags` VALUES ('13', '1');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', 'https://picsum.photos/id/1011/100/100', 'sdasd', '2019-09-05 14:23:22', '916070312@qq.com', 'asda', null, '13', null, null);
INSERT INTO `t_comment` VALUES ('2', 'https://picsum.photos/id/1011/100/100', 'asdasdas', '2019-09-05 14:28:53', '15570034245@sina.cn', 'asdas', null, '13', '1', null);
INSERT INTO `t_comment` VALUES ('3', 'https://picsum.photos/id/1011/100/100', 'asdasd', '2019-09-05 14:33:13', '916070312@qq.com', 'asdasdas', null, '13', null, null);
INSERT INTO `t_comment` VALUES ('4', 'https://picsum.photos/id/1011/100/100', 'asdasdasd', '2019-09-05 14:49:54', '916070312@qq.com', 'asdasdasasdasd', null, '13', '1', null);
INSERT INTO `t_comment` VALUES ('5', 'https://picsum.photos/id/1011/100/100', 'sadasdasdasd', '2019-09-05 15:18:37', '916070312@qq.com', 'qwqe', null, '13', null, null);
INSERT INTO `t_comment` VALUES ('6', 'https://picsum.photos/id/1011/100/100', 'asdas', '2019-09-05 15:23:46', '916070312@qq.com', 'ffffff', null, '13', '5', null);
INSERT INTO `t_comment` VALUES ('7', 'https://picsum.photos/id/1011/100/100', 'sdffsdf', '2019-09-05 15:23:55', '916070312@qq.com', 'ffffff1111111111', null, '13', '5', null);
INSERT INTO `t_comment` VALUES ('8', 'https://picsum.photos/id/1005/100/100', 'asdas', '2019-09-05 15:42:19', '916070312@qq.com', '晨梦意志', null, '14', null, '');

-- ----------------------------
-- Table structure for t_comment_reply_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_reply_comments`;
CREATE TABLE `t_comment_reply_comments` (
  `comment_id` bigint(20) NOT NULL,
  `reply_comments_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_181tutafpifbbnwb6csxt0q71` (`reply_comments_id`),
  KEY `FK7wrsbnf1ayveyki6ocwaa07vx` (`comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment_reply_comments
-- ----------------------------

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', 'Java');
INSERT INTO `t_tag` VALUES ('2', 'JavaScript');
INSERT INTO `t_tag` VALUES ('3', 'CSS');
INSERT INTO `t_tag` VALUES ('4', 'Html');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('19', '认知升级');
INSERT INTO `t_type` VALUES ('18', '错误日志');
INSERT INTO `t_type` VALUES ('32', '开发者手册');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'https://picsum.photos/id/1011/100/100', '2019-09-03', '916070312@qq.com', '晨梦意志', 'e10adc3949ba59abbe56e057f20f883e', '1', '2019-09-03', 'jishixin');

-- ----------------------------
-- Table structure for t_user_blogs
-- ----------------------------
DROP TABLE IF EXISTS `t_user_blogs`;
CREATE TABLE `t_user_blogs` (
  `user_id` bigint(20) NOT NULL,
  `blogs_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_s6y547935lfsn0to4p2ai3t65` (`blogs_id`),
  KEY `FK4go78e209tolrpwnq6pj2j239` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_blogs
-- ----------------------------
