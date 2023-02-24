DROP TABLE IF EXISTS recipient;
CREATE TABLE IF NOT EXISTS recipient
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id  BIGINT      NOT NULL COMMENT '监控ID',
    alert_id  BIGINT      NOT NULL COMMENT '报警ID',
    `account`   VARCHAR(50) NOT NULL COMMENT '接收人账号不带邮箱后缀',
    `type`      VARCHAR(32) NOT NULL DEFAULT 'ALERT' COMMENT '归属于 ALERT:报警, ALERT_UPGRADE:报警升级',
    create_at DATETIME    NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警接收人';

ALTER TABLE recipient
    ADD INDEX idx_alertid (alert_id);
ALTER TABLE recipient
    ADD INDEX idx_alarmid (alarm_id);