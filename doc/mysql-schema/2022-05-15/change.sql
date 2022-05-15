/*------------------------------------------- create alert_upgrade -------------------------------------------*/
DROP TABLE IF EXISTS alert_upgrade;
CREATE TABLE IF NOT EXISTS alert_upgrade
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id          BIGINT       NOT NULL COMMENT '监控ID',
    status            VARCHAR(32)  NOT NULL COMMENT '状态开关 OPEN:打开  CLOSE:关闭',
    times_to_upgrade  INT          NULL COMMENT '连续报警n次后升级',
    ways              VARCHAR(512) NULL COMMENT '报警方式(sms,dingding,email,http_post,wechat)',
    ding_robot_hook   VARCHAR(512) NULL COMMENT '钉钉机器人hook地址',
    http_post_url     VARCHAR(512) NULL COMMENT 'http post报警方式地址',
    wechat_robot_hook VARCHAR(512) NULL COMMENT '企业微信机器人hook地址',
    feishu_robot_hook VARCHAR(512) NULL COMMENT '飞书机器人hook地址',
    creator           VARCHAR(128) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间'
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警升级配置';

ALTER TABLE alert_upgrade
    ADD INDEX idx_alarm_id (alarm_id);


ALTER TABLE `alert_event`
    ADD COLUMN upgrade TINYINT NOT NULL DEFAULT 0 COMMENT '报警升级';
ALTER TABLE `recipient`
    ADD COLUMN type VARCHAR(32) NOT NULL DEFAULT 'ALERT' COMMENT '归属于 ALERT:报警, ALERT_UPGRADE:报警升级';
