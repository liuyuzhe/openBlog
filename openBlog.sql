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

 Date: 02/15/2017 23:41:45 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `category_table`
-- ----------------------------
DROP TABLE IF EXISTS `category_table`;
CREATE TABLE `category_table` (
  `id` varchar(20) NOT NULL,
  `category_name` varchar(20) NOT NULL COMMENT '''分类名称''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_table`
-- ----------------------------
DROP TABLE IF EXISTS `post_table`;
CREATE TABLE `post_table` (
  `id` varchar(20) NOT NULL,
  `post_title` text NOT NULL COMMENT '标题',
  `post_excerpt` text NOT NULL COMMENT '摘要',
  `post_content` longtext NOT NULL COMMENT '内容',
  `post_create_time` datetime NOT NULL COMMENT '创建时间',
  `post_modified_time` datetime NOT NULL COMMENT '修改时间',
  `post_status` varchar(20) NOT NULL DEFAULT 'publish' COMMENT '文章状态',
  `post_comment_status` varchar(20) NOT NULL DEFAULT 'open' COMMENT '评论状态',
  `post_type` varchar(20) NOT NULL DEFAULT 'post' COMMENT '文章类型',
  `post_mime_type` varchar(100) NOT NULL COMMENT '文件类型',
  `post_comment_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '评论数',
  `post_author_Id` varchar(20) NOT NULL COMMENT '作者ID',
  `post_category_Id` int(11) unsigned NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `id` varchar(20) NOT NULL,
  `user_name` varchar(60) NOT NULL COMMENT '用户名',
  `user_password` varchar(255) NOT NULL COMMENT '密码',
  `user_nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `user_avatar_url` varchar(100) DEFAULT NULL COMMENT '头像URL',
  `user_register_time` datetime NOT NULL COMMENT '注册时间',
  `user_status` int(11) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
