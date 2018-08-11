/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-08-11 11:30:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `machine`
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nameplate` varchar(255) NOT NULL COMMENT '技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate',
  `order_num` varchar(255) NOT NULL COMMENT '对应的订单号,来自sinsim_db.machine_order.order_num',
  `contract_num` varchar(255) DEFAULT NULL COMMENT '对应的合同号，可能用不到，允许空',
  `geo_location` varchar(255) DEFAULT NULL COMMENT '机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '机器地址，厂家门牌号',
  `status` varchar(255) NOT NULL DEFAULT '1' COMMENT '机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核',
  `nameplate_picture` varchar(255) DEFAULT NULL COMMENT '老机器，客户拍的铭牌照片的保存地址；非老机器可以为空',
  `machine_type` varchar(255) NOT NULL COMMENT '机型',
  `needle_num` varchar(255) NOT NULL COMMENT '针数',
  `x_distance` varchar(255) NOT NULL COMMENT 'x行程',
  `y_distance` varchar(255) NOT NULL COMMENT 'y行程',
  `head_distance` varchar(255) NOT NULL COMMENT '头距',
  `head_num` varchar(255) NOT NULL COMMENT '头数',
  `loadinglist` varchar(255) DEFAULT NULL COMMENT '装车单路径，共用流程管理系统的装车单，老机器允许空',
  `customer_in_contract` varchar(255) DEFAULT NULL COMMENT '合同里的客户',
  `customer` int(10) unsigned DEFAULT NULL COMMENT '机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。',
  `facory_date` date DEFAULT NULL COMMENT '出厂日期，老机器允许空',
  `is_old_machine` varchar(255) NOT NULL COMMENT '0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）',
  `old_machine_check` varchar(255) DEFAULT NULL COMMENT '老机器审核是否通过，空表示非老机器，0:未通过，1：通过',
  PRIMARY KEY (`id`),
  KEY `contract_num` (`contract_num`),
  KEY `order_num` (`order_num`),
  KEY `nameplate` (`nameplate`),
  KEY `fk_m_customer` (`customer`),
  CONSTRAINT `fk_m_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES ('1', 'mph2233', 'ddh22333', 'ht22333', 'geo-11-34', '机器地址杭州市XX路XX号', '1', null, '绣花机', '22', '33', '44', '55', '66', null, '合同客户ABC', '11', '2018-07-10', '1', null);
INSERT INTO `machine` VALUES ('2', 'mph333', 'ddh444', 'thaaa223', 'geo233', '机器地址杭州市XX路XX222', '3', null, '帽子机', '33', '22', '11', '11', '11', null, '合同客户ABC22', '8', '2018-07-06', '1', null);
INSERT INTO `machine` VALUES ('3', 'mph555', 'ddh-abc111', 'thaaa555', 'geo2555', '机器地址 杭州市XX路XX555', '1', null, 'maoziji', '33', '22', '11', '11', '11', null, '合同客户ABC22', '9', '2018-07-06', '0', null);
INSERT INTO `machine` VALUES ('4', 'mph444', 'ddh444', 'htaaa233', 'geo111', '机器地址xxl路xx区块', '1', null, 'maoziji', '25', '500', '300', '200', '100', null, '合同客户888', '7', '2018-07-21', '0', null);
INSERT INTO `machine` VALUES ('5', 'mph777', 'ddh0550', 'th_agent_customer444', 'geo999', '机器地址xxx路', '1', null, '帽子机', '31', '55', '59', '66', '100', null, '合同客户223', '11', '2018-07-11', '0', null);
INSERT INTO `machine` VALUES ('6', 'mph788', 'ddh0550', 'th_agent_customer444', 'geo999', '机器地址xxx路', '1', null, '帽子机', '31', '55', '59', '66', '100', null, '合同客户223', '11', '2018-07-11', '1', null);
INSERT INTO `machine` VALUES ('7', 'mph789', 'ddh0550', 'th_agent_customer444', 'geo999', '机器地址xxx路', '1', null, '帽子机', '31', '55', '59', '66', '100', null, '合同客户223', '12', '2018-07-22', '1', null);
INSERT INTO `machine` VALUES ('8', 'mph--all-data', 'ddh0550', 'th_agent_customer444', 'geottt23.33', '机器地址xxx路11h', '1', null, 'maoziji', '31', '55', '59', '66', '100', null, '合同客户223', '11', '2018-07-22', '0', null);
INSERT INTO `machine` VALUES ('13', '1806004', 'XS-1801109B', 'XS-1801109', null, '', '1', null, '高速双凸轮', '6', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '2', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('14', '1806003', 'XS-1801109B', 'XS-1801109', null, '', '1', null, '高速双凸轮', '6', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '2', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('17', '1806001', 'XS-1801109A', 'XS-1801109', null, '', '1', null, '高速双凸轮', '4', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '7', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('18', '1806002', 'XS-1801109A', 'XS-1801109', null, '', '1', null, '高速双凸轮', '4', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '7', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('19', '1806026', 'XS-1801122', 'XS-1801122', null, '', '1', null, '单凸轮双驱动', '3', '700', '1600', '110', '132', null, '汝州市安思纺织品有限公司', '11', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('20', '1806025', 'XS-1801122', 'XS-1801122', null, '', '1', null, '单凸轮双驱动', '3', '700', '1600', '110', '132', null, '汝州市安思纺织品有限公司', '11', '2018-08-31', '0', null);
INSERT INTO `machine` VALUES ('21', 'wesdfsd', 'wewr32', null, null, '客户地址kehu1', '1', null, '纯毛巾', '12', '2', '23', '12', '23', null, null, '6', null, '2', '1');
