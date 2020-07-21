DROP TABLE IF EXISTS recipient_group;
CREATE TABLE IF NOT EXISTS recipient_group
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id  BIGINT      NOT NULL COMMENT '监控ID',
    alert_id  BIGINT      NOT NULL COMMENT '报警ID',
    alert_group_id   BIGINT NOT NULL COMMENT '报警组Id',
    create_at DATETIME    NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警接收组';

ALTER TABLE recipient
    ADD INDEX idx_alertid (alert_id);
ALTER TABLE recipient
    ADD INDEX idx_alarmid (alarm_id);