DROP TABLE IF EXISTS `cls_market_etc_global_setting`;
CREATE TABLE `cls_market_etc_global_setting`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(64)  NOT NULL COMMENT '名称',
    `data`        varchar(100) NOT NULL COMMENT '值',
    `describe`    varchar(100) NOT NULL COMMENT '描述',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `cls_market_etc_global_setting_index` (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='全局配置信息表';

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_OFFLINE_ACTIVE_BONUS', '60', '代理商推介用户成功申办，并现场安装ETC套装。每激活一套，代理商即可获得奖励60元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_OFFLINE_EXTRA_ACTIVE_BONUS', '20', '代理商推介用户成功申办，并现场安装ETC套装。每激活一套，代理商即可获得额外奖励20元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD', '200', '代理商推介用户成功申办，并现场安装ETC套装。当月设备激活量超过200件后，当月每激活一套，即可获得额外的奖励', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_OFFLINE_MORE_EXTRA_ACTIVE_BONUS', '10', '代理商推介用户成功申办，并现场安装ETC套装。当月设备激活量超过200件后，当月每激活一套，即可获得额外奖励10元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_OFFLINE_FIRST_CONSUME_BONUS', '20', '代理商推介用户成功申办，并现场安装ETC套装。用户首次消费后，代理商即可获得奖励20元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_ONLINE_ACTIVE_BONUS', '40', '代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。每激活一套，代理商即可获得奖励40元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS_THRESHOLD', '200', '代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。当月设备激活量超过200件后，当月每激活一套，即可获得额外的奖励', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_ONLINE_MORE_EXTRA_ACTIVE_BONUS', '10', '代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。当月设备激活量超过200件后，当月每激活一套，即可获得额外奖励10元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('AGENT_ONLINE_FIRST_CONSUME_BONUS', '20', '代理商推介用户成功申办，甲方邮寄给用户，用户自行安装ETC套装。用户首次消费后，代理商即可获得奖励20元', now(), now());

insert into cls_market_etc_global_setting(name, data, `describe`, update_time, create_time)
    value ('STAFF_BONUS', '10', '员工直接推介用户成功申办并激活安装ETC套装。每激活一套，员工即可获得奖励10元', now(), now());

DROP TABLE IF EXISTS `cls_market_etc_goods`;
CREATE TABLE `cls_market_etc_goods`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `brand`       varchar(20) NOT NULL COMMENT '品牌',
    `model`       varchar(20) NOT NULL COMMENT '型号',
    `images`      varchar(1000) DEFAULT NULL COMMENT '图片',
    `details`     varchar(2000) DEFAULT NULL COMMENT '详情',
    `stock`       int         NOT NULL COMMENT '库存',
    `sales`       bigint        DEFAULT 0 COMMENT '销量',
    `status`      int(2)      NOT NULL COMMENT '状态(未上架|已上架)',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_goods_index` (`brand`, `model`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='ETC商品信息表';

DROP TABLE IF EXISTS `cls_market_etc_goods_sku`;
CREATE TABLE `cls_market_etc_goods_sku`
(
    `id`          bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `goods_id`    bigint(20)     NOT NULL COMMENT '商品id',
    `quantity`    int            NOT NULL COMMENT '数量',
    `deposit`     decimal(10, 2) NOT NULL COMMENT '押金(元)',
    `repl_ratio`  decimal(10, 2) NOT NULL COMMENT '补货比例',
    `full_repl`   tinyint(1)     NOT NULL COMMENT '补货时是否全补',
    `status`      int(2)         NOT NULL COMMENT '状态(未上架|已上架)',
    `update_time` datetime       NOT NULL COMMENT '更新时间',
    `create_time` datetime       NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_goods_sku_index` (`goods_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='ETC商品规格信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent`;
CREATE TABLE `cls_market_etc_agent`
(
    `id`                        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `phone`                     varchar(11) NOT NULL COMMENT '手机号',
    `real_name`                 varchar(30) NOT NULL COMMENT '真实姓名',
    `id_no`                     varchar(18)    DEFAULT NULL COMMENT '身份证号',
    `role`                      int(2)      NOT NULL COMMENT '角色',
    `staff_id`                  bigint(20)     DEFAULT NULL COMMENT '上级内部员工id',
    `agent1_id`                 bigint(20)     DEFAULT NULL COMMENT '上级代理(一级)id',
    `agent2_id`                 bigint(20)     DEFAULT NULL COMMENT '上级代理(二级)id',
    `audit_status`              int(2)      NOT NULL COMMENT '审核状态(待审核|已拒绝|已通过|已申请解约|已解约)',
    `refuse_reason`             varchar(100)   DEFAULT NULL COMMENT '审核拒绝原因',
    `status`                    int(2)      NOT NULL COMMENT '状态(正常|禁用)',
    `disable_reason`            varchar(100)   DEFAULT NULL COMMENT '禁用原因',
    `total_card_quantity`       int            DEFAULT 0 COMMENT '累计收货卡数量',
    `total_device_quantity`     int            DEFAULT 0 COMMENT '累计收货设备数量',
    `activated_quantity`        int            DEFAULT 0 COMMENT '已激活卡数量',
    `total_active_bonus`        decimal(10, 2) DEFAULT 0.00 COMMENT '累计激活奖励金额(元)',
    `total_first_consume_bonus` decimal(10, 2) DEFAULT 0.00 COMMENT '累计首次消费奖励金额(元)',
    `total_withdraw_amount`     decimal(10, 2) DEFAULT 0.00 COMMENT '累计提现金额(元)',
    `enable_withdraw`           tinyint(1)     DEFAULT 1 COMMENT '是否可提现',
    `update_time`               datetime    NOT NULL COMMENT '更新时间',
    `create_time`               datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `cls_market_etc_agent_index` (`phone`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_bank_card`;
CREATE TABLE `cls_market_etc_agent_bank_card`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agent_id`    bigint(20)  NOT NULL COMMENT '代理商id',
    `real_name`   varchar(18) NOT NULL COMMENT '银行卡开户姓名',
    `id_card`     varchar(18) NOT NULL COMMENT '身份证号',
    `bank_name`   varchar(32) NOT NULL COMMENT '银行名称',
    `card_no`     varchar(32) NOT NULL COMMENT '银行开户卡号',
    `phone_num`   varchar(16) NOT NULL COMMENT '手机号码',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `cls_market_etc_agent_bank_card_index` (`card_no`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商银行卡信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_goods`;
CREATE TABLE `cls_market_etc_agent_goods`
(
    `id`           bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agent_id`     bigint(20)     NOT NULL COMMENT '代理商id',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_sku_id` bigint(20)     NOT NULL COMMENT '商品规格id',
    `quantity`     int            NOT NULL COMMENT '数量',
    `deposit`      decimal(10, 2) NOT NULL COMMENT '需缴纳押金(元)',
    `repl_ratio`   decimal(10, 2) NOT NULL COMMENT '补货比例',
    `full_repl`    tinyint(1)     NOT NULL COMMENT '补货时是否全补',
    `paid_deposit` decimal(10, 2) DEFAULT 0.00 COMMENT '实缴纳押金(元)',
    `status`       int(2)         NOT NULL COMMENT '状态(待缴纳押金|已缴纳押金|已退还押金)',
    `update_time`  datetime       NOT NULL COMMENT '更新时间',
    `create_time`  datetime       NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_agent_order_index` (`agent_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商代理商品信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_order`;
CREATE TABLE `cls_market_etc_agent_order`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `type`              int(2)       NOT NULL COMMENT '类型(订货|补货)',
    `agent_goods_id`    bigint(20)   NOT NULL COMMENT '代理商品id',
    `agent_id`          bigint(20)   NOT NULL COMMENT '代理商id',
    `goods_id`          bigint(20)   NOT NULL COMMENT '商品id',
    `card_quantity`     int          NOT NULL COMMENT '申请卡数量',
    `device_quantity`   int          NOT NULL COMMENT '申请设备数量',
    `consignee`         varchar(30)  NOT NULL COMMENT '收货人姓名',
    `consignee_phone`   varchar(11)  NOT NULL COMMENT '收货人电话',
    `consignee_address` varchar(100) NOT NULL COMMENT '收货地址',
    `status`            int(2)       NOT NULL COMMENT '状态(待发货|已发货|申请补货)',
    `update_time`       datetime     NOT NULL COMMENT '更新时间',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_agent_order_index` (`agent_goods_id`) USING BTREE,
    KEY `cls_market_etc_agent_order_index1` (`agent_id`) USING BTREE,
    KEY `cls_market_etc_agent_order_index2` (`goods_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商补货订单信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_order_goods`;
CREATE TABLE `cls_market_etc_agent_order_goods`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`      bigint(20) NOT NULL COMMENT '订单id',
    `agent_id`      bigint(20) NOT NULL COMMENT '代理商id',
    `staff_id`      bigint(20) NOT NULL COMMENT '上级内部员工id',
    `agent1_id`     bigint(20)  DEFAULT NULL COMMENT '上级代理(一级)id',
    `agent2_id`     bigint(20)  DEFAULT NULL COMMENT '上级代理(二级)id',
    `goods_id`      bigint(20) NOT NULL COMMENT '商品id',
    `card_sn`       varchar(64) DEFAULT NULL COMMENT 'ETC卡号',
    `card_status`   int(2)      DEFAULT NULL COMMENT '卡状态(待激活|已激活｜已损坏｜已损坏并退还)',
    `device_sn`     varchar(64) DEFAULT NULL COMMENT 'OBU标签号',
    `device_status` int(2)      DEFAULT NULL COMMENT '设备状态(待激活|已激活｜已损坏｜已损坏并退还)',
    `update_time`   datetime   NOT NULL COMMENT '更新时间',
    `create_time`   datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_agent_order_goods_index` (`order_id`) USING BTREE,
    KEY `cls_market_etc_agent_order_goods_index1` (`agent_id`) USING BTREE,
    KEY `cls_market_etc_agent_order_goods_index2` (`goods_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='已发货商品信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_promote_history`;
CREATE TABLE `cls_market_etc_agent_promote_history`
(
    `id`                         bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agent_id`                   bigint(20)  NOT NULL COMMENT '代理商id',
    `staff_id`                   bigint(20)     DEFAULT NULL COMMENT '上级内部员工id',
    `agent1_id`                  bigint(20)     DEFAULT NULL COMMENT '上级代理(一级)id',
    `agent2_id`                  bigint(20)     DEFAULT NULL COMMENT '上级代理(二级)id',
    `user_id`                    bigint(20)  NOT NULL COMMENT '激活的用户id',
    `channel`                    varchar(10)    DEFAULT NULL COMMENT '激活渠道',
    `method`                     char(1)     NOT NULL COMMENT '激活方式:E-用户线上申请,自己激活 S-代理商线下安装激活',
    `car_no`                     varchar(10) NOT NULL COMMENT '激活的车牌号',
    `car_color`                  varchar(10) NOT NULL COMMENT '车牌号颜色',
    `etc_account`                varchar(64)    DEFAULT NULL COMMENT '激活的ECT账户',
    `card_sn`                    varchar(64)    DEFAULT NULL COMMENT '激活的ETC卡号',
    `device_sn`                  varchar(64)    DEFAULT NULL COMMENT '激活的OBU标签号',
    `active_time`                datetime       DEFAULT NULL COMMENT '激活时间',
    `active_bonus`               decimal(10, 2) DEFAULT 0.00 COMMENT '激活奖励金额(元)',
    `active_bonus_status`        tinyint(1)     DEFAULT 1 COMMENT '激活奖励状态(待结算|已结算|结算失败)',
    `first_consume_order_sn`     varchar(64)    DEFAULT NULL COMMENT '首次消费订单号',
    `first_consume_time`         datetime       DEFAULT NULL COMMENT '首次消费时间',
    `first_consume_bonus`        decimal(10, 2) DEFAULT 0.00 COMMENT '首次消费奖励金额(元)',
    `first_consume_bonus_status` tinyint(1)     DEFAULT 1 COMMENT '首次消费奖励状态(待结算|已结算|结算失败)',
    `staff_bonus`                decimal(10, 2) DEFAULT 0.00 COMMENT '员工奖励金额(元)',
    `update_time`                datetime    NOT NULL COMMENT '更新时间',
    `create_time`                datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_market_etc_agent_promote_history_index` (`agent_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商推广记录信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_withdraw_order`;
CREATE TABLE `cls_market_etc_agent_withdraw_order`
(
    `id`                    bigint(20)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agent_id`              bigint(20)     NOT NULL COMMENT '代理商id',
    `sn`                    varchar(64)    NOT NULL COMMENT '订单号',
    `ref`                   varchar(64)    DEFAULT NULL COMMENT '综合服务平台流水号',
    `broker_bank_bill`      varchar(64)    DEFAULT NULL COMMENT '综合服务平台打款交易流水号',
    `withdraw_platform`     varchar(10)    DEFAULT NULL COMMENT '提现平台',
    `real_name`             varchar(32)    NOT NULL COMMENT '姓名',
    `bank_name`             varchar(32)    NOT NULL COMMENT '银行名称',
    `card_no`               varchar(32)    NOT NULL COMMENT '收款账号',
    `id_card`               varchar(18)    NOT NULL COMMENT '身份证号',
    `phone_no`              varchar(11)    DEFAULT NULL COMMENT '手机号',
    `broker_amount`         decimal(10, 2) NOT NULL COMMENT '综合服务主体打款金额(元)',
    `broker_fee`            decimal(10, 2) DEFAULT 0.00 COMMENT '综合服务主体服务费(元)',
    `status`                varchar(2)     NOT NULL COMMENT '订单状态码',
    `status_detail`         varchar(6)     DEFAULT NULL COMMENT '订单详细状态码',
    `status_detail_message` varchar(30)    DEFAULT NULL COMMENT '订单详细状态码描述',
    `finished_time`         datetime       DEFAULT NULL COMMENT '订单完成时间',
    `remark`                varchar(64)    DEFAULT NULL COMMENT '备注',
    `update_time`           datetime       NOT NULL COMMENT '更新时间',
    `create_time`           datetime       NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `cls_market_etc_agent_withdraw_order_index1` (`sn`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商提现订单信息表';

DROP TABLE IF EXISTS `cls_market_etc_es_trade`;
CREATE TABLE `cls_market_etc_es_trade`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `trade_id`    bigint(20)  NOT NULL COMMENT '交易id',
    `agent_id`    bigint(20)  NOT NULL COMMENT '代理商id',
    `phone`       varchar(11) NOT NULL COMMENT '手机号',
    `real_name`   varchar(30) NOT NULL COMMENT '真实姓名',
    `role`        int(2)      NOT NULL COMMENT '角色',
    `type`        int(2)      NOT NULL COMMENT '交易类型',
    `update_time` datetime    NOT NULL COMMENT '更新时间',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='ES交易信息表';