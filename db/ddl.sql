/*
 Navicat MySQL Data Transfer

 Source Server Type    : MySQL
 Source Schema         : xavier_pms
*/

CREATE TABLE `tb_approval` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `approval_group_id` bigint(20) unsigned NOT NULL COMMENT '审批分组id',
  `approval_name` varchar(255) NOT NULL COMMENT '审批名称',
  `submit_type` varchar(16) NOT NULL COMMENT '提交类型',
  `icon` varchar(50) NOT NULL COMMENT '图标',
  `form` json DEFAULT NULL COMMENT '表单json',
  `process` json DEFAULT NULL COMMENT '流程json',
  `app_status` tinyint(2) NOT NULL COMMENT '应用状态',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批表';

-- ----------------------------
-- Table structure for tb_approval_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_approval_group`;
CREATE TABLE `tb_approval_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_name` varchar(50) NOT NULL COMMENT '分组名称',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批分组表';

-- ----------------------------
-- Table structure for tb_audit_form
-- ----------------------------
DROP TABLE IF EXISTS `tb_audit_form`;
CREATE TABLE `tb_audit_form` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `approval_id` bigint(20) unsigned NOT NULL COMMENT '审批id',
  `approval_name` varchar(255) NOT NULL COMMENT '审批名称',
  `form` json DEFAULT NULL COMMENT '表单json',
  `form_data` json DEFAULT NULL COMMENT '表单数据',
  `approval_process` json DEFAULT NULL COMMENT '审批流程',
  `audit_status` tinyint(2) unsigned NOT NULL COMMENT '审批状态',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批单表';

-- ----------------------------
-- Table structure for tb_audit_form_flow
-- ----------------------------
DROP TABLE IF EXISTS `tb_audit_form_flow`;
CREATE TABLE `tb_audit_form_flow` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `audit_form_id` bigint(20) unsigned NOT NULL COMMENT '审批单id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `approval_type` varchar(50) NOT NULL COMMENT '审批类型',
  `approval_mode` varchar(50) DEFAULT NULL COMMENT '审批方式',
  `approval_more_type` varchar(50) DEFAULT NULL COMMENT '多人审批时采用的审批方式',
  `process_node` int(11) unsigned NOT NULL COMMENT '流程节点',
  `audit_status` tinyint(2) unsigned NOT NULL COMMENT '审批状态',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批单流程表';

-- ----------------------------
-- Table structure for tb_audit_form_flow_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_audit_form_flow_detail`;
CREATE TABLE `tb_audit_form_flow_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `audit_form_id` bigint(20) unsigned NOT NULL COMMENT '审批单id',
  `audit_form_flow_id` bigint(20) unsigned NOT NULL COMMENT '审批单流程id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `approval_type` varchar(50) NOT NULL COMMENT '审批类型',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `audit_status` tinyint(2) unsigned NOT NULL COMMENT '审批状态',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审批单流程详细表';

-- ----------------------------
-- Table structure for tb_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `car_name` varchar(255) NOT NULL COMMENT '车辆名称',
  `car_number` varchar(50) DEFAULT NULL COMMENT '车牌号码',
  `is_enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆表';

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父id',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '负责人id',
  `full_id` varchar(1000) DEFAULT NULL COMMENT '全id',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Table structure for tb_meeting
-- ----------------------------
DROP TABLE IF EXISTS `tb_meeting`;
CREATE TABLE `tb_meeting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `meeting_name` varchar(255) NOT NULL COMMENT '会议室名称',
  `position` varchar(100) DEFAULT NULL COMMENT '位置',
  `is_enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会议室表';

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父id',
  `menu_code` varchar(100) DEFAULT '' COMMENT '菜单编码',
  `menu_type` varchar(8) NOT NULL COMMENT '菜单类型 M.目录 C.菜单 I.接口',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `component` varchar(100) DEFAULT NULL COMMENT '组件路径',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `is_frame` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否外链',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_cache` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否缓存',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否显示',
  `is_enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Table structure for tb_notification
-- ----------------------------
DROP TABLE IF EXISTS `tb_notification`;
CREATE TABLE `tb_notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `type` varchar(50) NOT NULL COMMENT '通知类型',
  `content` varchar(255) DEFAULT NULL COMMENT '通知内容',
  `target_type` varchar(50) DEFAULT NULL COMMENT '目标类型',
  `target_id` varchar(50) DEFAULT NULL COMMENT '目标id',
  `is_read` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否已读',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- ----------------------------
-- Table structure for tb_post
-- ----------------------------
DROP TABLE IF EXISTS `tb_post`;
CREATE TABLE `tb_post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `post_name` varchar(50) NOT NULL COMMENT '职位名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `role` varchar(1000) DEFAULT NULL COMMENT '角色列表',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职位表';

-- ----------------------------
-- Table structure for tb_professional_title
-- ----------------------------
DROP TABLE IF EXISTS `tb_professional_title`;
CREATE TABLE `tb_professional_title` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title_name` varchar(255) NOT NULL COMMENT '职称',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父id',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职称表';

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for tb_seal
-- ----------------------------
DROP TABLE IF EXISTS `tb_seal`;
CREATE TABLE `tb_seal` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `seal_name` varchar(255) NOT NULL COMMENT '印章名称',
  `is_enable` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='印章表';

-- ----------------------------
-- Table structure for tb_temp_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_temp_data`;
CREATE TABLE `tb_temp_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `a` varchar(255) DEFAULT NULL,
  `b` decimal(12,6) DEFAULT NULL,
  `c` decimal(12,6) DEFAULT NULL,
  `d` decimal(12,6) DEFAULT NULL,
  `e` varchar(255) DEFAULT NULL,
  `f` varchar(255) DEFAULT NULL,
  `g` decimal(12,2) DEFAULT NULL,
  `h` varchar(255) DEFAULT NULL,
  `i` varchar(255) DEFAULT NULL,
  `j` decimal(10,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123806 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `employee_number` varchar(50) NOT NULL COMMENT '工号',
  `nick_name` varchar(50) NOT NULL COMMENT '姓名',
  `nick_name_py` varchar(255) DEFAULT NULL COMMENT '姓名-拼音',
  `user_pwd` varchar(255) NOT NULL COMMENT '密码',
  `is_init_pwd` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否初始密码',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `first_employment_date` datetime DEFAULT NULL COMMENT '首次参加工作日期',
  `direct_leader_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '直属领导id',
  `entry_date` datetime DEFAULT NULL COMMENT '入职日期',
  `probation_period` int(5) DEFAULT NULL COMMENT '试用期(月)',
  `estimated_conversion_date` datetime DEFAULT NULL COMMENT '预计转正日期',
  `probationary_wage` decimal(10,2) DEFAULT NULL COMMENT '试用期薪酬(月)',
  `salary` decimal(10,2) DEFAULT NULL COMMENT '转正薪酬(月)',
  `department_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '部门id',
  `post_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '职位id',
  `professional_title_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '职称id',
  `document_type` varchar(100) DEFAULT NULL COMMENT '证件类型',
  `document_number` varchar(100) DEFAULT NULL COMMENT '证件号',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `household_registration_type` varchar(100) DEFAULT NULL COMMENT '户籍类型',
  `domicile_address` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `gender` varchar(100) DEFAULT NULL COMMENT '性别',
  `nationality` varchar(100) DEFAULT NULL COMMENT '民族',
  `marital_status` varchar(100) DEFAULT NULL COMMENT '婚姻状况',
  `political_status` varchar(100) DEFAULT NULL COMMENT '政治面貌',
  `social_security_account_number` varchar(100) DEFAULT NULL COMMENT '社保账号',
  `provident_fund_account` varchar(100) DEFAULT NULL COMMENT '公积金账号',
  `residential_address` varchar(255) DEFAULT NULL COMMENT '居住地址',
  `highest_degree` varchar(100) DEFAULT NULL COMMENT '最高学历',
  `graduate_school` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `graduation_time` varchar(100) DEFAULT NULL COMMENT '毕业时间',
  `major` varchar(100) DEFAULT NULL COMMENT '所学专业',
  `deposit_bank` varchar(100) DEFAULT NULL COMMENT '开户银行',
  `bank_card_number` varchar(100) DEFAULT NULL COMMENT '银行卡号',
  `work_experience` varchar(5000) DEFAULT NULL COMMENT '工作经历',
  `family_information` varchar(2000) DEFAULT NULL COMMENT '家庭信息',
  `emergency_contact` varchar(2000) DEFAULT NULL COMMENT '紧急联系人',
  `is_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `user_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_employee_number` (`employee_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for tb_user_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_group`;
CREATE TABLE `tb_user_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `group_name` varchar(50) NOT NULL COMMENT '组名称',
  `member_json` json DEFAULT NULL COMMENT '成员json',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工组表';

-- ----------------------------
-- Table structure for tb_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_token`;
CREATE TABLE `tb_user_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `token` varchar(100) NOT NULL COMMENT '令牌',
  `expiration_time` datetime NOT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户令牌表';