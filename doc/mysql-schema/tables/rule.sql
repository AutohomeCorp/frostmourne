DROP TABLE IF EXISTS rule;
CREATE TABLE IF NOT EXISTS rule
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    rule_type           VARCHAR(100)  NOT NULL COMMENT '规则类型(numeric,percentage,expression)',
    alarm_id            BIGINT        NOT NULL COMMENT '报警ID',
    alert_template      VARCHAR(5000) NOT NULL COMMENT '报警内容模板',
    alert_template_type VARCHAR(32)   NOT NULL COMMENT '报警消息类型(TEXT,MARKDOWN)',
    creator             VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at           DATETIME      NOT NULL COMMENT '创建时间'
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警规则配置';

ALTER TABLE rule
    ADD INDEX idx_alarmid (alarm_id);