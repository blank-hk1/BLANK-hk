/*
 Navicat MySQL Data Transfer

 Source Server         : 连接1
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : freshnetwork

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 03/07/2020 17:07:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for add_list
-- ----------------------------
DROP TABLE IF EXISTS `add_list`;
CREATE TABLE `add_list`  (
  `add_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址编号',
  `User_num` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `ord_number` int(11) NULL DEFAULT NULL COMMENT '订单编号',
  `sheng` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `shi` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `qu` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区',
  `address` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `contacts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `con_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  PRIMARY KEY (`add_number`) USING BTREE,
  INDEX `FK_选择地址`(`User_num`) USING BTREE,
  INDEX `FK_配送2`(`ord_number`) USING BTREE,
  CONSTRAINT `FK_选择地址` FOREIGN KEY (`User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_配送2` FOREIGN KEY (`ord_number`) REFERENCES `order_form` (`ord_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of add_list
-- ----------------------------

-- ----------------------------
-- Table structure for adm_info
-- ----------------------------
DROP TABLE IF EXISTS `adm_info`;
CREATE TABLE `adm_info`  (
  `Emp_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `Emp_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `Emp_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`Emp_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adm_info
-- ----------------------------

-- ----------------------------
-- Table structure for commodity_information
-- ----------------------------
DROP TABLE IF EXISTS `commodity_information`;
CREATE TABLE `commodity_information`  (
  `Trade_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `Pro_number` int(11) NULL DEFAULT NULL COMMENT '促销编号',
  `chase_number` int(11) NULL DEFAULT NULL COMMENT '采购单编号',
  `Category_number` int(11) NULL DEFAULT NULL COMMENT '类别编号',
  `Trade_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `Price` float NOT NULL COMMENT '商品单价',
  `Member_price` float NOT NULL COMMENT '会员价',
  `number` int(11) NOT NULL COMMENT '数量',
  `Specifications` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格',
  `details` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情',
  PRIMARY KEY (`Trade_number`) USING BTREE,
  INDEX `FK_促销2`(`Pro_number`) USING BTREE,
  INDEX `FK_包括`(`Category_number`) USING BTREE,
  INDEX `FK_采购`(`chase_number`) USING BTREE,
  CONSTRAINT `FK_促销2` FOREIGN KEY (`Pro_number`) REFERENCES `time_pro` (`Pro_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_包括` FOREIGN KEY (`Category_number`) REFERENCES `fresh_information` (`Category_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_采购` FOREIGN KEY (`chase_number`) REFERENCES `purchase_list` (`chase_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_information
-- ----------------------------

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `Cou_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券编号',
  `ord_number` int(11) NULL DEFAULT NULL COMMENT '订单编号',
  `content` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `App_money` float NOT NULL COMMENT '适用金额',
  `Ded_money` float NOT NULL COMMENT '减免金额',
  `Start_date` date NOT NULL COMMENT '起始日期',
  `End_date` date NOT NULL COMMENT '结束日期',
  PRIMARY KEY (`Cou_number`) USING BTREE,
  INDEX `FK_用于_6`(`ord_number`) USING BTREE,
  CONSTRAINT `FK_用于_6` FOREIGN KEY (`ord_number`) REFERENCES `order_form` (`ord_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for fresh_information
-- ----------------------------
DROP TABLE IF EXISTS `fresh_information`;
CREATE TABLE `fresh_information`  (
  `Category_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `Category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  `Category_description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别描述',
  PRIMARY KEY (`Category_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fresh_information
-- ----------------------------

-- ----------------------------
-- Table structure for full_sheet
-- ----------------------------
DROP TABLE IF EXISTS `full_sheet`;
CREATE TABLE `full_sheet`  (
  `Full_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '满折编号',
  `Full_content` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `App_number` int(11) NOT NULL COMMENT '适用商品数量',
  `Discount` float NOT NULL COMMENT '折扣',
  `FulStart_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `FulEnd_date` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`Full_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of full_sheet
-- ----------------------------

-- ----------------------------
-- Table structure for goods_eva
-- ----------------------------
DROP TABLE IF EXISTS `goods_eva`;
CREATE TABLE `goods_eva`  (
  `Use_User_num` int(11) NOT NULL COMMENT '用户编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Eva_content` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `Eva_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价日期',
  `Star` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '星级',
  `Photo` longblob NOT NULL COMMENT '照片',
  PRIMARY KEY (`Use_User_num`, `Com_Trade_number`) USING BTREE,
  INDEX `FK_goods_eva2`(`Com_Trade_number`) USING BTREE,
  CONSTRAINT `FK_goods_eva` FOREIGN KEY (`Use_User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_goods_eva2` FOREIGN KEY (`Com_Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_eva
-- ----------------------------

-- ----------------------------
-- Table structure for menu_information
-- ----------------------------
DROP TABLE IF EXISTS `menu_information`;
CREATE TABLE `menu_information`  (
  `Menu_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜谱编号',
  `Menu_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜谱名称',
  `Menu_Materials` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜谱原料',
  `step` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '步骤',
  `picture` longblob NOT NULL COMMENT '图片',
  PRIMARY KEY (`Menu_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_information
-- ----------------------------

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `ord_ord_number` int(11) NOT NULL COMMENT '订单编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Ful_Full_number` int(11) NOT NULL COMMENT '满折编号',
  `pur_number` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品数量',
  `Discount` float NOT NULL COMMENT '折扣',
  `set_money` float(10, 2) NOT NULL COMMENT '结算金额',
  PRIMARY KEY (`ord_ord_number`, `Com_Trade_number`, `Ful_Full_number`) USING BTREE,
  INDEX `FK_Order_details2`(`Com_Trade_number`) USING BTREE,
  INDEX `FK_Order_details3`(`Ful_Full_number`) USING BTREE,
  CONSTRAINT `FK_Order_details` FOREIGN KEY (`ord_ord_number`) REFERENCES `order_form` (`ord_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Order_details2` FOREIGN KEY (`Com_Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Order_details3` FOREIGN KEY (`Ful_Full_number`) REFERENCES `full_sheet` (`Full_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `ord_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品订单编号',
  `User_num` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `add_number` int(11) NULL DEFAULT NULL COMMENT '地址编号',
  `Cou_number` int(11) NULL DEFAULT NULL COMMENT '优惠券编号',
  `ori_money` float NULL DEFAULT NULL COMMENT '原始金额',
  `set_money` float NULL DEFAULT NULL COMMENT '结算金额',
  `ari_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '到达时间',
  `ord_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`ord_number`) USING BTREE,
  INDEX `FK_用于_7`(`Cou_number`) USING BTREE,
  INDEX `FK_订单`(`User_num`) USING BTREE,
  INDEX `FK_配送`(`add_number`) USING BTREE,
  CONSTRAINT `FK_用于_7` FOREIGN KEY (`Cou_number`) REFERENCES `coupon` (`Cou_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_订单` FOREIGN KEY (`User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_配送` FOREIGN KEY (`add_number`) REFERENCES `add_list` (`add_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_form
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_list
-- ----------------------------
DROP TABLE IF EXISTS `purchase_list`;
CREATE TABLE `purchase_list`  (
  `chase_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购编号',
  `Emp_number` int(11) NULL DEFAULT NULL COMMENT '员工编号',
  `purchase_amount` int(11) NOT NULL COMMENT '采购数量',
  `chase_stat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`chase_number`) USING BTREE,
  INDEX `FK_hava`(`Emp_number`) USING BTREE,
  CONSTRAINT `FK_hava` FOREIGN KEY (`Emp_number`) REFERENCES `adm_info` (`Emp_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_list
-- ----------------------------

-- ----------------------------
-- Table structure for recommend_menu
-- ----------------------------
DROP TABLE IF EXISTS `recommend_menu`;
CREATE TABLE `recommend_menu`  (
  `Men_Menu_number` int(11) NOT NULL COMMENT '菜谱编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `miaoshu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`Men_Menu_number`, `Com_Trade_number`) USING BTREE,
  INDEX `FK_recommend_menu2`(`Com_Trade_number`) USING BTREE,
  CONSTRAINT `FK_recommend_menu` FOREIGN KEY (`Men_Menu_number`) REFERENCES `menu_information` (`Menu_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_recommend_menu2` FOREIGN KEY (`Com_Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommend_menu
-- ----------------------------

-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation`  (
  `Ful_Full_number` int(11) NOT NULL COMMENT '满折编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `FulStart_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始日期',
  `FulEnd_date` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束日期',
  PRIMARY KEY (`Ful_Full_number`, `Com_Trade_number`) USING BTREE,
  INDEX `FK_relation2`(`Com_Trade_number`) USING BTREE,
  CONSTRAINT `FK_relation` FOREIGN KEY (`Ful_Full_number`) REFERENCES `full_sheet` (`Full_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_relation2` FOREIGN KEY (`Com_Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relation
-- ----------------------------

-- ----------------------------
-- Table structure for time_pro
-- ----------------------------
DROP TABLE IF EXISTS `time_pro`;
CREATE TABLE `time_pro`  (
  `Pro_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '促销编号',
  `Trade_number` int(11) NULL DEFAULT NULL COMMENT '商品编号',
  `Pro_price` float NOT NULL COMMENT '促销价格',
  `Prom_number` int(11) NOT NULL COMMENT '促销数量',
  `ProStart_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `ProEnd_date` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  PRIMARY KEY (`Pro_number`) USING BTREE,
  INDEX `FK_促销`(`Trade_number`) USING BTREE,
  CONSTRAINT `FK_促销` FOREIGN KEY (`Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of time_pro
-- ----------------------------

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `User_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `User_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `User_gender` tinyint(1) NOT NULL COMMENT '性别',
  `User_pwd` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `User_phone` decimal(11, 0) NOT NULL COMMENT '手机号码',
  `User_mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在城市',
  `Regtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `ISmember` tinyint(1) NOT NULL COMMENT '是否为会员',
  `closedate` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '截止时间',
  PRIMARY KEY (`User_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
