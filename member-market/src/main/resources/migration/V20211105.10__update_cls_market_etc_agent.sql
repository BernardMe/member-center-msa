
alter table cls_market_etc_agent
    add threshold_bonus_achieve tinyint(1) default 0  null comment '代理人是否已获取阀值全量佣金 0:未获取, 1:已获取';