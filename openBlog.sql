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

 Date: 02/19/2017 16:19:12 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `category_table`
-- ----------------------------
DROP TABLE IF EXISTS `category_table`;
CREATE TABLE `category_table` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(20) NOT NULL COMMENT '分类名称',
  `category_visable` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '是否可见',
  `category_create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `link_table`
-- ----------------------------
DROP TABLE IF EXISTS `link_table`;
CREATE TABLE `link_table` (
  `link_id` varchar(20) NOT NULL,
  `link_url` varchar(255) NOT NULL COMMENT 'URL',
  `link_name` varchar(255) NOT NULL COMMENT '名称',
  `link_creater_id` varchar(20) NOT NULL COMMENT '创建者ID',
  `link_description` varchar(255) DEFAULT NULL COMMENT '描述',
  `link_visible` enum('true','false') NOT NULL DEFAULT 'true' COMMENT '是否可见',
  `link_creater_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `option_table`
-- ----------------------------
DROP TABLE IF EXISTS `option_table`;
CREATE TABLE `option_table` (
  `option_id` varchar(20) NOT NULL,
  `option_name` varchar(50) NOT NULL COMMENT '名称',
  `option_value` longtext NOT NULL COMMENT '值',
  PRIMARY KEY (`option_id`)
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
  `post_comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `author_id` varchar(20) NOT NULL COMMENT '创建者ID',
  `category_id` int(11) unsigned NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tag_table`
-- ----------------------------
DROP TABLE IF EXISTS `tag_table`;
CREATE TABLE `tag_table` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(20) NOT NULL COMMENT '名称',
  `tag_create_time` datetime NOT NULL COMMENT '创建时间',
  `post_id` varchar(20) NOT NULL COMMENT '文章ID',
  PRIMARY KEY (`tag_id`)
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
  `user_status` int(11) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
