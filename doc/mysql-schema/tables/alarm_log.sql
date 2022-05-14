DROP TABLE IF EXISTS alarm_log;
CREATE TABLE IF NOT EXISTS alarm_log
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id       BIGINT      NOT NULL COMMENT '监控ID',
    exe_start      DATETIME    NOT NULL COMMENT '监控任务执行开始时间',
    exe_end        DATETIME    NOT NULL COMMENT '监控任务执行结束时间',
    cost           INT         NOT NULL COMMENT '监控任务执行耗时，单位：毫秒',
    execute_result VARCHAR(50) NOT NULL COMMENT '执行结果(SUCCESS,ERROR)',
    alert          TINYINT     NOT NULL COMMENT '是否报警',
    verify_result  VARCHAR(50) NOT NULL DEFAULT 'NONE' COMMENT 'NONE,TRUE,FALSE',
    message        TEXT COMMENT '日志消息',
    create_at      DATETIME    NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控任务执行日志';

ALTER TABLE alarm_log ADD INDEX idx_createat_alarmid(create_at, alarm_id);
