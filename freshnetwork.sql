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

 Date: 13/07/2020 22:07:31
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
  `sheng` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `shi` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `qu` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区',
  `address` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `contacts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `con_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  PRIMARY KEY (`add_number`) USING BTREE,
  INDEX `FK_选择地址`(`User_num`) USING BTREE,
  CONSTRAINT `FK_选择地址` FOREIGN KEY (`User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of add_list
-- ----------------------------
INSERT INTO `add_list` VALUES (1, 1, '浙江省', '杭州市', '拱墅区', '浙江大学城市学院', 'hk', '123');
INSERT INTO `add_list` VALUES (2, 1, '浙江省', '杭州市', '拱墅区', '下沙', 'byn', '123456789');
INSERT INTO `add_list` VALUES (3, 1, '浙江省', '杭州市', '江干区', '理工大学', 'xj', '12345678910');
INSERT INTO `add_list` VALUES (4, 4, '浙江省', '杭州市', '江干区', '西湖', 'byb', '12345678910');
INSERT INTO `add_list` VALUES (5, 12, '浙江省', '杭州市', '拱墅区', '南校区', 'ysg', '01234567891');
INSERT INTO `add_list` VALUES (6, 1, '浙江省', '杭州市', '江干区', '城院', 'yyy', '12345678910');

-- ----------------------------
-- Table structure for adm_info
-- ----------------------------
DROP TABLE IF EXISTS `adm_info`;
CREATE TABLE `adm_info`  (
  `Emp_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `Emp_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `Emp_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`Emp_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adm_info
-- ----------------------------
INSERT INTO `adm_info` VALUES (1, 'admin', '456');
INSERT INTO `adm_info` VALUES (2, 'qwe', '123');
INSERT INTO `adm_info` VALUES (3, 'xj', '123');

-- ----------------------------
-- Table structure for commodity_information
-- ----------------------------
DROP TABLE IF EXISTS `commodity_information`;
CREATE TABLE `commodity_information`  (
  `Trade_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `Pro_number` int(11) NULL DEFAULT NULL COMMENT '促销编号',
  `Category_number` int(11) NULL DEFAULT NULL COMMENT '类别编号',
  `Trade_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `Price` float NOT NULL COMMENT '商品单价',
  `Member_price` float NOT NULL COMMENT '会员价',
  `number` int(11) NOT NULL COMMENT '数量',
  `Specifications` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格',
  `details` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情',
  `SaleNumber` int(10) NOT NULL DEFAULT 0 COMMENT '销量',
  PRIMARY KEY (`Trade_number`) USING BTREE,
  INDEX `FK_促销2`(`Pro_number`) USING BTREE,
  INDEX `FK_包括`(`Category_number`) USING BTREE,
  CONSTRAINT `FK_促销2` FOREIGN KEY (`Pro_number`) REFERENCES `time_pro` (`Pro_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_包括` FOREIGN KEY (`Category_number`) REFERENCES `fresh_information` (`Category_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_information
-- ----------------------------
INSERT INTO `commodity_information` VALUES (3, 4, 1, '龙虾', 45, 41, 5, 'wu', 'wu', 37);
INSERT INTO `commodity_information` VALUES (4, NULL, 1, '龙虾2', 34, 23, 7, '无', '无', 26);
INSERT INTO `commodity_information` VALUES (7, 5, 5, '谢建', 100, 89, 99985, '无', '无', 15);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `Cou_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券编号',
  `content` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `App_money` float NOT NULL COMMENT '适用金额',
  `Ded_money` float NOT NULL COMMENT '减免金额',
  `Start_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始日期',
  `End_date` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束日期',
  PRIMARY KEY (`Cou_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, '满20减2', 20, 2, '2020-05-09 00:00:00', '2020-11-09 00:00:00');
INSERT INTO `coupon` VALUES (2, '满30减5', 30, 5, '2020-05-08 00:00:00', '2020-07-08 00:00:00');

-- ----------------------------
-- Table structure for fresh_information
-- ----------------------------
DROP TABLE IF EXISTS `fresh_information`;
CREATE TABLE `fresh_information`  (
  `Category_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `Category_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  `Category_description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别描述',
  PRIMARY KEY (`Category_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fresh_information
-- ----------------------------
INSERT INTO `fresh_information` VALUES (1, '海鲜', '描述2');
INSERT INTO `fresh_information` VALUES (5, '水果2', '评价');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of full_sheet
-- ----------------------------
INSERT INTO `full_sheet` VALUES (2, '满10打九折', 10, 0.9, '2020-07-10 00:00:00', '2020-09-08 00:00:00');

-- ----------------------------
-- Table structure for goods_eva
-- ----------------------------
DROP TABLE IF EXISTS `goods_eva`;
CREATE TABLE `goods_eva`  (
  `Use_User_num` int(11) NOT NULL COMMENT '用户编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Eva_content` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `Eva_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价日期',
  `Star` int(5) NOT NULL COMMENT '星级',
  `Photo` longblob NULL COMMENT '照片',
  PRIMARY KEY (`Use_User_num`, `Com_Trade_number`) USING BTREE,
  INDEX `FK_goods_eva2`(`Com_Trade_number`) USING BTREE,
  CONSTRAINT `FK_goods_eva` FOREIGN KEY (`Use_User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_goods_eva2` FOREIGN KEY (`Com_Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_eva
-- ----------------------------
INSERT INTO `goods_eva` VALUES (1, 3, '评论', '2020-07-13 18:36:23', 4, NULL);
INSERT INTO `goods_eva` VALUES (12, 4, '1', '2020-07-13 09:32:18', 5, NULL);

-- ----------------------------
-- Table structure for menu_information
-- ----------------------------
DROP TABLE IF EXISTS `menu_information`;
CREATE TABLE `menu_information`  (
  `Menu_number` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜谱编号',
  `Menu_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜谱名称',
  `Menu_Materials` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜谱原料',
  `step` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '步骤',
  `picture` longblob NULL COMMENT '图片',
  PRIMARY KEY (`Menu_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_information
-- ----------------------------
INSERT INTO `menu_information` VALUES (1, '水果', '苹果，香蕉', '步骤1', NULL);
INSERT INTO `menu_information` VALUES (2, '菜谱1', '原料', 'step', NULL);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `ord_ord_number` int(11) NOT NULL COMMENT '订单编号',
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Ful_Full_number` int(11) NOT NULL COMMENT '满折编号',
  `pur_number` int(10) NOT NULL COMMENT '商品数量',
  `Discount` float NOT NULL COMMENT '折扣',
  `set_money` float(10, 2) NOT NULL COMMENT '结算金额',
  PRIMARY KEY (`ord_ord_number`, `Com_Trade_number`, `Ful_Full_number`) USING BTREE,
  INDEX `FK_Order_details2`(`Com_Trade_number`) USING BTREE,
  INDEX `FK_Order_details3`(`Ful_Full_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (1, 3, 2, 3, 0.9, 105.00);
INSERT INTO `order_details` VALUES (1, 4, 2, 1, 0.9, 23.00);
INSERT INTO `order_details` VALUES (2, 3, 2, 5, 0.9, 175.00);
INSERT INTO `order_details` VALUES (7, 3, 2, 4, 0.9, 140.00);
INSERT INTO `order_details` VALUES (7, 4, 2, 10, 0.9, 230.00);
INSERT INTO `order_details` VALUES (8, 3, 2, 4, 0.9, 140.00);
INSERT INTO `order_details` VALUES (9, 3, 2, 4, 0.9, 140.00);
INSERT INTO `order_details` VALUES (10, 4, 2, 11, 0.9, 247.50);
INSERT INTO `order_details` VALUES (10, 7, 2, 15, 0.9, 1201.50);

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `onumber` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `ord_number` int(11) NOT NULL COMMENT '商品订单编号',
  `User_num` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `add_number` int(11) NULL DEFAULT NULL COMMENT '地址编号',
  `Cou_number` int(11) NULL DEFAULT NULL COMMENT '优惠券编号',
  `ori_money` float NULL DEFAULT NULL COMMENT '原始金额',
  `set_money` float NULL DEFAULT NULL COMMENT '结算金额',
  `ari_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '到达时间',
  `ord_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`onumber`) USING BTREE,
  INDEX `FK_订单`(`User_num`) USING BTREE,
  INDEX `FK_配送`(`add_number`) USING BTREE,
  INDEX `FK_优惠`(`Cou_number`) USING BTREE,
  CONSTRAINT `FK_优惠` FOREIGN KEY (`Cou_number`) REFERENCES `coupon` (`Cou_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_用于_7` FOREIGN KEY (`Cou_number`) REFERENCES `coupon` (`Cou_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_订单` FOREIGN KEY (`User_num`) REFERENCES `user_table` (`User_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_配送` FOREIGN KEY (`add_number`) REFERENCES `add_list` (`add_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES (69, 1, 1, 1, 2, 46, 41, '2020-07-12 13:39:42', '下单');
INSERT INTO `order_form` VALUES (70, 2, 1, 1, 1, 46, 44, '2020-07-12 13:42:48', '下单');
INSERT INTO `order_form` VALUES (71, 3, 1, 2, 2, 69, 64, '2020-07-12 13:45:57', '下单');
INSERT INTO `order_form` VALUES (72, 4, 1, 1, 2, 115, 110, '2020-07-12 21:44:22', '下单');
INSERT INTO `order_form` VALUES (73, 5, 1, 1, 1, 215, 213, '2020-07-12 21:45:22', '下单');
INSERT INTO `order_form` VALUES (74, 1, 4, 4, 2, 90, 85, '2020-07-12 21:47:31', '下单');
INSERT INTO `order_form` VALUES (75, 6, 1, 3, 2, 175, 170, '2020-07-13 09:39:04', '下单');
INSERT INTO `order_form` VALUES (76, 1, 12, 5, 1, 128, 126, '2020-07-13 10:30:57', '下单');
INSERT INTO `order_form` VALUES (77, 2, 12, 5, 2, 175, 170, '2020-07-13 10:31:31', '下单');
INSERT INTO `order_form` VALUES (78, 7, 1, 3, 2, 370, 365, '2020-07-13 11:19:22', '下单');
INSERT INTO `order_form` VALUES (79, 8, 1, 6, 1, 140, 138, '2020-07-13 19:38:23', '下单');
INSERT INTO `order_form` VALUES (80, 9, 1, 3, 2, 140, 135, '2020-07-13 21:23:18', '下单');
INSERT INTO `order_form` VALUES (81, 10, 1, 1, 2, 1448, 1443, '2020-07-13 21:56:29', '下单');

-- ----------------------------
-- Table structure for purchase_list
-- ----------------------------
DROP TABLE IF EXISTS `purchase_list`;
CREATE TABLE `purchase_list`  (
  `chase_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `chase_number` int(11) NOT NULL COMMENT '采购编号',
  `Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Emp_number` int(11) NOT NULL COMMENT '员工编号',
  `purchase_amount` int(11) NOT NULL COMMENT '采购数量',
  `chase_stat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`chase_num`) USING BTREE,
  INDEX `FK_hava`(`Emp_number`) USING BTREE,
  INDEX `FK_yongyou`(`Trade_number`) USING BTREE,
  CONSTRAINT `FK_hava` FOREIGN KEY (`Emp_number`) REFERENCES `adm_info` (`Emp_number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_yongyou` FOREIGN KEY (`Trade_number`) REFERENCES `commodity_information` (`Trade_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_list
-- ----------------------------
INSERT INTO `purchase_list` VALUES (5, 2, 3, 1, 5, '下单');

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
INSERT INTO `recommend_menu` VALUES (1, 3, '无');
INSERT INTO `recommend_menu` VALUES (1, 4, '无');

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
INSERT INTO `relation` VALUES (2, 3, '2020-07-10 00:00:00', '2020-09-08 00:00:00');
INSERT INTO `relation` VALUES (2, 4, '2020-07-10 00:00:00', '2020-09-08 00:00:00');

-- ----------------------------
-- Table structure for shopping
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping`  (
  `Com_Trade_number` int(11) NOT NULL COMMENT '商品编号',
  `Ful_Full_number` int(11) NOT NULL COMMENT '满折编号',
  `pur_number` int(111) NOT NULL COMMENT '商品数量',
  `Discount` float(255, 1) NOT NULL COMMENT '折扣',
  `set_money` float(255, 2) NOT NULL COMMENT '结算金额',
  `user_number` int(10) NOT NULL COMMENT '用户编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES (3, 2, 3, 0.9, 105.00, 11);
INSERT INTO `shopping` VALUES (3, 2, 3, 0.9, 105.00, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of time_pro
-- ----------------------------
INSERT INTO `time_pro` VALUES (4, 3, 35, 12, '2020-07-13 00:00:00', '2020-08-09 00:00:00');
INSERT INTO `time_pro` VALUES (5, 7, 24, 15, '2020-04-08 00:00:00', '2020-06-08 00:00:00');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `User_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `User_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `User_gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `User_pwd` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `User_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `User_mail` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在城市',
  `Regtime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `ISmember` tinyint(1) NULL DEFAULT NULL COMMENT '是否为会员',
  `closedate` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00' COMMENT '截止时间',
  PRIMARY KEY (`User_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (1, 'zucc', '1', '123', '12345678910', '123@qq.com', '南京', '2020-07-13 21:02:29', 1, '2021-02-05 15:31:13');
INSERT INTO `user_table` VALUES (4, 'zucc1', '男', '123', '123456789', 'zucc1', '上海', '2020-07-05 15:23:34', 0, NULL);
INSERT INTO `user_table` VALUES (10, '123', '男', '123', '12345678910', '157@qq.com', '南京', '2020-07-05 15:31:00', 0, NULL);
INSERT INTO `user_table` VALUES (11, 'zucc2', '男', '123', '12345678910', '123@qq.com', '杭州', '2020-07-13 08:52:20', NULL, NULL);
INSERT INTO `user_table` VALUES (12, 'xx', '女', '1', '12345678912', '1@11.com', '杭州', '2020-07-13 09:33:01', 1, '2021-01-13 09:33:02');
INSERT INTO `user_table` VALUES (15, 'zs', '男', '123', '10987485694', '123@qq.com', '杭州', '2020-07-13 17:25:16', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
