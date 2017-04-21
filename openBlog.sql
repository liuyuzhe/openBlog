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

 Date: 04/21/2017 21:33:31 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `link_table`
-- ----------------------------
DROP TABLE IF EXISTS `link_table`;
CREATE TABLE `link_table` (
  `link_id` varchar(20) NOT NULL,
  `link_url` varchar(255) NOT NULL COMMENT 'URL',
  `link_name` varchar(255) NOT NULL COMMENT '名称',
  `link_description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_visible` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '是否可见',
  `link_create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` varchar(20) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`link_id`),
  UNIQUE KEY `creator_id` (`creator_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `option_table`
-- ----------------------------
DROP TABLE IF EXISTS `option_table`;
CREATE TABLE `option_table` (
  `option_name` varchar(50) NOT NULL COMMENT '名称',
  `option_value` longtext NOT NULL COMMENT '值',
  PRIMARY KEY (`option_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_table`
-- ----------------------------
DROP TABLE IF EXISTS `post_table`;
CREATE TABLE `post_table` (
  `post_id` varchar(20) NOT NULL,
  `post_title` text NOT NULL COMMENT '标题',
  `post_excerpt` text NOT NULL COMMENT '摘要',
  `post_content` longtext NOT NULL COMMENT '内容',
  `post_create_time` datetime NOT NULL COMMENT '创建时间',
  `post_update_time` datetime NOT NULL COMMENT '更新时间',
  `post_status` enum('publish','draft','private','inherit') NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  `post_comment_status` enum('open','close') NOT NULL DEFAULT 'open' COMMENT '评论状态',
  `post_type` enum('post','attachment') NOT NULL DEFAULT 'post' COMMENT '文章类型',
  `post_mime_type` varchar(100) NOT NULL COMMENT '附件类型',
  `post_comment_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '评论数',
  `post_read_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '阅读数',
  `post_spot_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数',
  `creator_id` varchar(20) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `creator_id` (`creator_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `relationship_table`
-- ----------------------------
DROP TABLE IF EXISTS `relationship_table`;
CREATE TABLE `relationship_table` (
  `target_id` varchar(20) NOT NULL,
  `term_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`target_id`,`term_id`),
  UNIQUE KEY `target_id` (`target_id`) USING BTREE,
  UNIQUE KEY `term_id` (`term_id`) USING BTREE,
  CONSTRAINT `link_id` FOREIGN KEY (`target_id`) REFERENCES `link_table` (`link_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_id` FOREIGN KEY (`target_id`) REFERENCES `post_table` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `term_id` FOREIGN KEY (`term_id`) REFERENCES `term_table` (`term_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `term_table`
-- ----------------------------
DROP TABLE IF EXISTS `term_table`;
CREATE TABLE `term_table` (
  `term_id` int(11) unsigned NOT NULL,
  `term_name` varchar(20) NOT NULL COMMENT '名称',
  `term_slug` varchar(20) NOT NULL COMMENT '缩略名',
  `term_type` enum('category','tag') NOT NULL COMMENT '类型',
  `term_count` int(10) unsigned NOT NULL COMMENT '所属内容数',
  PRIMARY KEY (`term_id`),
  UNIQUE KEY `term_slug` (`term_slug`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(60) NOT NULL COMMENT '用户名',
  `user_password` varchar(255) NOT NULL COMMENT '密码',
  `user_nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `user_avatar_url` varchar(100) DEFAULT NULL COMMENT '头像URL',
  `user_register_time` datetime NOT NULL COMMENT '注册时间',
  `user_activate_time` datetime NOT NULL COMMENT '活跃时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  UNIQUE KEY `user_email` (`user_email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
