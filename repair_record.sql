/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-08-15 18:51:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `repair_record`
-- ----------------------------
DROP TABLE IF EXISTS `repair_record`;
CREATE TABLE `repair_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `repair_record_num` varchar(255) DEFAULT NULL COMMENT '维修单编号，备用',
  `customer` int(10) unsigned DEFAULT NULL COMMENT '联系人,',
  `machine_nameplate` varchar(255) NOT NULL COMMENT '机器编号',
  `repair_request_info` int(10) unsigned NOT NULL COMMENT '用户发起报修信息，一次报修可以有多个维修记录。',
  `in_warranty_period` varchar(255) DEFAULT NULL COMMENT '1：在保修期内，0：保修期已过， 在派单时指定。',
  `repair_plan_date` date DEFAULT NULL COMMENT '计划的上门日期',
  `repair_charge_person` int(10) unsigned DEFAULT NULL COMMENT '维修人员',
  `repair_start_time` datetime DEFAULT NULL COMMENT '维修工时',
  `repair_end_time` datetime DEFAULT NULL,
  `customer_feedback` int(10) unsigned DEFAULT NULL COMMENT '改善建议',
  `status` varchar(255) NOT NULL COMMENT '维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。',
  `create_time` datetime DEFAULT NULL COMMENT '该条记录的创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '该条记录更新时间',
  `forward_info` int(10) unsigned DEFAULT NULL COMMENT '转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。',
  PRIMARY KEY (`id`),
  KEY `fk_rr_machine_nameplate` (`machine_nameplate`),
  KEY `fk_rr_contacter` (`customer`),
  KEY `fk_rr_forward_info` (`forward_info`),
  KEY `fk_rr_repair_charge_person` (`repair_charge_person`),
  KEY `fk_rr_customer_feedback` (`customer_feedback`),
  KEY `fk_rr_repair_request_info` (`repair_request_info`),
  CONSTRAINT `fk_rr_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_rr_customer_feedback` FOREIGN KEY (`customer_feedback`) REFERENCES `repair_customer_feedback` (`id`),
  CONSTRAINT `fk_rr_forward_info` FOREIGN KEY (`forward_info`) REFERENCES `forward_info` (`id`),
  CONSTRAINT `fk_rr_machine_nameplate` FOREIGN KEY (`machine_nameplate`) REFERENCES `machine` (`nameplate`),
  CONSTRAINT `fk_rr_repair_charge_person` FOREIGN KEY (`repair_charge_person`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_rr_repair_request_info` FOREIGN KEY (`repair_request_info`) REFERENCES `repair_request_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of repair_record
-- ----------------------------
INSERT INTO `repair_record` VALUES ('3', 'xx11', '6', 'mph333', '11', '1', '2018-07-06', '3', '2018-07-24 10:08:45', '2018-07-25 10:08:50', '20', '5', '2018-07-25 10:38:25', '2018-08-08 11:08:45', '1');
INSERT INTO `repair_record` VALUES ('4', null, '6', 'mph--all-data', '12', '1', '2018-07-06', '4', '2018-07-24 10:37:35', '2018-07-25 10:15:51', '1', '5', '2018-07-19 11:09:49', '2018-07-25 10:38:32', '1');
INSERT INTO `repair_record` VALUES ('5', null, '7', 'mph788', '13', '1', '2018-07-06', '4', null, '2018-07-25 10:15:55', null, '1', '2018-07-19 13:41:09', null, null);
INSERT INTO `repair_record` VALUES ('6', null, '7', 'mph333', '14', '0', '2018-07-06', '3', null, '2018-07-26 10:15:59', null, '2', '2018-07-19 13:44:14', null, null);
INSERT INTO `repair_record` VALUES ('7', null, '8', 'mph2233', '15', '0', '2018-07-06', '3', null, '2018-07-26 10:16:03', null, '4', '2018-07-19 13:45:23', '2018-08-15 13:15:08', null);
INSERT INTO `repair_record` VALUES ('8', null, '8', 'mph555', '16', '0', '2018-07-16', '3', null, '2018-07-25 10:16:18', null, '4', '2018-07-19 13:46:38', null, null);
INSERT INTO `repair_record` VALUES ('9', null, '9', 'mph777', '17', '1', '2018-07-17', '10', null, '2018-07-26 10:16:07', null, '2', '2018-07-19 13:50:51', null, null);
INSERT INTO `repair_record` VALUES ('10', null, '14', 'mph789', '18', '1', '2018-07-18', '10', null, '2018-07-26 10:16:07', null, '4', '2018-07-19 13:55:23', '2018-08-15 13:35:10', null);
INSERT INTO `repair_record` VALUES ('21', null, '6', 'mph--all-data', '29', '0', '2018-08-25', '4', null, null, null, '1', '2018-08-12 14:38:05', '2018-08-15 13:05:45', null);
INSERT INTO `repair_record` VALUES ('22', '2018081513062700000', '6', 'mph2233', '15', '1', '2018-08-25', '4', null, null, null, '4', '2018-08-15 13:06:25', '2018-08-15 14:07:11', null);
INSERT INTO `repair_record` VALUES ('23', '2018081513150800000', '6', 'mph2233', '15', '1', '2018-08-25', '4', null, null, null, '5', '2018-08-15 13:15:08', '2018-08-15 17:20:11', null);
INSERT INTO `repair_record` VALUES ('24', '2018081513350700000', '6', 'mph789', '18', '0', '2018-08-25', '4', null, null, '1', '1', '2018-08-15 13:34:52', null, null);
INSERT INTO `repair_record` VALUES ('25', '2018081514071000001', '6', 'mph2233', '15', '0', '2018-08-31', '4', null, null, null, '1', '2018-08-15 14:07:10', null, null);
INSERT INTO `repair_record` VALUES ('26', '2018081517200900002', null, 'mph2233', '15', '1', null, null, null, null, null, '0', '2018-08-15 17:20:08', null, '1');
