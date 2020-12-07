DROP TABLE IF EXISTS alarm;
CREATE TABLE IF NOT EXISTS alarm
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_name     VARCHAR(100)  NOT NULL COMMENT '监控名称',
    alarm_type     VARCHAR(200)  NOT NULL COMMENT '监控数据类型。(http: http监控；其他值: 关联data_name表)',
    description    VARCHAR(1000) NOT NULL COMMENT '监控描述',
    owner_key      VARCHAR(200) COMMENT '所属对象关键字',
    status         VARCHAR(50)   NOT NULL COMMENT '开关状态（OPEN,CLOSE）',
    execute_result VARCHAR(50)   NOT NULL DEFAULT 'NONE' COMMENT '最近一次执行结果',
    execute_at     DATETIME COMMENT '最近一次执行时间',
    job_id         BIGINT        NOT NULL DEFAULT 0 COMMENT '调度任务id',
    cron           VARCHAR(500)  NOT NULL COMMENT 'cron表达式',
    creator        VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at      DATETIME      NOT NULL COMMENT '创建时间',
    modifier       VARCHAR(200)  NOT NULL COMMENT '修改人',
    modify_at      DATETIME      NOT NULL COMMENT '修改时间',
    team_name      VARCHAR(200)  NOT NULL COMMENT '监控所属团队',
    risk_level     VARCHAR(500) COMMENT '风险等级。info: 提示；important: 重要；emergency: 紧急； crash: 我崩了',
    service_id     BIGINT(20)             DEFAULT '0' COMMENT '服务ID',
    recover_notice_status varchar(50) NOT NULL DEFAULT 'OPEN' COMMENT '恢复通知开关（OPEN,CLOSE）'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控报警';

ALTER TABLE alarm
    ADD INDEX idx_ownerkey (owner_key(20));
