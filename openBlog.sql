/*
 Navicat Premium Data Transfer

 Source Server         : openBlog
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : openBlog

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 05/25/2017 00:59:14 AM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `attach_table`
-- ----------------------------
DROP TABLE IF EXISTS `attach_table`;
CREATE TABLE `attach_table` (
  `attach_id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_slug` varchar(100) NOT NULL COMMENT '链接地址',
  `attach_name` varchar(20) NOT NULL COMMENT '名称',
  `attach_type` varchar(20) NOT NULL COMMENT '类型',
  `attach_create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`attach_id`),
  UNIQUE KEY `attach_slug` (`attach_slug`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `option_table`
-- ----------------------------
DROP TABLE IF EXISTS `option_table`;
CREATE TABLE `option_table` (
  `option_name` varchar(20) NOT NULL COMMENT '名称',
  `option_value` varchar(255) NOT NULL COMMENT '值',
  PRIMARY KEY (`option_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_table`
-- ----------------------------
DROP TABLE IF EXISTS `post_table`;
CREATE TABLE `post_table` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_slug` varchar(20) NOT NULL COMMENT '链接地址',
  `post_thumb_url` varchar(100) DEFAULT NULL COMMENT '缩略图URL',
  `post_title` text NOT NULL COMMENT '标题',
  `post_excerpt` text NOT NULL COMMENT '摘要',
  `post_content` longtext NOT NULL COMMENT '内容',
  `post_create_time` datetime NOT NULL COMMENT '创建时间',
  `post_update_time` datetime NOT NULL COMMENT '更新时间',
  `post_type` varchar(20) NOT NULL DEFAULT 'post' COMMENT '文章类型(''post'',''page'')',
  `post_fmt_type` varchar(20) NOT NULL DEFAULT '' COMMENT '输出格式(''markdown'',''html'')',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态(''publish'',''draft'',''private'')',
  `post_comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论状态(''open'',''close'')',
  `post_comment_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
  `post_read_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '阅读数',
  `post_spot_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `creator_id` int(11) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `post_slug` (`post_slug`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `relationship_table`
-- ----------------------------
DROP TABLE IF EXISTS `relationship_table`;
CREATE TABLE `relationship_table` (
  `target_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  PRIMARY KEY (`target_id`,`term_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `term_table`
-- ----------------------------
DROP TABLE IF EXISTS `term_table`;
CREATE TABLE `term_table` (
  `term_id` int(11) NOT NULL AUTO_INCREMENT,
  `term_slug` varchar(100) NOT NULL COMMENT '链接地址',
  `term_name` varchar(20) NOT NULL COMMENT '名称',
  `term_type` varchar(20) NOT NULL COMMENT '类型(''category'',''tag'')',
  `term_count` int(10) unsigned NOT NULL COMMENT '所属内容数',
  PRIMARY KEY (`term_id`),
  UNIQUE KEY `term_slug` (`term_slug`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `user_password` varchar(50) NOT NULL COMMENT '密码',
  `user_nickname` varchar(50) NOT NULL COMMENT '昵称',
  `user_email` varchar(50) NOT NULL COMMENT '邮箱',
  `user_avatar_url` varchar(100) DEFAULT NULL COMMENT '头像URL',
  `user_register_time` datetime NOT NULL COMMENT '注册时间',
  `user_activate_time` datetime NOT NULL COMMENT '活跃时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `user_email` (`user_email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
