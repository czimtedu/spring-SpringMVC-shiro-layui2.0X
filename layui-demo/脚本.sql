/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : base

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-02 14:50:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_oplog
-- ----------------------------
DROP TABLE IF EXISTS `sys_oplog`;
CREATE TABLE `sys_oplog` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '关联管理员id',
  `operat_address` varchar(100) NOT NULL DEFAULT '' COMMENT '操作位置',
  `content` varchar(500) NOT NULL DEFAULT '' COMMENT '操作内容',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '删除位',
  PRIMARY KEY (`id`),
  KEY `ix_admin_id` (`user_id`),
  KEY `ix_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统管理员操作日志';

-- ----------------------------
-- Records of sys_oplog
-- ----------------------------
INSERT INTO `sys_oplog` VALUES ('1', '1', '数据字典', '超级管理员账号:admin,编辑code为:[dept]的字典', '2018-03-02 14:39:55', '2018-03-02 14:39:55', '0');
INSERT INTO `sys_oplog` VALUES ('2', '1', '数据字典', '超级管理员账号:admin,编辑code为:[dept]的字典', '2018-03-02 14:40:24', '2018-03-02 14:40:24', '0');
INSERT INTO `sys_oplog` VALUES ('3', '1', '数据字典', '超级管理员账号:admin,编辑code为:[dept]的字典', '2018-03-02 14:41:07', '2018-03-02 14:41:06', '0');
INSERT INTO `sys_oplog` VALUES ('4', '1', '数据字典', '超级管理员账号:admin,编辑code为:[dept]的字典', '2018-03-02 14:44:20', '2018-03-02 14:44:20', '0');
INSERT INTO `sys_oplog` VALUES ('5', '1', '数据字典', '超级管理员账号:admin,编辑code为:[dept]的字典', '2018-03-02 14:44:27', '2018-03-02 14:44:26', '0');

-- ----------------------------
-- Table structure for sys_systemdictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemdictionary`;
CREATE TABLE `sys_systemdictionary` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL COMMENT '类型代号',
  `name` varchar(20) DEFAULT NULL COMMENT '类型名称',
  `description` varchar(50) DEFAULT NULL COMMENT '描述信息',
  `status` int(5) DEFAULT NULL COMMENT '启用状态(0启用,1:禁用)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uix_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_systemdictionary
-- ----------------------------
INSERT INTO `sys_systemdictionary` VALUES ('1', 'dept', '部门', '部门信息去', '0', '2018-03-02 14:49:11', '2018-03-02 14:44:27');
INSERT INTO `sys_systemdictionary` VALUES ('2', 'service_time', '使用月数', '使用月数', '0', '2018-03-02 14:49:14', null);
INSERT INTO `sys_systemdictionary` VALUES ('3', 'job', '岗位', '员工岗位信息', '0', '2018-03-02 14:49:16', null);
INSERT INTO `sys_systemdictionary` VALUES ('4', 'industrySector', '行业领域', '行业领域信息', '0', '2018-03-02 14:49:18', null);
INSERT INTO `sys_systemdictionary` VALUES ('5', 'certificate', '资格证书', '资格证书', '0', '2018-03-02 14:49:20', null);
INSERT INTO `sys_systemdictionary` VALUES ('6', 'project_role', '项目角色', '项目角色', '0', '2018-03-02 14:49:22', null);
INSERT INTO `sys_systemdictionary` VALUES ('7', 'protocol', '培训协议期', '培训协议期', '0', '2018-03-02 14:49:25', null);
INSERT INTO `sys_systemdictionary` VALUES ('8', 'language', '技术能力', '技术语言信息', '0', '2018-03-02 14:49:28', null);

-- ----------------------------
-- Table structure for sys_systemdictionaryitem
-- ----------------------------
DROP TABLE IF EXISTS `sys_systemdictionaryitem`;
CREATE TABLE `sys_systemdictionaryitem` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(20) NOT NULL COMMENT '代码编号，来自sys_systemdictionary.code',
  `value` varchar(50) NOT NULL COMMENT '枚举_值',
  `name` varchar(20) NOT NULL COMMENT '枚举_名称',
  `description` varchar(50) NOT NULL DEFAULT '' COMMENT '描述信息',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uix_code_value` (`code`,`value`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='字典类型值枚举表';

-- ----------------------------
-- Records of sys_systemdictionaryitem
-- ----------------------------
INSERT INTO `sys_systemdictionaryitem` VALUES ('2', 'dept', 'dept_data_service', '数据服务部', '数据服务部', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('3', 'dept', 'dept_Info_security_service', '信息安全服务部', '信息安全服务部', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('4', 'dept', 'dept_application_service', '应用与服务部', '应用与服务部', null, '2017-12-21 04:59:43');
INSERT INTO `sys_systemdictionaryitem` VALUES ('5', 'dept', 'dept_net_o2o_service', '互联网O2O业务线', '互联网O2O业务线', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('6', 'dept', 'dept_ipp_service', 'IPP智慧家庭业务线', 'IPP智慧家庭业务线', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('7', 'dept', 'dept_smart_photo_team', '智慧相册项目组', '智慧相册项目组', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('8', 'dept', 'dept_Inte_management', '综合管理部', '综合管理部', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('9', 'job', 'job_architect', '架构师', '架构师', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('10', 'job', 'job_web_engineer', '前端开发工程师', '前端开发工程师', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('11', 'job', 'job_back_engineer', '后端工程师', '后端工程师', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('12', 'job', 'job_software_testing', '软件测试', '软件测试', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('13', 'job', 'dept_operational_engineering', '运维工程', '运维工程', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('14', 'job', 'job_software_sales', '软件销售', '软件销售', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('15', 'job', 'job_pre_sales_support', '售前支持', '售前支持', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('17', 'job', 'job_after_service', '售后服务', '售后服务', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('18', 'job', 'job_product_manager', '产品经理', '产品经理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('19', 'job', 'job_product_specialist', '产品专员', '产品专员', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('20', 'job', 'operations_manager', '运营经理', '运营经理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('21', 'job', 'operating_commissioner', '运营专员', '运营专员', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('22', 'job', 'project_management', '项目管理', '项目管理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('23', 'job', 'personnel_management', '人事管理', '人事管理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('24', 'job', 'financial_management', '财务管理', '财务管理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('25', 'job', 'integrated_management', '综合管理', '综合管理', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('26', 'job', 'ui_or_ue', 'UI/UE', 'UI/UE', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('27', 'service_time', 'less_than_six_months', '6个月以下', '6个月以下', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('28', 'service_time', 'six_to_twelve_months', '6-12个月', '6-12个月', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('29', 'industrySector', 'int_education', '互联网教育', '互联网教育', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('30', 'industrySector', 'online_finance', '互联网金融', '互联网金融', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('31', 'industrySector', 'int_healthcare', '互联网医疗', '互联网医疗', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('32', 'service_time', 'six_to_twenty_four_months', '12-24个月', '12-24个月', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('33', 'industrySector', 'mobile_office', '移动办公', '移动办公', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('34', 'industrySector', 'mobile_internet', '移动互联网', '移动互联网', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('35', 'service_time', 'more_than_twenty_four_months', '24个月以上', '24个月以上', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('36', 'industrySector', 'political_software', '政法系统软件', '政法系统软件', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('37', 'industrySector', 'enterprise_info', '企业信息化', '企业信息化', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('38', 'industrySector', 'real_estate', '房地产', '房地产', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('39', 'industrySector', 'finance_banking_securities', '金融/银行/证券', '金融/银行/证券', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('40', 'industrySector', 'forestry_fishing', '农/林/牧/渔', '农/林/牧/渔', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('41', 'industrySector', 'game', '游戏', '游戏', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('42', 'industrySector', 'smart_home', '智能家居/智能硬件', '智能家居/智能硬件', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('43', 'industrySector', 'military', '军工', '军工', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('44', 'industrySector', 'car_networking', '车联网', '车联网', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('45', 'industrySector', 'tourism', '旅游', '旅游', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('46', 'industrySector', 'trade_lease', '贸易/批发/零售/租赁', '贸易/批发/零售/租赁', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('47', 'industrySector', 'television', '广电', '广电', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('48', 'industrySector', 'info_security', '信息安全', '信息安全', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('49', 'industrySector', 'big_data', '大数据', '大数据', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('50', 'industrySector', 'cloud_platform', '云平台', '云平台', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('51', 'language', 'c_language', 'C', 'C', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('52', 'language', 'c_hard_language', 'C++', 'C++', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('53', 'language', 'java', 'Java', 'Java', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('54', 'language', 'html', 'HTML', 'HTML', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('55', 'language', 'java_script', 'JS', 'JS', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('56', 'protocol', 'one_year', '1', '1年', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('57', 'language', 'python', 'Python', 'Python', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('58', 'language', 'php_language', 'PHP', 'PHP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('59', 'language', 'android', 'Android', 'Android', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('60', 'protocol', 'two_years', '2', '2年', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('61', 'language', 'ios_language', 'IOS', 'IOS', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('62', 'protocol', 'three_years', '3', '3年', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('63', 'protocol', 'four_years', '4', '4年', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('64', 'protocol', 'five_years', '5', '5年', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('65', 'project_role', 'total_charge', '项目总负责人', '项目总负责人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('66', 'project_role', 'development_leader', '项目开发负责人', '项目开发负责人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('67', 'project_role', 'development_participant', '项目开发参与人', '项目开发参与人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('68', 'project_role', 'test_leader', '项目测试负责人', '项目测试负责人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('69', 'project_role', 'test_participants', '项目测试参与人', '项目测试参与人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('70', 'project_role', 'demand_leader', '需求负责人', '需求负责人', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('71', 'project_role', 'other_roles', '其他角色', '其他角色', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('72', 'certificate', 'QCCSTP', '计算机技术与软件专业技术资格（水平）证', '计算机技术与软件专业技术资格（水平）证', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('73', 'certificate', 'NCRE_four', '国家计算机等级证书四级', '国家计算机等级证书四级', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('74', 'certificate', 'NCRE_three', '国家计算机等级证书三级', '国家计算机等级证书三级', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('75', 'certificate', 'NPDP_certification', 'NPDP认证', 'NPDP认证', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('76', 'certificate', 'network_engineer', '网络工程师', '网络工程师', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('77', 'certificate', 'CPMP', '中国项目管理师（CPMP）', '中国项目管理师（CPMP）', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('78', 'certificate', 'IPMP', 'IPMP', 'IPMP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('79', 'certificate', 'PMP_certification', 'PMP', 'PMP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('80', 'certificate', 'CISP', 'CISP', 'CISP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('81', 'certificate', 'CISA', 'CISA', 'CISA', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('82', 'certificate', 'CISSP', 'CISSP', 'CISSP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('83', 'certificate', 'ORACLE', 'ORACLE', 'ORACLE', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('84', 'certificate', 'microsoft_certified_professional', '微软认证专家', '微软认证专家', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('85', 'certificate', 'CCNP', 'CCNP', 'CCNP', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('86', 'certificate', 'CCNA', 'CCNA', 'CCNA', null, null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('92', 'dept', 'sssqqq', 'sss', '111', '2017-12-21 03:37:59', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('94', 'dept', 'qqqq_11', '11111', '11111', '2017-12-21 06:24:35', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('95', 'dept', 'www_111', '2222', '111', '2017-12-21 06:24:44', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('96', 'dept', 'eeeee', '222', 'eee', '2017-12-21 06:25:04', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('97', 'dept', 'qqqqqq', '222qqq', 'qqqq', '2017-12-21 06:25:12', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('98', 'dept', 'dddd', 'ddd', 'ddd', '2017-12-21 06:25:17', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('99', 'dept', 'aaaaa', 'aaaaa', 'aaaa', '2017-12-21 06:25:24', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('100', 'dept', 'cccc', 'cccc', 'ccc', '2017-12-21 06:25:29', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('101', 'dept', 'vvvv', 'vvvvv', 'vvvv', '2017-12-21 06:25:38', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('102', 'dept', 'bbbb', 'bbbb', 'bbb', '2017-12-21 06:25:46', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('103', 'dept', 'nnnnn', 'bbbbnn', 'nnn', '2017-12-21 06:25:52', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('104', 'dept', 'gggg', 'gggg', 'ggg', '2017-12-21 06:25:58', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('105', 'dept', 'hhhh', 'hhh', 'hh', '2017-12-21 06:26:06', null);
INSERT INTO `sys_systemdictionaryitem` VALUES ('106', 'dept', 'jjjjjj', 'jjjj', 'jjjj', '2017-12-21 06:26:16', null);

-- ----------------------------
-- Table structure for yb_platform_resource
-- ----------------------------
DROP TABLE IF EXISTS `yb_platform_resource`;
CREATE TABLE `yb_platform_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `resKey` varchar(50) DEFAULT NULL,
  `resName` varchar(20) DEFAULT NULL,
  `parentId` bigint(12) DEFAULT NULL,
  `searchSn` varchar(200) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `resUrl` varchar(100) DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `ishide` bit(1) DEFAULT b'0',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `btnType` int(2) DEFAULT NULL COMMENT '功能按钮类型（1.功能按钮、2.超链接）',
  `btnHtml` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`level`),
  KEY `idx_sys_resource_parent_ids` (`icon`),
  KEY `ix_parentId` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_platform_resource
-- ----------------------------
INSERT INTO `yb_platform_resource` VALUES ('1', 'system', '系统管理', '0', '0', 'menu1', ' #', '0', '', '\0', '2016-12-08 09:20:29', '2017-03-27 14:35:54', '0', '', 'ffff');
INSERT INTO `yb_platform_resource` VALUES ('2', 'user_list', '用户管理', '1', '0,1', 'menu2', 'sysuser/listUI', '1', '', '\0', '2016-12-08 09:20:46', '2017-03-07 11:32:22', '0', '', null);
INSERT INTO `yb_platform_resource` VALUES ('3', 'user_add', '用户新增', '2', '0,1,2', 'button', 'sysuser/addUI', '21', '', '\0', '2016-12-08 09:20:49', '2017-03-07 14:43:39', '1', '<button id=\"toAdd\" class=\"layui-btn layui-btn-primary layui-btn-sm\" >添加</button>', null);
INSERT INTO `yb_platform_resource` VALUES ('4', 'user_edit', '用户编辑', '2', '0,1,2', 'button', 'sysuser/editUI', '21', '', '\0', '2016-12-08 09:20:51', '2017-03-07 14:43:30', '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"edit\\\">编辑</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('5', 'user_details', '用户详情', '2', '0,1,2', 'button', 'sysuser/view', '21', '', '\0', '2016-12-08 09:20:56', '2017-03-07 14:43:01', '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"detail\\\">详情</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('6', 'user_exportadmin', '用户导出', '2', '0,1', 'button', 'sysuser/exportadmin', '1', '', '\0', '2016-12-08 09:20:59', null, '1', '<button id=\"toExport\" class=\"layui-btn layui-btn-primary layui-btn-sm\">导出</button>', null);
INSERT INTO `yb_platform_resource` VALUES ('7', 'user_enabled', '用户启用', '2', '0,1,7', 'button', 'sysuser/userStatusUI/*', '31', '', '\0', '2016-12-08 09:21:01', null, '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"enabled\\\">启用</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('8', 'user_forbid', '用户禁用', '2', '0,1,7', 'button', 'sysuser/userStatusUI/*', '31', '', '\0', '2016-12-08 09:21:03', '2017-03-07 13:57:16', '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"forbid\\\">禁用</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('9', 'role_list', '角色管理', '1', '0,1,7', 'menu2', 'role/listUI', '31', '', '\0', '2016-12-08 09:21:06', '2017-03-07 14:42:37', '0', '', null);
INSERT INTO `yb_platform_resource` VALUES ('10', 'role_add', '角色新增', '9', '0,1,7', 'button', 'role/addUI', '31', '', '\0', '2016-12-08 09:21:08', '2017-03-07 14:42:13', '1', '<button id=\"toAdd\" class=\"layui-btn layui-btn-primary layui-btn-sm\" >添加</button>', null);
INSERT INTO `yb_platform_resource` VALUES ('11', 'role_edit', '角色编辑', '9', '0,1', 'button', 'role/editUI', '1', '', '\0', '2016-12-08 09:21:11', null, '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"edit\\\">编辑</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('12', 'role_details', '角色详情', '9', '0,1,12', 'button', 'role/view', '41', '', '\0', '2016-12-08 09:21:14', '2017-03-07 14:44:30', '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"detail\\\">详情</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('13', 'resource_list', '资源管理', '1', '0,1,12', 'menu2', 'resource/listUI', '41', '', '\0', '2016-12-08 09:21:17', '2017-03-07 14:41:45', '0', ' ', null);
INSERT INTO `yb_platform_resource` VALUES ('14', 'log_list', '操作日志', '1', '0,1,12', 'menu2', 'log/listUI', '41', '', '\0', '2016-12-08 09:21:21', '2017-03-07 14:44:12', '0', ' ', null);
INSERT INTO `yb_platform_resource` VALUES ('15', 'systemDictionary_list', '数据字典', '1', '0,1,2', 'menu2', 'systemDictionary/listUI', null, null, '\0', null, null, '0', '', null);
INSERT INTO `yb_platform_resource` VALUES ('16', 'systemDictionary_edit', '字典编辑', '15', '0,1,2', 'button', 'systemDictionary/edit', null, null, '\0', null, null, '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"edit\\\">编辑</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('17', 'systemDictionary_detail', '字典详情', '15', '0,1,2', 'button', 'systemDictionary/detail', null, null, '\0', null, null, '2', '<a class=\\\"layui-btn layui-btn-primary layui-btn-xs\\\" lay-event=\\\"detail\\\">详情</a>', null);
INSERT INTO `yb_platform_resource` VALUES ('18', 'systemDictionary_add', '字典添加', '15', '0,1', 'button', 'systemDictionary/add', null, null, '\0', null, null, '1', '<button id=\"toAdd\" class=\"layui-btn layui-btn-primary layui-btn-sm\" >添加</button>', null);

-- ----------------------------
-- Table structure for yb_platform_role
-- ----------------------------
DROP TABLE IF EXISTS `yb_platform_role`;
CREATE TABLE `yb_platform_role` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_key` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `available` tinyint(4) DEFAULT '0' COMMENT '是否显示(0:可见,1:不可见)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(200) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_platform_role
-- ----------------------------
INSERT INTO `yb_platform_role` VALUES ('1', 'admin', '超级管理员', '0', '2017-05-25 09:30:39', '2017-06-28 09:55:09', '超级管理员');
INSERT INTO `yb_platform_role` VALUES ('2', 'common_employee', '普通员工', '1', '2017-09-12 13:47:26', '2017-11-07 10:33:12', '普通员工');
INSERT INTO `yb_platform_role` VALUES ('3', 'manager_employee', '管理员', '1', '2017-10-24 16:53:02', '2017-11-07 11:30:42', '管理员管理');
INSERT INTO `yb_platform_role` VALUES ('5', null, '12', '1', '2017-11-03 10:30:52', null, '1212');

-- ----------------------------
-- Table structure for yb_platform_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `yb_platform_role_resource`;
CREATE TABLE `yb_platform_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uix_roleid_resourceid` (`role_id`,`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yb_platform_role_resource
-- ----------------------------
INSERT INTO `yb_platform_role_resource` VALUES ('1', '1', '1');
INSERT INTO `yb_platform_role_resource` VALUES ('2', '1', '2');
INSERT INTO `yb_platform_role_resource` VALUES ('3', '1', '3');
INSERT INTO `yb_platform_role_resource` VALUES ('4', '1', '4');
INSERT INTO `yb_platform_role_resource` VALUES ('90', '1', '6');
INSERT INTO `yb_platform_role_resource` VALUES ('6', '1', '7');
INSERT INTO `yb_platform_role_resource` VALUES ('7', '1', '8');
INSERT INTO `yb_platform_role_resource` VALUES ('8', '1', '9');
INSERT INTO `yb_platform_role_resource` VALUES ('9', '1', '10');
INSERT INTO `yb_platform_role_resource` VALUES ('10', '1', '11');
INSERT INTO `yb_platform_role_resource` VALUES ('11', '1', '12');
INSERT INTO `yb_platform_role_resource` VALUES ('12', '1', '13');
INSERT INTO `yb_platform_role_resource` VALUES ('13', '1', '14');
INSERT INTO `yb_platform_role_resource` VALUES ('14', '1', '15');
INSERT INTO `yb_platform_role_resource` VALUES ('15', '1', '16');
INSERT INTO `yb_platform_role_resource` VALUES ('16', '1', '17');
INSERT INTO `yb_platform_role_resource` VALUES ('17', '1', '18');
INSERT INTO `yb_platform_role_resource` VALUES ('18', '1', '19');
INSERT INTO `yb_platform_role_resource` VALUES ('36', '1', '20');
INSERT INTO `yb_platform_role_resource` VALUES ('73', '1', '21');
INSERT INTO `yb_platform_role_resource` VALUES ('74', '1', '22');
INSERT INTO `yb_platform_role_resource` VALUES ('75', '1', '23');
INSERT INTO `yb_platform_role_resource` VALUES ('76', '1', '24');
INSERT INTO `yb_platform_role_resource` VALUES ('49', '1', '25');
INSERT INTO `yb_platform_role_resource` VALUES ('50', '1', '26');
INSERT INTO `yb_platform_role_resource` VALUES ('52', '1', '28');
INSERT INTO `yb_platform_role_resource` VALUES ('53', '1', '29');
INSERT INTO `yb_platform_role_resource` VALUES ('54', '1', '31');
INSERT INTO `yb_platform_role_resource` VALUES ('55', '1', '32');
INSERT INTO `yb_platform_role_resource` VALUES ('57', '1', '33');
INSERT INTO `yb_platform_role_resource` VALUES ('56', '1', '34');
INSERT INTO `yb_platform_role_resource` VALUES ('58', '1', '35');
INSERT INTO `yb_platform_role_resource` VALUES ('87', '1', '38');
INSERT INTO `yb_platform_role_resource` VALUES ('88', '1', '39');
INSERT INTO `yb_platform_role_resource` VALUES ('91', '1', '40');
INSERT INTO `yb_platform_role_resource` VALUES ('101', '1', '50');
INSERT INTO `yb_platform_role_resource` VALUES ('102', '1', '51');
INSERT INTO `yb_platform_role_resource` VALUES ('77', '2', '25');
INSERT INTO `yb_platform_role_resource` VALUES ('78', '2', '28');
INSERT INTO `yb_platform_role_resource` VALUES ('79', '2', '29');
INSERT INTO `yb_platform_role_resource` VALUES ('80', '3', '26');
INSERT INTO `yb_platform_role_resource` VALUES ('81', '3', '31');
INSERT INTO `yb_platform_role_resource` VALUES ('82', '3', '32');
INSERT INTO `yb_platform_role_resource` VALUES ('84', '3', '33');
INSERT INTO `yb_platform_role_resource` VALUES ('85', '3', '34');
INSERT INTO `yb_platform_role_resource` VALUES ('83', '3', '35');
INSERT INTO `yb_platform_role_resource` VALUES ('59', '4', '26');
INSERT INTO `yb_platform_role_resource` VALUES ('60', '4', '33');
INSERT INTO `yb_platform_role_resource` VALUES ('61', '5', '1');
INSERT INTO `yb_platform_role_resource` VALUES ('62', '5', '21');
INSERT INTO `yb_platform_role_resource` VALUES ('63', '5', '24');

-- ----------------------------
-- Table structure for yb_platform_sysuser
-- ----------------------------
DROP TABLE IF EXISTS `yb_platform_sysuser`;
CREATE TABLE `yb_platform_sysuser` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(18) NOT NULL DEFAULT '' COMMENT '账号',
  `password` char(32) NOT NULL DEFAULT '' COMMENT '密码',
  `passStatus` tinyint(1) DEFAULT '0' COMMENT '是否初始化的密码(0:初始密码,1:用户密码)',
  `role_id` bigint(10) DEFAULT '0' COMMENT '角色id,来自yb_platform_role.id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `city_code` varchar(2000) NOT NULL DEFAULT '' COMMENT '城市id',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:启动,1：禁用',
  `locked` bit(1) DEFAULT NULL COMMENT '锁定状态(0:正常,1:锁定)',
  `salt` varchar(50) DEFAULT '' COMMENT '盐,密码加工处理串',
  `remark` varchar(200) DEFAULT '' COMMENT '描述信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_account` (`status`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=2008 DEFAULT CHARSET=utf8 COMMENT='系统管理员';

-- ----------------------------
-- Records of yb_platform_sysuser
-- ----------------------------
INSERT INTO `yb_platform_sysuser` VALUES ('1', 'admin', '0f10af953f3305a07bc28836403bab55', '1', '1', '超级管理员', '', '000', '0', '\0', '28cb5ee3408b7a4a983b47f78ae99307', '11', '2017-05-10 07:38:38', '2017-11-13 17:55:21');
INSERT INTO `yb_platform_sysuser` VALUES ('2', 'superstar', 'c1bee4a2c3f7af7b816365f4325a2794', '0', '2', '超级明星', '', '000', '0', '\0', '6287c320aa07a60fc4524578fe01a602', '11', '2017-09-12 13:42:50', '2017-10-24 16:31:35');
INSERT INTO `yb_platform_sysuser` VALUES ('3', 'lrjang', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('4', 'lrjang1', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('5', 'lrjang2', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('6', '1', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('7', '2', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('8', '3', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('9', '4', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('10', '5', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('11', '6', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('12', '7', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('13', '8', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('14', '9', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('15', '10', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('16', '11', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('17', '12', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('18', '13', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('19', '14', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('20', '15', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('21', '16', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('22', '17', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('23', '18', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('24', '19', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('25', '20', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('26', '21', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('27', '22', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('28', '23', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('29', '24', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('30', '25', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('31', '26', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('32', '27', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('33', '28', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('34', '29', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('35', '30', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('36', '31', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('37', '32', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('38', '33', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('39', '34', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('40', '35', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('41', '36', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('42', '37', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('43', '38', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('44', '39', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('45', '40', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('46', '41', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('47', '42', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('48', '43', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('49', '44', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('50', '45', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('51', '46', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('52', '47', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('53', '48', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('54', '49', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('55', '50', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('56', '51', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('57', '52', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('58', '53', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('59', '54', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('60', '55', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('61', '56', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('62', '57', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('63', '58', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('64', '59', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('65', '60', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('66', '61', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('67', '62', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('68', '63', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('69', '64', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('70', '65', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('71', '66', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('72', '67', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('73', '68', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('74', '69', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('75', '70', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('76', '71', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('77', '72', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('78', '73', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('79', '74', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('80', '75', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('81', '76', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('82', '77', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('83', '78', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('84', '79', 'de2a22d4727ab386a4942af4d629600c', '0', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', '3d63cee4fd2f9fd818daf748d3e80e1f', '', '2017-10-24 16:53:59', '2017-11-14 13:31:15');
INSERT INTO `yb_platform_sysuser` VALUES ('85', '80', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('86', '81', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('87', '82', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('88', '83', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('89', '84', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('90', '85', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('91', '86', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('92', '87', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('93', '88', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('94', '89', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('95', '90', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('96', '91', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('97', '92', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('98', '93', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('99', '94', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
INSERT INTO `yb_platform_sysuser` VALUES ('100', '95', '36b4227b936af05433b3cf031f0bda8f', '1', '3', '纠纷分类1sss', '15198053941', '000', '0', '\0', 'fb5a74954018de163507a5e76772740b', '', '2017-10-24 16:53:59', '2017-11-09 16:58:03');
