ALTER TABLE cls_market_etc_agent ADD open_id varchar(200) DEFAULT null COMMENT '微信的openId';
ALTER TABLE cls_market_etc_agent ADD COLUMN remarks VARCHAR(200) DEFAULT null COMMENT '备注' ;


-- 修改表结构
-- 代理商品信息表新增is_enabled字段
alter table cls_market_etc.cls_market_etc_agent_goods
    add is_enabled tinyint(1) not null comment '是否有效(0:无效, 1:有效)' after status;
-- 代理商品信息表新增audit_goods_sku_id字段
alter table cls_market_etc.cls_market_etc_agent_goods
    add audit_goods_sku_id bigint null comment '待审核商品规格id' after goods_sku_id;


-- 修改表结构
-- 代理商补货订单信息表的type字段更新枚举值
alter table cls_market_etc.cls_market_etc_agent_order modify type int(2) not null comment '类型(订货|补货|提现|提标补货|退货)';
-- 代理商补货订单信息表的status字段更新枚举值
alter table cls_market_etc.cls_market_etc_agent_order modify status int(2) not null comment '状态(待发货|已发货|申请补货|已拒绝|已取消|申请退货|已退货)';
-- 代理商补货订单信息表添加operator字段
alter table cls_market_etc.cls_market_etc_agent_order
    add operator varchar(30) null comment '操作人员' after status;
-- 代理商补货订单信息表添加is_reduce_quantity字段
alter table cls_market_etc.cls_market_etc_agent_order
    add is_reduce_quantity int(2) default 0 not null comment '是否降低规格(0:否, 1:是)' after operator;
-- 代理商补货订单信息表添加reduce_sku_id字段
alter table cls_market_etc.cls_market_etc_agent_order
    add reduce_sku_id bigint null comment '申请提升的规格id或降低后规格id' after is_reduce_quantity;
-- 代理商补货订单信息表添加reduce_sku_id字段
ALTER TABLE cls_market_etc_agent_order add `goods_sku_id` bigint(20) DEFAULT null COMMENT '商品规格id';


-- 修改表结构
-- 已发货商品信息表card_status字段更新枚举值
alter table cls_market_etc_agent_order_goods modify card_status int(2) null comment '卡状态(待激活|已激活｜已损坏未退回｜已损坏已退回|未激活退回)';
-- 已发货商品信息表device_status字段更新枚举值
alter table cls_market_etc_agent_order_goods modify device_status int(2) null comment '设备状态(待激活|已激活｜已损坏未退回｜已损坏已退回|未激活退回)';


-- 修改表结构
-- ETC商品信息表更新注解
alter table cls_market_etc.cls_market_etc_goods modify stock int not null comment '卡库存';
alter table cls_market_etc.cls_market_etc_goods modify sales bigint default 0 null comment '卡销量';
-- ETC商品信息表新增obu_stock字段
alter table cls_market_etc.cls_market_etc_goods add obu_stock int not null comment 'OBU库存' after sales;
-- ETC商品信息表新增obu_sales字段
alter table cls_market_etc.cls_market_etc_goods add obu_sales bigint default 0 null comment 'OBU销量' after obu_stock;


-- 修改表结构
-- 代理商推广记录信息表新增extra_status字段
alter table cls_market_etc.cls_market_etc_agent_promote_history
    add extra_status varchar(2) default '0' not null comment '额外分润标记，0未分，1已分' after staff_bonus;
