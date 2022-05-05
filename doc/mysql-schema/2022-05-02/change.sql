ALTER TABLE `alert`
    ADD COLUMN silence_expression VARCHAR(512) NULL COMMENT '静默判断表达式';

/*------------------------------------------- create alert_event -------------------------------------------*/
DROP TABLE IF EXISTS alert_event;
CREATE TABLE IF NOT EXISTS alert_event
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id   BIGINT      NOT NULL COMMENT '监控ID',
    alert_type VARCHAR(16) NOT NULL COMMENT '消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)',
    in_silence TINYINT     NOT NULL COMMENT '是否在静默期',
    event_md5  JSON        NULL COMMENT '摘要md5',
    create_at  DATETIME    NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
    key idx_alarm_id (alarm_id),
    key idx_create_at (create_at)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警事件';