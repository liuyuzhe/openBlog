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

 Date: 02/12/2017 23:10:37 PM
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `category_table`
-- ----------------------------
DROP TABLE IF EXISTS `category_table`;
CREATE TABLE `category_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_table`
-- ----------------------------
DROP TABLE IF EXISTS `post_table`;
CREATE TABLE `post_table` (
  `id` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `excerpt` varchar(100) NOT NULL,
  `content` mediumtext NOT NULL,
  `createTime` datetime NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
