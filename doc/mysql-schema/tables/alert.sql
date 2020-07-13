DROP TABLE IF EXISTS alert;
CREATE TABLE IF NOT EXISTS alert
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id        BIGINT       NOT NULL COMMENT '监控ID',
    ways            VARCHAR(500) NOT NULL COMMENT '报警方式(sms,dingding,email,http_post,wechat)',
    silence         BIGINT       NOT NULL COMMENT '静默时间，单位：分钟',
    creator         VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at       DATETIME     NOT NULL COMMENT '创建时间',
    allow_sms_from  INTEGER      NULL COMMENT '短信允许发送开始时间，[0,23]',
    allow_sms_to    INTEGER      NULL COMMENT '短信允许发送结束时间，[0,23]',
    ding_robot_hook VARCHAR(500) NULL COMMENT '钉钉机器人hook地址',
    http_post_url   VARCHAR(500) COMMENT 'http post报警方式地址',
    wechat_robot_hook VARCHAR(500) NULL COMMENT '企业微信机器人hook地址'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警配置';

ALTER TABLE alert
    ADD INDEX idx_alarmid (alarm_id);