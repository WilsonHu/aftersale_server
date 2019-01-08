/*
Navicat MySQL Data Transfer

Source Server         : MyDB
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2019-01-08 11:36:59
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent
-- ----------------------------
INSERT INTO `agent` VALUES ('1', '阿里巴巴', '15715766877', '浙江杭州西溪湿地', '2018-09-21 11:20:34');
INSERT INTO `agent` VALUES ('2', '小米科技', '15715766877', '北京市朝阳区', '2018-09-21 11:21:09');
INSERT INTO `agent` VALUES ('3', 'XX布业公司', '15715766877', 'XX国XX省XX市XX路XX号', '2018-09-21 14:38:31');

-- ----------------------------
-- Table structure for `customer_company`
-- ----------------------------
DROP TABLE IF EXISTS `customer_company`;
CREATE TABLE `customer_company` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_company_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_company
-- ----------------------------
INSERT INTO `customer_company` VALUES ('1', '杭州市XXX公司');
INSERT INTO `customer_company` VALUES ('2', '北京市XXX公司');
INSERT INTO `customer_company` VALUES ('3', '印度XXX公司');
INSERT INTO `customer_company` VALUES ('4', '智利XXX公司');
INSERT INTO `customer_company` VALUES ('5', '北美XXX公司');
INSERT INTO `customer_company` VALUES ('6', '绍兴华文电脑刺绣有限公司\r\n绍兴华文电脑刺绣有限公司\r\n绍兴华文电脑刺绣有限公司\r\n绍兴华文电脑刺绣有限公司\r\n绍兴华文电脑刺绣有限公司\r\n绍兴华文电脑刺绣有限公司');

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
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '目前forward_info这个表未使用，只是在数据库记录了是否转派， repair_record到forward_info的外键现在也删除。',
  `forword_time` datetime NOT NULL COMMENT '转派时间',
  `comment` varchar(255) DEFAULT NULL COMMENT '可以附带一些信息，告知原厂',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forward_info
-- ----------------------------

-- ----------------------------
-- Table structure for `install_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `install_customer_feedback`;
CREATE TABLE `install_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_customer_feedback
-- ----------------------------
INSERT INTO `install_customer_feedback` VALUES ('1', '0', '辣鸡');
INSERT INTO `install_customer_feedback` VALUES ('3', '0', '好好好');
INSERT INTO `install_customer_feedback` VALUES ('4', '0', '嘎嘎嘎嘎');
INSERT INTO `install_customer_feedback` VALUES ('5', '0', '非常好');
INSERT INTO `install_customer_feedback` VALUES ('6', '0', '哈哈哈哈啊可能');
INSERT INTO `install_customer_feedback` VALUES ('7', '0', '嘎嘎嘎');
INSERT INTO `install_customer_feedback` VALUES ('8', '0', '机器调试正常，非常满意');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_lib
-- ----------------------------
INSERT INTO `install_lib` VALUES ('2', '0', '基础项', null);
INSERT INTO `install_lib` VALUES ('3', '1', '基础项', '电源电压A');
INSERT INTO `install_lib` VALUES ('4', '1', '基础项', '电源电压B');
INSERT INTO `install_lib` VALUES ('5', '1', '基础项', '电源电压C');
INSERT INTO `install_lib` VALUES ('6', '1', '基础项', '单相电压');
INSERT INTO `install_lib` VALUES ('7', '1', '基础项', '接地情况');
INSERT INTO `install_lib` VALUES ('8', '1', '基础项', '机器水平');
INSERT INTO `install_lib` VALUES ('9', '1', '基础项', '台板支撑');
INSERT INTO `install_lib` VALUES ('10', '0', '平绣', null);
INSERT INTO `install_lib` VALUES ('11', '1', '平绣', '1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。');
INSERT INTO `install_lib` VALUES ('12', '1', '平绣', '2.与客户清点工具箱内配件。');
INSERT INTO `install_lib` VALUES ('13', '1', '平绣', '3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。');
INSERT INTO `install_lib` VALUES ('14', '1', '平绣', '4.检查每一个机头的伞齿轮间隙。');
INSERT INTO `install_lib` VALUES ('15', '1', '平绣', '5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。');
INSERT INTO `install_lib` VALUES ('16', '1', '平绣', '6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）					\n');
INSERT INTO `install_lib` VALUES ('17', '1', '平绣', '7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。					');
INSERT INTO `install_lib` VALUES ('18', '1', '平绣', '8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。					');
INSERT INTO `install_lib` VALUES ('19', '1', '平绣', '9.测试主轴转速，对旋梭、针杆、主动轴加注白油。');
INSERT INTO `install_lib` VALUES ('20', '1', '平绣', '10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。');
INSERT INTO `install_lib` VALUES ('21', '1', '平绣', '11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。');
INSERT INTO `install_lib` VALUES ('22', '1', '平绣', '12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。');
INSERT INTO `install_lib` VALUES ('23', '1', '平绣', '13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。');
INSERT INTO `install_lib` VALUES ('24', '1', '平绣', '14.调整底面线张力。');
INSERT INTO `install_lib` VALUES ('25', '1', '平绣', '15.U盘输入花样，上调试布并调试。');
INSERT INTO `install_lib` VALUES ('26', '1', '平绣', '16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。');
INSERT INTO `install_lib` VALUES ('27', '0', '特种装置', null);
INSERT INTO `install_lib` VALUES ('28', '1', '特种装置', '1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。');
INSERT INTO `install_lib` VALUES ('29', '1', '特种装置', '2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。');
INSERT INTO `install_lib` VALUES ('30', '1', '特种装置', '3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。');
INSERT INTO `install_lib` VALUES ('31', '1', '特种装置', '4.对客户领班、机修工，对装置维修，金片规格改装、绳绣粗细调整，进行指导。');
INSERT INTO `install_lib` VALUES ('32', '0', '毛巾绣机型', null);
INSERT INTO `install_lib` VALUES ('33', '1', '毛巾绣机型', '1.查看主轴位置35°，换色箱位置正常再开机。');
INSERT INTO `install_lib` VALUES ('34', '1', '毛巾绣机型', '2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。');
INSERT INTO `install_lib` VALUES ('35', '1', '毛巾绣机型', '3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。');
INSERT INTO `install_lib` VALUES ('36', '1', '毛巾绣机型', '4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。');
INSERT INTO `install_lib` VALUES ('37', '1', '毛巾绣机型', '5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。');
INSERT INTO `install_lib` VALUES ('38', '1', '毛巾绣机型', '6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。');

-- ----------------------------
-- Table structure for `install_members`
-- ----------------------------
DROP TABLE IF EXISTS `install_members`;
CREATE TABLE `install_members` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,    20181102更新：也包含安装负责人',
  `install_record_id` int(10) unsigned NOT NULL COMMENT '安装记录的ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '员工的ID',
  PRIMARY KEY (`id`),
  KEY `fk_install_record_id` (`install_record_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_install_record_id` FOREIGN KEY (`install_record_id`) REFERENCES `install_record` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_members
-- ----------------------------
INSERT INTO `install_members` VALUES ('1', '1', '3');
INSERT INTO `install_members` VALUES ('2', '2', '3');
INSERT INTO `install_members` VALUES ('3', '2', '10');
INSERT INTO `install_members` VALUES ('4', '3', '3');
INSERT INTO `install_members` VALUES ('5', '3', '10');
INSERT INTO `install_members` VALUES ('6', '3', '13');
INSERT INTO `install_members` VALUES ('7', '2', '10');
INSERT INTO `install_members` VALUES ('8', '3', '8');
INSERT INTO `install_members` VALUES ('9', '3', '10');
INSERT INTO `install_members` VALUES ('10', '4', '8');
INSERT INTO `install_members` VALUES ('11', '5', '8');
INSERT INTO `install_members` VALUES ('12', '6', '3');
INSERT INTO `install_members` VALUES ('13', '6', '8');
INSERT INTO `install_members` VALUES ('14', '6', '10');
INSERT INTO `install_members` VALUES ('15', '6', '13');
INSERT INTO `install_members` VALUES ('16', '18', '3');
INSERT INTO `install_members` VALUES ('17', '18', '8');
INSERT INTO `install_members` VALUES ('18', '18', '10');
INSERT INTO `install_members` VALUES ('19', '18', '13');
INSERT INTO `install_members` VALUES ('20', '19', '3');
INSERT INTO `install_members` VALUES ('21', '19', '8');
INSERT INTO `install_members` VALUES ('22', '7', '18');
INSERT INTO `install_members` VALUES ('23', '21', '18');
INSERT INTO `install_members` VALUES ('24', '23', '8');
INSERT INTO `install_members` VALUES ('25', '23', '3');
INSERT INTO `install_members` VALUES ('26', '23', '18');
INSERT INTO `install_members` VALUES ('27', '22', '3');
INSERT INTO `install_members` VALUES ('28', '22', '18');
INSERT INTO `install_members` VALUES ('29', '24', '8');
INSERT INTO `install_members` VALUES ('30', '28', '8');
INSERT INTO `install_members` VALUES ('31', '25', '21');
INSERT INTO `install_members` VALUES ('32', '25', '22');
INSERT INTO `install_members` VALUES ('33', '26', '21');
INSERT INTO `install_members` VALUES ('34', '35', '21');
INSERT INTO `install_members` VALUES ('35', '37', '21');
INSERT INTO `install_members` VALUES ('36', '38', '22');
INSERT INTO `install_members` VALUES ('37', '27', '29');
INSERT INTO `install_members` VALUES ('38', '29', '21');
INSERT INTO `install_members` VALUES ('39', '8', '3');
INSERT INTO `install_members` VALUES ('40', '8', '21');
INSERT INTO `install_members` VALUES ('41', '8', '26');
INSERT INTO `install_members` VALUES ('42', '82', '8');
INSERT INTO `install_members` VALUES ('43', '83', '3');
INSERT INTO `install_members` VALUES ('44', '83', '8');
INSERT INTO `install_members` VALUES ('45', '85', '3');
INSERT INTO `install_members` VALUES ('46', '85', '8');
INSERT INTO `install_members` VALUES ('47', '87', '3');
INSERT INTO `install_members` VALUES ('48', '87', '8');
INSERT INTO `install_members` VALUES ('49', '86', '3');
INSERT INTO `install_members` VALUES ('50', '86', '8');
INSERT INTO `install_members` VALUES ('51', '84', '21');
INSERT INTO `install_members` VALUES ('52', '84', '8');
INSERT INTO `install_members` VALUES ('53', '81', '3');
INSERT INTO `install_members` VALUES ('54', '81', '8');
INSERT INTO `install_members` VALUES ('55', '80', '3');
INSERT INTO `install_members` VALUES ('56', '80', '8');
INSERT INTO `install_members` VALUES ('57', '88', '22');
INSERT INTO `install_members` VALUES ('58', '88', '23');
INSERT INTO `install_members` VALUES ('59', '57', '3');
INSERT INTO `install_members` VALUES ('60', '57', '8');
INSERT INTO `install_members` VALUES ('61', '48', '10');
INSERT INTO `install_members` VALUES ('62', '48', '8');
INSERT INTO `install_members` VALUES ('63', '78', '8');
INSERT INTO `install_members` VALUES ('64', '78', '3');
INSERT INTO `install_members` VALUES ('65', '75', '8');
INSERT INTO `install_members` VALUES ('66', '75', '3');
INSERT INTO `install_members` VALUES ('67', '39', '8');
INSERT INTO `install_members` VALUES ('68', '39', '3');
INSERT INTO `install_members` VALUES ('69', '59', '8');
INSERT INTO `install_members` VALUES ('70', '59', '10');
INSERT INTO `install_members` VALUES ('71', '52', '8');
INSERT INTO `install_members` VALUES ('72', '52', '3');
INSERT INTO `install_members` VALUES ('73', '49', '8');
INSERT INTO `install_members` VALUES ('74', '49', '3');
INSERT INTO `install_members` VALUES ('75', '41', '8');
INSERT INTO `install_members` VALUES ('76', '41', '13');
INSERT INTO `install_members` VALUES ('77', '40', '8');
INSERT INTO `install_members` VALUES ('78', '40', '10');
INSERT INTO `install_members` VALUES ('79', '89', '8');
INSERT INTO `install_members` VALUES ('80', '89', '10');
INSERT INTO `install_members` VALUES ('81', '89', '3');
INSERT INTO `install_members` VALUES ('82', '92', '21');
INSERT INTO `install_members` VALUES ('83', '92', '26');
INSERT INTO `install_members` VALUES ('84', '90', '21');
INSERT INTO `install_members` VALUES ('85', '90', '26');
INSERT INTO `install_members` VALUES ('86', '36', '21');
INSERT INTO `install_members` VALUES ('87', '31', '21');
INSERT INTO `install_members` VALUES ('88', '33', '21');
INSERT INTO `install_members` VALUES ('89', '95', '21');
INSERT INTO `install_members` VALUES ('90', '91', '21');
INSERT INTO `install_members` VALUES ('91', '17', '21');

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
  `install_info` longtext COMMENT '全部安装信息，json格式\r\n [\r\n  {\r\n   "install_content": "4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。",\r\n   "install_lib_name": "毛巾绣机型",\r\n   "install_value": "1",\r\n   "is_base_lib": "1"\r\n  },\r\n  {\r\n   "install_content": "5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。",\r\n   "install_lib_name": "毛巾绣机型",\r\n   "install_value": "1",\r\n   "is_base_lib": "1"\r\n  },\r\n  {\r\n   "install_content": "电源电压A",\r\n   "install_lib_name": "基础项",\r\n   "install_value": "",\r\n   "is_base_lib": "1"\r\n  },\r\n  {\r\n   "install_content": "电源电压B",\r\n   "install_lib_name": "基础项",\r\n   "install_value": "",\r\n   "is_base_lib": "1"\r\n  }\r\n]',
  `create_time` datetime DEFAULT NULL COMMENT '该记录的创建时间',
  `update_time` datetime DEFAULT NULL,
  `customer` int(10) unsigned DEFAULT NULL COMMENT '客户',
  PRIMARY KEY (`id`),
  KEY `fk_ir_machine_nameplate` (`machine_nameplate`),
  KEY `fk_ir_contacter` (`customer`),
  KEY `fk_ir_maintain_charge_person` (`install_charge_person`),
  KEY `fk_ir_customer_feedback` (`customer_feedback`),
  CONSTRAINT `fk_ir_machine_nameplate` FOREIGN KEY (`machine_nameplate`) REFERENCES `machine` (`nameplate`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_record
-- ----------------------------
INSERT INTO `install_record` VALUES ('1', '2018092116441400000', null, null, '1806004', '安装OK', '3', '0', '3', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-21 16:44:14', '2018-09-21 17:42:32', '0');
INSERT INTO `install_record` VALUES ('2', '2018092116441400001', null, null, '1806003', null, '1', null, '10', null, '2018-09-21 16:44:14', '2018-09-21 19:50:42', null);
INSERT INTO `install_record` VALUES ('3', '2018092117595500002', null, null, '1806030', null, '1', null, '10', null, '2018-09-21 17:59:56', '2018-09-21 20:01:38', null);
INSERT INTO `install_record` VALUES ('4', '2018092117595500003', null, null, '1806029', '', '2', '0', '8', '', '2018-09-21 17:59:56', '2018-09-21 20:03:54', '0');
INSERT INTO `install_record` VALUES ('5', '2018092121135300000', null, null, '1806024', '123', '3', '0', '8', '[{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-09-21 21:13:53', '2018-09-21 21:22:20', '9');
INSERT INTO `install_record` VALUES ('6', '2018092211363100000', null, null, '1806023', '大撒法大使', '3', '0', '8', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 11:36:31', '2018-09-22 11:37:20', '0');
INSERT INTO `install_record` VALUES ('7', '2018092211465200001', null, null, '1807027', null, '1', null, '18', null, '2018-09-22 11:46:52', '2018-09-26 11:46:35', '17');
INSERT INTO `install_record` VALUES ('8', '2018092211474000002', '2018-11-01', '2018-11-01 11:55:50', '1807026', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 11:47:40', '2018-11-01 10:53:08', '17');
INSERT INTO `install_record` VALUES ('9', '2018092211474000003', null, null, '1807025', null, '0', null, null, null, '2018-09-22 11:47:40', null, null);
INSERT INTO `install_record` VALUES ('10', '2018092211570300004', null, null, '1806014', '', '0', '0', '0', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 11:57:03', null, '0');
INSERT INTO `install_record` VALUES ('11', '2018092211571700005', null, null, '1807052', null, '0', null, null, null, '2018-09-22 11:57:18', null, null);
INSERT INTO `install_record` VALUES ('12', '2018092211573400006', null, null, '1807049', null, '0', null, null, null, '2018-09-22 11:57:34', null, null);
INSERT INTO `install_record` VALUES ('13', '2018092211575800007', null, null, '1807090', null, '0', null, null, null, '2018-09-22 11:57:58', null, null);
INSERT INTO `install_record` VALUES ('14', '2018092211575800008', null, null, '1807089', null, '0', null, null, null, '2018-09-22 11:57:58', null, null);
INSERT INTO `install_record` VALUES ('15', '2018092211575800009', null, null, '1807088', null, '0', null, null, null, '2018-09-22 11:57:58', null, null);
INSERT INTO `install_record` VALUES ('16', '2018092211575800010', null, null, '1807087', null, '0', null, null, null, '2018-09-22 11:57:58', null, null);
INSERT INTO `install_record` VALUES ('17', '2018092211575800011', '2018-11-06', null, '1807086', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 11:57:58', '2018-11-06 14:26:57', '17');
INSERT INTO `install_record` VALUES ('18', '2018092214120500012', null, null, '1806016', '完成', '3', '1', '8', '[{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 14:12:05', '2018-10-09 10:54:34', '0');
INSERT INTO `install_record` VALUES ('19', '2018092215104300013', null, null, '1806018', 'fasd ', '3', '0', '8', '[{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.对客户领班、机修工，对装置维修，金片规格改装、绳绣粗细调整，进行指导。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"asfd\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"fas\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"fas\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-22 15:10:44', '2018-09-22 15:11:46', '0');
INSERT INTO `install_record` VALUES ('20', '2018092215104300014', null, null, '1806017', null, '0', null, null, null, '2018-09-22 15:10:44', null, null);
INSERT INTO `install_record` VALUES ('21', '2018092316444600000', null, null, '1806001', '', '1', '0', '18', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-09-23 16:44:47', '2018-09-30 17:10:56', '6');
INSERT INTO `install_record` VALUES ('22', '2018092316444600001', null, null, '1806002', 'OK', '3', '3', '3', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"380\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-09-23 16:44:47', '2018-10-19 14:35:26', '6');
INSERT INTO `install_record` VALUES ('23', '2018100715471000000', null, null, '1806022', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-07 15:47:11', '2018-10-19 14:08:56', '17');
INSERT INTO `install_record` VALUES ('24', '2018102409360200000', null, '2018-10-24 10:57:38', '1806026', '好好好', '4', '4', '8', '[{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 09:36:02', '2018-10-24 10:57:38', '19');
INSERT INTO `install_record` VALUES ('25', '2018102409360200001', null, '2018-10-25 16:42:20', '1806025', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 09:36:02', '2018-10-25 16:17:59', '19');
INSERT INTO `install_record` VALUES ('26', '2018102409471400002', null, null, '1806020', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 09:47:15', '2018-10-25 16:18:28', '19');
INSERT INTO `install_record` VALUES ('27', '2018102409471400003', null, '2018-10-28 10:56:33', '1806019', '', '2', '0', '29', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 09:47:15', '2018-10-28 10:55:54', '19');
INSERT INTO `install_record` VALUES ('28', '2018102413354100000', null, '2018-11-01 13:53:51', '1806021', 'fdasfda', '3', '0', '8', '[{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.对客户领班、机修工，对装置维修，金片规格改装、绳绣粗细调整，进行指导。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"380\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"380\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"没有\",\"is_base_lib\":\"1\"}]', '2018-10-24 13:35:41', '2018-10-24 14:42:41', '19');
INSERT INTO `install_record` VALUES ('29', '2018102414543000000', null, null, '1806015', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 14:54:31', '2018-10-29 11:07:12', '19');
INSERT INTO `install_record` VALUES ('30', '2018102414544200001', null, null, '1806031', '', '0', '0', '0', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.对客户领班、机修工，对装置维修，金片规格改装、绳绣粗细调整，进行指导。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 14:54:43', null, '0');
INSERT INTO `install_record` VALUES ('31', '2018102415031800002', '2018-11-30', '2018-11-04 10:09:46', '1807032', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 15:03:18', '2018-11-04 13:47:35', '19');
INSERT INTO `install_record` VALUES ('32', '2018102416063900003', null, null, '1806038', null, '0', null, null, null, '2018-10-24 16:06:40', null, null);
INSERT INTO `install_record` VALUES ('33', '2018102416063900004', '2018-11-06', null, '1806037', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-24 16:06:40', '2018-11-06 14:25:53', '19');
INSERT INTO `install_record` VALUES ('34', '2018102417212600000', null, null, '1806027', null, '0', null, null, null, '2018-10-24 17:21:27', null, null);
INSERT INTO `install_record` VALUES ('35', '2018102516275500000', null, '2018-11-04 10:02:19', '1806049', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-25 16:27:56', '2018-10-25 16:31:10', '19');
INSERT INTO `install_record` VALUES ('36', '2018102516275500001', '2018-11-04', null, '1806050', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-25 16:27:56', '2018-11-04 10:06:14', '19');
INSERT INTO `install_record` VALUES ('37', '2018102610070700000', null, '2018-10-26 10:24:50', '1807001', '好好好', '3', '0', '21', '[{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.对客户领班、机修工，对装置维修，金片规格改装、绳绣粗细调整，进行指导。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"11\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"11\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"1111\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"222\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-10-26 10:07:08', '2018-10-26 10:07:36', '19');
INSERT INTO `install_record` VALUES ('38', '2018102810121400000', null, '2018-10-28 10:39:10', '1806028', '调试完成，机器正常', '4', '5', '22', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"100\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"30\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"50\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"60\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-10-28 10:12:14', '2018-10-28 10:39:10', '28');
INSERT INTO `install_record` VALUES ('39', '2018103117062800000', '2018-11-02', '2018-11-02 11:40:51', '1806044', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-10-31 17:06:29', '2018-11-02 11:39:05', '19');
INSERT INTO `install_record` VALUES ('40', '2018110109290700000', '2018-11-02', '2018-11-02 14:49:56', '1806034', '呵呵', '4', '6', '8', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"223\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"380\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"不正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"不正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-11-01 09:29:07', '2018-11-02 14:49:56', '19');
INSERT INTO `install_record` VALUES ('41', '2018110109354600000', '2018-11-02', '2018-11-02 13:37:23', '1806033', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 09:35:47', '2018-11-02 13:37:08', '19');
INSERT INTO `install_record` VALUES ('48', '2018110110022800000', '2018-11-02', null, '1806067', '', '1', '0', '10', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 10:02:29', '2018-11-02 10:58:03', '19');
INSERT INTO `install_record` VALUES ('49', '2018110110022900001', '2018-11-02', '2018-11-02 13:27:19', '1806066', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 10:02:29', '2018-11-02 13:26:43', '19');
INSERT INTO `install_record` VALUES ('51', '2018110110075400003', null, null, '1806046', null, '0', null, null, null, '2018-11-01 10:07:54', null, null);
INSERT INTO `install_record` VALUES ('52', '2018110110131300000', '2018-11-02', '2018-11-02 11:54:07', '1806032', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 10:13:14', '2018-11-02 11:53:51', '19');
INSERT INTO `install_record` VALUES ('57', '2018110114520800004', '2018-11-02', null, '1806041', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 14:52:08', '2018-11-02 10:56:56', '19');
INSERT INTO `install_record` VALUES ('59', '2018110115141800000', '2018-11-02', '2018-11-02 11:46:12', '1806036', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 15:14:18', '2018-11-02 11:43:40', '19');
INSERT INTO `install_record` VALUES ('66', '2018110116192700000', null, null, '1806035', null, '0', null, null, null, '2018-11-01 16:19:28', null, null);
INSERT INTO `install_record` VALUES ('70', '2018110116290600004', null, null, '1806068', null, '0', null, null, null, '2018-11-01 16:29:07', null, null);
INSERT INTO `install_record` VALUES ('71', '2018110116355900005', null, null, '1806056', null, '0', null, null, null, '2018-11-01 16:35:59', null, null);
INSERT INTO `install_record` VALUES ('75', '2018110116403600009', '2018-11-02', null, '1807020', '', '1', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 16:40:37', '2018-11-02 11:25:19', '19');
INSERT INTO `install_record` VALUES ('78', '2018110116435800012', '2018-11-02', null, '1807107', '', '1', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 16:43:59', '2018-11-02 11:24:43', '19');
INSERT INTO `install_record` VALUES ('79', '2018110116555700000', null, null, '1806039', '', '0', '0', '0', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 16:55:58', null, '0');
INSERT INTO `install_record` VALUES ('80', '2018110116570300001', '2018-11-02', null, '1806069', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 16:57:04', '2018-11-02 09:38:04', '19');
INSERT INTO `install_record` VALUES ('81', '2018110116573000002', '2018-11-02', null, '1806057', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-01 16:57:31', '2018-11-02 09:32:22', '11');
INSERT INTO `install_record` VALUES ('82', '2018110208563400003', '2018-11-02', '2018-11-02 11:17:28', '1806040', '', '2', '0', '8', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 08:56:35', '2018-11-02 08:59:34', '19');
INSERT INTO `install_record` VALUES ('83', '2018110209002400004', '2018-11-02', null, '1806042', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 09:00:24', '2018-11-02 09:00:57', '19');
INSERT INTO `install_record` VALUES ('84', '2018110209024400005', '2018-11-02', null, '1806043', '', '1', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 09:02:44', '2018-11-02 09:31:41', '19');
INSERT INTO `install_record` VALUES ('85', '2018110209031600006', '2018-11-02', null, '1806045', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 09:03:16', '2018-11-02 09:04:10', '19');
INSERT INTO `install_record` VALUES ('86', '2018110209213000000', '2018-11-02', null, '1806059', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 09:21:31', '2018-11-02 09:30:48', '19');
INSERT INTO `install_record` VALUES ('87', '2018110209213100001', '2018-11-02', null, '1806058', '', '1', '0', '3', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 09:21:32', '2018-11-02 09:27:43', '19');
INSERT INTO `install_record` VALUES ('88', '2018110210360800000', '2018-11-03', '2018-11-06 10:21:03', '1807093', '正常', '4', '8', '23', '[{\"install_content\":\"1.查看主轴位置35°，换色箱位置正常再开机。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.机头升降无异响，针高位置一致，针高最低点针离针板2mm。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.松线电机、换色、剪线电机测试，位置准确，无异响、卡顿。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.原点、穿线点无偏差。调整线的张力，挑线簧弹力。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.链式绣无毛线、漏线。毛巾高度一致，无倾斜，剪线正常。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.对客户领班、机修工，日常线毛清理、机器设置、维修进行指导。\",\"install_lib_name\":\"毛巾绣机型\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"220\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"}]', '2018-11-02 10:36:08', '2018-11-06 10:21:03', '24');
INSERT INTO `install_record` VALUES ('89', '2018110216271500000', '2018-11-02', '2018-11-02 16:31:42', '1806062', '啦啦啦啦啦啦', '4', '7', '8', '[{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"1\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"111\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"11\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"555\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"正常\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"没有\",\"is_base_lib\":\"1\"}]', '2018-11-02 16:27:15', '2018-11-02 16:31:42', '19');
INSERT INTO `install_record` VALUES ('90', '2018110216271500001', '2018-11-04', '2018-11-04 10:01:06', '1806063', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 16:27:16', '2018-11-04 09:58:21', '19');
INSERT INTO `install_record` VALUES ('91', '2018110218005700000', '2018-11-23', '2018-12-04 11:51:33', '1806051', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-02 18:00:57', '2018-11-05 14:33:43', '19');
INSERT INTO `install_record` VALUES ('92', '2018110408550900001', '2018-11-04', '2018-11-04 10:00:03', '1806047', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.机器摆放至预定位置，安装底脚盘，调整机器水平，清洁机器。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.与客户清点工具箱内配件。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.关闭机器及外接的电源开关，区分火线、零线、地线，相应接线。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"4.检查每一个机头的伞齿轮间隙。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"5.测试换色，无卡滞。针与针板孔的位置在中心，压脚高度1~1.5mm。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"6.移动框架，并检查限位开关是否正常。（增加横档必须重新调整限位开关位置）\\t\\t\\t\\t\\t\\n\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"7.测试按钮开关、拉杆开关、勾、剪、扣，断线报警等各项基本功能。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"8.主轴点动，查看主轴运转是否正常，停车位置是否在100°。\\t\\t\\t\\t\\t\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"9.测试主轴转速，对旋梭、针杆、主动轴加注白油。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"10.机架、线架无较大震动;轴罩盖，传动箱、皮带、主轴无较大异响。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"11.对每个针位进行点动、针停下位操作，压脚针杆无抖动。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"12.机器两头、中间打开针板，查看针位深浅、旋梭间隙，根据实际情况进行调整。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"13.有雕孔刀机型，查看刀棱方向、高度、头距是否一致。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"14.调整底面线张力。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"15.U盘输入花样，上调试布并调试。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"16.对客户的领班、机修工，进行机器操作、加油、简单维修进行指导。\",\"install_lib_name\":\"平绣\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-04 08:55:09', '2018-11-04 09:55:34', '19');
INSERT INTO `install_record` VALUES ('93', '2018110513314000005', null, null, '1806048', null, '0', null, null, null, '2018-11-05 13:31:41', null, null);
INSERT INTO `install_record` VALUES ('94', '2018110513322800006', null, null, '1806060', null, '0', null, null, null, '2018-11-05 13:32:28', null, null);
INSERT INTO `install_record` VALUES ('95', '2018110514003900000', '2018-11-29', '2018-11-06 15:11:50', '1806061', '', '2', '0', '21', '[{\"install_content\":\"电源电压A\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压B\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"电源电压C\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"单相电压\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"接地情况\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"机器水平\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"台板支撑\",\"install_lib_name\":\"基础项\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"2.同时升降所有金片、绳绣装置，应升降一致，无撞击、迟缓。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"3.装上相应规格金片、绳线调试，金片无尾及漏片。绳绣平整、无漏针。\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"},{\"install_content\":\"1.接好气路，调整过滤器气压，解开金片、绳绣装置机械锁。麻将机军军\",\"install_lib_name\":\"特种装置\",\"install_value\":\"\",\"is_base_lib\":\"1\"}]', '2018-11-05 14:00:39', '2018-11-05 14:02:47', '30');
INSERT INTO `install_record` VALUES ('96', '2018110615453700000', null, null, '1806054', null, '0', null, null, null, '2018-11-06 15:45:38', null, null);

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
INSERT INTO `issue_position_list` VALUES ('1', '机头');
INSERT INTO `issue_position_list` VALUES ('2', '针杆架');
INSERT INTO `issue_position_list` VALUES ('3', '台板');

-- ----------------------------
-- Table structure for `knowledge_lib`
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_lib`;
CREATE TABLE `knowledge_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `html` mediumblob NOT NULL,
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
  `order_num` varchar(255) DEFAULT NULL COMMENT '对应的订单号,来自sinsim_db.machine_order.order_num  老机器报修时可能空',
  `contract_num` varchar(255) DEFAULT NULL COMMENT '对应的合同号，可能用不到，允许空',
  `geo_location` varchar(255) DEFAULT NULL COMMENT '机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示',
  `address` varchar(255) DEFAULT '' COMMENT '机器地址，厂家门牌号',
  `status` varchar(255) DEFAULT '1' COMMENT '机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核',
  `nameplate_picture` varchar(255) DEFAULT NULL COMMENT '老机器，客户拍的铭牌照片的保存地址；非老机器可以为空',
  `machine_type` varchar(255) DEFAULT NULL COMMENT '机型',
  `needle_num` varchar(255) DEFAULT NULL COMMENT '针数',
  `x_distance` varchar(255) DEFAULT NULL COMMENT 'x行程',
  `y_distance` varchar(255) DEFAULT NULL COMMENT 'y行程',
  `head_distance` varchar(255) DEFAULT NULL COMMENT '头距',
  `head_num` varchar(255) DEFAULT NULL COMMENT '头数',
  `loadinglist` varchar(255) DEFAULT NULL COMMENT '装车单路径，共用流程管理系统的装车单，老机器允许空',
  `customer_in_contract` varchar(255) DEFAULT NULL COMMENT '合同里的客户',
  `customer` int(10) unsigned DEFAULT NULL COMMENT '机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。',
  `facory_date` date DEFAULT NULL COMMENT '出厂日期，老机器允许空',
  `is_old_machine` varchar(255) NOT NULL COMMENT ' \r\n0：表示生产部数据库出来的机器\r\n1：表示客户报的老机器（生产部新系统之前生产的机器，不在生产部数据库）\r\n2：表示售后主动加入的机器',
  `old_machine_check` varchar(255) DEFAULT NULL COMMENT '老机器审核是否通过，空表示非老机器，0:未通过，1：通过',
  PRIMARY KEY (`id`),
  KEY `contract_num` (`contract_num`),
  KEY `order_num` (`order_num`),
  KEY `nameplate` (`nameplate`),
  KEY `fk_m_customer` (`customer`),
  CONSTRAINT `fk_m_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES ('7', '1806004', 'XS-1801109B(改-20180801)', 'XS-1801109', null, '', '3', null, '高速双凸轮', '6', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '6', '2018-09-21', '0', null);
INSERT INTO `machine` VALUES ('8', '1806003', 'XS-1801109B(改-20180801)', 'XS-1801109', null, '', '1', null, '高速双凸轮', '6', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '6', '2018-09-21', '0', null);
INSERT INTO `machine` VALUES ('9', '1806030', 'XS-1802056', 'XS-1802056', null, '', '1', null, '普通平绣', '3', '600', '1520', '55', '176', null, '卓奕杰', '7', '2018-09-21', '0', null);
INSERT INTO `machine` VALUES ('10', '1806029', 'XS-1802056', 'XS-1802056', null, '', '1', null, '普通平绣', '3', '600', '1520', '55', '176', null, '卓奕杰', '7', '2018-09-21', '0', null);
INSERT INTO `machine` VALUES ('11', '1806024', 'XS-1801122', 'XS-1801122', null, '', '3', null, '单凸轮双驱动', '3', '700', '1600', '110', '132', null, '汝州市安思纺织品有限公司', '17', '2018-09-21', '0', null);
INSERT INTO `machine` VALUES ('12', '1806023', 'XS-1801121', 'XS-1801121', null, '', '1', null, '高速双凸轮', '12', '950', '950', '400', '25', null, '杭州星驰刺绣厂', '6', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('13', '1807027', '曹460D', '曹460', null, '', '5', null, '高速双凸轮+毛巾', '906+606', '混合刺绣范围850', '700+', '500', '6', null, '孟加拉JANN GROUP', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('14', '1807026', '曹460C', '曹460', null, '', '2', null, '高速双凸轮+毛巾', '915+615', '混合刺绣范围850', '700+', '500', '15', null, '孟加拉JANN GROUP', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('15', '1807025', '曹460C', '曹460', null, '', '5', null, '高速双凸轮+毛巾', '915+615', '混合刺绣范围850', '700+', '500', '15', null, '孟加拉JANN GROUP', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('16', '1806014', '曹454A(改-20180726)', '曹454', null, '', '4', null, '高速双凸轮+毛巾', '915+615', '混合刺绣范围850', '800+', '500', '15', null, 'COIN010', '12', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('17', '1807052', 'XS-1801134B', 'XS-1801134', null, '', '1', null, '高速双凸轮', '12', '1000', '1000', '300', '40', null, '杭州伟创刺绣有限公司', '6', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('18', '1807049', 'XS-1801133-1', 'XS-1801133', null, '', '1', null, '高速双凸轮', '15', '1000', '1000', '400', '30', null, '杭州连达服装绣花有限公司', '12', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('19', '1807090', '迪138B(LE126B/130/131)', 'D138', null, '', '5', null, '普通平绣', '4', '500', '1300', '125', '50', null, 'COIN001--迪', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('20', '1807089', '迪138B(LE126B/130/131)', 'D138', null, '', '1', null, '普通平绣', '4', '500', '1300', '125', '50', null, 'COIN001--迪', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('21', '1807088', '迪138B(LE126B/130/131)', 'D138', null, '', '1', null, '普通平绣', '4', '500', '1300', '125', '50', null, 'COIN001--迪', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('22', '1807087', '迪138B(LE126B/130/131)', 'D138', null, '', '1', null, '普通平绣', '4', '500', '1300', '125', '50', null, 'COIN001--迪', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('23', '1807086', '迪138B(LE126B/130/131)', 'D138', null, '', '2', null, '普通平绣', '4', '500', '1300', '125', '50', null, 'COIN001--迪', '17', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('24', '1806016', 'XS-1801119', 'XS-1801119', null, '', '5', null, '高速双凸轮', '9', '600', '700', '275', '18', null, '冯靖', '11', '2018-09-22', '0', null);
INSERT INTO `machine` VALUES ('25', '1806018', 'XS-1801114', 'XS-1801114', null, '', '3', null, '高速双凸轮', '9', '600', '800', '400', '24', null, '海城市御美健保健用品有限公司', '11', '2018-09-18', '0', null);
INSERT INTO `machine` VALUES ('26', '1806017', 'XS-1801114', 'XS-1801114', null, '', '4', null, '高速双凸轮', '9', '600', '800', '400', '24', null, '海城市御美健保健用品有限公司', '11', '2018-09-18', '0', null);
INSERT INTO `machine` VALUES ('27', '1806001', 'XS-1801109A(改-20180801)', 'XS-1801109', null, '', '1', null, '高速双凸轮', '4', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '6', '2018-09-23', '0', null);
INSERT INTO `machine` VALUES ('28', '1806002', 'XS-1801109A(改-20180801)', 'XS-1801109', null, '', '3', null, '高速双凸轮', '4', '800', '1600', '165', '88+2', null, '绍兴华文电脑刺绣有限公司', '6', '2018-09-23', '0', null);
INSERT INTO `machine` VALUES ('29', '1806022', 'XS-1801118B', 'XS-1801118', null, '', '1', null, '高速双凸轮', '9', '750', '1150', '330', '38', null, '绍兴夏叶绣品有限公司', '17', '2018-10-07', '0', null);
INSERT INTO `machine` VALUES ('30', '老机器-555', null, null, null, '', '5', null, null, null, null, null, null, null, null, null, null, null, '1', '3');
INSERT INTO `machine` VALUES ('31', '1806026', 'XS-1801122', '', null, '', '4', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('32', '1806025', 'XS-1801122', '', null, '', '3', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('33', '1806020', 'XS-1801118A', '', null, '', '2', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('34', '1806019', 'XS-1801118A', '', null, '', '3', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('35', '1806021', 'XS-1801118B', '', null, '', '2', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('36', '1806015', '曹454B', '', null, '', '2', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('37', '1806031', 'XS-1802057', '', null, '', '1', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('38', '1807032', '骆1047', '', null, '', '2', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('39', '1806038', 'XS-1801115', '', null, '', '1', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('40', '1806037', 'XS-1801115', '', null, '', '2', null, '', '', '', '', '', '', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('41', '1806027', '曹455', '', null, '', '1', null, '', '9', '450', '750+', '450', '18', null, '', '19', '2018-10-24', '0', null);
INSERT INTO `machine` VALUES ('42', '1806049', '迪134B     (LE116)', '', null, '', '2', null, '', '9', '650', '1300+', '250', '27', null, '', '19', '2018-10-25', '0', null);
INSERT INTO `machine` VALUES ('43', '1806050', '迪134B     (LE116)', '', null, '', '2', null, '', '9', '650', '1300+', '250', '27', null, '', '19', '2018-10-25', '0', null);
INSERT INTO `machine` VALUES ('44', '1807001', 'XS-1801132A', '', null, '', '2', null, '', '15', '1050', '1500', '1000', '15', null, '', '19', '2018-10-26', '0', null);
INSERT INTO `machine` VALUES ('45', '123456', null, null, null, '', '6', null, null, null, null, null, null, null, null, null, null, null, '1', '3');
INSERT INTO `machine` VALUES ('46', '123456', null, null, null, '', '6', null, null, null, null, null, null, null, null, null, null, null, '1', '3');
INSERT INTO `machine` VALUES ('47', '123456', null, null, null, '', '6', null, null, null, null, null, null, null, null, null, null, null, '1', '3');
INSERT INTO `machine` VALUES ('49', '111222', null, null, null, '', '5', null, null, null, null, null, null, null, null, null, '17', null, '1', '3');
INSERT INTO `machine` VALUES ('50', '1806028', 'XS-1802054', '', null, '', '4', null, '', '8', '700', '1540', '165', '60', null, '', '28', '2018-10-28', '0', null);
INSERT INTO `machine` VALUES ('51', '1806044', '骆1043B', '', null, '', '2', null, '', '9', '700', '1330', '330', '8', null, '', '19', '2018-10-31', '0', null);
INSERT INTO `machine` VALUES ('52', '1806034', '曹456C', '', null, '', '3', null, '', '9', '800', '1520', '250', '28', null, '', '19', '2018-11-08', '0', null);
INSERT INTO `machine` VALUES ('53', '1806033', '曹456B', '', null, '', '2', null, '', '6', '800', '1300', '180', '29', null, '', '19', '2018-11-08', '0', null);
INSERT INTO `machine` VALUES ('60', '1806067', 'XS-1801123(改-20180727)(改-20180806)', '', null, '', '2', null, '', '6', '750', '1540', '300', '50', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('61', '1806066', 'XS-1801123(改-20180727)(改-20180806)', '', null, '', '2', null, '', '6', '750', '1540', '300', '50', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('63', '1806046', '迪134A   (LE115-117A)', '', null, '', '1', null, '', '6', '650', '1300+', '250', '27', null, '', '17', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('64', '1806032', '曹456A', '', null, '', '2', null, '', '6', '800', '1300', '250', '25', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('69', '1806041', '骆1033B', '', null, '', '2', null, '', '0', '600', '1350', '450', '24', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('71', '1806036', 'XS-1801111改', '', null, '', '2', null, '', '6', '700', '1000', '165', '11', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('78', '1806035', 'XS-1801047', '', null, '', '1', null, '', '6', '650', '900', '600', '2', null, '', '12', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('82', '1806068', 'XS-1801116', '', null, '', '1', null, '', '4', '700', '1050', '168', '90', null, '', '6', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('83', '1806056', '迪133C     (LE109-110)', '', null, '', '1', null, '', '4', '500', '1300', '125', '50', null, '', '6', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('87', '1807020', '迪135A       （LE118/119)', '', null, '', '2', null, '', '6', '650', '1300+', '250', '25', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('90', '1807107', 'XS-1801140', '', null, '', '2', null, '', '9', '700', '900', '500', '4', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('91', '1806039', '骆1033A', '', null, '', '1', null, '', '9', '600', '1350', '450', '24', null, '', '12', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('92', '1806069', 'XS-1801116', '', null, '', '2', null, '', '4', '700', '1050', '168', '90', null, '', '19', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('93', '1806057', '迪133C     (LE109-110)', '', null, '', '2', null, '', '4', '500', '1300', '125', '50', null, '', '11', '2018-11-01', '0', null);
INSERT INTO `machine` VALUES ('94', '1806040', '骆1033B', '', null, '', '2', null, '', '0', '600', '1350', '450', '24', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('95', '1806042', 'XS-1801120', '', null, '', '5', null, '', '9', '750', '1050', '300', '30', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('96', '1806043', '骆1043A', '', null, '', '2', null, '', '9', '700', '1300', '330', '8', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('97', '1806045', '骆1045', '', null, '', '2', null, '', '9', '850', '1330', '400', '24', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('98', '1806059', '迪133C     (LE109-110)', '', null, '', '2', null, '', '4', '500', '1300', '125', '50', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('99', '1806058', '迪133C     (LE109-110)', '', null, '', '2', null, '', '4', '500', '1300', '125', '50', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('100', '1807093', 'XS-1801138', '', null, '', '3', null, '', '6', '750', '1600', '300', '50', null, '', '24', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('101', '1806062', '迪133E    (LE112)', '', null, '', '3', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('102', '1806063', '迪133E    (LE112)', '', null, '', '5', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('103', '1806051', '迪134C    (LE177B)', '', null, '', '2', null, '', '6', '650', '1300+', '250', '25', null, '', '19', '2018-11-02', '0', null);
INSERT INTO `machine` VALUES ('104', '1806047', '迪134A   (LE115-117A)', '', null, '', '2', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-11-29', '0', null);
INSERT INTO `machine` VALUES ('105', '1806048', '迪134A   (LE115-117A)', '', null, '', '1', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-11-05', '0', null);
INSERT INTO `machine` VALUES ('106', '1806060', '迪133D    (LE111)', '', null, '', '1', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-11-05', '0', null);
INSERT INTO `machine` VALUES ('107', '1806061', '迪133D    (LE111)', '', null, '', '2', null, '', '6', '650', '1300+', '250', '27', null, '', '19', '2018-12-05', '0', null);
INSERT INTO `machine` VALUES ('108', '1806054', '迪133B      (LE108)', '', null, '', '1', null, '', '6', '650', '1300+', '250', '24', null, '', '11', '2018-11-06', '0', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_abnormal_record
-- ----------------------------
INSERT INTO `maintain_abnormal_record` VALUES ('1', '1', '用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。', '111', '1111');

-- ----------------------------
-- Table structure for `maintain_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_customer_feedback`;
CREATE TABLE `maintain_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_customer_feedback
-- ----------------------------
INSERT INTO `maintain_customer_feedback` VALUES ('1', '0', '那就');
INSERT INTO `maintain_customer_feedback` VALUES ('2', '0', '差评');
INSERT INTO `maintain_customer_feedback` VALUES ('3', '0', '一期OK');
INSERT INTO `maintain_customer_feedback` VALUES ('4', '0', '嘎嘎嘎嘎过');
INSERT INTO `maintain_customer_feedback` VALUES ('5', '0', '保养完成，很满意服务');
INSERT INTO `maintain_customer_feedback` VALUES ('6', '0', '');
INSERT INTO `maintain_customer_feedback` VALUES ('7', '0', '');
INSERT INTO `maintain_customer_feedback` VALUES ('8', '0', '很好很好');
INSERT INTO `maintain_customer_feedback` VALUES ('9', '0', '师傅很尽职，各个部位均检查完成，服务非常满意');

-- ----------------------------
-- Table structure for `maintain_lib`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_lib`;
CREATE TABLE `maintain_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maintain_lib_name` varchar(255) DEFAULT NULL COMMENT '保养库的名字， 一期，二期，三期等',
  `maintain_type` int(10) unsigned NOT NULL COMMENT '1: 清理清洁， 2：注油润滑， 3： 检查修理',
  `maintain_content` text COMMENT '保养内容',
  PRIMARY KEY (`id`),
  KEY `fk_mi_maintain_lib` (`maintain_lib_name`) USING BTREE,
  KEY `fk_mi_maintain_type` (`maintain_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_lib
-- ----------------------------
INSERT INTO `maintain_lib` VALUES ('4', '保养一期', '0', null);
INSERT INTO `maintain_lib` VALUES ('5', '保养一期', '1', '用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。');
INSERT INTO `maintain_lib` VALUES ('6', '保养一期', '2', '给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。');
INSERT INTO `maintain_lib` VALUES ('7', '保养一期', '3', '1.检查中间脚盘是否正常');
INSERT INTO `maintain_lib` VALUES ('8', '保养一期', '3', '2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；');
INSERT INTO `maintain_lib` VALUES ('9', '保养一期', '3', '3. 检查最低点角度（172°/178°高速±2°）是否正确；');
INSERT INTO `maintain_lib` VALUES ('10', '保养一期', '3', '4. 检查主轴转动应活络、运行平稳、停车位置准确；');
INSERT INTO `maintain_lib` VALUES ('11', '保养一期', '3', '5. 检查大连杆是否润滑良好，大连杆是否有磨损；');
INSERT INTO `maintain_lib` VALUES ('12', '保养一期', '3', '6. 检查100度时跳线杆高度一致，换色轻松准确；');
INSERT INTO `maintain_lib` VALUES ('13', '保养一期', '3', '7. 检查针杆架间隙，是否有松动；');
INSERT INTO `maintain_lib` VALUES ('14', '保养一期', '3', '8. 检查针杆压下时针杆的上下间隙是否正常；');
INSERT INTO `maintain_lib` VALUES ('15', '保养一期', '3', '9. 检查针下位时与针板孔的左右位置是否居中');
INSERT INTO `maintain_lib` VALUES ('16', '保养一期', '3', '10. 检查针板孔是否有毛刺；');
INSERT INTO `maintain_lib` VALUES ('17', '保养一期', '3', '11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确');
INSERT INTO `maintain_lib` VALUES ('18', '保养一期', '3', '12. 检查旋梭轴轴向窜动无明显间隙');
INSERT INTO `maintain_lib` VALUES ('19', '保养一期', '3', '13. 检查伞齿轮间隙范围0.1～0.15mm');
INSERT INTO `maintain_lib` VALUES ('20', '保养一期', '3', '14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('21', '保养一期', '3', '15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('22', '保养一期', '3', '16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('23', '保养一期', '3', '17. 检查金片装置调整位置是否准确，工作是否正常');
INSERT INTO `maintain_lib` VALUES ('24', '保养一期', '3', '18. 检查X、Y同步带张力');
INSERT INTO `maintain_lib` VALUES ('25', '保养一期', '3', '19. 检查X、Y伺服电机皮带张力');
INSERT INTO `maintain_lib` VALUES ('26', '保养一期', '3', '20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损');
INSERT INTO `maintain_lib` VALUES ('27', '保养一期', '3', '21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）');
INSERT INTO `maintain_lib` VALUES ('28', '保养一期', '3', '22. 检查台板、防尘板是否平整');
INSERT INTO `maintain_lib` VALUES ('29', '保养一期', '3', '23. 检查移框时无卡顿、无杂音；');
INSERT INTO `maintain_lib` VALUES ('30', '保养二期', '0', null);
INSERT INTO `maintain_lib` VALUES ('31', '保养二期', '1', '用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；');
INSERT INTO `maintain_lib` VALUES ('32', '保养二期', '2', '给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；');
INSERT INTO `maintain_lib` VALUES ('33', '保养二期', '3', '1.检查中间脚盘是否正常');
INSERT INTO `maintain_lib` VALUES ('34', '保养二期', '3', '2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；');
INSERT INTO `maintain_lib` VALUES ('35', '保养二期', '3', '3. 检查最低点角度（172°/178°高速±2°）是否正确；');
INSERT INTO `maintain_lib` VALUES ('36', '保养二期', '3', '4. 检查主轴转动应活络、运行平稳、停车位置准确；');
INSERT INTO `maintain_lib` VALUES ('37', '保养二期', '3', '5. 检查大连杆是否润滑良好，大连杆是否有磨损');
INSERT INTO `maintain_lib` VALUES ('38', '保养二期', '3', '6. 检查100度时跳线杆高度一致，换色轻松准确；');
INSERT INTO `maintain_lib` VALUES ('39', '保养二期', '3', '7. 检查针杆架间隙，是否有松动；');
INSERT INTO `maintain_lib` VALUES ('40', '保养二期', '3', '8. 检查针杆压下时针杆的上下间隙是否正常；');
INSERT INTO `maintain_lib` VALUES ('41', '保养二期', '3', '9. 检查针下位时与针板孔的左右位置是否居中');
INSERT INTO `maintain_lib` VALUES ('42', '保养二期', '3', '10. 检查针板孔是否有毛刺；');
INSERT INTO `maintain_lib` VALUES ('43', '保养二期', '3', '11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确');
INSERT INTO `maintain_lib` VALUES ('44', '保养二期', '3', '12. 检查旋梭轴轴向窜动无明显间隙');
INSERT INTO `maintain_lib` VALUES ('45', '保养二期', '3', '13. 检查伞齿轮间隙范围0.1～0.15mm');
INSERT INTO `maintain_lib` VALUES ('46', '保养二期', '3', '14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。');
INSERT INTO `maintain_lib` VALUES ('47', '保养二期', '3', '15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('48', '保养二期', '3', '16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('49', '保养二期', '3', '17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('50', '保养二期', '3', '18. 检查金片装置调整位置是否准确，工作是否正常');
INSERT INTO `maintain_lib` VALUES ('51', '保养二期', '3', '19. 检查X、Y同步带张力');
INSERT INTO `maintain_lib` VALUES ('52', '保养二期', '3', '20. 检查X、Y伺服电机皮带张力');
INSERT INTO `maintain_lib` VALUES ('53', '保养二期', '3', '21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损');
INSERT INTO `maintain_lib` VALUES ('54', '保养二期', '3', '22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）');
INSERT INTO `maintain_lib` VALUES ('55', '保养二期', '3', '23. 检查台板、防尘板是否平整');
INSERT INTO `maintain_lib` VALUES ('56', '保养二期', '3', '24. 检查移框时无卡顿、无杂音；');
INSERT INTO `maintain_lib` VALUES ('57', '保养三期', '0', null);
INSERT INTO `maintain_lib` VALUES ('58', '保养三期', '1', '用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。');
INSERT INTO `maintain_lib` VALUES ('59', '保养三期', '2', '给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、大连杆、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂。');
INSERT INTO `maintain_lib` VALUES ('60', '保养三期', '3', '1.检查中间脚盘是否正常');
INSERT INTO `maintain_lib` VALUES ('61', '保养三期', '3', '2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；');
INSERT INTO `maintain_lib` VALUES ('62', '保养三期', '3', '3. 检查最低点角度（172°/178°高速±2°）是否正确；');
INSERT INTO `maintain_lib` VALUES ('63', '保养三期', '3', '4. 检查主轴转动应活络、运行平稳、停车位置准确；');
INSERT INTO `maintain_lib` VALUES ('64', '保养三期', '3', '5. 检查大连杆是否润滑良好，大连杆是否有磨损；');
INSERT INTO `maintain_lib` VALUES ('65', '保养三期', '3', '6. 检查100度时跳线杆高度一致，换色轻松准确；');
INSERT INTO `maintain_lib` VALUES ('66', '保养三期', '3', '7. 检查针杆架间隙，是否有松动；');
INSERT INTO `maintain_lib` VALUES ('67', '保养三期', '3', '8. 检查针杆压下时针杆的上下间隙是否正常；');
INSERT INTO `maintain_lib` VALUES ('68', '保养三期', '3', '9. 检查针下位时与针板孔的左右位置是否居中');
INSERT INTO `maintain_lib` VALUES ('69', '保养三期', '3', '10. 检查针板孔是否有毛刺；');
INSERT INTO `maintain_lib` VALUES ('70', '保养三期', '3', '11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确');
INSERT INTO `maintain_lib` VALUES ('71', '保养三期', '3', '12. 检查旋梭轴轴向窜动无明显间隙');
INSERT INTO `maintain_lib` VALUES ('72', '保养三期', '3', '13. 检查伞齿轮间隙范围0.1～0.15mm');
INSERT INTO `maintain_lib` VALUES ('73', '保养三期', '3', '14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。');
INSERT INTO `maintain_lib` VALUES ('74', '保养三期', '3', '15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('75', '保养三期', '3', '16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('76', '保养三期', '3', '17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；');
INSERT INTO `maintain_lib` VALUES ('77', '保养三期', '3', '18. 检查金片装置调整位置是否准确，工作是否正常');
INSERT INTO `maintain_lib` VALUES ('78', '保养三期', '3', '19. 检查X、Y同步带张力');
INSERT INTO `maintain_lib` VALUES ('79', '保养三期', '3', '20. 检查X、Y伺服电机皮带张力');
INSERT INTO `maintain_lib` VALUES ('80', '保养三期', '3', '21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损');
INSERT INTO `maintain_lib` VALUES ('81', '保养三期', '3', '22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）');
INSERT INTO `maintain_lib` VALUES ('82', '保养三期', '3', '23. 检查台板、防尘板是否平整');
INSERT INTO `maintain_lib` VALUES ('83', '保养三期', '3', '24. 检查移框时无卡顿、无杂音；');

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_members
-- ----------------------------
INSERT INTO `maintain_members` VALUES ('1', '1', '3');
INSERT INTO `maintain_members` VALUES ('2', '1', '8');
INSERT INTO `maintain_members` VALUES ('3', '2', '8');
INSERT INTO `maintain_members` VALUES ('4', '2', '10');
INSERT INTO `maintain_members` VALUES ('5', '2', '13');
INSERT INTO `maintain_members` VALUES ('6', '2', '3');
INSERT INTO `maintain_members` VALUES ('7', '3', '18');
INSERT INTO `maintain_members` VALUES ('8', '5', '3');
INSERT INTO `maintain_members` VALUES ('9', '5', '13');
INSERT INTO `maintain_members` VALUES ('10', '6', '3');
INSERT INTO `maintain_members` VALUES ('11', '7', '3');
INSERT INTO `maintain_members` VALUES ('12', '8', '3');
INSERT INTO `maintain_members` VALUES ('13', '9', '8');
INSERT INTO `maintain_members` VALUES ('14', '4', '8');
INSERT INTO `maintain_members` VALUES ('15', '10', '8');
INSERT INTO `maintain_members` VALUES ('16', '11', '21');
INSERT INTO `maintain_members` VALUES ('17', '14', '22');
INSERT INTO `maintain_members` VALUES ('18', '14', '23');
INSERT INTO `maintain_members` VALUES ('19', '17', '23');
INSERT INTO `maintain_members` VALUES ('20', '18', '8');
INSERT INTO `maintain_members` VALUES ('21', '18', '10');
INSERT INTO `maintain_members` VALUES ('22', '22', '3');
INSERT INTO `maintain_members` VALUES ('23', '22', '8');
INSERT INTO `maintain_members` VALUES ('28', '23', '8');
INSERT INTO `maintain_members` VALUES ('29', '23', '10');
INSERT INTO `maintain_members` VALUES ('30', '23', '13');
INSERT INTO `maintain_members` VALUES ('31', '23', '22');
INSERT INTO `maintain_members` VALUES ('32', '24', '21');
INSERT INTO `maintain_members` VALUES ('33', '25', '21');
INSERT INTO `maintain_members` VALUES ('34', '9', '21');
INSERT INTO `maintain_members` VALUES ('35', '10', '21');

-- ----------------------------
-- Table structure for `maintain_record`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_record`;
CREATE TABLE `maintain_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `machine_nameplate` varchar(255) NOT NULL COMMENT '机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号',
  `maintain_lib_name` varchar(255) DEFAULT NULL COMMENT '1：一期，2：二期，3：三期保养',
  `maintain_date_plan` date DEFAULT NULL,
  `maintain_date_actual` date DEFAULT NULL COMMENT '实际保养日期',
  `maintain_charge_person` int(10) unsigned DEFAULT NULL COMMENT '保养人员',
  `maintain_suggestion` varchar(255) DEFAULT NULL COMMENT '保养建议',
  `customer` int(10) unsigned DEFAULT NULL COMMENT '客户',
  `maintain_info` text COMMENT '保养json 举例:\r\n \r\n[\r\n  {\r\n   "contentList": [\r\n     {\r\n       "checkValue": 0,\r\n       "content": "dsfdsfdsfdsfdsafasdfdasfasd"\r\n     }\r\n   ],\r\n   "maintainType": 1\r\n  },\r\n  {\r\n   "contentList": [\r\n     {\r\n       "checkValue": 0,\r\n       "content": "sdfsdfsdfasd"\r\n     },\r\n     {\r\n       "checkValue": 0,\r\n       "content": "无可奈何"\r\n     }\r\n   ],\r\n   "maintainType": 2\r\n  }\r\n]',
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_record
-- ----------------------------
INSERT INTO `maintain_record` VALUES ('1', '1806004', '保养一期', '2018-09-22', null, '8', '12313', '0', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-09-21 00:00:00', '2018-10-19 14:33:03', '3', '1');
INSERT INTO `maintain_record` VALUES ('2', '1806003', '保养二期', '2018-09-29', null, '8', '客户要定时清理相关重要部件', '0', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-09-22 00:00:00', '2018-09-22 14:50:34', '3', '0');
INSERT INTO `maintain_record` VALUES ('3', '1806016', '保养一期', '2018-10-01', null, '18', null, '11', null, '2018-09-29 00:00:00', '2018-09-30 17:13:20', '1', null);
INSERT INTO `maintain_record` VALUES ('4', '1806018', '保养二期', '2018-10-24', null, '8', null, '9', null, '2018-09-29 00:00:00', '2018-10-24 11:02:18', '1', null);
INSERT INTO `maintain_record` VALUES ('5', '1806018', '保养一期', '2018-10-19', null, '3', '家基友', '11', '[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"},{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"},{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}]', '2018-10-01 00:00:00', '2018-10-19 14:58:02', '3', '2');
INSERT INTO `maintain_record` VALUES ('6', '1807087', '保养一期', '2018-10-19', null, '3', null, '9', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-19 14:57:31', '2018-10-19 14:59:38', '2', null);
INSERT INTO `maintain_record` VALUES ('7', '1806001', '保养二期', '2018-10-19', null, '3', null, '6', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-19 15:01:07', '2018-10-19 15:01:27', '1', null);
INSERT INTO `maintain_record` VALUES ('8', '1807052', '保养一期', '2018-10-19', null, '3', null, '6', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-19 15:23:31', '2018-10-19 15:24:08', '2', null);
INSERT INTO `maintain_record` VALUES ('9', '1806026', '保养一期', '2018-11-06', '2018-11-06', '21', '拜拜拜拜布', '19', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-24 10:58:51', '2018-11-06 14:51:55', '2', '4');
INSERT INTO `maintain_record` VALUES ('10', '1806014', '保养一期', '2018-11-06', '2018-11-06', '21', '呵呵呵呵', '12', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-24 11:05:44', '2018-11-06 14:52:20', '2', '3');
INSERT INTO `maintain_record` VALUES ('11', '1806014', '保养二期', '2018-10-24', '2018-12-04', '21', null, '12', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-24 11:09:35', '2018-10-24 11:10:50', '2', null);
INSERT INTO `maintain_record` VALUES ('12', '1806003', '保养一期', null, null, null, null, null, '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-24 11:19:06', null, '0', null);
INSERT INTO `maintain_record` VALUES ('13', '1806030', '保养一期', null, null, null, null, null, '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-24 11:19:06', null, '0', null);
INSERT INTO `maintain_record` VALUES ('14', '1806028', '保养一期', '2018-10-30', '2018-10-28', '23', '后期机器配件定时清洗', '28', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-28 11:12:04', '2018-10-28 11:36:22', '3', '5');
INSERT INTO `maintain_record` VALUES ('15', '1806028', '保养一期', null, null, null, null, null, '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-10-28 11:31:50', null, '0', null);
INSERT INTO `maintain_record` VALUES ('16', '1807093', null, null, null, null, null, null, null, '2018-11-02 11:16:07', null, '0', null);
INSERT INTO `maintain_record` VALUES ('17', '1807093', '保养一期', '2018-11-30', '2018-11-02', '23', '需经常添加润滑油', '24', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-02 11:17:36', '2018-11-06 10:22:24', '4', '9');
INSERT INTO `maintain_record` VALUES ('18', '1806034', '保养一期', '2018-11-02', '2018-11-02', '8', '哈哈啦啦啦', '19', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-02 14:43:44', '2018-11-02 16:36:37', '4', '6');
INSERT INTO `maintain_record` VALUES ('19', '1806033', '保养二期', null, null, null, null, null, '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-02 14:43:44', null, '0', null);
INSERT INTO `maintain_record` VALUES ('20', '1806066', null, null, null, null, null, null, null, '2018-11-02 14:43:44', null, '0', null);
INSERT INTO `maintain_record` VALUES ('21', '1806032', null, null, null, null, null, null, null, '2018-11-02 14:43:44', null, '0', null);
INSERT INTO `maintain_record` VALUES ('22', '1806062', '保养三期', '2018-11-02', null, '3', null, '19', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、大连杆、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-02 16:32:11', '2018-11-02 16:32:43', '1', null);
INSERT INTO `maintain_record` VALUES ('23', '1806034', '保养三期', '2018-11-02', '2018-11-02', '8', '阿库', '19', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、大连杆、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":\"1\",\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-02 16:32:11', '2018-11-02 16:39:22', '4', '7');
INSERT INTO `maintain_record` VALUES ('24', '1806017', '保养一期', '2018-11-05', '2018-11-05', '21', '保养好嘎嘎嘎嘎过过', '11', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘。\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损；\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"15. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"18. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"21. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"22. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"23. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-05 11:26:54', '2018-11-05 11:31:00', '4', '8');
INSERT INTO `maintain_record` VALUES ('25', '1806017', '保养二期', '2018-11-05', '2018-11-05', '21', '斤斤计较', '9', '[{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":\"1\",\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":\"1\",\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":\"1\",\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":\"1\",\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":\"1\",\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":\"1\",\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":\"1\",\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":\"1\",\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":\"1\",\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":\"1\",\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":\"1\",\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":\"1\",\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":\"1\",\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":\"1\",\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":\"1\",\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":\"1\",\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":\"1\",\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":\"1\",\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":\"1\",\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":\"1\",\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-11-05 11:31:34', '2018-11-05 11:32:38', '3', '0');

-- ----------------------------
-- Table structure for `maintain_type`
-- ----------------------------
DROP TABLE IF EXISTS `maintain_type`;
CREATE TABLE `maintain_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `maintain_type_name` varchar(255) NOT NULL COMMENT '保养类型，比如清洁清理/注油润滑/检查修理',
  PRIMARY KEY (`id`),
  KEY `type_name` (`maintain_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_type
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parts_info
-- ----------------------------
INSERT INTO `parts_info` VALUES ('9', '配件aaa', null, null, '10', '1', null, null, null, null, '1');
INSERT INTO `parts_info` VALUES ('10', '阿库娅', null, null, '11', '1', null, null, null, null, '1');
INSERT INTO `parts_info` VALUES ('11', '卡通', null, null, '11', '2', null, null, null, null, '1');
INSERT INTO `parts_info` VALUES ('12', '皮带', '', '', '12', '3', '22222222222', '/home/sinsim/output/aftersale/parts_info_img/1806028_FILE_TYPE_PARTS_SENDBACK_IMAGE_2018-10-28-11-08-04_0.jpg', null, null, '1');
INSERT INTO `parts_info` VALUES ('13', '继续', null, null, '13', '2', null, null, null, null, '1');
INSERT INTO `parts_info` VALUES ('14', '德', null, null, '14', '2', null, null, null, null, '1');
INSERT INTO `parts_info` VALUES ('15', '配件zzz', null, null, '16', '4', null, null, null, '2018-11-05 11:50:47', '1');
INSERT INTO `parts_info` VALUES ('16', '配件ddd', '', '', '16', '4', 'hh222', '/home/sinsim/output/aftersale/parts_info_img/1806051_FILE_TYPE_PARTS_SENDBACK_IMAGE_2018-11-05-11-51-18_0.jpg', null, '2018-11-05 11:51:39', '1');
INSERT INTO `parts_info` VALUES ('17', '皮带', null, null, '18', '2', null, null, null, null, '1');

-- ----------------------------
-- Table structure for `repair_actual_info`
-- ----------------------------
DROP TABLE IF EXISTS `repair_actual_info`;
CREATE TABLE `repair_actual_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `repair_record_id` int(10) unsigned NOT NULL COMMENT '对应的维修记录，一次报修可以有多个实际维修',
  `issue_position` varchar(255) NOT NULL COMMENT '维修部位ID号，多个部位时用逗号隔开',
  `issue_description` text NOT NULL COMMENT '实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”',
  `repair_method` text NOT NULL COMMENT '实际维修中的“处理方法”',
  `after_resolve_pictures` varchar(1000) DEFAULT NULL COMMENT '解决后的照片（保存文件路径）',
  `upload_files_amount` varchar(255) NOT NULL DEFAULT '0' COMMENT '要上传的文件总数，add时通过参数指明',
  `already_uploaded_files_number` varchar(255) NOT NULL DEFAULT '0' COMMENT '已上传成功的文件的数量',
  PRIMARY KEY (`id`),
  KEY `fk_rai_repair_record_id` (`repair_record_id`),
  KEY `fk_rai_issue_position` (`issue_position`),
  CONSTRAINT `fk_rai_repair_record_id` FOREIGN KEY (`repair_record_id`) REFERENCES `repair_record` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_actual_info
-- ----------------------------
INSERT INTO `repair_actual_info` VALUES ('10', '14', '1', '问题在于。。。。', '换机头', '/home/sinsim/output/aftersale/repair_actual_info_img/ 1807026_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-19-10-26-37_1.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/ 1807026_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-19-10-26-40_2.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/ 1807026_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-19-10-26-43_3.jpg', '3', '3');
INSERT INTO `repair_actual_info` VALUES ('11', '16', '1,2,3', '拉篮', '晒晒', '/home/sinsim/output/aftersale/repair_actual_info_img/1806018_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-26-16-00-28_1.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806018_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-26-16-00-28_2.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806018_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-26-16-00-28_3.jpg', '3', '3');
INSERT INTO `repair_actual_info` VALUES ('12', '24', '1,2', '皮带断裂引起异响', '更换皮带', '/home/sinsim/output/aftersale/repair_actual_info_img/1806028_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-10-28-11-01-48_1.jpg', '1', '1');
INSERT INTO `repair_actual_info` VALUES ('13', '32', '1,2', '来了吗', '刻意', '/home/sinsim/output/aftersale/repair_actual_info_img/1806019_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-16-58-26_1.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806019_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-16-58-27_2.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806019_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-16-58-27_3.jpg', '3', '3');
INSERT INTO `repair_actual_info` VALUES ('14', '31', '1,2', '拒绝', '罗', '/home/sinsim/output/aftersale/repair_actual_info_img/1806062_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-17-21-59_1.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806062_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-17-22-00_2.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1806062_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-17-22-00_3.jpg', '3', '3');
INSERT INTO `repair_actual_info` VALUES ('15', '33', '1,2', '阿狸，', '裸照', '/home/sinsim/output/aftersale/repair_actual_info_img/1806025_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-02-17-29-52_1.jpg', '1', '1');
INSERT INTO `repair_actual_info` VALUES ('16', '40', '3', '密密麻麻', '处理方法学远了可能的', '/home/sinsim/output/aftersale/repair_actual_info_img/1806051_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-05-11-48-54_1.jpg', '1', '1');
INSERT INTO `repair_actual_info` VALUES ('17', '41', '2', '买买买', '哈哈哈哈哈哈哈哈哈哈哈', '/home/sinsim/output/aftersale/repair_actual_info_img/1806051_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-05-11-58-51_1.jpg', '1', '1');
INSERT INTO `repair_actual_info` VALUES ('18', '43', '1', '皮带断裂导致机器不能正常运转', '更换皮带', '/home/sinsim/output/aftersale/repair_actual_info_img/1807093_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-06-13-26-10_1.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1807093_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-06-13-26-10_2.jpg,/home/sinsim/output/aftersale/repair_actual_info_img/1807093_FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE_2018-11-06-13-26-11_3.jpg', '3', '3');

-- ----------------------------
-- Table structure for `repair_customer_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `repair_customer_feedback`;
CREATE TABLE `repair_customer_feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_mark` varchar(255) NOT NULL COMMENT '客户给的评分',
  `customer_suggestion` varchar(255) NOT NULL COMMENT '客户意见',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，也即用户完成评价的时间，维修最后完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_customer_feedback
-- ----------------------------
INSERT INTO `repair_customer_feedback` VALUES ('1', '0', '满意', null);
INSERT INTO `repair_customer_feedback` VALUES ('2', '0', '机器已正常，维修非常满意', '2018-10-28 11:07:31');
INSERT INTO `repair_customer_feedback` VALUES ('3', '0', '好好好', '2018-11-05 11:18:49');
INSERT INTO `repair_customer_feedback` VALUES ('4', '0', '咯咯咯', '2018-11-05 11:50:07');
INSERT INTO `repair_customer_feedback` VALUES ('5', '0', '红红火火', '2018-11-05 11:59:41');
INSERT INTO `repair_customer_feedback` VALUES ('6', '0', '机器已正常运行，目前没有问题出现', '2018-11-06 16:02:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_members
-- ----------------------------
INSERT INTO `repair_members` VALUES ('5', '13', '8');
INSERT INTO `repair_members` VALUES ('6', '14', '3');
INSERT INTO `repair_members` VALUES ('7', '16', '8');
INSERT INTO `repair_members` VALUES ('8', '24', '22');
INSERT INTO `repair_members` VALUES ('9', '25', '8');
INSERT INTO `repair_members` VALUES ('10', '26', '8');
INSERT INTO `repair_members` VALUES ('11', '26', '3');
INSERT INTO `repair_members` VALUES ('12', '17', '10');
INSERT INTO `repair_members` VALUES ('13', '17', '3');
INSERT INTO `repair_members` VALUES ('14', '17', '8');
INSERT INTO `repair_members` VALUES ('15', '32', '8');
INSERT INTO `repair_members` VALUES ('16', '32', '10');
INSERT INTO `repair_members` VALUES ('17', '31', '8');
INSERT INTO `repair_members` VALUES ('18', '33', '8');
INSERT INTO `repair_members` VALUES ('19', '33', '10');
INSERT INTO `repair_members` VALUES ('20', '36', '32');
INSERT INTO `repair_members` VALUES ('21', '17', '21');
INSERT INTO `repair_members` VALUES ('22', '26', '21');
INSERT INTO `repair_members` VALUES ('23', '40', '21');
INSERT INTO `repair_members` VALUES ('24', '40', '13');
INSERT INTO `repair_members` VALUES ('25', '41', '21');
INSERT INTO `repair_members` VALUES ('26', '41', '26');
INSERT INTO `repair_members` VALUES ('27', '42', '21');
INSERT INTO `repair_members` VALUES ('28', '43', '23');

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
  `forward_info` int(10) unsigned DEFAULT NULL COMMENT '转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。\r\n==> 目前forward_info这个表未使用，只是在数据库记录了是否转派， repair_record到forward_info的外键现在也删除。',
  PRIMARY KEY (`id`),
  KEY `fk_rr_machine_nameplate` (`machine_nameplate`),
  KEY `fk_rr_contacter` (`customer`),
  KEY `fk_rr_forward_info` (`forward_info`),
  KEY `fk_rr_repair_charge_person` (`repair_charge_person`),
  KEY `fk_rr_customer_feedback` (`customer_feedback`),
  KEY `fk_rr_repair_request_info` (`repair_request_info`),
  CONSTRAINT `fk_rr_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_rr_customer_feedback` FOREIGN KEY (`customer_feedback`) REFERENCES `repair_customer_feedback` (`id`),
  CONSTRAINT `fk_rr_machine_nameplate` FOREIGN KEY (`machine_nameplate`) REFERENCES `machine` (`nameplate`),
  CONSTRAINT `fk_rr_repair_charge_person` FOREIGN KEY (`repair_charge_person`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_rr_repair_request_info` FOREIGN KEY (`repair_request_info`) REFERENCES `repair_request_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of repair_record
-- ----------------------------
INSERT INTO `repair_record` VALUES ('11', null, '9', '1806024', '11', null, null, null, null, null, null, '0', '2018-10-18 11:06:41', null, null);
INSERT INTO `repair_record` VALUES ('13', null, '17', '1807027', '13', '0', '2018-10-18', '8', null, null, null, '3', '2018-10-18 11:45:10', '2018-10-18 14:43:10', null);
INSERT INTO `repair_record` VALUES ('14', null, '17', '1807026', '14', '0', '2018-10-19', '3', null, '2018-10-19 10:26:43', '1', '8', '2018-10-19 09:54:53', '2018-10-19 10:35:18', null);
INSERT INTO `repair_record` VALUES ('15', null, '19', '老机器-555', '16', null, null, null, null, null, null, '1', '2018-10-23 16:45:20', null, null);
INSERT INTO `repair_record` VALUES ('16', null, '11', '1806018', '17', '0', '2018-10-23', '8', null, '2018-10-26 16:00:29', '3', '8', '2018-10-23 16:58:11', '2018-11-05 11:18:49', null);
INSERT INTO `repair_record` VALUES ('17', null, '19', '1806026', '18', '0', '2018-11-29', '10', null, null, null, '5', '2018-10-24 17:39:00', '2018-11-05 10:49:10', null);
INSERT INTO `repair_record` VALUES ('18', null, '17', '1807025', '19', null, null, null, null, null, null, '1', '2018-10-26 11:26:19', null, null);
INSERT INTO `repair_record` VALUES ('21', null, '17', '123456', '22', null, null, null, null, null, null, '0', '2018-10-26 11:35:00', null, null);
INSERT INTO `repair_record` VALUES ('23', null, '17', '111222', '24', null, null, null, null, null, null, '1', '2018-10-26 15:14:23', null, null);
INSERT INTO `repair_record` VALUES ('24', null, '28', '1806028', '25', '0', '2018-10-29', '22', null, '2018-10-28 11:01:49', '2', '8', '2018-10-28 10:57:40', '2018-10-28 11:07:31', null);
INSERT INTO `repair_record` VALUES ('25', null, '19', '1806042', '26', '1', '2018-11-02', '8', null, null, null, '3', '2018-11-02 14:52:55', '2018-11-02 14:55:28', null);
INSERT INTO `repair_record` VALUES ('26', null, '19', '1806063', '27', '1', '2018-12-06', '8', null, null, null, '5', '2018-11-02 16:41:12', '2018-11-05 10:50:57', null);
INSERT INTO `repair_record` VALUES ('31', null, '19', '1806062', '28', '0', '2018-11-02', '8', null, '2018-11-02 17:22:00', null, '7', '2018-11-02 16:52:16', '2018-11-02 17:21:01', null);
INSERT INTO `repair_record` VALUES ('32', null, '19', '1806019', '29', '1', '2018-11-02', '8', null, '2018-11-02 16:58:27', null, '7', '2018-11-02 16:55:02', '2018-11-02 16:55:30', null);
INSERT INTO `repair_record` VALUES ('33', null, '19', '1806025', '30', '0', '2018-11-02', '8', null, '2018-11-02 17:29:53', null, '7', '2018-11-02 17:28:13', '2018-11-02 17:28:47', null);
INSERT INTO `repair_record` VALUES ('36', null, '11', '1806016', '31', '0', '2018-11-05', '32', null, null, null, '2', '2018-11-05 10:10:02', '2018-11-05 10:17:24', null);
INSERT INTO `repair_record` VALUES ('38', '2018110510490900003', '19', '1806026', '18', '0', '2018-11-05', '21', null, null, null, '3', '2018-11-05 10:49:10', null, '0');
INSERT INTO `repair_record` VALUES ('39', '2018110510505700004', '19', '1806063', '27', '1', '2018-11-05', '21', null, null, null, '3', '2018-11-05 10:50:57', null, '0');
INSERT INTO `repair_record` VALUES ('40', null, '19', '1806051', '32', '1', '2018-11-05', '21', null, '2018-11-05 11:48:54', '4', '8', '2018-11-05 11:41:58', '2018-11-05 11:50:07', null);
INSERT INTO `repair_record` VALUES ('41', null, '19', '1806051', '33', '1', '2018-11-05', '21', null, '2018-11-05 11:58:51', '5', '8', '2018-11-05 11:53:03', '2018-11-05 11:59:41', null);
INSERT INTO `repair_record` VALUES ('42', null, '19', '1806051', '34', '1', '2018-11-05', '21', null, null, null, '3', '2018-11-05 12:00:26', '2018-11-05 12:01:42', null);
INSERT INTO `repair_record` VALUES ('43', null, '24', '1807093', '35', '1', '2018-11-15', '23', null, '2018-11-06 13:26:11', '6', '8', '2018-11-06 10:25:38', '2018-11-06 16:02:37', null);

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
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，也即用户报修时间',
  `upload_files_amount` varchar(255) NOT NULL DEFAULT '0' COMMENT '要上传的文件总数，add时通过参数指明 ',
  `already_uploaded_files_number` varchar(255) NOT NULL DEFAULT '0' COMMENT '已上传成功的文件的数量',
  PRIMARY KEY (`id`),
  KEY `fk_rri_contacter` (`customer`),
  CONSTRAINT `fk_rri_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_request_info
-- ----------------------------
INSERT INTO `repair_request_info` VALUES ('11', '1806024', '', '', 'iiiiii', 'ooooooo', '', '9', null, '4', '0');
INSERT INTO `repair_request_info` VALUES ('13', '1807027', '', '/home/sinsim/output/aftersale/repair_req_voice/1807027_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-18-11-45-10_0.mp3', 'jjjjjjjj', 'jnknkmmmmmm', '/home/sinsim/output/aftersale/repair_req_img/1807027_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-18-11-45-14_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1807027_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-18-11-45-23_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1807027_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-18-11-46-00_3.jpg', '9', null, '4', '4');
INSERT INTO `repair_request_info` VALUES ('14', '1807026', '', '/home/sinsim/output/aftersale/repair_req_voice/1807026_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-19-09-54-53_0.mp3', '报修', '报修问题', '/home/sinsim/output/aftersale/repair_req_img/1807026_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-19-09-54-53_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1807026_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-19-09-54-54_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1807026_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-19-09-54-54_3.jpg', '9', null, '4', '4');
INSERT INTO `repair_request_info` VALUES ('16', '老机器-555', '/home/sinsim/output/aftersale/repair_req_nameplate_img/老机器-555_FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-10-23-16-45-19_0.jpg', '/home/sinsim/output/aftersale/repair_req_voice/老机器-555_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-23-16-45-20_0.mp3', '呵呵呵呵', '么么么么', '/home/sinsim/output/aftersale/repair_req_img/老机器-555_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-23-16-45-20_1.jpg,/home/sinsim/output/aftersale/repair_req_img/老机器-555_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-23-16-45-20_2.jpg,/home/sinsim/output/aftersale/repair_req_img/老机器-555_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-23-16-45-20_3.jpg', '19', '2018-10-23 16:45:20', '5', '5');
INSERT INTO `repair_request_info` VALUES ('17', '1806018', '', '/home/sinsim/output/aftersale/repair_req_voice/1806018_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-23-16-58-11_0.mp3', '123', '1231', '/home/sinsim/output/aftersale/repair_req_img/1806018_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-23-16-58-11_1.jpg', '11', '2018-10-23 16:58:11', '2', '2');
INSERT INTO `repair_request_info` VALUES ('18', '1806026', '', '/home/sinsim/output/aftersale/repair_req_voice/1806026_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-24-17-39-00_0.mp3', '嘎嘎嘎嘎', '急急急急急急', '/home/sinsim/output/aftersale/repair_req_img/1806026_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-24-17-39-01_1.jpg', '19', '2018-10-24 17:39:00', '2', '2');
INSERT INTO `repair_request_info` VALUES ('19', '1807025', '', '/home/sinsim/output/aftersale/repair_req_voice/1807025_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-26-11-26-18_0.mp3', '没办法', '没来了', '/home/sinsim/output/aftersale/repair_req_img/1807025_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-11-26-18_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1807025_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-11-26-19_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1807025_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-11-26-19_3.jpg', '17', '2018-10-26 11:26:18', '4', '4');
INSERT INTO `repair_request_info` VALUES ('22', '123456', '/home/sinsim/output/aftersale/repair_req_nameplate_img/123456_FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-10-26-11-35-00_0.jpg', '/home/sinsim/output/aftersale/repair_req_voice/123456_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-26-11-35-00_0.mp3', '123546', '123456', '/home/sinsim/output/aftersale/repair_req_img/123456_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-11-35-00_1.jpg,/home/sinsim/output/aftersale/repair_req_img/123456_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-11-35-00_2.jpg', '17', '2018-10-26 11:35:00', '5', '4');
INSERT INTO `repair_request_info` VALUES ('24', '111222', '/home/sinsim/output/aftersale/repair_req_nameplate_img/111222_FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE_2018-10-26-15-14-23_0.jpg', '/home/sinsim/output/aftersale/repair_req_voice/111222_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-26-15-14-23_0.mp3', '1112222', '111222', '/home/sinsim/output/aftersale/repair_req_img/111222_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-15-14-24_1.jpg,/home/sinsim/output/aftersale/repair_req_img/111222_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-15-14-24_2.jpg,/home/sinsim/output/aftersale/repair_req_img/111222_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-26-15-14-24_3.jpg', '17', '2018-10-26 15:14:23', '5', '5');
INSERT INTO `repair_request_info` VALUES ('25', '1806028', '', '/home/sinsim/output/aftersale/repair_req_voice/1806028_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-10-28-10-57-39_0.mp3', '机器异响', '主轴皮带异响', '/home/sinsim/output/aftersale/repair_req_img/1806028_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-28-10-57-40_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1806028_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-28-10-57-40_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1806028_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-10-28-10-57-40_3.jpg', '28', '2018-10-28 10:57:40', '4', '4');
INSERT INTO `repair_request_info` VALUES ('26', '1806042', '', '/home/sinsim/output/aftersale/repair_req_voice/1806042_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-02-14-52-55_0.mp3', '喂喂喂喂我', '会渐渐渐渐', '/home/sinsim/output/aftersale/repair_req_img/1806042_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-14-52-56_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1806042_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-14-52-57_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1806042_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-14-52-57_3.jpg', '19', '2018-11-02 14:52:55', '4', '4');
INSERT INTO `repair_request_info` VALUES ('27', '1806063', '', '/home/sinsim/output/aftersale/repair_req_voice/1806063_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-02-16-41-12_0.mp3', '么么么么么么么么', '呵呵呵呵', '/home/sinsim/output/aftersale/repair_req_img/1806063_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-16-41-13_1.jpg', '19', '2018-11-02 16:41:12', '2', '2');
INSERT INTO `repair_request_info` VALUES ('28', '1806062', '', '/home/sinsim/output/aftersale/repair_req_voice/1806062_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-02-16-52-17_0.mp3', '士官网公告称', '发发发发', '/home/sinsim/output/aftersale/repair_req_img/1806062_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-16-52-19_1.jpg', '19', '2018-11-02 16:52:16', '2', '2');
INSERT INTO `repair_request_info` VALUES ('29', '1806019', '', '/home/sinsim/output/aftersale/repair_req_voice/1806019_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-02-16-55-02_0.mp3', '突突突', '也一样', '/home/sinsim/output/aftersale/repair_req_img/1806019_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-16-55-06_1.jpg', '19', '2018-11-02 16:55:02', '2', '2');
INSERT INTO `repair_request_info` VALUES ('30', '1806025', '', '/home/sinsim/output/aftersale/repair_req_voice/1806025_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-02-17-28-13_0.mp3', '学习下', '学习下', '/home/sinsim/output/aftersale/repair_req_img/1806025_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-02-17-28-14_1.jpg', '19', '2018-11-02 17:28:13', '2', '2');
INSERT INTO `repair_request_info` VALUES ('31', '1806016', '', '/home/sinsim/output/aftersale/repair_req_voice/1806016_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-05-10-10-01_0.mp3', '转派测试', '转派测试去去去', '/home/sinsim/output/aftersale/repair_req_img/1806016_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-05-10-10-02_1.jpg', '11', '2018-11-05 10:10:02', '2', '2');
INSERT INTO `repair_request_info` VALUES ('32', '1806051', '', '/home/sinsim/output/aftersale/repair_req_voice/1806051_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-05-11-41-57_0.mp3', '报修1111', '哈哈哈哈', '/home/sinsim/output/aftersale/repair_req_img/1806051_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-05-11-41-58_1.jpg', '19', '2018-11-05 11:41:58', '2', '2');
INSERT INTO `repair_request_info` VALUES ('33', '1806051', '', '/home/sinsim/output/aftersale/repair_req_voice/1806051_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-05-11-53-03_0.mp3', '报修\n22222次', '发发发发', '/home/sinsim/output/aftersale/repair_req_img/1806051_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-05-11-53-03_1.jpg', '19', '2018-11-05 11:53:03', '2', '2');
INSERT INTO `repair_request_info` VALUES ('34', '1806051', '', '/home/sinsim/output/aftersale/repair_req_voice/1806051_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-05-12-00-26_0.mp3', '报修3333', '不错', '/home/sinsim/output/aftersale/repair_req_img/1806051_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-05-12-00-26_1.jpg', '19', '2018-11-05 12:00:26', '2', '2');
INSERT INTO `repair_request_info` VALUES ('35', '1807093', '', '/home/sinsim/output/aftersale/repair_req_voice/1807093_FILE_TYPE_REPAIR_REQUEST_VOICE_2018-11-06-10-25-38_0.mp3', '皮带断裂，导致机器不能正常运行', '更换皮带', '/home/sinsim/output/aftersale/repair_req_img/1807093_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-06-10-25-38_1.jpg,/home/sinsim/output/aftersale/repair_req_img/1807093_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-06-10-25-38_2.jpg,/home/sinsim/output/aftersale/repair_req_img/1807093_FILE_TYPE_REPAIR_REQUEST_IMAGE_2018-11-06-10-25-38_3.jpg', '24', '2018-11-06 10:25:38', '4', '4');

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
INSERT INTO `role` VALUES ('2', '管理员', '售后业务管理-test-update', '');
INSERT INTO `role` VALUES ('3', '普通员工', '安装，保养，维修（包括了信胜和客户的）', null);
INSERT INTO `role` VALUES ('4', '代理商', '代理商', null);
INSERT INTO `role` VALUES ('5', '客户', '权限在客户级别，比如查看客户自己所有机器', null);
INSERT INTO `role` VALUES ('6', '联系人', '权限在联系人自己级别，比如只能查看联系人自己名下的机器', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `wechatUnionId` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信unionId，在没授权前是空的。',
  `role_id` int(10) unsigned NOT NULL,
  `agent` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '代理商,如果是空表示是信胜自己的员工',
  `customer_company` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '客户公司',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL,
  `valid` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '是否在职 ， “1”:在职 “0”:离职',
  `phone` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '电话',
  `create_time` datetime NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_u_role_id` (`role_id`),
  KEY `fk_u_agent` (`agent`),
  CONSTRAINT `fk_u_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'wechat222', '1', '0', '0', 'sinsim', '1', '13188888888', '2018-07-11 10:03:43', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('2', 'zhangguang', 'zhangguang', null, '2', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX2号');
INSERT INTO `user` VALUES ('3', 'wangpu', 'wangpu', null, '3', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('4', '王管', '王管', null, '2', '2', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('5', '李代', '李代', null, '4', '2', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('6', '李客', '李客', '', '5', '0', 'XX有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('7', '王客', '王客', null, '5', '0', 'YY公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('8', 'zhaopu', 'zhaopu', '', '3', '0', '1', 'sinsim', '1', '13737373737', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('9', '李联', '李联', null, '6', '0', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('10', '马普', '马普', null, '3', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('11', '胡客', '胡客', null, '5', '1', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', 'XX市88路99号');
INSERT INTO `user` VALUES ('12', '张客', '张客', null, '5', '0', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('13', '韩普', '韩普', null, '3', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('14', '张普', '张普', null, '3', '2', '4', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('15', '刘代', '刘代', null, '4', '2', '0', 'sinsim', '1', '13155556666', '2018-09-21 19:40:31', '诸暨市XXXX路1号');
INSERT INTO `user` VALUES ('16', '张代', '张代', null, '4', '2', '0', 'sinsim', '1', '13500003333', '2018-09-21 19:42:20', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('17', 'liuke', 'liuke', 'oDPlm0m_R-7oR1Ry6u1nGkII6pOU', '5', '0', '杭州YY有限公司', 'sinsim', '1', '13300003333', '2018-09-21 20:08:39', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('18', 'wuxuemin_wxid', 'wuxuemin_wxid', null, '3', '0', null, 'sinsim', '1', '13588889999', '2018-09-23 16:25:29', null);
INSERT INTO `user` VALUES ('19', 'wuke', 'wuke姓名', 'oDPlm0kxW8jYDRuZt6koPO1G4CXw', '5', '0', 'XXX公司', 'sinsim', '1', '13011112222', '2018-10-18 17:25:23', 'HZ市12号大街');
INSERT INTO `user` VALUES ('21', 'wupu', 'wupu', null, '3', '0', null, 'sinsim', '1', '13500001111', '2018-10-24 11:10:31', null);
INSERT INTO `user` VALUES ('22', 'xs001', 'xs001', null, '3', '0', null, 'password', '1', '13455557777', '2018-10-24 11:13:04', null);
INSERT INTO `user` VALUES ('23', 'xs002', 'xs002', 'oDPlm0hIH6rr7aMPwqex_tSEKbCw', '3', '0', null, 'password', '1', '13455557772', '2018-10-24 11:13:17', null);
INSERT INTO `user` VALUES ('24', 'kh001', 'kh001', 'oDPlm0uI4f89_fA-Y_kJ-PDGwMuw', '5', '0', '1233有限公司', 'password', '1', '14366667777', '2018-10-24 11:14:16', 'XX市XX路555号');
INSERT INTO `user` VALUES ('25', 'kh002', 'kh002', 'oDPlm0hjE_4hhdVGj7bM5a3c0ukc', '5', '0', '小刘有限公司', 'password', '1', '14366667772', '2018-10-24 11:14:33', 'XX市XX路225号');
INSERT INTO `user` VALUES ('26', 'ttt', 'ttt', null, '3', '0', null, 'password', '0', '13466667777', '2018-10-26 10:19:36', null);
INSERT INTO `user` VALUES ('27', 'ttaaa', 'tt', null, '5', '0', 'ddd', 'password', '0', '13344444555', '2018-10-26 10:20:10', 'dddd');
INSERT INTO `user` VALUES ('28', '15258522855', '周', 'oDPlm0r7_pXdDTxd7bBNf6WGsgiA', '5', '0', '嘉方', 'password', '1', '15258522855', '2018-10-28 10:10:55', '浙江杭州');
INSERT INTO `user` VALUES ('29', '李普', '李普', null, '3', '0', null, 'password', '1', '18758069807', '2018-10-28 10:54:06', null);
INSERT INTO `user` VALUES ('30', 'wupu_lxr1', 'wupu_lxr1', null, '6', '0', 'XXX公司', 'password', '1', '13500001111', '2018-10-29 10:44:58', '杭州市ll路33号');
INSERT INTO `user` VALUES ('31', '阿里巴巴代理商1', '阿里巴巴代理商1', null, '4', '1', null, 'password', '1', '13311112222', '2018-11-05 10:12:03', null);
INSERT INTO `user` VALUES ('32', '阿里巴巴代理商1_员工A', '阿里巴巴代理商1_员工A', null, '4', '1', null, 'password', '1', '13311116666', '2018-11-05 10:17:01', null);
INSERT INTO `user` VALUES ('33', '小米科技代理商1', '小米科技代理商1', null, '4', '2', null, 'password', '1', '14455556666', '2018-11-08 10:21:39', null);

-- ----------------------------
-- Table structure for `wechat_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_user_info`;
CREATE TABLE `wechat_user_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `union_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  `nickname` text,
  `sex` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `headimgrul` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `privilege` text CHARACTER SET utf8 COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_user_info
-- ----------------------------
INSERT INTO `wechat_user_info` VALUES ('21', 'obIMx1vQhmXoZ224Y40HZgG-XjCM', 'oDPlm0hIH6rr7aMPwqex_tSEKbCw', '丹', '2', 'Zhejiang', 'Shaoxing', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIor03K1Wd456b1thFlHf0kMSz8iar5uEfNia7AecrSlV87Zw7u8vDYkVcWXD92bqjphwFawaQHO97g/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('22', 'obIMx1tPa0TWXsUEB7_gYU2gS4Ao', 'oDPlm0lzfKsDqLVy_-l81r8fRZ7c', 'Hay', '1', 'Zhejiang', 'Hangzhou', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoib5uevvw1CDGsUib1nqgqP2yicZxrlQH16qGm4cU7f2bJgzrBvqLUnEQ65X85tDhNarcq5bfxrLfqw/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('27', 'obIMx1hbpxQ_P0S_EpqU0PgROJnk', 'oDPlm0uI4f89_fA-Y_kJ-PDGwMuw', 'Mark2', '2', 'Alicante', '', 'ES', 'http://thirdwx.qlogo.cn/mmopen/vi_32/KaibibhX8cW35icwS2ad9yKLLLPk1PDribtqr3A6oRQXoYXfZ1gONTGEZxg0UQNYa9scZUuHiaSg7464Opotlcxibq4Q/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('31', 'obIMx1kMSx3wWlNrIaTDheroWeT4', 'oDPlm0hjE_4hhdVGj7bM5a3c0ukc', '小马千', '1', 'Hubei', 'Wuhan', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJENX9qlb6J72ItvicDAxMdKSRNeBIdOMmkMksubHm8ia5qbm86YwyicE2TGxG0TfMD42ia9RlEckDsAQ/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('32', 'obIMx1lFXD6IEM12px1p0B5Hmnrs', 'oDPlm0m_R-7oR1Ry6u1nGkII6pOU', '南阳诸葛庐?', '1', 'Zhejiang', 'Hangzhou', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIaIqx5G3ibMHZFR8nM5Ep97lNLTWJtUTYW4Ovib1OcgBupnuXBtiaYZpSOjNqhrhQCeQmoVnKfMox9A/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('37', 'obIMx1rHbcGcSwxNDZ3QyRS4ZbqI', 'oDPlm0kxW8jYDRuZt6koPO1G4CXw', '吴学敏*', '1', 'Zhejiang', 'Hangzhou', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/FbZEaTWcYyZAScoKqnz8BnVUb6WQKIx1I85oUs5M4Q1e97dibHZ0exHfOXPqBlicCyjUfH9CTM9zyibyxqTwxaZqQ/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('38', 'obIMx1lgVCoP7Dd3IIDuxi-1I2P4', 'oDPlm0jLcYZX0biFYb8XXWfUhZWM', 'redhat', '1', '', '', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eodenOW1ohl56oNt0MbhSA2zKu4KSIUUfFfpHiaSFpZYeC0libyrAFibePOzLqmOqCG4FEOv1w6GlG0A/132', '[]');
