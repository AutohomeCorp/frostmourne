DROP TABLE IF EXISTS alert_log;
CREATE TABLE IF NOT EXISTS alert_log
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id    BIGINT        NOT NULL COMMENT '监控ID',
    execute_id  BIGINT        NOT NULL COMMENT '监控执行ID',
    way         VARCHAR(100)  NOT NULL COMMENT '报警方式',
    recipient   VARCHAR(100)  NOT NULL COMMENT '报警接收人',
    content     VARCHAR(2000) NOT NULL COMMENT '报警内容',
    in_silence  VARCHAR(50)   NOT NULL COMMENT '是否在静默期(YES,NO)',
    send_status VARCHAR(50)   NOT NULL COMMENT '发送状态(NONE,SUCCESS,FAIL,FORBID)',
    alert_type VARCHAR(50) NOT NULL COMMENT '消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)',
    create_at   DATETIME      NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警日志';

ALTER TABLE alert_log ADD INDEX idx_createat_recipient(create_at, recipient);

ALTER TABLE alert_log
    ADD INDEX idx_alarmid (alarm_id);