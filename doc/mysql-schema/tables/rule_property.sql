DROP TABLE IF EXISTS rule_property;
CREATE TABLE IF NOT EXISTS rule_property
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id   BIGINT        NOT NULL COMMENT '监控Id',
    rule_id    BIGINT        NOT NULL COMMENT '报警规则ID',
    prop_key   VARCHAR(100)  NOT NULL COMMENT '属性key',
    prop_value VARCHAR(1000) NOT NULL COMMENT '属性value',
    creator    VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at  DATETIME      NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警规则属性';

ALTER TABLE rule_property
    ADD INDEX idx_alarmid (alarm_id);
ALTER TABLE rule_property
    ADD INDEX idx_ruleid (rule_id);