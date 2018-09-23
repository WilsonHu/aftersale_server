/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : aftersale_db

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-09-23 17:49:43
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain_record
-- ----------------------------
INSERT INTO `maintain_record` VALUES ('1', '1806004', '保养二期', '2018-09-29', null, '3', '12313', '9', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-09-21 00:00:00', '2018-09-23 10:49:46', '1', '0');
INSERT INTO `maintain_record` VALUES ('2', '1806003', '保养二期', '2018-09-29', null, '10', '客户要定时清理相关重要部件', '9', '[{\"contentList\":[{\"checkValue\":0,\"content\":\"用风枪清除机身表面以及夹线器、压脚、中导轨、装置、旋梭、剪线刀、前后驱动等部位附着的线毛、灰尘；\"}],\"maintainType\":1},{\"contentList\":[{\"checkValue\":0,\"content\":\"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给伞齿轮、Y前后导轨、X驱动导轨及滑车轴承加注适量润滑脂；\"}],\"maintainType\":2},{\"contentList\":[{\"checkValue\":0,\"content\":\"1.检查中间脚盘是否正常\"},{\"checkValue\":0,\"content\":\"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；\"},{\"checkValue\":0,\"content\":\"3. 检查最低点角度（172°/178°高速±2°）是否正确；\"},{\"checkValue\":0,\"content\":\"4. 检查主轴转动应活络、运行平稳、停车位置准确；\"},{\"checkValue\":0,\"content\":\"5. 检查大连杆是否润滑良好，大连杆是否有磨损\"},{\"checkValue\":0,\"content\":\"6. 检查100度时跳线杆高度一致，换色轻松准确；\"},{\"checkValue\":0,\"content\":\"7. 检查针杆架间隙，是否有松动；\"},{\"checkValue\":0,\"content\":\"8. 检查针杆压下时针杆的上下间隙是否正常；\"},{\"checkValue\":0,\"content\":\"9. 检查针下位时与针板孔的左右位置是否居中\"},{\"checkValue\":0,\"content\":\"10. 检查针板孔是否有毛刺；\"},{\"checkValue\":0,\"content\":\"11. 检查压脚是否变形、高度（0.5-1.5mm）是否准确\"},{\"checkValue\":0,\"content\":\"12. 检查旋梭轴轴向窜动无明显间隙\"},{\"checkValue\":0,\"content\":\"13. 检查伞齿轮间隙范围0.1～0.15mm\"},{\"checkValue\":0,\"content\":\"14.检查并调整针与旋梭的位置，针位高低用夹具定位，间隙0.1～0.3mm。\"},{\"checkValue\":0,\"content\":\"15. 检查剪线动定刀压线簧部件是否完好，位置是否正确，切线工作是否正常；\"},{\"checkValue\":0,\"content\":\"16. 检查扣线部件是否完好，位置是否正确，扣线工作是否正常；\"},{\"checkValue\":0,\"content\":\"17. 检查勾线机构部件是否完好，位置是否正确，勾线工作是否正常；\"},{\"checkValue\":0,\"content\":\"18. 检查金片装置调整位置是否准确，工作是否正常\"},{\"checkValue\":0,\"content\":\"19. 检查X、Y同步带张力\"},{\"checkValue\":0,\"content\":\"20. 检查X、Y伺服电机皮带张力\"},{\"checkValue\":0,\"content\":\"21. 检查X、Y绣框间隙是否正常，轴承、型材是否有磨损\"},{\"checkValue\":0,\"content\":\"22. 检查毛毡是否磨损，绣框是否与台板摩擦（标准2mm）\"},{\"checkValue\":0,\"content\":\"23. 检查台板、防尘板是否平整\"},{\"checkValue\":0,\"content\":\"24. 检查移框时无卡顿、无杂音；\"}],\"maintainType\":3}]', '2018-09-22 00:00:00', '2018-09-23 11:46:52', '1', '0');
