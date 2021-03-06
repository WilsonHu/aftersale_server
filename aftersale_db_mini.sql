/*
Navicat MySQL Data Transfer

Source Server         : Local_sinsim
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50553
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agent
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_company`
-- ----------------------------
DROP TABLE IF EXISTS `customer_company`;
CREATE TABLE `customer_company` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customer_company_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_company
-- ----------------------------

-- ----------------------------
-- Table structure for `experience_lib`
-- ----------------------------
DROP TABLE IF EXISTS `experience_lib`;
CREATE TABLE `experience_lib` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑',
  `repair_actual_info_id` int(10) unsigned NOT NULL COMMENT '维修记录的ID',
  `issue_description` text COMMENT '问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑',
  `before_resolve_pictures` varchar(255) DEFAULT NULL COMMENT '解决前的照片，\r\n和repair_request_info.pictures是同一份图片 ，\r\n管理员可以编辑。但是不让删除图片。',
  `repair_method` text COMMENT '解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑',
  `after_resolve_pictures` varchar(1000) DEFAULT NULL COMMENT '解决后图片，来自于repair_actual_info.after_resolve_pictures。\r\n是同一份图片 ，\r\n管理员可以编辑。也不让删除图片。',
  `author` varchar(255) NOT NULL,
  `read_times` varchar(255) DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_customer_feedback
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_members
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install_record
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_customer_feedback
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_members
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_record
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parts_info
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_actual_info
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_customer_feedback
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_members
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of repair_record
-- ----------------------------

-- ----------------------------
-- Table structure for `repair_request_info`
-- ----------------------------
DROP TABLE IF EXISTS `repair_request_info`;
CREATE TABLE `repair_request_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nameplate` varchar(255) NOT NULL DEFAULT '' COMMENT '老机器报修时填写的铭牌号，非老机器自动填写',
  `nameplate_picture` varchar(255) DEFAULT NULL COMMENT '老机器报修时拍的铭牌的图片的保存路径，非老机器为空。',
  `voice` varchar(1000) DEFAULT NULL COMMENT '报修的语音文件的路径',
  `repair_title` varchar(255) DEFAULT NULL COMMENT '报修的标题',
  `content` text COMMENT '报修内容',
  `pictures` varchar(1000) DEFAULT NULL COMMENT '报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片',
  `customer` int(10) unsigned NOT NULL COMMENT '联系人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，也即用户报修时间',
  `upload_files_amount` varchar(255) DEFAULT '0' COMMENT '要上传的文件总数，add时通过参数指明 ',
  `already_uploaded_files_number` varchar(255) DEFAULT '0' COMMENT '已上传成功的文件的数量',
  PRIMARY KEY (`id`),
  KEY `fk_rri_contacter` (`customer`),
  CONSTRAINT `fk_rri_customer` FOREIGN KEY (`customer`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair_request_info
-- ----------------------------

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
  `wechat_union_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信unionId，在没授权前是空的。',
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

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
INSERT INTO `user` VALUES ('8', 'zhaopu', 'zhaopu', 'oDPlm0m_R-7oR1Ry6u1nGkII6pOU', '3', '0', '1', 'sinsim', '1', '13737373737', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('9', '李联', '李联', null, '6', '0', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('10', '马普', '马普', null, '3', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('11', '胡客', '胡客', 'oDPlm0kxW8jYDRuZt6koPO1G4CXw', '5', '1', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', 'XX市88路99号');
INSERT INTO `user` VALUES ('12', '张客', '张客', null, '5', '0', '杭州YY有限公司', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '');
INSERT INTO `user` VALUES ('13', '韩普', '韩普', null, '3', '0', '0', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('14', '张普', '张普', null, '3', '2', '4', 'sinsim', '1', '13188888888', '2018-07-21 08:53:49', '上海市XX路XX号');
INSERT INTO `user` VALUES ('15', '刘代', '刘代', null, '4', '0', '0', 'sinsim', '1', '13155556666', '2018-09-21 19:40:31', '诸暨市XXXX路1号');
INSERT INTO `user` VALUES ('16', '张代', '张代', null, '4', '0', '0', 'sinsim', '1', '13500003333', '2018-09-21 19:42:20', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('17', 'liuke', 'liuke', null, '5', '0', '杭州YY有限公司', 'sinsim', '1', '13300003333', '2018-09-21 20:08:39', '诸暨市XX路XX号');
INSERT INTO `user` VALUES ('18', 'wuxuemin_wxid', 'wuxuemin_wxid', null, '3', '0', null, 'sinsim', '1', '13588889999', '2018-09-23 16:25:29', null);
INSERT INTO `user` VALUES ('19', 'wuke', 'wuke', null, '5', '0', 'XXX公司', 'sinsim', '1', '13011112222', '2018-10-18 17:25:23', 'HZ市12号大街');
INSERT INTO `user` VALUES ('21', 'wupu', 'wupu', null, '3', '0', null, 'sinsim', '1', '13500001111', '2018-10-24 11:10:31', null);
INSERT INTO `user` VALUES ('22', 'xs001', 'xs001', null, '3', '0', null, 'password', '1', '13455557777', '2018-10-24 11:13:04', null);
INSERT INTO `user` VALUES ('23', 'xs002', 'xs002', 'oDPlm0hIH6rr7aMPwqex_tSEKbCw', '3', '0', null, 'password', '1', '13455557772', '2018-10-24 11:13:17', null);
INSERT INTO `user` VALUES ('24', 'kh001', 'kh001', 'oDPlm0lsiP-b_lIn5ChYGBzE5-Vo', '5', '0', '1233有限公司', 'password', '1', '14366667777', '2018-10-24 11:14:16', 'XX市XX路555号');
INSERT INTO `user` VALUES ('25', 'kh002', 'kh002', 'oDPlm0hjE_4hhdVGj7bM5a3c0ukc', '5', '0', '小刘有限公司', 'password', '1', '14366667772', '2018-10-24 11:14:33', 'XX市XX路225号');
INSERT INTO `user` VALUES ('26', 'ttt', 'ttt', null, '3', '0', null, 'password', '0', '13466667777', '2018-10-26 10:19:36', null);
INSERT INTO `user` VALUES ('27', 'ttaaa', 'tt', null, '5', '0', 'ddd', 'password', '0', '13344444555', '2018-10-26 10:20:10', 'dddd');
INSERT INTO `user` VALUES ('28', '15258522855', '周', 'oDPlm0r7_pXdDTxd7bBNf6WGsgiA', '5', '0', '嘉方', 'password', '1', '15258522855', '2018-10-28 10:10:55', '浙江杭州');
INSERT INTO `user` VALUES ('29', '李普', '李普', null, '3', '0', null, 'password', '1', '18758069807', '2018-10-28 10:54:06', null);
INSERT INTO `user` VALUES ('30', 'wupu_lxr1', 'wupu_lxr1', null, '6', '0', 'XXX公司', 'password', '1', '13500001111', '2018-10-29 10:44:58', '杭州市ll路33号');
INSERT INTO `user` VALUES ('31', '阿里巴巴代理商1', '阿里巴巴代理商1', null, '4', '1', null, 'password', '1', '13311112222', '2018-11-05 10:12:03', null);
INSERT INTO `user` VALUES ('32', '阿里巴巴代理商1_员工A', '阿里巴巴代理商1_员工A', null, '3', '1', null, 'password', '1', '13311116666', '2018-11-05 10:17:01', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_user_info
-- ----------------------------
INSERT INTO `wechat_user_info` VALUES ('21', 'obIMx1vQhmXoZ224Y40HZgG-XjCM', 'oDPlm0hIH6rr7aMPwqex_tSEKbCw', '丹', '2', 'Zhejiang', 'Shaoxing', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIor03K1Wd456b1thFlHf0kMSz8iar5uEfNia7AecrSlV87Zw7u8vDYkVcWXD92bqjphwFawaQHO97g/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('22', 'obIMx1tPa0TWXsUEB7_gYU2gS4Ao', 'oDPlm0lzfKsDqLVy_-l81r8fRZ7c', 'Hay', '1', 'Zhejiang', 'Hangzhou', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoib5uevvw1CDGsUib1nqgqP2yicZxrlQH16qGm4cU7f2bJgzrBvqLUnEQ65X85tDhNarcq5bfxrLfqw/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('27', 'obIMx1hbpxQ_P0S_EpqU0PgROJnk', 'oDPlm0uI4f89_fA-Y_kJ-PDGwMuw', 'Mark2', '2', 'Alicante', '', 'ES', 'http://thirdwx.qlogo.cn/mmopen/vi_32/KaibibhX8cW35icwS2ad9yKLLLPk1PDribtqr3A6oRQXoYXfZ1gONTGEZxg0UQNYa9scZUuHiaSg7464Opotlcxibq4Q/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('30', 'obIMx1vQhmXoZ224Y40HZgG-XjCM', 'oDPlm0hIH6rr7aMPwqex_tSEKbCw', '丹', '2', 'Zhejiang', 'Shaoxing', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIor03K1Wd456b1thFlHf0kMSz8iar5uEfNia7AecrSlV87Zw7u8vDYkVcWXD92bqjphwFawaQHO97g/132', '[]');
INSERT INTO `wechat_user_info` VALUES ('31', 'obIMx1kMSx3wWlNrIaTDheroWeT4', 'oDPlm0hjE_4hhdVGj7bM5a3c0ukc', '小马千', '1', 'Hubei', 'Wuhan', 'CN', 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJENX9qlb6J72ItvicDAxMdKSRNeBIdOMmkMksubHm8ia5qbm86YwyicE2TGxG0TfMD42ia9RlEckDsAQ/132', '[]');
