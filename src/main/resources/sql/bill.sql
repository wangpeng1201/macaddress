/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : bill

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 25/01/2021 16:02:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束mac地址',
  `application_department` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请单位',
  `customer` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户',
  `amount` int NOT NULL COMMENT '申请数量',
  `applicant` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人',
  `application_date` datetime NULL DEFAULT NULL COMMENT '申请日期',
  `release_date` datetime NULL DEFAULT NULL COMMENT '发放日期',
  `director` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用转嫁承办人工号',
  `shift_cost_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '费用转嫁单号',
  `shift_cost` decimal(20, 2) NULL DEFAULT NULL COMMENT '转嫁费用金额',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '申请状态(0:不显示，1：新建申请，2：分发mac地址成功，3：分发mac地址失败）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'mac地址申请单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES (1, NULL, NULL, 'DTSA/DPD/HW/PM', '武汉PM ', 360, '项红', '2020-11-13 15:01:38', '2020-11-13 15:01:40', NULL, NULL, NULL, 'ROOT', '2020-11-13 15:01:38', '2020-11-13 15:01:38', NULL, 2);
INSERT INTO `application` VALUES (2, NULL, NULL, 'EMD', 'EMD', 500000, '陈胜', '2020-11-13 15:02:15', '2020-11-13 15:02:17', NULL, NULL, NULL, 'ROOT', '2020-11-13 15:02:15', '2020-11-13 15:02:15', NULL, 2);
INSERT INTO `application` VALUES (3, NULL, NULL, '華為白牌服務器', '龙华', 50000, '黃文輝', '2020-12-03 13:51:50', '2020-12-03 13:52:51', NULL, NULL, NULL, 'ROOT', '2020-12-03 13:51:50', '2020-12-03 13:51:50', NULL, 2);
INSERT INTO `application` VALUES (4, NULL, NULL, 'D01-3F(PCEBG-IPBD L6)', '重慶富士康', 110000, '鄭   清', '2020-12-09 13:52:32', '2020-12-09 13:54:04', NULL, NULL, NULL, 'ROOT', '2020-12-09 13:52:32', '2020-12-09 13:52:32', NULL, 2);
INSERT INTO `application` VALUES (5, NULL, NULL, '华为白牌服務器', '龍華富士康', 200000, ' 黃文輝', '2020-12-22 08:49:18', '2020-12-22 08:49:42', NULL, NULL, NULL, 'root', '2020-12-22 08:49:18', '2020-12-22 08:49:18', NULL, 2);
INSERT INTO `application` VALUES (6, NULL, NULL, 'PM/HW/DPD/R&D', '武漢PM/HW/DPD/R&D', 65, '陳瑜英', '2020-12-22 09:08:03', '2020-12-22 09:08:27', NULL, NULL, NULL, 'root', '2020-12-22 09:08:03', '2020-12-22 09:08:03', NULL, 2);
INSERT INTO `application` VALUES (7, NULL, NULL, '深信服NVPD-MU1工程', '龍華深信服', 2000, ' 何靖/560-83319/18681506316', '2021-01-13 16:30:14', '2021-01-13 16:34:24', NULL, NULL, NULL, 'root', '2021-01-13 16:30:14', '2021-01-13 16:30:14', NULL, 2);
INSERT INTO `application` VALUES (8, NULL, NULL, 'NVPD-MU1工程', '龍華深信服', 8000, '何靖/83319/18681506316', '2021-01-15 10:08:07', '2021-01-15 10:14:07', NULL, NULL, NULL, 'root', '2021-01-15 10:08:07', '2021-01-15 10:08:07', NULL, 2);
INSERT INTO `application` VALUES (9, NULL, NULL, 'EMD/TE/ NPI', 'EMD/TE/ NPI', 6986, '陳 勝68432', '2021-01-16 10:18:10', '2021-01-16 10:19:13', NULL, NULL, NULL, 'root', '2021-01-16 10:18:10', '2021-01-16 10:18:10', NULL, 2);
INSERT INTO `application` VALUES (10, NULL, NULL, 'EMD/TE/ NPI ', 'EMD/TE/ NPI ', 493014, '陳 勝68432 ', '2021-01-16 10:44:45', '2021-01-16 10:44:51', NULL, NULL, NULL, 'root', '2021-01-16 10:44:45', '2021-01-16 10:44:45', NULL, 2);
INSERT INTO `application` VALUES (11, NULL, NULL, 'D01-3F(PCEBG-IPBD L6)', '重慶富士康', 120000, '鄭   清      577+32967', '2021-01-16 10:48:06', '2021-01-16 10:48:10', NULL, NULL, NULL, 'root', '2021-01-16 10:48:06', '2021-01-16 10:48:06', NULL, 2);
INSERT INTO `application` VALUES (12, NULL, NULL, '白牌服務器', '华为白牌服務器', 3000000, ' 黃文輝 13642342029', '2021-01-16 10:50:08', '2021-01-16 10:50:14', NULL, NULL, NULL, 'root', '2021-01-16 10:50:08', '2021-01-16 10:50:08', NULL, 2);
INSERT INTO `application` VALUES (13, NULL, NULL, 'CESBG-EPDVI 工程部', 'CESBG-EPDVI 工程部', 983040, '鄒玉斌', '2021-01-16 10:51:23', '2021-01-16 10:51:25', NULL, NULL, NULL, 'root', '2021-01-16 10:51:23', '2021-01-16 10:51:23', NULL, 2);
INSERT INTO `application` VALUES (14, NULL, NULL, 'DPD/HW/PM', '武汉PM', 450, '項虹   63177', '2021-01-21 10:20:36', '2021-01-21 10:20:38', NULL, NULL, NULL, 'root', '2021-01-21 10:20:36', '2021-01-21 10:20:36', NULL, 2);

-- ----------------------------
-- Table structure for delivery_record
-- ----------------------------
DROP TABLE IF EXISTS `delivery_record`;
CREATE TABLE `delivery_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mac_id` int NOT NULL COMMENT 'mac地址表主键',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `application_id` int NOT NULL COMMENT 'application表主键',
  `amount` int NULL DEFAULT NULL COMMENT '结束mac十进制-起始mac十进制+1',
  `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updateDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(0:不显示，1:显示，2:撤回发放)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'mac地址交付记录，此表代表申请提交后从mac地址表中分配的记录，若存在跨段(申请数量大于mac地址表该段可用数量)，则同一个mac_id生成多个记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delivery_record
-- ----------------------------
INSERT INTO `delivery_record` VALUES (1, 1, 'A4AE12F29C9D', 'A4AE12F29E04', 1, 360, '2020-11-13 15:01:40', 'ROOT', '2020-11-13 15:01:40', NULL, 1);
INSERT INTO `delivery_record` VALUES (2, 1, 'A4AE12F29E05', 'A4AE12FA3F24', 2, 500000, '2020-11-13 15:02:17', 'ROOT', '2020-11-13 16:11:15', 'wangwu', 1);
INSERT INTO `delivery_record` VALUES (3, 1, 'A4AE12FA3F25', 'A4AE12FB0274', 3, 50000, '2020-12-03 13:52:51', 'ROOT', '2020-12-03 13:52:51', NULL, 1);
INSERT INTO `delivery_record` VALUES (4, 1, 'A4AE12FB0275', 'A4AE12FCB024', 4, 110000, '2020-12-09 13:54:04', 'ROOT', '2020-12-09 13:54:04', NULL, 1);
INSERT INTO `delivery_record` VALUES (5, 1, 'A4AE12FCB025', 'A4AE12FFBD64', 5, 200000, '2020-12-22 08:49:42', 'root', '2020-12-22 08:49:42', NULL, 1);
INSERT INTO `delivery_record` VALUES (6, 1, 'A4AE12FFBD65', 'A4AE12FFBDA5', 6, 65, '2020-12-22 09:08:27', 'root', '2020-12-22 09:08:27', NULL, 1);
INSERT INTO `delivery_record` VALUES (7, 1, 'A4AE12FFBDA6', 'A4AE12FFC575', 7, 2000, '2021-01-13 16:34:24', 'root', '2021-01-13 16:34:24', NULL, 1);
INSERT INTO `delivery_record` VALUES (8, 1, 'A4AE12FFC576', 'A4AE12FFE4B5', 8, 8000, '2021-01-15 10:14:07', 'root', '2021-01-15 10:14:07', NULL, 1);
INSERT INTO `delivery_record` VALUES (9, 1, 'A4AE12FFE4B6', 'A4AE12FFFFFF', 9, 6986, '2021-01-16 10:19:13', 'root', '2021-01-16 10:19:13', NULL, 1);
INSERT INTO `delivery_record` VALUES (10, 2, 'F46B8C000000', 'F46B8C0785D5', 10, 493014, '2021-01-16 10:44:51', 'root', '2021-01-16 10:44:51', NULL, 1);
INSERT INTO `delivery_record` VALUES (11, 2, 'F46B8C0785D6', 'F46B8C095A95', 11, 120000, '2021-01-16 10:48:10', 'root', '2021-01-16 10:48:10', NULL, 1);
INSERT INTO `delivery_record` VALUES (12, 2, 'F46B8C095A96', 'F46B8C372155', 12, 3000000, '2021-01-16 10:50:14', 'root', '2021-01-16 10:50:14', NULL, 1);
INSERT INTO `delivery_record` VALUES (13, 2, 'F46B8C372156', 'F46B8C462155', 13, 983040, '2021-01-16 10:51:25', 'root', '2021-01-16 10:51:25', NULL, 1);
INSERT INTO `delivery_record` VALUES (14, 2, 'F46B8C462156', 'F46B8C462317', 14, 450, '2021-01-21 10:20:38', 'root', '2021-01-21 10:20:38', NULL, 1);

-- ----------------------------
-- Table structure for macaddress
-- ----------------------------
DROP TABLE IF EXISTS `macaddress`;
CREATE TABLE `macaddress`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `signs` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '以前6位作为标识',
  `start_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始mac地址',
  `end_mac_address` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结束mac地址',
  `starting_inventory` int NULL DEFAULT NULL COMMENT '初始库存[(结束mac的十进制-初始mac的十进制数)+1]',
  `current_inventory` int NULL DEFAULT NULL COMMENT '当前库存',
  `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updateDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示(0:不显示，1:显示)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of macaddress
-- ----------------------------
INSERT INTO `macaddress` VALUES (1, 'A4AE12', 'A4AE12F29C9D', 'A4AE12FFFFFF', 877411, NULL, '2020-11-13 15:01:08', 'ROOT', '2020-11-13 15:01:08', NULL, 1);
INSERT INTO `macaddress` VALUES (2, 'F46B8C', 'F46B8C000000', 'F46B8CFFFFFF', 16777216, NULL, '2021-01-16 10:38:47', 'root', '2021-01-16 10:38:47', NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int NULL DEFAULT NULL COMMENT '性别：1 女  2 男',
  `birthday` datetime NULL DEFAULT NULL,
  `user_type` int NULL DEFAULT NULL COMMENT '1管理员  2经理  3普通用户',
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
