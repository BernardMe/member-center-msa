CREATE TABLE `c_market_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `account_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 NOT NULL,
  `status` int(2) NOT NULL COMMENT '0-有效 1-无效',
  `create_time` datetime NOT NULL,
  `create_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `create_use_id` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

CREATE TABLE `c_market_meun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `parent_id` int(20) NOT NULL COMMENT '0-根节点',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0-启用 1-停用',
  `sort` int(10) NOT NULL COMMENT '顺序',
  `url` varchar(100) DEFAULT NULL,
  `level` int(3) NOT NULL COMMENT '级别 1-一级 2-二级 3-三级',
  `leaf` int(2) DEFAULT NULL COMMENT '0-非叶子节点  1-叶子节点',
  PRIMARY KEY (`id`),
  KEY `c_market_meun_index` (`parent_id`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

CREATE TABLE `c_market_meun_mo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meun_id` bigint(20) NOT NULL COMMENT '菜单id',
  `mo_value` bigint(20) NOT NULL COMMENT '权限值',
  `mo_name` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '权限名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `mo_type` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `c_market_meun_mo_index` (`meun_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COMMENT='菜单和功能权限表';


CREATE TABLE `c_market_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '角色描述',
  `create_id` bigint(20) DEFAULT NULL,
  `create_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '0-启用 1-停用 2-删除',
  `limit_number` bigint(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `c_market_role_name_index` (`role_name`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


CREATE TABLE `c_market_role_data_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL,
  `data_auth` int(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_market_role_dataAuth_index` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色与数据权限关系表';


CREATE TABLE `c_market_role_meun` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `meun_id` bigint(20) NOT NULL COMMENT '菜单id',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '0-启用  1-停用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_market_role_id_index` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='角色与菜单功能关系表';


CREATE TABLE `c_market_role_mo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `meun_id` bigint(20) NOT NULL COMMENT '菜单id',
  `mo_value` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_use` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_market_role_mo_index` (`role_id`,`meun_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='角色与mo值关系表';


INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (1, 2, 20000000, '搜索', '2021-11-1 14:28:02', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (2, 2, 20000001, '编辑', '2021-11-1 14:28:05', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (3, 2, 20000002, '添加商品', '2021-11-5 10:15:45', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (4, 2, 20000003, '同步商品销量', '2021-11-5 14:19:22', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (5, 3, 30000000, '搜索', '2021-11-10 15:06:52', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (6, 3, 30000001, '添加员工', '2021-11-5 10:17:01', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (7, 3, 30000002, '修改', '2021-11-5 10:53:08', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (8, 3, 30000003, '允许提现', '2021-11-5 13:53:06', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (9, 3, 30000004, '禁止提现', '2021-11-5 13:53:33', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (10, 4, 40000000, '搜索', '2021-11-5 10:53:45', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (11, 4, 40000001, '发货', '2021-11-5 10:54:14', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (12, 4, 40000002, '取消订单', '2021-11-5 13:54:34', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (13, 4, 40000003, '详情', '2021-11-5 13:54:55', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (14, 4, 40000004, '审核', '2021-11-5 13:55:36', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (15, 4, 40000005, '同步订单信息', '2021-11-5 14:22:58', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (22, 6, 60000000, '搜索', '2021-11-10 15:02:53', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (23, 6, 60000001, '同步分润金额', '2021-11-5 14:45:54', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (24, 9, 90000000, '搜索', '2021-11-5 14:50:27', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (25, 9, 90000001, '新增角色', '2021-11-5 14:51:11', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (26, 9, 90000002, '编辑', '2021-11-5 14:51:37', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (27, 9, 90000003, '删除', '2021-11-5 14:52:02', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (28, 10, 100000000, '搜索', '2021-11-5 14:52:42', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (29, 10, 100000001, '新增用户', '2021-11-5 14:53:21', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (30, 10, 100000002, '编辑', '2021-11-5 14:53:47', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (31, 10, 100000003, '删除', '2021-11-5 14:54:06', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (32, 4, 40000006, '补货', '2021-11-8 18:18:09', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (33, 3, 30000005, '审核', '2021-11-9 17:57:13', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (34, 3, 30000006, '解约', '2021-11-9 17:57:39', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (35, 2, 20000004, '删除', '2021-11-10 10:26:24', NULL, 3);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (38, 5, 50000008, '搜索卡', '2021-11-10 10:28:54', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (39, 5, 50000009, '搜索设备', '2021-11-10 10:29:19', NULL, 1);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (40, 5, 50000010, '同步卡激活状态', '2021-11-10 10:30:19', NULL, 2);
INSERT INTO `c_market_meun_mo` (`id`, `meun_id`, `mo_value`, `mo_name`, `create_time`, `update_time`, `mo_type`) VALUES (41, 5, 50000011, '同步设备激活状态', '2021-11-10 10:30:45', NULL, 2);


INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (1, '控制台', 0, 0, 1, '', 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (2, '商品管理', 0, 0, 2, '', 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (3, '代理商管理', 0, 0, 3, '', 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (4, '订单管理', 0, 0, 4, '', 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (5, '设备管理', 0, 0, 5, NULL, 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (6, '分润管理', 0, 0, 6, NULL, 1, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (7, '配置管理', 0, 0, 7, NULL, 1, 0);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (8, '推广管理', 7, 0, 71, NULL, 2, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (9, '角色管理', 7, 0, 72, NULL, 2, 1);
INSERT INTO `c_market_meun` (`id`, `name`, `parent_id`, `status`, `sort`, `url`, `level`, `leaf`) VALUES (10, '用户管理', 7, 0, 73, NULL, 2, 1);


INSERT INTO `c_market_role` (`id`, `role_name`, `role_desc`, `create_id`, `create_name`, `create_time`, `update_time`, `status`, `limit_number`) VALUES (1, '超级管理员', '超级管理员', 1, '超级管理员', '2021-11-8 11:17:23', NULL, 0, 5);


INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (7, 1, 1, 0, '2021-11-2 15:03:58', '2021-11-2 15:03:58');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (8, 1, 2, 0, '2021-11-5 09:50:16', '2021-11-5 09:50:19');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (9, 1, 3, 0, '2021-11-5 09:50:35', '2021-11-5 09:50:37');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (10, 1, 4, 0, '2021-11-5 09:50:49', '2021-11-5 09:50:51');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (11, 1, 5, 0, '2021-11-5 09:51:01', '2021-11-5 09:51:04');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (12, 1, 6, 0, '2021-11-5 09:51:13', '2021-11-5 09:51:16');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (13, 1, 7, 0, '2021-11-5 09:51:28', '2021-11-5 09:51:31');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (14, 1, 8, 0, '2021-11-5 09:51:59', '2021-11-5 09:52:01');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (15, 1, 9, 0, '2021-11-5 09:52:10', '2021-11-5 09:52:13');
INSERT INTO `c_market_role_meun` (`id`, `role_id`, `meun_id`, `status`, `create_time`, `update_time`) VALUES (16, 1, 10, 0, '2021-11-5 09:52:25', '2021-11-5 09:52:28');


INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (1, 1, 2, 20000000, '2021-11-5 16:10:59', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (2, 1, 2, 20000001, '2021-11-5 16:30:51', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (3, 1, 2, 20000002, '2021-11-5 16:31:25', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (4, 1, 2, 20000003, '2021-11-5 17:40:13', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (5, 1, 3, 30000000, '2021-11-5 17:40:13', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (6, 1, 3, 30000001, '2021-11-5 17:40:13', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (7, 1, 3, 30000002, '2021-11-5 17:40:13', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (8, 1, 3, 30000003, '2021-11-18 14:43:15', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (9, 1, 3, 30000004, '2021-11-18 14:44:27', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (10, 1, 4, 40000000, '2021-11-18 14:45:02', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (11, 1, 4, 40000001, '2021-11-18 14:45:15', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (12, 1, 4, 40000002, '2021-11-18 14:45:26', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (13, 1, 4, 40000003, '2021-11-18 14:45:40', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (14, 1, 4, 40000004, '2021-11-18 14:45:55', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (15, 1, 4, 40000005, '2021-11-18 14:46:14', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (16, 1, 9, 90000000, '2021-11-18 14:46:44', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (17, 1, 9, 90000001, '2021-11-18 14:47:25', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (18, 1, 9, 90000002, '2021-11-18 14:48:37', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (19, 1, 9, 90000003, '2021-11-18 14:48:48', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (20, 1, 10, 100000000, '2021-11-18 14:49:04', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (21, 1, 10, 100000001, '2021-11-18 14:49:32', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (22, 1, 10, 100000002, '2021-11-18 14:49:48', NULL, NULL);
INSERT INTO `c_market_role_mo` (`id`, `role_id`, `meun_id`, `mo_value`, `create_time`, `create_use`, `update_time`) VALUES (23, 1, 10, 100000003, '2021-11-18 14:50:12', NULL, NULL);


INSERT INTO `c_market_account` (`id`, `role_id`, `account_name`, `phone`, `status`, `create_time`, `create_name`, `create_use_id`, `update_time`) VALUES (1, 1, '管理员', '18605376839', 0, '2021-11-3 18:40:47', '2', 1, '2021-11-8 17:52:46');



-- 新增表结构
-- 提标补货订单关联表
create table membercenter.c_market_agent_upper_order (
    id                  bigint auto_increment comment 'id' primary key,
    order_id            int(2) null     not null comment '提标补货订单号',
    agent_id            varchar(64)     not null comment '代理人id',
    goods_sku_id        bigint          not null comment '商品规格id',
    audit_goods_sku_id  bigint       null comment '待审核商品规格id',
    update_time         datetime     not null comment '更新时间',
    create_time         datetime     not null comment '创建时间'
) comment '提标补货订单关联表'
;


-- 新增表结构
-- 退货订单关联表
create table membercenter.c_market_agent_return_goods (
    id              bigint auto_increment comment 'id' primary key,
    order_id        int(2) null     not null comment '退货订单号',
    agent_id        varchar(64)     not null comment '代理人id',
    card_sn         varchar(64)     null comment 'ETC卡号',
    card_status     int(2)          null comment '卡状态(待激活|已激活｜已损坏未退回｜已损坏已退回|未激活退回)',
    device_sn       varchar(64)     null comment 'OBU标签号',
    device_status   int(2)          null comment '设备状态(待激活|已激活｜已损坏未退回｜已损坏已退回|未激活退回)',
    status          int(2)          default 0 null comment '0-正常 1-拒绝',
    update_time     datetime        not null comment '更新时间',
    create_time     datetime        not null comment '创建时间'
) comment '退货订单关联表'
;