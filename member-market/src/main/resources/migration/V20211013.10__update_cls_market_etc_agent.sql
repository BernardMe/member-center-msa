alter table cls_market_etc_agent
    drop column total_card_quantity;
alter table cls_market_etc_agent
    drop column total_device_quantity;
alter table cls_market_etc_agent
    drop column activated_quantity;

create index cls_market_etc_agent_index1
    on cls_market_etc_agent (role);

create index cls_market_etc_agent_index2
    on cls_market_etc_agent (role, audit_status);

create index cls_market_etc_agent_index3
    on cls_market_etc_agent (role, audit_status, create_time);

create index cls_market_etc_agent_index4
    on cls_market_etc_agent (real_name);

create index cls_market_etc_agent_bank_card_index1
    on cls_market_etc_agent_bank_card (agent_id);

create index cls_market_etc_agent_day_stats_index
    on cls_market_etc_agent_day_stats (agent_id, stats_date);

create index cls_market_etc_agent_day_stats_index1
    on cls_market_etc_agent_day_stats (stats_date);

create index cls_market_etc_agent_goods_index
    on cls_market_etc_agent_goods (agent_id, status);

create index cls_market_etc_agent_goods_index1
    on cls_market_etc_agent_goods (agent_id, goods_id, status);

create index cls_market_etc_agent_order_index3
    on cls_market_etc_agent_order (type, agent_goods_id);

create index cls_market_etc_agent_order_index4
    on cls_market_etc_agent_order (type, agent_goods_id, status);

create index cls_market_etc_agent_order_goods_index3
    on cls_market_etc_agent_order_goods (card_sn);

create index cls_market_etc_agent_order_goods_index4
    on cls_market_etc_agent_order_goods (card_sn, device_sn);

create index cls_market_etc_agent_order_goods_index5
    on cls_market_etc_agent_order_goods (agent_id, goods_id, card_sn, card_status);

create index cls_market_etc_agent_order_goods_index6
    on cls_market_etc_agent_order_goods (device_sn);

create index cls_market_etc_agent_order_goods_index7
    on cls_market_etc_agent_order_goods (device_status);

create index cls_market_etc_agent_promote_history_index1
    on cls_market_etc_agent_promote_history (card_sn);

create index cls_market_etc_agent_promote_history_index2
    on cls_market_etc_agent_promote_history (card_sn, active_bonus_status);

create index cls_market_etc_agent_promote_history_index3
    on cls_market_etc_agent_promote_history (active_bonus_status, first_consume_order_sn);

create index cls_market_etc_agent_promote_history_index4
    on cls_market_etc_agent_promote_history (active_time);

create index cls_market_etc_agent_promote_history_index5
    on cls_market_etc_agent_promote_history (active_bonus_status, first_consume_order_sn, first_consume_bonus_status);

create index cls_market_etc_agent_promote_history_index6
    on cls_market_etc_agent_promote_history (agent_id, etc_account, active_time);

create index cls_market_etc_agent_promote_history_index7
    on cls_market_etc_agent_promote_history (car_no, car_color);

create index cls_market_etc_agent_promote_history_index8
    on cls_market_etc_agent_promote_history (agent_id, active_time);

create index cls_market_etc_agent_promote_history_index9
    on cls_market_etc_agent_promote_history (agent1_id, active_time);

create index cls_market_etc_agent_withdraw_order_index
    on cls_market_etc_agent_withdraw_order (agent_id, status);

create index cls_market_etc_agent_withdraw_order_index2
    on cls_market_etc_agent_withdraw_order (agent_id, status, create_time);

create index cls_market_etc_day_stats_index
    on cls_market_etc_day_stats (stats_date);

create index cls_market_etc_goods_sku_index1
    on cls_market_etc_goods_sku (goods_id, status);