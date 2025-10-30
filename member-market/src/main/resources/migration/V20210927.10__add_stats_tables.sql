DROP TABLE IF EXISTS `cls_market_etc_day_stats`;
CREATE TABLE `cls_market_etc_day_stats`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `increase_agent_count` int DEFAULT 0 COMMENT '新增代理人数',
    `total_agent1_count`   int DEFAULT 0 COMMENT '累计1级代理人数',
    `total_agent2_count`   int DEFAULT 0 COMMENT '累计2级代理人数',
    `total_agent3_count`   int DEFAULT 0 COMMENT '累计3级代理人数',
    `total_agent_count`    int DEFAULT 0 COMMENT '累计代理人数',
    `increase_card_count`  int DEFAULT 0 COMMENT '新增发卡量',
    `total_card_count`     int DEFAULT 0 COMMENT '累计发卡量',
    `stats_date`           date       NOT NULL COMMENT '统计日期',
    `update_time`          datetime   NOT NULL COMMENT '更新时间',
    `create_time`          datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_meds_index` (`stats_date`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='营销日统计信息表';

DROP TABLE IF EXISTS `cls_market_etc_agent_day_stats`;
CREATE TABLE `cls_market_etc_agent_day_stats`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `agent_id`       bigint(20) NOT NULL COMMENT '代理商id',
    `card_count`     int            DEFAULT 0 COMMENT '发卡量',
    `passage_times`  int            DEFAULT 0 COMMENT '通行笔数',
    `passage_amount` decimal(10, 2) DEFAULT 0.00 COMMENT '交易金额(元)',
    `stats_date`     date       NOT NULL COMMENT '统计日期',
    `update_time`    datetime   NOT NULL COMMENT '更新时间',
    `create_time`    datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `cls_medads_index` (`stats_date`) USING BTREE,
    KEY `cls_medads_index2` (`card_count`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='代理商日统计信息表';