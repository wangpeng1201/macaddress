/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : bill

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 21/10/2020 08:24:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `application_department` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单位',
  `customer` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `amount` int(0) NOT NULL COMMENT '申请数量',
  `applicant` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `application_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `release_date` datetime(0) NULL DEFAULT NULL COMMENT '发放日期',
  `director` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用转嫁承办人工号',
  `shift_cost_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用转嫁单号',
  `shift_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '转嫁费用金额',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(0:不显示，1:显示)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'mac地址申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES (1, 'q', 'q', 'q', 'PCA PM Dept', 30, '萬亞平', '2018-03-27 00:00:00', '2018-03-27 00:00:00', '', '未收費，為胡晶晶手工發放', NULL, 'root', '2020-10-17 15:11:30', '2020-10-17 15:11:30', NULL, 1);
INSERT INTO `application` VALUES (2, 'w', 'w', 'w', 'PCA PM Dept', 70, '萬亞平', '2020-10-20 00:00:00', '2020-10-20 00:00:00', '未收費，為胡晶晶手工發放', '', NULL, 'root', '2020-10-20 16:56:29', '2020-10-20 16:56:29', NULL, 1);
INSERT INTO `application` VALUES (3, '300ED5FFFA49', '300ED5FFFFFE', NULL, 'PE/EPDIII/CESBG', 1462, '周德剛', '2020-10-20 00:00:00', '2020-10-20 00:00:00', '費用轉嫁 3312.48 NTD  HH2018030885', 'HH2018030885', 3312.48, 'root', '2020-10-20 17:17:01', '2020-10-20 17:17:01', NULL, NULL);
INSERT INTO `application` VALUES (4, '002268FEF140', '002268FFFFFE', 'PE/EPDIII/CESBG', 'PE/EPDIII/CESBG', 69311, '周德剛', '2020-10-20 00:00:00', '2020-10-20 00:00:00', '費用轉嫁 3312.48 NTD  HH2018030885', '費用轉嫁 3312.48 NTD  HH2018030885', 3312.48, 'root', '2020-10-20 17:25:28', '2020-10-20 17:25:28', NULL, NULL);

-- ----------------------------
-- Table structure for livestock
-- ----------------------------
DROP TABLE IF EXISTS `livestock`;
CREATE TABLE `livestock`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `current_stock` int(0) NULL DEFAULT NULL COMMENT '实时库存总数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实时库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of livestock
-- ----------------------------

-- ----------------------------
-- Table structure for macaddress
-- ----------------------------
DROP TABLE IF EXISTS `macaddress`;
CREATE TABLE `macaddress`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `signs` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '以前6位作为标识',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `starting_inventory` int(0) NULL DEFAULT NULL COMMENT '初始库存[(结束mac的十进制-初始mac的十进制数)+1]',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updateDate` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(0:不显示，1:显示)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of macaddress
-- ----------------------------
INSERT INTO `macaddress` VALUES (1, '00016C', '00016C06A625', '00016C06A629', NULL, '2020-10-13 09:34:27', 'root', '2020-10-13 09:34:36', NULL, 1);
INSERT INTO `macaddress` VALUES (2, NULL, 'BC9830686430', 'BC9830686439', NULL, '2020-10-13 09:37:02', 'root', '2020-10-20 09:30:58', NULL, 1);
INSERT INTO `macaddress` VALUES (3, NULL, '1', '1', NULL, '2020-10-14 13:53:43', 'root', '2020-10-14 13:53:43', NULL, 1);
INSERT INTO `macaddress` VALUES (4, NULL, '2', '2', NULL, '2020-10-14 13:56:27', 'root', '2020-10-14 13:56:27', NULL, 1);
INSERT INTO `macaddress` VALUES (5, NULL, '3', '3', NULL, '2020-10-14 13:57:55', 'root', '2020-10-14 13:57:55', NULL, 1);
INSERT INTO `macaddress` VALUES (6, NULL, '4', '4', NULL, '2020-10-14 13:59:48', 'root', '2020-10-20 09:31:43', NULL, 1);
INSERT INTO `macaddress` VALUES (7, NULL, '5', '5', NULL, '2020-10-14 14:06:43', 'root', '2020-10-14 14:06:43', NULL, 1);
INSERT INTO `macaddress` VALUES (8, NULL, '8', '8', NULL, '2020-10-14 14:08:17', 'root', '2020-10-14 14:08:17', NULL, 1);
INSERT INTO `macaddress` VALUES (9, NULL, '9', '9', NULL, '2020-10-14 14:10:07', 'root', '2020-10-14 14:10:07', NULL, 1);
INSERT INTO `macaddress` VALUES (10, NULL, '10', '10', NULL, '2020-10-14 14:18:56', 'wangwu', '2020-10-14 14:18:56', NULL, 1);
INSERT INTO `macaddress` VALUES (11, NULL, '11', '11', NULL, '2020-10-14 14:20:43', 'root', '2020-10-14 14:20:43', NULL, 1);
INSERT INTO `macaddress` VALUES (12, NULL, '12', '12', NULL, '2020-10-14 14:30:35', 'root', '2020-10-14 14:30:35', NULL, 1);
INSERT INTO `macaddress` VALUES (13, NULL, '13', '13', NULL, '2020-10-14 14:40:06', 'root', '2020-10-14 14:40:06', NULL, 1);
INSERT INTO `macaddress` VALUES (14, NULL, '14', '14', NULL, '2020-10-14 14:41:57', 'root', '2020-10-14 14:41:57', NULL, 1);
INSERT INTO `macaddress` VALUES (15, NULL, '15', '15', NULL, '2020-10-14 14:42:07', 'root', '2020-10-14 14:42:07', NULL, 1);
INSERT INTO `macaddress` VALUES (16, NULL, '16', '16', NULL, '2020-10-14 18:56:01', 'root', '2020-10-20 13:55:56', NULL, 1);
INSERT INTO `macaddress` VALUES (17, NULL, '20', '20', NULL, '2020-10-16 09:08:53', 'wangwu', '2020-10-20 13:55:42', NULL, 1);
INSERT INTO `macaddress` VALUES (18, NULL, '21', '21', NULL, '2020-10-16 09:12:41', 'wangwu', '2020-10-20 13:54:32', NULL, 1);
INSERT INTO `macaddress` VALUES (19, NULL, 'D0278812FFD3', 'D0278812FFF5', NULL, '2020-10-16 10:46:37', 'root', '2020-10-16 10:46:37', NULL, 1);
INSERT INTO `macaddress` VALUES (20, NULL, '22', '22', NULL, '2020-10-20 15:26:19', 'root', '2020-10-20 15:26:19', NULL, 1);
INSERT INTO `macaddress` VALUES (21, NULL, '23', '23', NULL, '2020-10-20 15:27:35', 'root', '2020-10-20 15:27:35', NULL, 1);
INSERT INTO `macaddress` VALUES (22, NULL, '24', '24', NULL, '2020-10-20 15:27:53', 'root', '2020-10-20 15:27:53', NULL, 1);
INSERT INTO `macaddress` VALUES (23, NULL, 'F4939F000000', 'F4939F31FFFF', NULL, '2020-10-20 16:07:52', 'root', '2020-10-20 16:07:52', NULL, 1);
INSERT INTO `macaddress` VALUES (24, NULL, 'F4939FF91B2D', 'F4939FFFFFFE', NULL, '2020-10-20 16:10:45', 'root', '2020-10-20 16:10:45', NULL, 1);
INSERT INTO `macaddress` VALUES (25, NULL, 'F4939FE91B00', 'F4939FF91AFF', 1048576, '2020-10-20 16:14:05', 'root', '2020-10-20 16:14:21', NULL, 1);
INSERT INTO `macaddress` VALUES (26, NULL, 'F4939F9CCFC0', 'F4939FCA967F', 3000000, '2020-10-20 16:15:15', 'root', '2020-10-20 16:15:15', NULL, 1);
INSERT INTO `macaddress` VALUES (27, '28C13', '28C13C989680', '28C13CC65D3F', 3000000, '2020-10-20 16:22:58', 'root', '2020-10-20 16:22:58', NULL, 1);
INSERT INTO `macaddress` VALUES (28, '28C13C', '28C13CE4E1C0', '28C13CFFFFFE', 1777215, '2020-10-20 16:24:32', 'root', '2020-10-20 16:24:32', NULL, 1);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `stock` int(0) NULL DEFAULT NULL COMMENT '总库存数量',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'mac地址起始创建信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL COMMENT '性别：1 女  2 男',
  `birthday` datetime(0) NULL DEFAULT NULL,
  `user_type` int(0) NULL DEFAULT NULL COMMENT '1管理员  2经理  3普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '系统管理员', '11', 1, '2003-11-12 00:00:00', 1);
INSERT INTO `user` VALUES (2, 'lisi', '李四', '123', 2, '1983-11-12 00:00:00', 2);
INSERT INTO `user` VALUES (3, 'wangwu', '王五', '123', 2, '1953-11-12 00:00:00', 3);
INSERT INTO `user` VALUES (4, 'zhangsan', '张三', '123', 1, '1973-11-12 00:00:00', 3);
INSERT INTO `user` VALUES (6, 'a', 'www', '123', 2, '2018-12-12 00:00:00', 2);
INSERT INTO `user` VALUES (8, 'b', 'www', '111', NULL, '2018-12-12 00:00:00', NULL);
INSERT INTO `user` VALUES (10, 'c', '4444', '111', 1, '2018-12-12 00:00:00', 2);
INSERT INTO `user` VALUES (11, 'd', 'd', '111', 1, '2018-12-12 00:00:00', 2);
INSERT INTO `user` VALUES (12, '2aaaa', 'e', '111', 2, '2018-12-12 00:00:00', 1);

SET FOREIGN_KEY_CHECKS = 1;
