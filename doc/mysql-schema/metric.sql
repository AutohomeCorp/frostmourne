DROP TABLE IF EXISTS metric;
CREATE TABLE IF NOT EXISTS metric
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    aggregation_type  VARCHAR(100) COMMENT '数据源为http类型时无效。指标聚合类型. (count, spike, sum, avg)',
    aggregation_field VARCHAR(100) COMMENT '聚合字段',
    metric_type       VARCHAR(100) NOT NULL COMMENT '指标类型(numeric：数值; ring_than: 环比; same_time: 同比; object: 对象)',
    alarm_id          BIGINT       NOT NULL COMMENT '监控ID',
    rule_id           BIGINT       NOT NULL COMMENT '规则ID',
    data_source_id    BIGINT       NOT NULL DEFAULT 0 COMMENT '数据源id',
    data_name_id      BIGINT       NOT NULL DEFAULT 0 COMMENT '数据名id',
    data_name         VARCHAR(200) NOT NULL COMMENT '监控数据名。(http：表示静态http数据; 其他data_name关联data_name表)',
    query_string      VARCHAR(1000) COMMENT '查询语句',
    properties        VARCHAR(2000) COMMENT '附加属性JSON格式',
    post_data         VARCHAR(2000) COMMENT 'http数据监控，post数据内容',
    creator           VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控指标配置';

ALTER TABLE metric
    ADD INDEX idx_ruleid (rule_id);
ALTER TABLE metric
    ADD INDEX idx_alarmid (alarm_id);