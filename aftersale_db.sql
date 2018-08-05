/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-08-05 14:50:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `agent`
-- ----------------------------
DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL COMMENT '电话号码',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '代理商1', '代理商1的的电话13566661111', 'XX市XX路XX号', '2018-07-11 10:46:10');
INSERT INTO `agent` VALUES ('2', '代理商2', '代理商2的的电话13566662222', 'xx路XX号', '2018-07-11 10:46:42');

-- ----------------------------
-- Table structure for `customer_company`
-- ----------------------------
DROP TABLE IF EXISTS `customer_company`;
CREATE TABLE `customer_company` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_company_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_company
-- ----------------------------
INSERT INTO `customer_company` VALUES ('1', 'sinsim');
INSERT INTO `customer_company` VALUES ('2', 'company_222');
INSERT INTO `customer_company` VALUES ('3', 'company_333');
INSERT INTO `customer_company` VALUES ('4', 'company_444');

-- ----------------------------
-- Table structure for `experience_lib`
-- ----------------------------
DROP TABLE IF EXISTS `experience_lib`;
CREATE TABLE `experience_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑',
  `repair_actual_info_id` int(10) unsigned NOT NULL COMMENT '维修记录的ID',
  `issue_description` text NOT NULL COMMENT '问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑',
  `before_resolve_pictures` varchar(255) DEFAULT NULL COMMENT '解决前的照片，\r\n和repair_request_info.pictures是同一份图片 ，\r\n管理员可以编辑。但是不让删除图片。',
  `repair_method` text NOT NULL COMMENT '解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑',
  `after_resolve_pictures` varchar(255) DEFAULT NULL COMMENT '解决后图片，来自于repair_actual_info.after_resolve_pictures。\r\n是同一份图片 ，\r\n管理员可以编辑。也不让删除图片。',
  `author` varchar(255) NOT NULL,
  `read_times` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间，比如管理员编辑的时间',
  PRIMARY KEY (`id`),
  KEY `fk_el_repair_actual_info_id` (`repair_actual_info_id`),
  CONSTRAINT `fk_el_repair_actual_info_id` FOREIGN KEY (`repair_actual_info_id`) REFERENCES `repair_actual_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of experience_lib
-- ----------------------------

-- ----------------------------
-- Table structure for `forward_info`
-- ----------------------------
DROP TABLE IF EXISTS `forward_info`;
CREATE TABLE `forward_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `forword_time` datetime NOT NULL COMMENT '转派时间',
  `comment` varchar(255) DEFAULT NULL COMMENT '可以附带一些信息，告知原厂',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forward_info
-- ----------------------------
INSERT INTO `forward_info` VALUES ('1', '2018-07-25 10:39:31', '维修注意马达已经有异响修了一半');

-- ----------------------------
-- Table structure for `install_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `install_customer_feedback`;
CREATE TABLE `install_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_customer_feedback
-- ----------------------------
INSERT INTO `install_customer_feedback` VALUES ('1', '3分', '建议balabala111');
INSERT INTO `install_customer_feedback` VALUES ('2', '4分', '建议bbbbbb222');

-- ----------------------------
-- Table structure for `install_lib`
-- ----------------------------
DROP TABLE IF EXISTS `install_lib`;
CREATE TABLE `install_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `is_base_lib` varchar(255) NOT NULL COMMENT '0:非基础库，1：基础库',
  `install_lib_name` varchar(255) NOT NULL COMMENT '所属的安装库的名称， ',
  `install_content` text COMMENT '安装验收的内容',
  PRIMARY KEY (`id`),
  KEY `fk_ii_install_lib` (`install_lib_name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_lib
-- ----------------------------
INSERT INTO `install_lib` VALUES ('1', '0', 'aaa', null);
INSERT INTO `install_lib` VALUES ('2', '1', 'aaa', 'dfsssssssssssssssssssssssssssssssssssfdsf');
INSERT INTO `install_lib` VALUES ('4', '1', 'aaa', 'eetertre');
INSERT INTO `install_lib` VALUES ('6', '1', 'aaa', 'sddfgf');
INSERT INTO `install_lib` VALUES ('23', '0', 'bbb', null);
INSERT INTO `install_lib` VALUES ('24', '1', 'bbb', 'sdfdsfdsa');
INSERT INTO `install_lib` VALUES ('25', '1', 'bbb', 'fsdgfgfdfd');
INSERT INTO `install_lib` VALUES ('26', '1', 'bbb', 'asdsdfsd');

-- ----------------------------
-- Table structure for `install_members`
-- ----------------------------
DROP TABLE IF EXISTS `install_members`;
CREATE TABLE `install_members` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,',
  `install_record_id` int(10) unsigned NOT NULL COMMENT '安装记录的ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '员工的ID',
  PRIMARY KEY (`id`),
  KEY `fk_install_record_id` (`install_record_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_install_record_id` FOREIGN KEY (`install_record_id`) REFERENCES `install_record` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_members
-- ----------------------------
INSERT INTO `install_members` VALUES ('7', '9', '3');
INSERT INTO `install_members` VALUES ('8', '9', '4');

-- ----------------------------
-- Table structure for `install_record`
-- ----------------------------
DROP TABLE IF EXISTS `install_record`;
CREATE TABLE `install_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `install_record_num` varchar(255) DEFAULT NULL COMMENT '安装调试验收单的编号，先保留着，可能用不到,允许空。',
  `install_plan_date` date DEFAULT NULL COMMENT '安装调试计划日期（上门日期）',
  `install_actual_time` datetime DEFAULT NULL COMMENT '安装调试的结束时间（安装工提交时的时间）',
  `machine_nameplate` varchar(255) NOT NULL COMMENT '机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号',
  `install_result` text COMMENT '安装结果(安装人员建议可以写在此处)',
  `install_status` varchar(255) DEFAULT '0' COMMENT '安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认',
  `customer_feedback` int(10) unsigned DEFAULT NULL COMMENT '客户反馈和建议',
  `install_charge_person` int(10) unsigned DEFAULT NULL COMMENT '安装的负责人',
  `install_info` longtext COMMENT '全部安装信息，json格式\r\n [\r\n   {\r\n        "is_base_lib":1,\r\n        "install_lib_name":"基础库",\r\n        "install_content":"电源电压A",\r\n        "install_value":"220v"\r\n    },\r\n    {\r\n        "is_base_lib":1,\r\n        "install_lib_name":"基础库",\r\n        "install_content":"电源电压B",\r\n        "install_value":"220v"\r\n    }\r\n]',
  `create_time` datetime DEFAULT NULL COMMENT '该记录的创建时间',
  `update_time` datetime DEFAULT NULL,
  `customer` int(10) unsigned DEFAULT NULL COMMENT '客户',
  PRIMARY KEY (`id`),
  KEY `fk_ir_machine_nameplate` (`machine_nameplate`),
  KEY `fk_ir_contacter` (`customer`),
  KEY `fk_ir_maintain_charge_person` (`install_charge_person`),
  KEY `fk_ir_customer_feedback` (`customer_feedback`),
  CONSTRAINT `fk_ir_machine_nameplate` FOREIGN KEY (`machine_nameplate`) REFERENCES `machine` (`nameplate`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_record
-- ----------------------------
INSERT INTO `install_record` VALUES ('1', 'aaa', '2018-07-11', '2018-07-11 14:27:13', 'mph2233', 'an安装ok', '2', '1', '1', 'ins info aaaa', '0000-00-00 00:00:00', null, '9');
INSERT INTO `install_record` VALUES ('2', 'abbb', '2018-07-16', '2018-07-16 15:03:24', 'mph2233', 'okkk', '2', '2', '5', 'info4444', '0000-00-00 00:00:00', null, '8');
INSERT INTO `install_record` VALUES ('9', null, null, null, '1806004', null, '1', null, '3', null, '2018-08-05 14:17:18', '2018-08-05 14:49:32', '7');
INSERT INTO `install_record` VALUES ('10', null, null, null, '1806003', null, '0', null, null, null, '2018-08-05 14:17:24', null, null);

-- ----------------------------
-- Table structure for `issue_position_list`
-- ----------------------------
DROP TABLE IF EXISTS `issue_position_list`;
CREATE TABLE `issue_position_list` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '故障部位列表，由信胜提供',
  `name` varchar(255) NOT NULL COMMENT '故障部位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issue_position_list
-- ----------------------------
INSERT INTO `issue_position_list` VALUES ('1', '上轴问题');
INSERT INTO `issue_position_list` VALUES ('2', '下轴问题');
INSERT INTO `issue_position_list` VALUES ('3', '马达问题');

-- ----------------------------
-- Table structure for `knowledge_lib`
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_lib`;
CREATE TABLE `knowledge_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `html` longtext NOT NULL,
  `author` varchar(255) DEFAULT NULL COMMENT '文章作者',
  `read_times` varchar(255) NOT NULL COMMENT '阅读次数',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge_lib
-- ----------------------------

-- ----------------------------
-- Table structure for `knowledge_pictures`
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_pictures`;
CREATE TABLE `knowledge_pictures` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL COMMENT '知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge_pictures
-- ----------------------------

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
  `customer_in_contract` varchar(255) NOT NULL COMMENT '合同里的客户',
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for `maintain_abnormal_record`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_abnormal_record`;
CREATE TABLE `maintain_abnormal_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maintain_record_id` int(10) unsigned NOT NULL COMMENT '一次保养，可能有多个异常',
  `maintain_content` text NOT NULL COMMENT '异常对应的 保养内容',
  `maitain_abnormal_content` text NOT NULL COMMENT '保养的异常记录',
  `maintain_fix_result` text NOT NULL COMMENT '异常的修理结果',
  PRIMARY KEY (`id`),
  KEY `fk_mar_maintain_record_id` (`maintain_record_id`),
  CONSTRAINT `fk_mar_maintain_record_id` FOREIGN KEY (`maintain_record_id`) REFERENCES `maintain_record` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_abnormal_record
-- ----------------------------

-- ----------------------------
-- Table structure for `maintain_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_customer_feedback`;
CREATE TABLE `maintain_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_customer_feedback
-- ----------------------------
INSERT INTO `maintain_customer_feedback` VALUES ('1', '5', '5分很满意');
INSERT INTO `maintain_customer_feedback` VALUES ('2', '3', '3分还不错。');

-- ----------------------------
-- Table structure for `maintain_lib`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_lib`;
CREATE TABLE `maintain_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maintain_lib_name` varchar(255) NOT NULL COMMENT '保养库的名字， 一期，二期，三期等',
  `maintain_type` int(10) unsigned NOT NULL COMMENT '1: 清理清洁， 2：注油润滑， 3： 检查修理',
  `maintain_content` text COMMENT '保养内容',
  PRIMARY KEY (`id`),
  KEY `fk_mi_maintain_lib` (`maintain_lib_name`) USING BTREE,
  KEY `fk_mi_maintain_type` (`maintain_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_lib
-- ----------------------------
INSERT INTO `maintain_lib` VALUES ('2', 'y54yrt', '0', null);
INSERT INTO `maintain_lib` VALUES ('3', 'aaa', '0', null);
INSERT INTO `maintain_lib` VALUES ('5', 'aaa', '1', 'dsfdsfdsfdsfdsafasdfdasfasd');
INSERT INTO `maintain_lib` VALUES ('6', 'aaa', '1', 'sdfsdfsdfasd');
INSERT INTO `maintain_lib` VALUES ('7', 'bbbb', '0', null);
INSERT INTO `maintain_lib` VALUES ('8', 'bbbb', '1', 'cccccc');

-- ----------------------------
-- Table structure for `maintain_members`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_members`;
CREATE TABLE `maintain_members` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,',
  `maintain_record_id` int(10) unsigned NOT NULL COMMENT '安装记录的ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '员工的ID',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_maintain_record_id` (`maintain_record_id`) USING BTREE,
  CONSTRAINT `fk_mrh_maintain_record_id` FOREIGN KEY (`maintain_record_id`) REFERENCES `maintain_record` (`id`),
  CONSTRAINT `fk_mrh_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_members
-- ----------------------------
INSERT INTO `maintain_members` VALUES ('1', '1', '3');
INSERT INTO `maintain_members` VALUES ('2', '1', '4');
INSERT INTO `maintain_members` VALUES ('3', '2', '3');
INSERT INTO `maintain_members` VALUES ('4', '3', '3');
INSERT INTO `maintain_members` VALUES ('5', '4', '4');
INSERT INTO `maintain_members` VALUES ('6', '4', '14');

-- ----------------------------
-- Table structure for `maintain_record`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_record`;
CREATE TABLE `maintain_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `machine_nameplate` varchar(255) NOT NULL COMMENT '机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号',
  `maintain_lib_name` varchar(255) NOT NULL COMMENT '1：一期，2：二期，3：三期保养',
  `maintain_date_plan` date DEFAULT NULL,
  `maintain_date_actual` date DEFAULT NULL COMMENT '实际保养日期',
  `maintain_charge_person` int(10) unsigned DEFAULT NULL COMMENT '保养人员',
  `maintain_suggestion` varchar(255) DEFAULT NULL COMMENT '保养建议',
  `customer` int(10) unsigned DEFAULT NULL COMMENT '客户',
  `maintain_info` text COMMENT '保养json 举例:\r\n[\r\n  {\r\n        "maintain_lib_name":"一期",\r\n        "maintain_type":"注油润滑",\r\n        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" \r\n		"maintain_value":"1"			//1代表确认OK\r\n    },\r\n    {\r\n        "maintain_lib_name":"一期",\r\n        "maintain_type":"检查修理",\r\n        "maintain_content":"1.检查中间脚盘是否正常" \r\n		"maintain_value":"1" //1代表确认OK\r\n    },\r\n    {\r\n        "maintain_lib_name":"一期",\r\n        "maintain_type":"检查修理",\r\n        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" \r\n		"maintain_value":"0"  //0代表异常  \r\n		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json\r\n    }\r\n]',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `maintain_status` varchar(255) NOT NULL COMMENT '保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果',
  `customer_feedback` int(10) unsigned DEFAULT NULL COMMENT '用户反馈（意见&建议）',
  PRIMARY KEY (`id`),
  KEY `fk_mr_machine_nameplate` (`machine_nameplate`),
  KEY `fk_mr_maintain_person` (`maintain_charge_person`),
  KEY `fk_mr_contacter` (`customer`),
  KEY `fk_mr_customer_feedback` (`customer_feedback`),
  CONSTRAINT `fk_mr_machine_nameplate` FOREIGN KEY (`machine_nameplate`) REFERENCES `machine` (`nameplate`),
  CONSTRAINT `fk_mr_maintain_charge_person` FOREIGN KEY (`maintain_charge_person`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_record
-- ----------------------------
INSERT INTO `maintain_record` VALUES ('1', 'mph2233', '一期保养', '2018-07-12', '2018-07-12', '5', 'sssssgg', '12', 'info 1111', '2018-07-25 10:35:27', '2018-07-25 11:35:32', '3', '1');
INSERT INTO `maintain_record` VALUES ('2', 'mph333', '二期保养', '2018-07-16', '2018-07-16', '4', 'sssssssssssssssssg', '2', 'ffff23343', '0000-00-00 00:00:00', null, '3', '2');
INSERT INTO `maintain_record` VALUES ('3', 'mph--all-data', '三期保养', '2018-07-25', '2018-07-26', '9', 'ssgeeee', '11', 'maintain infos112233', '2018-07-25 17:51:26', null, '2', '2');
INSERT INTO `maintain_record` VALUES ('4', 'mph--all-data', '二期保养', '2018-07-12', '2018-07-25', '4', 'ssssssssssgestsss', '11', 'maint infos23444', '2018-07-25 18:07:32', null, '1', '1');

-- ----------------------------
-- Table structure for `maintain_type`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_type`;
CREATE TABLE `maintain_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maintain_type_name` varchar(255) NOT NULL COMMENT '保养类型，比如清洁清理/注油润滑/检查修理',
  PRIMARY KEY (`id`),
  KEY `type_name` (`maintain_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_type
-- ----------------------------
INSERT INTO `maintain_type` VALUES ('4', 'aaa');
INSERT INTO `maintain_type` VALUES ('5', 'bbb');
INSERT INTO `maintain_type` VALUES ('3', '检查修理');
INSERT INTO `maintain_type` VALUES ('2', '注油润滑');
INSERT INTO `maintain_type` VALUES ('1', '清理清洁');

-- ----------------------------
-- Table structure for `message_pushed`
-- ----------------------------
DROP TABLE IF EXISTS `message_pushed`;
CREATE TABLE `message_pushed` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message_content` text NOT NULL COMMENT '推送的消息内容，由后台组装完成。',
  `pushed_time` datetime NOT NULL COMMENT '推送的时间',
  `record_id` int(10) unsigned DEFAULT NULL COMMENT '安装/保养/维修的记录ID，空则表示只是让客户自己保养。',
  `type` varchar(255) NOT NULL COMMENT '1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_pushed
-- ----------------------------

-- ----------------------------
-- Table structure for `parts_info`
-- ----------------------------
DROP TABLE IF EXISTS `parts_info`;
CREATE TABLE `parts_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parts_name` varchar(255) NOT NULL COMMENT '配件名称',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
  `params` varchar(255) DEFAULT NULL COMMENT '配件参数',
  `repair_actual_info_id` int(10) unsigned NOT NULL COMMENT '一次报修，可以有多个维修，对应多个零件',
  `parts_status` varchar(255) NOT NULL COMMENT '1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)',
  `sendback_tracking_number` varchar(255) DEFAULT NULL COMMENT '快递单号，如果不用寄回，则为空',
  `sendback_tracking_pictrue` varchar(255) DEFAULT NULL COMMENT '寄回的快递单号的照片的保存路径',
  `sendback_date` date DEFAULT NULL COMMENT '寄回日期',
  `sendback_confirmed_time` datetime DEFAULT NULL COMMENT ' 确认寄回的时间',
  `sendback_confirmed_person` int(10) unsigned DEFAULT NULL COMMENT 'sinsim确认人 确认寄回的人',
  PRIMARY KEY (`id`),
  KEY `fk_pi_repair_actual_info` (`repair_actual_info_id`),
  KEY `fk_pi_sendback_confirmed_person` (`sendback_confirmed_person`),
  CONSTRAINT `fk_pi_repair_actual_info` FOREIGN KEY (`repair_actual_info_id`) REFERENCES `repair_actual_info` (`id`),
  CONSTRAINT `fk_pi_sendback_confirmed_person` FOREIGN KEY (`sendback_confirmed_person`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parts_info
-- ----------------------------
INSERT INTO `parts_info` VALUES ('1', '马达12AB', '松下', 'param123', '1', '2', 'sf-track-number-111--马达12AB', '马达12AB 回寄的照片', '2018-08-01', null, null);
INSERT INTO `parts_info` VALUES ('2', '上轴部件222', 'xs', 'pram222', '2', '3', 'sf-track-number-222', 'TEST上轴部件222回寄的照片', '2018-08-01', null, null);
INSERT INTO `parts_info` VALUES ('3', '部件333part_A', 'xs', 'param33', '3', '4', 'sf-track-number-333-部件333--part_A', 'TEST部件333part_A-回寄的照片', '2018-07-31', '2018-08-01 17:24:43', '2');
INSERT INTO `parts_info` VALUES ('4', '部件333part_B', 'xs', 'ppp', '3', '4', 'sf-track-number-333-部件333part_B', null, '2018-07-31', '2018-08-01 17:26:10', '2');
INSERT INTO `parts_info` VALUES ('5', '部件333part_C', 'xs', 'ppram', '3', '4', 'sf-track-number-333--部件333part_C', null, '2018-07-31', '2018-08-01 17:26:10', '2');

-- ----------------------------
-- Table structure for `repair_actual_info`
-- ----------------------------
DROP TABLE IF EXISTS `repair_actual_info`;
CREATE TABLE `repair_actual_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `repair_record_id` int(10) unsigned NOT NULL COMMENT '对应的维修记录，一次报修可以有多个实际维修',
  `issue_position` int(10) unsigned NOT NULL COMMENT '维修部位',
  `issue_description` text NOT NULL COMMENT '实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”',
  `repair_method` text NOT NULL COMMENT '实际维修中的“处理方法”',
  `after_resolve_pictures` varchar(255) DEFAULT NULL COMMENT '解决后的照片（保存文件路径）',
  PRIMARY KEY (`id`),
  KEY `fk_rai_repair_record_id` (`repair_record_id`),
  KEY `fk_rai_issue_position` (`issue_position`),
  CONSTRAINT `fk_rai_issue_position` FOREIGN KEY (`issue_position`) REFERENCES `issue_position_list` (`id`),
  CONSTRAINT `fk_rai_repair_record_id` FOREIGN KEY (`repair_record_id`) REFERENCES `repair_record` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_actual_info
-- ----------------------------
INSERT INTO `repair_actual_info` VALUES ('1', '3', '1', '问题描述,实际发生了xx11111', '实际维修方法1111', '维修后的图片路径111');
INSERT INTO `repair_actual_info` VALUES ('2', '4', '3', '问题描述,实际发生了xx22222', '实际维修方法22222', '维修后的图片路径222');
INSERT INTO `repair_actual_info` VALUES ('3', '5', '2', '问题描述,实际发生了xx3333', '实际维修方法3333', '维修后的图片路径333');
INSERT INTO `repair_actual_info` VALUES ('4', '6', '1', '问题描述,实际发生了xx4444', '实际维修方法4444', '维修后的图片路径444');

-- ----------------------------
-- Table structure for `repair_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `repair_customer_feedback`;
CREATE TABLE `repair_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  `customer_repair_result` varchar(255) DEFAULT NULL COMMENT '维修结果。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_customer_feedback
-- ----------------------------
INSERT INTO `repair_customer_feedback` VALUES ('1', '4', '4f满意', '修理OK');
INSERT INTO `repair_customer_feedback` VALUES ('2', '5', '5F很满意', '修理OK');
INSERT INTO `repair_customer_feedback` VALUES ('3', '5', '5F很满意', '修理OK');
INSERT INTO `repair_customer_feedback` VALUES ('4', '5', '5F很满意0719', '修理OK');
INSERT INTO `repair_customer_feedback` VALUES ('5', '5', '5F很满意0719', '修理OK');
INSERT INTO `repair_customer_feedback` VALUES ('6', '5', '5分很满意0719', '修理OK0727');
INSERT INTO `repair_customer_feedback` VALUES ('7', '5', '5分很满意0719', '修理OK0727');
INSERT INTO `repair_customer_feedback` VALUES ('8', '5', '5分很满意0719', '修理OK0727');
INSERT INTO `repair_customer_feedback` VALUES ('9', '5', '5分很满意0719-0727-a', '修理OK0727-a');
INSERT INTO `repair_customer_feedback` VALUES ('10', '5', '5分很满意0719-0727-a', '修理OK0727-a');
INSERT INTO `repair_customer_feedback` VALUES ('11', '5', '5分很满意0719-0727-a', '修理OK0727-a');

-- ----------------------------
-- Table structure for `repair_members`
-- ----------------------------
DROP TABLE IF EXISTS `repair_members`;
CREATE TABLE `repair_members` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,',
  `repair_record_id` int(10) unsigned NOT NULL COMMENT '安装记录的ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '员工的ID',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_rrh_repair_record_id` (`repair_record_id`),
  CONSTRAINT `fk_rrh_repair_record_id` FOREIGN KEY (`repair_record_id`) REFERENCES `repair_record` (`id`),
  CONSTRAINT `fk_rrh_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_members
-- ----------------------------
INSERT INTO `repair_members` VALUES ('1', '3', '3');
INSERT INTO `repair_members` VALUES ('2', '3', '4');
INSERT INTO `repair_members` VALUES ('3', '4', '3');
INSERT INTO `repair_members` VALUES ('4', '4', '3');
INSERT INTO `repair_members` VALUES ('5', '4', '4');
INSERT INTO `repair_members` VALUES ('6', '5', '4');
INSERT INTO `repair_members` VALUES ('7', '6', '4');
INSERT INTO `repair_members` VALUES ('8', '7', '3');

-- ----------------------------
-- Table structure for `repair_record`
-- ----------------------------
DROP TABLE IF EXISTS `repair_record`;
CREATE TABLE `repair_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `repair_record_num` varchar(255) DEFAULT NULL COMMENT '维修单编号，备用',
  `customer` int(10) unsigned NOT NULL COMMENT '联系人,',
  `machine_nameplate` varchar(255) NOT NULL COMMENT '机器编号',
  `repair_request_info` int(10) unsigned NOT NULL COMMENT '用户发起报修信息，一次报修可以有多个维修记录。',
  `in_warranty_period` varchar(255) DEFAULT NULL COMMENT '1：在保修期内，0：保修期已过， 在派单时指定。',
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of repair_record
-- ----------------------------
INSERT INTO `repair_record` VALUES ('3', 'xx11', '6', 'mph333', '11', '1', '3', '2018-07-24 10:08:45', '2018-07-25 10:08:50', '11', '5', '2018-07-25 10:38:25', '2018-07-27 13:58:54', '1');
INSERT INTO `repair_record` VALUES ('4', null, '6', 'mph--all-data', '12', '1', '4', '2018-07-24 10:37:35', '2018-07-25 10:15:51', '1', '5', '2018-07-19 11:09:49', '2018-07-25 10:38:32', '1');
INSERT INTO `repair_record` VALUES ('5', null, '7', 'mph788', '13', '1', '4', null, '2018-07-25 10:15:55', null, '1', '2018-07-19 13:41:09', null, null);
INSERT INTO `repair_record` VALUES ('6', null, '7', 'mph333', '14', '0', '3', null, '2018-07-26 10:15:59', null, '2', '2018-07-19 13:44:14', null, null);
INSERT INTO `repair_record` VALUES ('7', null, '8', 'mph2233', '15', '0', '3', null, '2018-07-26 10:16:03', null, '3', '2018-07-19 13:45:23', null, null);
INSERT INTO `repair_record` VALUES ('8', null, '8', 'mph555', '16', '0', '3', null, '2018-07-25 10:16:18', null, '4', '2018-07-19 13:46:38', null, null);
INSERT INTO `repair_record` VALUES ('9', null, '9', 'mph777', '17', '1', '10', null, '2018-07-26 10:16:07', null, '2', '2018-07-19 13:50:51', null, null);
INSERT INTO `repair_record` VALUES ('10', null, '6', 'mph789', '18', '1', '10', null, '2018-07-26 10:16:07', null, '3', '2018-07-19 13:55:23', null, null);

-- ----------------------------
-- Table structure for `repair_request_info`
-- ----------------------------
DROP TABLE IF EXISTS `repair_request_info`;
CREATE TABLE `repair_request_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nameplate` varchar(255) NOT NULL DEFAULT '' COMMENT '老机器报修时填写的铭牌号，非老机器自动填写',
  `nameplate_picture` varchar(255) DEFAULT NULL COMMENT '老机器报修时拍的铭牌的图片的保存路径，非老机器为空。',
  `voice` varchar(1000) DEFAULT NULL COMMENT '报修的语音文件的路径',
  `repair_title` varchar(255) NOT NULL COMMENT '报修的标题',
  `content` text NOT NULL COMMENT '报修内容',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片',
  `customer` int(10) unsigned NOT NULL COMMENT '联系人',
  PRIMARY KEY (`id`),
  KEY `fk_rri_contacter` (`customer`),
  CONSTRAINT `fk_rri_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_request_info
-- ----------------------------
INSERT INTO `repair_request_info` VALUES ('1', '', 'mph2233', null, '马达不转', '马达不转2222', null, '1');
INSERT INTO `repair_request_info` VALUES ('2', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-13-16-17-50_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018-07-13-16-17-50_3.png]', '标题有时会断线', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-13-16-17-50_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('3', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-13-16-24-52_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018-07-13-16-24-52_3.png]', '标题有时会断线', '内容最近从五月份开始有时候会断线', '[]', '1');
INSERT INTO `repair_request_info` VALUES ('4', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-13-17-06-34_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018-07-13-17-06-34_3.png]', '标题有时会断线', '内容最近从五月份开始有时候会断线', '[]', '1');
INSERT INTO `repair_request_info` VALUES ('5', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-00-07_2.png]', '[]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-00-07_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('6', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-00-16_2.png]', '[]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-00-16_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('7', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-04-40_2.png]', '[]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-04-40_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('8', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-05-54_2.png]', '[]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-05-54_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('9', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-06-01_2.png]', '[]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-06-01_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('10', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-06-35_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018xxxx.mp3]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-06-35_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('11', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-07-25_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018xxxx22.mp3]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-07-25_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('12', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-11-09-49_2.png]', '[D:/images/repair_req_voice/ mph333_REPAIR_REQUEST_VOICE_2018xxxx33.mp3]', '标题有时会断线0719', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-11-09-49_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('13', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-41-09_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-41-09_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('14', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-44-14_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-44-14_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('15', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-45-23_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-45-23_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('16', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-46-38_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-46-38_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('17', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-50-51_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-50-51_1.png]', '1');
INSERT INTO `repair_request_info` VALUES ('18', 'mph333', '[D:/images/repair_req_nameplate/ mph333_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-07-19-13-55-23_2.png]', '[]', '标题有时会断线0719-2', '内容最近从五月份开始有时候会断线', '[D:/images/repair_req_img/ mph333_REPAIR_REQUEST_IMAGE_2018-07-19-13-55-23_1.png]', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `role_des` varchar(255) DEFAULT NULL COMMENT '角色说明',
  `role_scope` text COMMENT '角色权限列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '全局管理', '');
INSERT INTO `role` VALUES ('2', '管理员', '售后业务管理', null);
INSERT INTO `role` VALUES ('3', '普通员工', '安装，保养，维修（包括了信胜和客户的）', null);
INSERT INTO `role` VALUES ('4', '代理商', '代理商', null);
INSERT INTO `role` VALUES ('5', '客户本身', '权限在客户级别，比如查看客户自己所有机器', null);
INSERT INTO `role` VALUES ('6', '客户的联系人', '权限在联系人自己级别，比如只能查看联系人自己名下的机器', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `wechat_union_id` varchar(255) DEFAULT NULL COMMENT '微信unionId，在没授权前是空的。',
  `role_id` int(10) unsigned NOT NULL,
  `agent` int(10) unsigned DEFAULT NULL COMMENT '代理商,如果是空表示是信胜自己的员工',
  `customer_company` int(10) unsigned DEFAULT NULL COMMENT '客户公司',
  `password` varchar(255) NOT NULL,
  `valid` varchar(255) NOT NULL COMMENT '是否在职 ， “1”:在职 “0”:离职',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `create_time` datetime NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_u_role_id` (`role_id`),
  KEY `fk_u_agent` (`agent`),
  CONSTRAINT `fk_u_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'wechat222', '1', '1', '0', 'sinsim', '1', 'admin13566667777', '2018-07-11 10:03:43', '客户地址admin111');
INSERT INTO `user` VALUES ('2', 'admin_in_aftersale', 'admin_in_aftersale', null, '2', '1', '0', 'sinsim', '1', 'admin_in_aftersale13455556666', '2018-07-21 08:53:49', '客户地址admin_in_after111');
INSERT INTO `user` VALUES ('3', 'repairPerson_zhansan', 'repairPerson_zhansan', '', '3', '1', '0', 'sinsim', '1', 'repairPerson_zhansan的电话13455556633', '0000-00-00 00:00:00', '客户地址zhangsan3333');
INSERT INTO `user` VALUES ('4', 'repairPerson_lisi', 'repairPerson_lisi', '', '3', '2', '0', 'sinsim', '1', 'repairPerson_lisi1的电话3455556666', '0000-00-00 00:00:00', '客户地址Lisi444');
INSERT INTO `user` VALUES ('5', 'agent444', 'agent444', null, '4', '2', '0', 'sinsim', '1', 'agent444的电话444', '0000-00-00 00:00:00', '客户地址agent444');
INSERT INTO `user` VALUES ('6', 'kehu1', 'kehu1', null, '5', '2', '0', 'sinsim', '1', 'kehu1的的电话xxxx', '0000-00-00 00:00:00', '客户地址kehu1');
INSERT INTO `user` VALUES ('7', 'kehu2', 'kehu2', null, '5', '1', '0', 'sinsim', '1', 'kehu2的的电话xxxx', '0000-00-00 00:00:00', '客户地址kehu2');
INSERT INTO `user` VALUES ('8', 'kehu_Lxr1', 'kehu_Lxr1', null, '6', '1', '0', 'sinsim', '1', 'kehu_Lxr1的的电话xxxx', '0000-00-00 00:00:00', '客户地址kehu_Lxr1');
INSERT INTO `user` VALUES ('9', 'kehu_Lxr2', 'kehu_Lxr2', null, '6', '1', '0', 'sinsim', '1', 'kehu_Lxr2的的电话xxxx', '0000-00-00 00:00:00', '客户地址kehu_Lxr2');
INSERT INTO `user` VALUES ('10', 'repairPerson_wangwu', 'repairPerson_wangwu', null, '3', '1', '0', 'sinsim', '1', 'repairPerson_wangwu的电话yyyy', '0000-00-00 00:00:00', '客户地址wangwu5555');
INSERT INTO `user` VALUES ('11', 'kehu3_w_agent', 'kehu3_w_agent', null, '5', '2', '0', 'sinsim', '1', 'kehu3_w_agent的电话zzz', '0000-00-00 00:00:00', '客户地址kehu3_w_agent3333');
INSERT INTO `user` VALUES ('12', 'kehu4_w_agent', 'kehu4_w_agent', null, '5', '1', '0', 'sinsim', '1', 'kehu4_w_agent的电话www4444', '0000-00-00 00:00:00', '客户地址kehu4_w_agent4444444');
INSERT INTO `user` VALUES ('13', 'repairPerson13', 'repairPerson13', null, '3', null, null, 'sinsim', '', 'repairPerson13-phone333', '0000-00-00 00:00:00', null);
INSERT INTO `user` VALUES ('14', 'user14', 'user14', null, '3', null, '0', 'sinsim', '1', 'user14-phone123', '0000-00-00 00:00:00', null);
